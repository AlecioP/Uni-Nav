package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LineaRegistroNavette;
import model.RegistroAttivitaNavette;
import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.AutistaDaoJDBC;
import persistence.daoManage.jdbcDao.NavettaDaoJDBC;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.daoManage.jdbcDao.TrattoLineaDaoJDBC;
import persistence.persistentModel.Autista;
import persistence.persistentModel.Linea;
import persistence.persistentModel.Navetta;
import persistence.persistentModel.Prenotazione;
import persistence.persistentModel.Studente;

public class ObliteraManualmente extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("entraa");
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		AutistaDaoJDBC adao = (AutistaDaoJDBC) df.getAutistaDAO();
		NavettaDaoJDBC ndao = (NavettaDaoJDBC) df.getNavettaDAO();
		TrattoLineaDaoJDBC ldao = (TrattoLineaDaoJDBC) df.getTrattoLineaDAO();
		StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
		String tmp = (String) req.getSession().getAttribute("username");
		int autistaID = Integer.parseInt(tmp);
		RegistroAttivitaNavette registro = RegistroAttivitaNavette.getInstance();
		LineaRegistroNavette linea = registro.getLineaRegistro(autistaID);
		if (linea == null) {
			registro.addLinea(autistaID);
			linea = registro.getLineaRegistro(autistaID);
			Autista a = (Autista) adao.findByPrimaryKey(autistaID + "");
			linea.setAutista(a);
			linea.setGiriCompletati(0);
			linea.setNavetta((Navetta) ndao.findByPrimaryKey("1"));
			linea.setLinea(new Linea("a"));
			linea.setPosizione(ldao.findByPrimaryKeyComposed("Castiglione_Cs._Stazione_FS", "Borromeo"));
		}
		if (linea.getNavetta() == null)
			linea.setNavetta((Navetta) ndao.findByPrimaryKey("1"));

		String matricola = req.getParameter("current-matricola");
		Studente s = sdao.findByPrimaryKey(matricola);
		if (s != null) {
			ArrayList<Prenotazione> prenotazioniS = s.getPrenotazioni();
			for (Prenotazione pren : prenotazioniS) {
				Date d3 = null, d4 = null;
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = pren.getDateTime().getTime();
				Date d2 = registro.getData();
				try {
					d3 = formatter.parse(formatter.format(d1));
					d4 = formatter.parse(formatter.format(d2));
					System.out.println(d3 + " " + d4);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (pren.getGiro() == linea.getGiriCompletati() && pren.getAutista().getID() == autistaID
						&& pren.getNavetta().getID() == linea.getNavetta().getID()
						&& pren.getTratto().getPartenza().getNome().equals(linea.getPosizione().getPartenza().getNome())
						&& d3.equals(d4)) {
					req.setAttribute("prenotazione", pren);
					req.setAttribute("prenotazioneID", Integer.valueOf(pren.getID()));
					RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/mostraPrenotazioni.jsp");
					rd.forward(req, resp);
					return;
				}
			}
		}
		req.getSession().setAttribute("registration-error", "Lo studente non è prenotato");
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/obliteraBiglietto.jsp");
		rd.forward(req, resp);
	}
}

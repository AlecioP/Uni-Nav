package controller;

import java.io.IOException;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		AutistaDaoJDBC adao = (AutistaDaoJDBC) df.getAutistaDAO();
		NavettaDaoJDBC ndao = (NavettaDaoJDBC) df.getNavettaDAO();
		TrattoLineaDaoJDBC ldao = (TrattoLineaDaoJDBC) df.getTrattoLineaDAO();
		StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
		String tmp = (String) req.getSession().getAttribute("username");
		int autistaID = Integer.parseInt(tmp);
		RegistroAttivitaNavette registro = (RegistroAttivitaNavette) req.getServletContext().getAttribute("registro");
		if (registro == null) {
			registro = new RegistroAttivitaNavette(new Date());
			req.getServletContext().setAttribute("registro", registro);
		}
		LineaRegistroNavette linea = registro.getLineaRegistro(autistaID);
		if (linea == null) {
			registro.addLinea(autistaID);
			linea = registro.getLineaRegistro(autistaID);
			Autista a = (Autista) adao.findByPrimaryKey(autistaID + "");
			linea.setAutista(a);
			linea.setGiriCompletati(0);
			linea.setNavetta((Navetta) ndao.findByPrimaryKey("1"));
			linea.setLinea(new Linea("a"));
			linea.setPosizione(ldao.findByPrimaryKeyComposed("b", "a"));
		}
		String matricola = req.getParameter("current-matricola");
		Studente s = sdao.findByPrimaryKey(matricola);
		if (s != null) {
			ArrayList<Prenotazione> prenotazioniS = s.getPrenotazioni();
			for (Prenotazione pren : prenotazioniS) {
				if (pren.getGiro() == linea.getGiriCompletati() + 1 && pren.getAutista().getID() == autistaID
						&& pren.getNavetta().getID() == linea.getNavetta().getID()
						&& pren.getDateTime().getTime().equals(registro.getData())
						&& pren.getTratto().getPartenza().getNome().equals(linea.getPosizione().getPartenza().getNome())
						&& pren.getTratto().getArrivo().getNome().equals(linea.getPosizione().getArrivo().getNome())) {
					req.setAttribute("prenotazioneID", Integer.valueOf(pren.getID()));
					RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/mostraPrenotazione.jsp");
					rd.forward(req, resp);
					return;
				}
			}
		}
		req.getSession().setAttribute("error-message", "Lo studente non � prenotato");
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/obliteraManualmente.jsp");
		rd.forward(req, resp);
	}
}

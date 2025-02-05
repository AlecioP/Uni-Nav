package controller.groupB;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.AmministratoreDaoJDBC;
import persistence.daoManage.jdbcDao.DomandaRiabilitazioneDaoJDBC;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Amministratore;
import persistence.persistentModel.DomandaRiabilitazione;
import persistence.persistentModel.Studente;
import persistence.utility.IdProvider;

public class InviaDomanda extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricola = (String) req.getSession().getAttribute("username");
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
		AmministratoreDaoJDBC adao = (AmministratoreDaoJDBC) df.getAmministratoreDAO();
		DomandaRiabilitazioneDaoJDBC ddao = (DomandaRiabilitazioneDaoJDBC) df.getDomandaRiabilitazioneDAO();
		ArrayList<DomandaRiabilitazione> domande = (ArrayList<DomandaRiabilitazione>) ddao.findAll();
		Studente s = sdao.findByPrimaryKey(matricola);
		Amministratore a = adao.findByPrimaryKey("1");
		if (domande.size() > 0) {
			for (DomandaRiabilitazione domandaRiabilitazione : domande) {
				if (domandaRiabilitazione.getStudente().getMatricola() == s.getMatricola()) {
					RequestDispatcher rd = req
							.getRequestDispatcher("WEB-INF/dynamicPages/domandaGiaEffettuata.jsp");
					rd.forward(req, resp);
					return;
				}
			}
		}
		IdProvider provider = IdProvider.getInstance();
		int id = provider.getNextId("Domanda_Riabilitazione");
		LocalDateTime l = LocalDateTime.now();
		DomandaRiabilitazione d = new DomandaRiabilitazione(id, l, s, a);
		ddao.save(d);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/homeStudente.jsp");
		rd.forward(req, resp);
	}
}

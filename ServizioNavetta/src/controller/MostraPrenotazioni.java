package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Prenotazione;
import persistence.persistentModel.Studente;

public class MostraPrenotazioni extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6342700169231418782L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricola = req.getParameter("matricola");
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
		@SuppressWarnings("unused")
		Studente s = sdao.findByPrimaryKey(matricola);
		PrenotazioneDaoJDBC p = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
		@SuppressWarnings("unchecked")
		ArrayList<Prenotazione> pr = (ArrayList<Prenotazione>) p.findAll();
		ArrayList<Prenotazione> prFinal = new ArrayList<Prenotazione>();
		@SuppressWarnings("unused")
		Prenotazione pp = (Prenotazione) p.findByPrimaryKey("1");
		for (Prenotazione prenotazione : pr) {
			if (prenotazione.getStudente().getMatricola() == Integer.parseInt(matricola))
				prFinal.add(prenotazione);
		}
		System.out.println(prFinal.size() + " ssss");
		req.getSession().setAttribute("prenotazioni", prFinal);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/mostraPrenotazioni.jsp");
		rd.forward(req, resp);
	}
}

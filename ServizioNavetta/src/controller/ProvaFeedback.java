package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.persistentModel.Prenotazione;

public class ProvaFeedback extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440958004855659979L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		PrenotazioneDaoJDBC pdao = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
		Prenotazione p = (Prenotazione) pdao.findByPrimaryKey("1");
		req.getSession().setAttribute("prenotazione", p);
		RequestDispatcher rq = req.getRequestDispatcher("WEB-INF/dynamicPages/feedback.jsp");
		rq.forward(req, resp);
	}
}

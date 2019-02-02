package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.FeedbackDaoJDBC;
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.persistentModel.FeedBack;
import persistence.persistentModel.Prenotazione;

public class InviaFeedback extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		PrenotazioneDaoJDBC pdao = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
		FeedbackDaoJDBC fdao = (FeedbackDaoJDBC) df.getFeedBackDAO();
		Prenotazione p = (Prenotazione) pdao.findByPrimaryKey(req.getParameter("prenID"));
//		System.out.println(req.getParameter("prenID") + " ss");
		String text = req.getParameter("prenID");
		String text2 = req.getParameter("commento");
		System.out.println(text+" "+text2);
		FeedBack feed = new FeedBack(p, text);
		fdao.save(feed);
		System.out.println(text);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/feedback.jsp");
		rd.forward(req, resp);
	}
}

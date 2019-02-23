package controller.groupB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.FeedbackDaoJDBC;
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.FeedBack;
import persistence.persistentModel.Prenotazione;
import persistence.persistentModel.Studente;

public class ProvaFeedback extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440958004855659979L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricola = (String) req.getSession().getAttribute("username");
		
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		PrenotazioneDaoJDBC pdao = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
		StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
		Studente s = sdao.findByPrimaryKey(matricola);
		FeedbackDaoJDBC feedDao = (FeedbackDaoJDBC) df.getFeedBackDAO();
		
		ArrayList<FeedBack> feeds = new ArrayList<FeedBack>();
		ArrayList<Prenotazione> p = pdao.findByReference(s);
		
		for(Prenotazione p1: p) {
			FeedBack fidd=(FeedBack) feedDao.findByPrimaryKey(p1.getID()+"");
			feeds.add(fidd);
		}
		
		
		System.out.println("CAZZO");
		
		req.getSession().setAttribute("prenotazione", p);
		req.getSession().setAttribute("feeds", feeds);
		RequestDispatcher rq = req.getRequestDispatcher("WEB-INF/dynamicPages/feedback.jsp");
		rq.forward(req, resp);
	}
}

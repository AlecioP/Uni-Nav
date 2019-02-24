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
import persistence.daoManage.jdbcDao.DomandaRiabilitazioneDaoJDBC;
import persistence.persistentModel.DomandaRiabilitazione;
import persistence.persistentModel.Studente;

/**
 * Servlet implementation class UserReclaim
 */

public class UserReclaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReclaim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String) request.getSession().getAttribute("tipo-login");
		if(!type.equals("admin")) {
			response.sendRedirect("home");
			return;
		}
		/*ONLY ADMIN CAN ACCESS THIS TYPE OF DATA*/
		
		
		String username = (String) request.getSession().getAttribute("username");
		
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		
		DomandaRiabilitazioneDaoJDBC domandaDao = (DomandaRiabilitazioneDaoJDBC) df.getDomandaRiabilitazioneDAO();
		
		ArrayList<DomandaRiabilitazione> domande = domandaDao.findByAdmin(username);
		
		request.getSession().setAttribute("domande", domande);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/solveReclaim.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String) request.getSession().getAttribute("tipo-login");
		if(!type.equals("admin")) {
			response.sendRedirect("home");
			return;
		}
		/*ONLY ADMIN CAN ACCESS THIS TYPE OF DATA*/
		
		String domandaId = request.getParameter("reclaim-id");
		
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		
		DomandaRiabilitazioneDaoJDBC domandaDao = (DomandaRiabilitazioneDaoJDBC) df.getDomandaRiabilitazioneDAO();
		
		DomandaRiabilitazione domanda = (DomandaRiabilitazione) domandaDao.findByPrimaryKey(domandaId);
		
		Studente stud = domanda.getStudente();
		
		stud.setFlag(0);
		
		//UPDATES FLAG FOR STUDENT 
		df.getStudenteDAO().update(stud);
		
		domandaDao.delete(domanda);
		
		response.sendRedirect("home");
		
		return;
		
	}

}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Navetta;

/**
 * Servlet implementation class InitBus
 */
@WebServlet("/initbus")
public class InitBus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitBus() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String navetta = request.getParameter("nav-id");
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		Crud navettaDAO = df.getNavettaDAO();
		
		Navetta nav = (Navetta) navettaDAO.findByPrimaryKey(navetta);
		
		if(nav==null)
			return;
		
		request.getSession().setAttribute("driven", nav);
		response.sendRedirect("home");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

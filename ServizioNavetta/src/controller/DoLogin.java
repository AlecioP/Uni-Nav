package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.SecurityDAO;
import persistence.persistentModel.Password;

/**
 * Servlet implementation class doLogin
 */
@WebServlet(description = "dopo Login home")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		String prova = (String) request.getAttribute("id");
		String username = (String) request.getAttribute("id");
		String pass = (String) request.getAttribute("pass");
		//Debug
		//System.out.println(username +" "+ pass);
		DAOFactory daoFactory = DatabaseManager.getInstance().getDaoFactory();
		SecurityDAO credenziali = daoFactory.getPersonaSecureDAO();
		credenziali.authorizeDao("1", "p");
		Password checkPass = (Password) credenziali.retriveSensitiveData(username);
		if(checkPass==null) {
			request.setAttribute("login-error", "L'utente non esiste.");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/home.jsp");
			rd.forward(request, response);
		}
		else if(checkPass.password.equals(pass)==false) {
			request.setAttribute("login-error", "Password errata.");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/home.jsp");
			rd.forward(request, response);
		}
		String type =  request.getParameter("login-type");
		switch(type) {
		case "driver" : {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/driver.jsp");
			rd.forward(request, response);
			break;
		}
		case "student" : {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/homeStudente.html");
			rd.forward(request, response);
		}
		default :{

		}
		}
	}

}

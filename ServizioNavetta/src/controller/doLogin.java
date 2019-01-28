package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class doLogin
 */
@WebServlet(description = "dopo Login home")
public class doLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doLogin() {
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
		System.out.println(username +" "+ pass);
		String type =  request.getParameter("login-type");
		switch(type) {
		case "driver" : {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/driver.jsp");
			rd.forward(request, response);
			break;
		}
		default :{
			
		}
		}
	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Autista;
import persistence.persistentModel.Studente;

public class HomeSenzaErrori extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("registration-error", null);
		String type = (String) req.getSession().getAttribute("tipo-login");
		switch (type) {
		case "driver": {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/driver.jsp");
			rd.forward(req, resp);
			return;
		}
		case "student": {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/homeStudente.jsp");
			rd.forward(req, resp);
			return;
		}
		default: {

		}
		}
	}
}

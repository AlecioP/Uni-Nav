package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.PostgresDAOFactory;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Password;
import persistence.persistentModel.Studente;

public class IscriviStudente extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricola = req.getParameter("matricola");
		// resp.getWriter().println(matricola);
		int matricolaReg = 0;
		if (matricola != null)
			matricolaReg = Integer.parseInt(matricola);
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Password passReg = new Password();
		passReg.password = password;
		// resp.getWriter().println(nome + " " + cognome + " " + email + " " + password
		// + " " + matricolaReg);
		DAOFactory df = PostgresDAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
		Studente s = sdao.findByPrimaryKey(matricola);
		if (s != null) {
			// req.setAttribute("errore", new String("esisteeeeeeee"));
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/iscriviStudenti.jsp");
			rd.forward(req, resp);
			// resp.getWriter().println("esiste non si puo registrare");
			// resp.getWriter().println("<option value=\"sbagliat" + "\">" + "</option>");
		} else {
			s = new Studente(matricolaReg, 0, nome, cognome, email, passReg);
			sdao.save(s);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/home.jsp");
			rd.forward(req, resp);
		}
	}
}

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LineaRegistroNavette;
import model.RegistroAttivitaNavette;
import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.AutistaDaoJDBC;
import persistence.daoManage.jdbcDao.NavettaDaoJDBC;
import persistence.persistentModel.Autista;
import persistence.persistentModel.Fermata;
import persistence.persistentModel.Linea;
import persistence.persistentModel.Navetta;
import persistence.persistentModel.TrattoLinea;

public class ObliteraManualmente extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		AutistaDaoJDBC adao = (AutistaDaoJDBC) df.getAutistaDAO();
		NavettaDaoJDBC ndao = (NavettaDaoJDBC) df.getNavettaDAO();
		RegistroAttivitaNavette r = new RegistroAttivitaNavette(new Date());
		Autista a = (Autista) adao.findByPrimaryKey("2");
		r.addLinea(a.getID());
		LineaRegistroNavette l = r.getLineaRegistro(a.getID());
		l.setAutista(a);
		l.setGiriCompletati(0);
		Navetta n = (Navetta) ndao.findByPrimaryKey("1");
		// insert into "Linea" values (ARRAY[['l'],['i'],['n'],['e'],['a']]);
		/**/
		l.setNavetta(n);
		Linea lineaA = new Linea("a");
		Linea lineaB = new Linea("b");
		Fermata f1 = new Fermata("a", 2.1, 2.2), f2 = new Fermata("b", 2.1, 2.2);
		TrattoLinea farlocco = new TrattoLinea(f1, f2, 1, 1);
		l.setPosizione(farlocco);
		l.setLinea(lineaA);
		req.getServletContext().setAttribute("registro", r);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/obliteraManualmente.jsp");
		rd.forward(req, resp);
	}
}

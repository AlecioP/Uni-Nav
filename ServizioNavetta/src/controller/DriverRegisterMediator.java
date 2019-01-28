package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LineaRegistroNavette;
import model.RegistroAttivitaNavette;
import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.persistentModel.Autista;
import persistence.persistentModel.Linea;
import persistence.persistentModel.Navetta;
import persistence.persistentModel.TrattoLinea;

/**
 * Servlet implementation class DriverRegisterMediator
 */

public class DriverRegisterMediator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverRegisterMediator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =(String) request.getSession().getAttribute("username");
		/*DEBUG*/
		if(username==null) {
			username = 2+"";//Present in DB
		}
//		DAOFactory daoFact = DatabaseManager.getInstance().getDaoFactory();
		
		/*DEBUG*/
		RegistroAttivitaNavette registro = (RegistroAttivitaNavette) request.getServletContext().getAttribute("registro");
		if(registro==null) {
			Date data = new Date();
			registro = new RegistroAttivitaNavette(data);
			request.getServletContext().setAttribute("registro", registro);
			
		}
		
		LineaRegistroNavette lineaRegistro = registro.getLineaRegistro(Integer.parseInt(username));
		if(lineaRegistro==null) {
			if(registro.addLinea(Integer.parseInt(username))==false) {
				/*Autista doesn't exist in DB*/
				// [...] DO SOMETHING
			}
			lineaRegistro = registro.getLineaRegistro(Integer.parseInt(username));
			DAOFactory daoFactory = DatabaseManager.getInstance().getDaoFactory();
			Autista autista =(Autista) daoFactory.getAutistaDAO().findByPrimaryKey(username);
			lineaRegistro.setAutista(autista);
			lineaRegistro.setGiriCompletati(0);
			/* Missing data */
			String navettaId = "";
			String nomeLinea = "linea";//Present in DB
			//insert into "Linea" values (ARRAY[['l'],['i'],['n'],['e'],['a']]);
			int capolinea = 0;
			/**/
			Navetta navetta = (Navetta) daoFactory.getNavettaDAO().findByPrimaryKey(navettaId);
			lineaRegistro.setNavetta(navetta);
			Linea linea = new Linea(nomeLinea);
			TrattoLinea posizione = linea.getTratti().get(capolinea);
			lineaRegistro.setPosizione(posizione);
			lineaRegistro.setLinea(linea);
		}
		
		request.setAttribute("linea-registro", lineaRegistro);
		
	}

}

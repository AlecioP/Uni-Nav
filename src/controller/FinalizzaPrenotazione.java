package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.NoSuchElementException;

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
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.daoManage.jdbcDao.TrattoLineaDaoJDBC;
import persistence.persistentModel.Autista;
import persistence.persistentModel.Navetta;
import persistence.persistentModel.Prenotazione;
import persistence.persistentModel.Studente;
import persistence.persistentModel.TrattoLinea;
import persistence.utility.IdProvider;

/**
 * Servlet implementation class FinalizzaPrenotazione
 */

public class FinalizzaPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizzaPrenotazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dim = Integer.parseInt(request.getParameter("dimension"));
		ArrayList<Integer> navs = new ArrayList<Integer>();
		for(int i=0;i<dim;i++) {
			String strId = request.getParameter("tratto-"+i);
			int id = Integer.parseInt(strId);
			navs.add(Integer.valueOf(id));
			/*DEBUG*/
			System.out.println(navs.get(i));
			/*DEBUG*/
		}
		
		String username = (String) request.getSession().getAttribute("username");
		
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		
		PrenotazioneDaoJDBC pDao = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
		NavettaDaoJDBC nDao = (NavettaDaoJDBC) df.getNavettaDAO();
		AutistaDaoJDBC aDao = (AutistaDaoJDBC) df.getAutistaDAO();
		StudenteDaoJDBC sDao = (StudenteDaoJDBC) df.getStudenteDAO();
		TrattoLineaDaoJDBC tDao = (TrattoLineaDaoJDBC) df.getTrattoLineaDAO();
		RegistroAttivitaNavette registro = RegistroAttivitaNavette.getInstance();
		
		int iterator = 0;
		for(Integer ing : navs) {
			int ID = IdProvider.getInstance().getNextId("prenotazione");
			String strPartenza = request.getParameter("start-"+iterator);
			String strArrivo = request.getParameter("stop-"+iterator);
			TrattoLinea tratto = tDao.findByPrimaryKeyComposed(strPartenza, strArrivo);
			Navetta navetta = (Navetta) nDao.findByPrimaryKey(ing.intValue()+"");
			int autistaID = RegistroAttivitaNavette.getInstance().getIdAutista(navetta.getID());
			LineaRegistroNavette lineaRegistro = RegistroAttivitaNavette.getInstance().getLineaRegistro(autistaID);
			ArrayList<TrattoLinea> tuttiITratti = lineaRegistro.getLinea().getTratti();
			boolean arrivoTrovato=false, posizioneTrovata=false; 
			int n=0;
			for(TrattoLinea t : tuttiITratti) {
				if(t.getPartenza().getNome().equals(lineaRegistro.getPosizione().getPartenza().getNome()) && t.getArrivo().getNome().equals(lineaRegistro.getPosizione().getArrivo().getNome()))
					posizioneTrovata=true;
				if(t.getPartenza().getNome().equals(tratto.getPartenza().getNome()) && t.getArrivo().getNome().equals(tratto.getArrivo().getNome()))
					arrivoTrovato=true;
				if(arrivoTrovato || posizioneTrovata)
					break;
			}
			if(posizioneTrovata && !arrivoTrovato)
				n=1;
			if(!posizioneTrovata && arrivoTrovato)
				n=2;
			int giro = lineaRegistro.getGiriCompletati()+n;
			Calendar dateTime = new Calendar.Builder().setInstant(new Date()).build();
			Autista autista = (Autista) aDao.findByPrimaryKey(registro.getIdAutista(navetta.getID())+"");
			Studente studente = sDao.findByPrimaryKey(username);
			Prenotazione p = new Prenotazione(ID, giro, navetta, tratto, dateTime, autista, studente);
			pDao.save(p);
			iterator++;
			
			/*simplification*/
			break;
			/*simplification*/
		}
		
		/*CLEAN SESSION*/
		StudenteDaoJDBC studenteDao = (StudenteDaoJDBC) DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
		Studente a= (Studente) studenteDao.findByPrimaryKey(username);
		
		String type = (String) request.getSession().getAttribute("tipo-login");
		Enumeration<String> attributes  = request.getSession().getAttributeNames();
		try {
			while(true) {
				String el = attributes.nextElement();
				request.getSession().removeAttribute(el);
			}
		}catch(NoSuchElementException e) {}
		request.getSession().setAttribute("stud", a);
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("tipo-login", type);
		/*CLEAN SESSION*/
		
		RequestDispatcher rd = request.getRequestDispatcher("/home");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

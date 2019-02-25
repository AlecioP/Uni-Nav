package controller.groupB.draft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LineaRegistroNavette;
import model.RegistroAttivitaNavette;
import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Autista;
import persistence.persistentModel.Fermata;
import persistence.persistentModel.Linea;
import persistence.persistentModel.Navetta;
import persistence.persistentModel.Prenotazione;
import persistence.persistentModel.Studente;
import persistence.persistentModel.TrattoLinea;
import persistence.utility.IdProvider;

/**
 * Servlet implementation class BigliettoNavetta
 */

public class BigliettoNavetta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BigliettoNavetta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RegistroAttivitaNavette registro = RegistroAttivitaNavette.getInstance();
		LineaRegistroNavette linearegistro  = registro.getLineaRegistro(Integer.parseInt((String)request.getSession().getAttribute("username")));
		Linea linea = linearegistro.getLinea();
		TrattoLinea attuale = linearegistro.getPosizione();
		ArrayList<TrattoLinea> tratti = linea.getTratti();
		System.out.println("LINEA : "+linea.getNome());
		boolean cond = false;

		ArrayList<Fermata> prossime = new ArrayList<Fermata>();

		boolean capolinea = attuale.getArrivo().getNome().equals(tratti.get(tratti.size()-1).getArrivo().getNome());

		if(!capolinea) {
			for(TrattoLinea t : tratti) {
				if(!cond && t.getPartenza().getNome().equals(attuale.getPartenza().getNome()) && t.getArrivo().getNome().equals(attuale.getArrivo().getNome())) {
					cond = true;
					continue;
				}
				if(cond) {
					prossime.add(t.getArrivo());
				}
			}
		}else {
			for(TrattoLinea t : tratti) {
				prossime.add(t.getArrivo());
			}
		}

		request.getSession().setAttribute("prossime", prossime);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/biglietto.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistroAttivitaNavette registro = RegistroAttivitaNavette.getInstance();
		LineaRegistroNavette linearegistro  = registro.getLineaRegistro(Integer.parseInt((String)request.getSession().getAttribute("username")));
		Linea linea = linearegistro.getLinea();
		TrattoLinea attuale = linearegistro.getPosizione();
		ArrayList<TrattoLinea> tratti = linea.getTratti();
		System.out.println("LINEA : "+linea.getNome());

		boolean capolinea = attuale.getArrivo().getNome().equals(tratti.get(tratti.size()-1).getArrivo().getNome());
		
		int giro = 0;
		
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		Crud studDao = df.getStudenteDAO();
		Crud autistaDao = df.getAutistaDAO();
		Crud prenotazioneDao = df.getPrenotazioneDAO();
		
		String matricola = request.getParameter("id");
		
		Studente studente = (Studente) studDao.findByPrimaryKey(matricola);
		
		String id_autista = (String) request.getSession().getAttribute("username");
		
		Autista autista = (Autista) autistaDao.findByPrimaryKey(id_autista);
		
		Navetta navetta = linearegistro.getNavetta();
		
		
		Calendar today = Calendar.getInstance();
		
		Prenotazione prenotazione = new Prenotazione(-1, -1, navetta, null, today, autista, studente);
		
		boolean cond = false;
		if(!capolinea) {
			giro = linearegistro.getGiriCompletati()+1;
			prenotazione.setGiro(giro);
			for(TrattoLinea t : tratti) {
				if(!cond && t.getArrivo().getNome().equals(attuale.getArrivo().getNome()) && t.getPartenza().getNome().equals(attuale.getPartenza().getNome())) {
					cond = true;
					continue;
				}
				if(cond) {
					int ID = IdProvider.getInstance().getNextId("prenotazione");
					prenotazione.setID(ID);
					prenotazione.setTratto(t);
					prenotazioneDao.save(prenotazione);
					/*simplification*/
					break;
					/*simplification*/
				}
			}
			
		}else {
			giro = linearegistro.getGiriCompletati()+2;
			prenotazione.setGiro(giro);
			for (TrattoLinea t : tratti) {
				int ID = IdProvider.getInstance().getNextId("prenotazione");
				prenotazione.setTratto(t);
				prenotazione.setID(ID);
				prenotazioneDao.save(prenotazione);
				/*simplification*/
				break;
				/*simplification*/
			}
		}
		
		response.sendRedirect("home");
		return;
	}

}

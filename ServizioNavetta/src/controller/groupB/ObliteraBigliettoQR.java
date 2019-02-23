package controller.groupB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import controller.conversionUtil.Converter;
import controller.conversionUtil.Validator;
import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Prenotazione;

/**
 * Servlet implementation class ObliteraBiglietto
 */

public class ObliteraBigliettoQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObliteraBigliettoQR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/obliteraBiglietto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lettoQR = request.getParameter("codice");
		try {
			JSONObject lettoQRJason = new JSONObject(lettoQR);
			String id = lettoQRJason.getString("code"); 
			DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
			Crud prenotazioneDAO = df.getPrenotazioneDAO();
			
			Prenotazione attuale = (Prenotazione) prenotazioneDAO.findByPrimaryKey(id);
			
			JSONObject risposta = new JSONObject();
			
			if(attuale==null) {
				risposta.append("risposta", "no");
				response.getOutputStream().println(risposta.toString());
				return;
			}
			
			if(!attuale.isObliteratoEntrata())
				attuale.setObliteratoEntrata(true);
			else
				if(!attuale.isObliteratoUscita())
					attuale.setObliteratoUscita(true);
			
			prenotazioneDAO.update(attuale);
			
			risposta.append("risposta", "si");
			response.getOutputStream().println(risposta.toString());
			return;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

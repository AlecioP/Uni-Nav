package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Prenotazione;

public class ObliteraEntrataManualmente extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -771141708825267352L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsonReceived = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String line = reader.readLine();
		while (line != null) {
			jsonReceived = jsonReceived + line + "\n";
			line = reader.readLine();
		}
		// System.out.println(jsonReceived);
		try {
			JSONObject json = new JSONObject(jsonReceived);
			DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
			PrenotazioneDaoJDBC pdao = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
			@SuppressWarnings("unused")
			StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
			// System.out.println(json.getString("id") + " jsonnn");
			Prenotazione p = (Prenotazione) pdao.findByPrimaryKey(json.getString("id"));
			// Studente s = sdao.findByPrimaryKey(p.getStudente().getMatricola() + "");
			// System.out.println(p.getID() + " iddd");
			// System.out.println(p.getTratto().getArrivo() + " nomeee");
			p.setObliteratoEntrata(true);
			// System.out.println(p.getStudente().ge + " fermaa");
			pdao.update(p);
			// int p = Integer.parseInt(json.getString("id"));
			// JSONObject jsonScuola = new JSONObject(p);

//			System.out.println(jsonScuola.toString());
//			resp.getWriter().println(jsonScuola.toString());
			System.out.println(json.toString() + " sss");
			resp.getWriter().println(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

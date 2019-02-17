package controller.groupB;

import java.awt.Point;
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

public class ControlloObliterazione extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7310983108598484101L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsonReceived = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String line = reader.readLine();
		while (line != null) {
			jsonReceived = jsonReceived + line + "\n";
			line = reader.readLine();
		}
		try {
			JSONObject json = new JSONObject(jsonReceived);
			DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
			PrenotazioneDaoJDBC pdao = (PrenotazioneDaoJDBC) df.getPrenotazioneDAO();
			@SuppressWarnings("unused")
			StudenteDaoJDBC sdao = (StudenteDaoJDBC) df.getStudenteDAO();
			System.out.println(json.getString("id") + " jsonnn");
			Prenotazione p = (Prenotazione) pdao.findByPrimaryKey(json.getString("id"));
			int entrata = 0, uscita = 0;
			if (p.isObliteratoEntrata()) 
				entrata = 1;
			if (p.isObliteratoUscita())
				uscita = 1;
			Point coppia = new Point(entrata, uscita);
			JSONObject jsonCoppia = new JSONObject(coppia);
			System.out.println(jsonCoppia.toString());
			resp.getWriter().println(jsonCoppia.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

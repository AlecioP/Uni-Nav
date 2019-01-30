package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.geoUtil.GeoUtil;
import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Fermata;
import persistence.persistentModel.TrattoLinea;
import persistence.daoManage.jdbcDao.FermataDaoJDBC;

public class CreaPrenotazione extends HttpServlet{

	/**
	 * 
	 */

	private static final int NUMERO_FERMATE_VICINE = 5;

	private static final long serialVersionUID = 7019570969697763456L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*Verifica ban*/

		/**/
		String state = (String) req.getParameter("state");
		if(state==null) {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/dynamicPages/NuovaPrenotazioneMap.jsp");
			rd.forward(req, resp);
			return;
		}

		switch(state) {

		case "partenza":
		case "arrivo":{
			double actualLat = Double.parseDouble(req.getParameter("actual-lat"));
			double actualLng = Double.parseDouble(req.getParameter("actual-lng"));
			ArrayList<Fermata> fermatevicine = new ArrayList<Fermata>();
			DAOFactory df = DatabaseManager.getInstance().getDaoFactory();

			FermataDaoJDBC fermataDao = (FermataDaoJDBC) df.getFermataDAO();
			ArrayList<Fermata> tuttefermate = (ArrayList<Fermata>) fermataDao.findAll();

			for(Fermata f : tuttefermate) {
				if(fermatevicine.size()<NUMERO_FERMATE_VICINE)
					fermatevicine.add(f);
				else {
					double maxDistance = GeoUtil.distance(actualLat, actualLng, 
							f.getLatitudine(), f.getLongitudine());
					int removeIndex = -1;
					for(Fermata f1 : fermatevicine) {
						double f1D = GeoUtil.distance(actualLat, actualLng, 
								f1.getLatitudine(), f1.getLongitudine());
						if(f1D>maxDistance)
							removeIndex = fermatevicine.indexOf(f1);
					}
					if(removeIndex>=0) {
						fermatevicine.remove(removeIndex);
						fermatevicine.add(f);
					}
				}

			}
			ArrayList<JSONObject> fermateJson = new ArrayList<JSONObject>();
			for(Fermata f : fermatevicine) {
				fermateJson.add(new JSONObject(f));
			}
			JSONArray fermate = new JSONArray(fermateJson);
			resp.getOutputStream().println(fermate.toString());
			break;
		}

		case "computeLine" :{
			String partenzaNome = (String) req.getSession().getAttribute("partenza-nome");
			String arrivoNome = (String) req.getSession().getAttribute("arrivo-nome");
			DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
			Crud fermataDao = df.getFermataDAO();
			Fermata partenza = (Fermata) fermataDao.findByPrimaryKey(partenzaNome), 
					arrivo = (Fermata) fermataDao.findByPrimaryKey(arrivoNome);
			ArrayList<ArrayList<TrattoLinea> > routes = GeoUtil.computeRoutes(partenza, arrivo);
			JSONObject routesJson = new JSONObject(routes);
			resp.getOutputStream().println(routesJson.toString());
			break;
		}
		case "computeBus" : {

			break;
		}
		}
	}

}

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

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.PrenotazioneDaoJDBC;
import persistence.utility.Pair;

/**
 * Servlet implementation class AdminStatistics
 */

public class AdminStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStatistics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String) request.getSession().getAttribute("tipo-login");
		if(!type.equals("admin")) {
			response.sendRedirect("home");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/statisticsQuery.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String) request.getSession().getAttribute("tipo-login");
		if(!type.equals("admin")) {
			response.sendRedirect("home");
			return;
		}
		DAOFactory daoFactory = DatabaseManager.getInstance().getDaoFactory();
		String stat_type = request.getParameter("stat-type");
		String id = request.getParameter("id");
		System.err.println(id+" "+stat_type);
		
		if(id==null || stat_type==null || id.equals("") || id.equals(" ")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/statisticsQuery.jsp");
			rd.forward(request, response);
			return;
		}
		switch(stat_type) {
		case "last-month-books":{
			PrenotazioneDaoJDBC booksDao = (PrenotazioneDaoJDBC) daoFactory.getPrenotazioneDAO(); 
			ArrayList<Pair<String, Integer> > result = booksDao.numberOfBookingsPerDayLastMonth(id); 
			if(result==null || result.isEmpty()){
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/statisticsQuery.jsp");
				rd.forward(request, response);
				return;
			}
				
			ArrayList<JSONObject> jsonHalf = new ArrayList<JSONObject>();
			
			for(Pair<String, Integer> p : result) {
				JSONObject current = new JSONObject(p);
				jsonHalf.add(current);
			}
			JSONArray jsonArray = new JSONArray(jsonHalf);
			request.getSession().setAttribute("data", jsonArray.toString());
			System.out.println(jsonArray.toString());
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/splineContainer.jsp");
			rd.forward(request, response);
			return;
		}
		default :{
			break;
		}
		}
	}

}

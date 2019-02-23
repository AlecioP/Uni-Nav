package controller.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.chatRoom.ChatRoom;
import model.chatRoom.Message;

/**
 * Servlet implementation class Updater
 */
@WebServlet("/Updater")
public class Updater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updater() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("last-message");
		boolean no_messages = Boolean.parseBoolean(request.getParameter("no-messages"));
		JSONObject message = null;
		Message m = null;
		try {
			message = new JSONObject(parameter);
			m = Message.parseJson(message);
		} catch (JSONException e) {}
		
		LinkedList<Message> newMessages = new LinkedList<Message>();
		
		boolean reachedIndex = false || no_messages;
		
		for(Message m1 : ChatRoom.getInstance().getMessages()) {
			System.out.println("iterating messages");
			if(!reachedIndex && m1.equals(m)) {
				reachedIndex = true;
				continue;
			}
			if(reachedIndex) 
				newMessages.add(m1);
		}
		
		ArrayList<JSONObject> jsonlist = new ArrayList<JSONObject>();
		
		for(Message m2 : newMessages) {
			JSONObject jsonObj = new JSONObject(m2);
			jsonlist.add(jsonObj);
		}
		
		JSONArray jsonarray = new JSONArray(jsonlist);
		
		response.getOutputStream().println(jsonarray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

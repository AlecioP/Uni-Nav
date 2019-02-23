package controller.chat;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.chatRoom.ChatRoom;
import model.chatRoom.Message;
import model.chatRoom.Message.User;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/Chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Message> m = ChatRoom.getInstance().getMessages();
		request.getSession().setAttribute("messages", m);
		request.getSession().setAttribute("tipo_login", request.getSession().getAttribute("tipo-login"));
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/dynamicPages/globalChat.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("new-message");
		message = message.replaceAll("\"", "");
		System.out.println("SERVER VALUE : "+message);
		String username = (String) request.getSession().getAttribute("username");
		String type = (String) request.getSession().getAttribute("tipo-login");
		User u;
		switch (type) {
		case "driver":{
			u = User.DRIVER;
			break;
		}
		case "admin":{
			u = User.ADMIN;
			break;
		}
		case "student":{
			u = User.STUDENT;
			break;
		}
		default:{
			System.out.println("TYPE NULL");
			u = null;
			break;
		}
		}
		
		Message m = new Message(u, Integer.parseInt(username), message);
		ChatRoom.getInstance().push(m);
		
		response.getOutputStream().print(new JSONObject(m).toString());
		return;
	}

}

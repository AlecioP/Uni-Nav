package model.chatRoom;

import org.json.JSONException;
import org.json.JSONObject;

public class Message implements Cloneable{
	
	public enum User {DRIVER,ADMIN,STUDENT; public String getValue() {return this.name();}};
	
	private User type;
	
	private String message;
	
	private int ID;
	
	public Message() {}
	
	public Message(User type,int ID, String message) {
		this.type = type;
		this.ID = ID;
		this.message = message;
	}

	public User getType() {
		return type;
	}

	public void setType(User type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	@Override
	protected Object clone() {
		Message m = new Message();
		m.setID(this.getID());
		m.setMessage(this.message+"");
		User u;
		switch (this.type) {
		case ADMIN:{
			u=User.ADMIN;
			break;
		}
		case DRIVER:{
			u = User.DRIVER;
			break;
		}
		case STUDENT :{
			u = User.STUDENT;
			break;
		}
		default :
			u = this.type;
		}
		m.setType(u);
		return m;
	}
	
	public static Message parseJson(JSONObject obj) throws JSONException {
		
		
		String txt = obj.getString("message");
		
		String id_txt = obj.getString("id");
		id_txt =  id_txt.replaceAll(" ", "");
		
		String type_txt = obj.getString("type");
		User u;
		switch (type_txt) {
		case "ADMIN":{
			u = User.ADMIN;
			break;
		}
		case "DRIVER":{
			u = User.DRIVER;
			break;
		}
		case "STUDENT":{
			u = User.STUDENT;
			break;
		}
		default:
			u = null;
		}
		
		return new Message(u, Integer.parseInt(id_txt), txt);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		else {
			if(obj instanceof Message) {
				Message copy = (Message) obj;
				
//				if(copy==null)
//					System.out.println("NULL COPY");
//				System.out.println("TYPE : "+copy.type.getValue());
//				System.out.println("CONTENT : "+ copy.message);
				
				return copy.type.equals(this.type) && copy.ID == this.ID && copy.message.equals(this.message);
			}else 
				return false;
		}
	}
}

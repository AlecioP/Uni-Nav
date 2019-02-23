package model.chatRoom;

import java.util.LinkedList;

public class ChatRoom {
	
	private static ChatRoom instance;
	
	private ChatRoom() {init();}
	
	public static ChatRoom getInstance() {
		if(instance == null)
			instance = new ChatRoom();
		return instance;
	}
	// Singleton (SI)
	
	private static final int MAX_NUMBER_OF_MESSAGES = 20;
	
	private LinkedList<Message> messages;
	
	private void init() {
		messages = new LinkedList<Message>();
	}
	
	public void push(Message m) {
		if(messages.size()==MAX_NUMBER_OF_MESSAGES)
			messages.removeFirst();
		messages.addLast(m);
	}
	
	public LinkedList<Message> getMessages(){
		LinkedList<Message> ms = new LinkedList<Message>();
		for(Message m : this.messages)
			ms.add((Message) m.clone());
		return ms;
	}

}

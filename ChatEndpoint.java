package fr.ul.miage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
//import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/hello")
public class ChatEndpoint {

	private Session session;
	
	@OnOpen
	public void onCreateSession(Session session) {
		System.out.printf("Session ouverte, id : " +  session.getId());
		try {
			session.getBasicRemote().sendText("La connexion est établie");
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		this.session = session;
	}
	
	@OnMessage
	public void onTextMessage(String message, Session session) {
		System.out.println("message = " + message + " , id" + session.getId());
		if(this.session != null && this.session.isOpen()) {
				try {
					this.session.getBasicRemote().sendText("Message reçue sur le serveur  : " + message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
		}
		
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onCLose(Session session) {
		System.out.println("Sessions fermée avec l'id : " + session.getId());
	}
	
}
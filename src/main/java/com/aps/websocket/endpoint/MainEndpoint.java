package com.aps.websocket.endpoint;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCode;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.aps.websocket.configurator.MainConfigurator;
import com.aps.websocket.util.SpringUtil;
import com.portalservice.dto.ManagerResponse;
import com.portalservice.dto.Terminal;
import com.portalservice.manager.PlayerManager;

@ServerEndpoint(value = "/endpoint", configurator=MainConfigurator.class)
public class MainEndpoint {
	
	private static List<Session> sessions = new CopyOnWriteArrayList<>();
	
	@OnOpen
	public synchronized void onOpen(Session session, EndpointConfig config){
		System.out.println("New Session has opened.");
		sessions.add(session);
		System.out.println("New Size : "+sessions.size());
	}
	
	@OnMessage
	public void onMessage(String msg, Session session){
		System.out.println("Message from client : "+msg);
		String[] msgArray = Pattern.compile("[|]").split(msg);
		if("PLAYERID".equalsIgnoreCase(msgArray[0])){
			String playerId  = msgArray[1];
			String sessionId = msgArray[2];
			if(validate(playerId, sessionId)){
				disconnectExisting(playerId, sessionId);
				session.getUserProperties().put("broadcastId", playerId);
				session.getUserProperties().put("sessionId", sessionId);
			}else{
				try {
					session.close(new CloseReason(CloseCodes.VIOLATED_POLICY, "Invalid player."));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@OnClose
	public synchronized void onClose(Session session, CloseReason closeReason){
		System.out.println("Session was closed. ");
		sessions.remove(session);
	}
	
	@OnError
	public void onError(Session session, Throwable t){
		System.out.println("Error :"+t.getMessage());
	}
	
	public synchronized static void broadcast(String msg, String broadcastTo){
		for(Session session : sessions){
			try {
				if("".equals(broadcastTo)){
					session.getBasicRemote().sendText(msg);
				}else if(broadcastTo.equalsIgnoreCase((String)session.getUserProperties().get("broadcastId"))){
					session.getBasicRemote().sendText(msg);
				}
			} catch (Exception e) {
				System.out.println("Unable to write. Disconnected.");
			}
		}
	}
	
	public static void disconnectAll(){
		for(Session session : sessions){
			try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<Session> getSessions(){
		return sessions;
	}
	
	
	private synchronized static void disconnectExisting(String playerId, String sessionId){
		int disconnectedCount = 0;
		for(Session s : sessions){
			if(playerId.equals(s.getUserProperties().get("broadcastId"))){
				if(!sessionId.equals(s.getUserProperties().get("sessionId"))){
					try {
						System.out.println(CloseCodes.VIOLATED_POLICY);
						s.close(new CloseReason(CloseCodes.VIOLATED_POLICY, "Account was logged-in in other device."));
						disconnectedCount++;
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("Number of disconnected : "+disconnectedCount);
	}
	
	private static boolean validate(String playerId, String sessionId){
		boolean result = false;
		try{
			PlayerManager playerManager = (PlayerManager) SpringUtil.getBean("playerManager");
			ManagerResponse mr = playerManager.validatePlayerId(playerId);
			if(mr.getStatus() == 0){
				Terminal terminal = (Terminal) mr.getObj();
				if(terminal != null && terminal.getSessionId().equals(sessionId)){
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

package com.aps.websocket.util;

import java.util.regex.Pattern;

import com.aps.websocket.endpoint.MainEndpoint;

public class Broadcaster implements Runnable{
	
	private String msg; 
	public Broadcaster(String msg){
		this.msg = msg;
	}
	
	private void broadcast(){
		if(!MainEndpoint.getSessions().isEmpty()){
			String[] msgArray = Pattern.compile("[|]").split(msg);
			String broadcastTo = "";
			if("UPDATE_BALANCE".equalsIgnoreCase(msgArray[0])){
				String shopId  			= msgArray[1];
				String playerId 	= msgArray[2];
				broadcastTo = playerId;
				
				MainEndpoint.broadcast(msg, broadcastTo);
			}else{
				
			}
		}
	}
	
	@Override
	public void run() {
		broadcast();
	}
	
}

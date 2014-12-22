package com.aps.websocket.configurator;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.aps.websocket.exception.UnableToConnectException;
import com.aps.websocket.util.InternalConnection;

public class MainConfigurator extends Configurator{

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		
		if(!InternalConnection.getInstance().isConnected()){
			throw new UnableToConnectException("Unable to connect. Server socket is disconnected.");
		}
		
		/*Map<String, List<String>> headers = request.getHeaders();
		Set<String> keys = headers.keySet();
		Iterator i = keys.iterator();
		String key;
		
		while(i.hasNext()){
			key = (String) i.next();
			System.out.println(key+" => "+headers.get(key));
		}
		List<String> cookies = headers.get("cookie");
		if(!cookies.isEmpty()){
			String username = HttpUtil.getCookie(cookies.get(0), "username");
			System.out.println(username);
		}*/
		
		super.modifyHandshake(sec, request, response);
	}
	
	@Override
	public boolean checkOrigin(String originHeaderValue) {
		return super.checkOrigin(originHeaderValue);
	}
	
}

package com.aps.websocket.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener{
	
	public InternalConnection internalConnection;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		internalConnection.disconnect();
		System.out.println("Context destroyed");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		internalConnection = InternalConnection.getInstance();
		System.out.println("Context initialized");
	}

}

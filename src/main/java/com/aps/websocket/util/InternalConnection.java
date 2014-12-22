package com.aps.websocket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

import com.aps.websocket.endpoint.MainEndpoint;

public class InternalConnection {
	
	private static final InternalConnection instance = new InternalConnection();
	
	private volatile boolean threadFlag = true;
	private volatile boolean isNotConnected = true;
	private Socket socket;
	
	public InternalConnection(){
		Thread connectionThread = new Thread(new Connector());
		connectionThread.setName("Connector-Thread");
		connectionThread.start();
	}
	
	public static InternalConnection getInstance(){
		return instance;
	}
	
	public boolean isConnected(){
		return !isNotConnected;
	}
	
	private Socket connectToServer(){
		String host = "";
		int port = 0;
		long reconnectInterval = 3000;
		while(isNotConnected){
			host = Configurator.getConfig("socket.host");
			port = Integer.parseInt(Configurator.getConfig("socket.port"));
			reconnectInterval = Long.parseLong(Configurator.getConfig("socket.reconnectInterval"));
			try {
				socket = new Socket(host, port);
				System.out.println("Connected to Server " + socket.getRemoteSocketAddress());
				break;
			}catch (IOException e) {
				System.out.println("Cannot connect to server ["+host+"] at port ["+port+"]");
				try {
					Thread.sleep(reconnectInterval);
				} catch (Exception e2) {}
				continue;
			}
		}
		return socket;
	}
	
	public void disconnect(){
		System.out.println("Disconnecting sockets...");
		
		threadFlag = false; 
		if(socket != null){
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class Connector implements Runnable{
		
		@Override
		public void run() {
			Socket socket = null;
			while(isNotConnected && threadFlag){
				socket = connectToServer();
				isNotConnected = false;
				Thread incomingReaderThread = new Thread(new IncomingReader(socket));
				incomingReaderThread.setName("SocketReader-Thread");
				incomingReaderThread.start();
				try {
					synchronized (incomingReaderThread) {
						incomingReaderThread.wait();
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	private class IncomingReader implements Runnable{
		
		Socket socket;
		public IncomingReader(Socket socket){
			this.socket = socket;
		}

		@Override
		public void run() {
			synchronized (this) {
				PrintWriter writer = null;
				BufferedReader reader = null;
				
				try {
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					writer.print("WEBSIGNON"+'\0');
					writer.flush();
					char[] charBuffer = new char[1];
					
					while(!socket.isClosed()){
						try {
							while(reader.read(charBuffer, 0, 1) != -1){
								StringBuffer strBuffer = new StringBuffer();
								if(socket.isClosed()) break;
								while(charBuffer[0] != '\0'){
									strBuffer.append(charBuffer[0]);
									reader.read(charBuffer, 0, 1);
								}
								
								String received = strBuffer.toString();
								System.out.println(socket.getRemoteSocketAddress()+" Says : "+received);
								
								String msgArray[] = Pattern.compile("[|]").split(received);
								if(msgArray[0].equalsIgnoreCase("ECHO")){
									writer.write("ECHO|"+'\0');
									writer.flush();
									//Remove this before prod!!!
									//Thread broadCasterThread = new Thread(new Broadcaster(received));
									//broadCasterThread.start();
								}else{
									Thread broadCasterThread = new Thread(new Broadcaster(received));
									broadCasterThread.start();
								}
								
								msgArray = null;
								received = null;
								strBuffer = null;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						reader.close();
						writer.close();
						socket.close();
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				} finally {
					if(socket != null){
						try {
							socket.close();
							System.out.println("Closing JavaThread " +Thread.currentThread().getName());
							MainEndpoint.disconnectAll();
							socket = null;
						} catch (IOException e) {
							e.printStackTrace();
						}
						isNotConnected = true;
						notify();
					}
				}
				
			}
		}
		
	}
}

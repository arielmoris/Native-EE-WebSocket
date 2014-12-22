package com.aps.websocket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator{
	
	private static boolean isFileSystem  = false;
	private static String  resourceName  = "/config/config.properties";
	
//	private static boolean isFileSystem  = true;	
//	private static String  resourceName  = "/conf/config.properties";
	
	private static Properties properties = new Properties();
	private static Configurator instance = new Configurator();
	private static long timeStamp;
	private static boolean reloadable;
	
	private Configurator(){
		loadConfig();
	}
	
	private static void loadConfig(){
		File file 		  = null;
		InputStream is	  = null;
		
		try {
			if(isFileSystem){
				file = new File(resourceName);
			}
			else{
				file = new File(Configurator.class.getClassLoader().getResource(resourceName).getFile());
			}

			long lastModified = file.lastModified();
			if(timeStamp != lastModified){
				timeStamp = file.lastModified();
				is = new FileInputStream(file);
				properties.load(is);
				reloadable = Boolean.parseBoolean(properties.getProperty("reloadable"));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getConfig(String key){
		if(reloadable){
			loadConfig();
		}
		return properties.getProperty(key);
	}
	
	public static Configurator getInstance(){
		return instance;
	}
	
}

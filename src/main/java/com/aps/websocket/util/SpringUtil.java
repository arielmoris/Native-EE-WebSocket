package com.aps.websocket.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringUtil {
	private static final String config;
	private static final BeanFactory factory;
	static{
		config = "spring/app-config.xml";
		factory = new ClassPathXmlApplicationContext(config);
	}
	public static Object getBean(String name){
		return factory.getBean(name);
	}
}

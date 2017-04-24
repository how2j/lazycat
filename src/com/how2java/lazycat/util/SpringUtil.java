package com.how2java.lazycat.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.how2java.lazycat.mapper.TomcatMapper;

public class SpringUtil {
	private static ApplicationContext context=null;
	public static void main(String[] args) {
		System.out.println(getBean(TomcatMapper.class));
		System.out.println(getBean(TomcatMapper.class));
		
	}
	
	public static Object getBean(Class clazz) {
		
		
		try {
			if(null==context)
				 context = new ClassPathXmlApplicationContext(
							new String[] { "classpath:applicationContext.xml" });
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context.getBean(clazz);
	}
	public static Object getBean(String beanName) {
		ApplicationContext context=null;

		try {
				 context = new ClassPathXmlApplicationContext(
							new String[] { "classpath:applicationContext.xml" });
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context.getBean(beanName);
	}
}

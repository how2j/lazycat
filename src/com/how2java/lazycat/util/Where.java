package com.how2java.lazycat.util;

import org.apache.catalina.core.ContainerBase;
import org.apache.catalina.startup.Catalina;

public class Where {

	public static void amI(){
		try{
			throw new Exception();	
		}
		catch(Exception e){

			e.printStackTrace();
		}
	}
	
	public static void log(Object o){
		try{
			throw new Exception();	
		}
		catch(Exception e){
			String msg = "(%s:%d)-%s%n";
			StackTraceElement s= e.getStackTrace()[1];
			System.out.printf(msg,s.getFileName(),s.getLineNumber(),o.toString());
			e.printStackTrace();
		}
		
	}

	public static void logParent(ContainerBase container) {
		ContainerBase cb= (ContainerBase) container.getParent();
		System.out.println(cb);
//		if(cb instanceof Catalina){
//			System.out.println(((Catalina)cb).getBootstrap().port);
//		}
			
		if(cb!=null){
			logParent(cb);
		}
		
	}
}

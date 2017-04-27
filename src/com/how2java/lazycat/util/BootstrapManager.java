package com.how2java.lazycat.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.startup.Bootstrap;
import org.apache.commons.io.FileUtils;

import com.how2java.lazycat.exception.PortException;
import com.how2java.lazycat.pojo.Tomcat;
import com.how2java.lazycat.pojo.WebApp;

public class BootstrapManager implements Runnable{

	private static Map<Integer,Bootstrap> bs = new HashMap<>();
	
	
	public static String getStatus(Tomcat t){
		Bootstrap b = bs.get(t.getPort());
		if(null==b)
			return Bootstrap.status_stopped;
		
		return b.getStatus();
	}
	
	
	public static Bootstrap getBoostrap(int port){
		Bootstrap b = bs.get(port);
		return b;
	}
	
	public synchronized static void startTomcat(Tomcat t){
		
		Bootstrap b = bs.get(t.getPort());
		
		if(null==b){
			
			b =new Bootstrap();
			
			b.port=t.getPort();
			
			bs.put(t.getPort(), b);
			
			b.setStatus(Bootstrap.status_starting);
			
		}
		
		try {
			List<WebApp> ws= MapperUtil.webAppMapper.listByTomcat(t);
			for (WebApp w : ws) {
				if(w.isDeploy())
					w.createXML();
			}
			
			
			startBootstrap(b);
			b.setStatus(Bootstrap.status_started);
			
		} catch (Exception e) {
			e.printStackTrace();
			b.setStatus(e.toString());
		}
		
		
	}
	
	public synchronized static void stopTomcat(Tomcat t){
		Bootstrap b = bs.get(t.getPort());
		if(null!=b){
			b.setStatus(Bootstrap.status_stopping);
			List<WebApp> ws= MapperUtil.webAppMapper.listByTomcat(t);
			for (WebApp w : ws) {
				w.getXMLFile().delete();
			}
			
			stopBootstrap(b);
			bs.remove(t.getPort());
			
			//加个延迟，显得很努力地在停止tomcat
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b.setStatus(Bootstrap.status_stopped);
			bs.remove(b);
		}
	}
	
	
	
	
	
	
	private synchronized static void stopBootstrap(Bootstrap b) {
		try {
			b.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		
		
		
	}




	public static void startBootstrap(int port) throws Exception {
			Bootstrap b = new Bootstrap();
	    	b.port=port;
	    	
	    	startBootstrap(b);
		}




	public static void startBootstrap(Bootstrap b) throws Exception  {
		try {
			
			PortUtil.check(b.port);
			
			ServerXMLGenerator.generate(b.port);
			
			try {
				
			    b.init();
			    
			} catch (Throwable t) {

			    t.printStackTrace();
			    return;
			}
			
//        Desktop.getDesktop().browse(new URI(String.format( "http://127.0.0.1:%d/j2ee/hello",b.port)));
//			b.setAwait(true);
			
			b.start();
			
		} catch (Exception e) {
			if(e instanceof PortException)
				throw e;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

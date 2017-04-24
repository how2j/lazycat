package com.how2java.lazycat.util;

import java.io.IOException;
import java.net.ServerSocket;

import com.how2java.lazycat.exception.PortException;

public class PortUtil {
	public static final int application_port = 2002;
	
	
	
	public static boolean testPort(int port) throws PortException{
		try {
			ServerSocket ss = new ServerSocket(port);
			ss.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new PortException(port);

		}
	}
	
	public static void usePort(int port) throws PortException{
		try {
			ServerSocket ss = new ServerSocket(port);
			new Thread(){
				public void run(){
					try {
						ss.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
//			ss.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new PortException(port);

		}
	}

	
	public static void check(int port) throws PortException {

		int port80 = port;
		

		if(!testPort(port80))
			throw new PortException(port80);

		
		
		
		
	}
	
}

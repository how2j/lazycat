package cn.how2j.lazycat.util;

import java.util.HashMap;
import java.util.Map;

public class LastVisitManager {

	private static Map<Integer, String> visitURI = new HashMap<>();

	public static String[] excludedExt = new String[]{".jpg",".gif",".ico",".png",".bmp",".js",".css"};
	
	public static void put(int id, String uri){
		if(endsWith(uri,excludedExt))
			return;
			
		
		visitURI.put(id, uri);
	}
	
	private static boolean endsWith(String uri, String[] excludedExt) {
		for (String s: excludedExt) {
			if(uri.toLowerCase().endsWith(s))
				return true;
		}
		return false;
	}

	public static String get(int id){
		return visitURI.get(id);
	}
	
	
	
}

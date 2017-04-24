package com.how2java.lazycat.util;

import com.how2java.lazycat.mapper.TomcatMapper;
import com.how2java.lazycat.mapper.WebAppMapper;
import com.how2java.lazycat.pojo.Tomcat;

public class MapperUtil {

	public static TomcatMapper tomcatMapper = (TomcatMapper) SpringUtil.getBean(TomcatMapper.class);
	public static WebAppMapper webAppMapper = (WebAppMapper) SpringUtil.getBean(WebAppMapper.class);
	
	public static void main(String[] args) {
		Tomcat t = tomcatMapper.get(5);
		t.setPort(8888);
		tomcatMapper.update(t);
				
		
	}
}

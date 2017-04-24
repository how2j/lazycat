package com.how2java.lazycat.mapper;
  
import java.util.List;
import java.util.Map;

import com.how2java.lazycat.pojo.Tomcat;
import com.how2java.lazycat.pojo.WebApp;
  
public interface WebAppMapper {
  
    public int add(WebApp webApp);  
        
    public void delete(int id);  	
        
    public WebApp get(int id);  
    
    public WebApp getByTomcatAndName(Map m);
      
    public int update(WebApp webApp);   
        
    public List<WebApp> list();
    
    public List<WebApp> listByTomcat(Tomcat tomcat);
     
    public int count();  
     
}
package com.how2java.lazycat.mapper;
  
import java.util.List;

import com.how2java.lazycat.pojo.Tomcat;
import com.how2java.lazycat.util.SpringUtil;
  
public interface TomcatMapper {
  
	
    public int add(Tomcat tomcat);  
        
    public void delete(int id);  	
        
    public Tomcat get(int id);  
      
    public int update(Tomcat tomcat);   
        
    public List<Tomcat> list();
    
    public Tomcat getByPort(int port);
     
    public int count();  
    

     
}
package com.how2java.lazycat.test;
 
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.how2java.lazycat.mapper.TomcatMapper;
import com.how2java.lazycat.mapper.WebAppMapper;
import com.how2java.lazycat.pojo.Tomcat;
import com.how2java.lazycat.util.MapperUtil;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestTomcat {
 
    @Autowired
    private TomcatMapper tomcatMapper;
    @Autowired
    private WebAppMapper webAppMapper;
 
    
    public static void main(String[] args) {

    	System.out.println(MapperUtil.webAppMapper.list().get(0).getTomcat());
	}

    
    @Test
    public void testList() {
        List<Tomcat> ts=tomcatMapper.list();
        for (Tomcat t : ts) {
            System.out.println(t);
        }
    }
 
}
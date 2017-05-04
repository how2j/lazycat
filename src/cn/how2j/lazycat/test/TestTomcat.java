package cn.how2j.lazycat.test;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.how2j.lazycat.mapper.TomcatMapper;
import cn.how2j.lazycat.mapper.WebAppMapper;
import cn.how2j.lazycat.pojo.Tomcat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestTomcat {

	@Autowired
	private TomcatMapper tomcatMapper;
	@Autowired
	private WebAppMapper webAppMapper;


	@Test
	public void testList() {
		List<Tomcat> ts = tomcatMapper.list();
		for (Tomcat t : ts) {
			System.out.println(t);
		}
		
		JOptionPane.showConfirmDialog(null, "xxx");
		
		
		
		
	}

}
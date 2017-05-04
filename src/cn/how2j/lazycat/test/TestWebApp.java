package cn.how2j.lazycat.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.how2j.lazycat.mapper.WebAppMapper;
import cn.how2j.lazycat.pojo.WebApp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestWebApp {

	@Autowired
	private WebAppMapper webAppMapper;

	@Test
	public void testAdd() {
		List<WebApp> ts = webAppMapper.list();
		if (ts.isEmpty()) {
			WebApp t = new WebApp();
			t.setName("yyy");
			t.setPath("e:/project/j2ee");
			t.setTestPage("index");
			t.setStartup(true);
			webAppMapper.add(t);
		}
	}

	@Test
	public void testList() {
		List<WebApp> ts = webAppMapper.list();
		for (WebApp t : ts) {
			// System.out.println(t);
		}
	}

}
package cn.how2j.lazycat.mapper;

import java.util.List;
import java.util.Map;

import cn.how2j.lazycat.pojo.Tomcat;
import cn.how2j.lazycat.pojo.WebApp;

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
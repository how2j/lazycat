package cn.how2j.lazycat.util;

import cn.how2j.lazycat.mapper.TomcatMapper;
import cn.how2j.lazycat.mapper.WebAppMapper;
import cn.how2j.lazycat.pojo.Tomcat;

public class MapperUtil {

	public static TomcatMapper tomcatMapper = (TomcatMapper) SpringUtil.getBean(TomcatMapper.class);
	public static WebAppMapper webAppMapper = (WebAppMapper) SpringUtil.getBean(WebAppMapper.class);

	public static void main(String[] args) {
		Tomcat t = tomcatMapper.get(5);
		t.setPort(8888);
		tomcatMapper.update(t);

	}
}

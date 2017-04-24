package com.how2java.lazycat.pojo;

import org.apache.catalina.startup.Bootstrap;

import com.how2java.lazycat.util.BootstrapManager;

public class Tomcat {
	

	
	int id;
	int port;
	String name;
	
	String status;
	
	
	
	
	public String getStatus() {
		
		
		
		return BootstrapManager.getStatus(this);
	}
	public void setStatus(String status) {
		this.status = status;

	}
	Bootstrap bootstrap;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Bootstrap getBootstrap() {
		return bootstrap;
	}
	public void setBootstrap(Bootstrap bootstrap) {
		this.bootstrap = bootstrap;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Tomcat [id=" + id + ", port=" + port + ", name=" + name + "]";
	}
	
	
	
}

package com.how2java.lazycat.pojo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Bootstrap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.how2java.lazycat.util.BootstrapManager;

public class WebApp {
	public static final String not_loaded = "未加载";
	public static final String loaded = "加载成功";
	public static final String loading = "加载中";
	public static final String failed = "加载失败";

	int id;
	String name;
	String path;
	String testPage;
	boolean startup;
	boolean deploy;
	Tomcat tomcat;
	String status;

	public Tomcat getTomcat() {
		return tomcat;
	}

	public void setTomcat(Tomcat tomcat) {
		this.tomcat = tomcat;
	}

	public boolean isStartup() {
		return startup;
	}

	public void setStartup(boolean startup) {
		this.startup = startup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		this.name = name.trim();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTestPage() {
		return testPage;
	}

	public void setTestPage(String testPage) {
		this.testPage = testPage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WebApp [id=" + id + ", name=" + name + ", path=" + path + ", testPage=" + testPage + ", startup="
				+ startup + "]";
	}

	public File getXMLFile() {
		File folder = new File("conf/Catalina");

		if (StringUtils.isEmpty(getName())) {
			File f = new File(folder, "localhost-" + getTomcat().getPort() + "/ROOT.xml");
			return f;
		} else {
			File f = new File(folder, "localhost-" + getTomcat().getPort() + "/" + getName() + ".xml");
			return f;
		}

	}

	public void createXML() {

		try {
			File f = getXMLFile();
			if (!f.exists())
				FileUtils.write(f, toContextXML());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toContextXML() {
		String format = "<Context path='/' docBase='%s' debug='0' reloadable='false' />";
		return String.format(format, this.getPath());
	}

	public String getStatus() {

		int port = this.getTomcat().getPort();
		Bootstrap b = BootstrapManager.getBoostrap(port);

		if (null == b) {
			return not_loaded;
		}

		if (!getXMLFile().exists())
			return not_loaded;

		List<Context> cs = b.listContexts();
		for (Context c : cs) {

			boolean match = this.nameMatch(c);

			if (match) {
				LifecycleState status = c.getState();
//				System.out.println(status);
				switch (status) {
				case FAILED:
				case STOPPED:
					return failed;
				case STARTED:
					return loaded;
				default:
					return loading;

				}
			}
		}
		if (this.getTomcat().getStatus().equals(Bootstrap.status_starting)
				|| this.getTomcat().getStatus().equals(Bootstrap.status_started))
			return loading;
		return not_loaded;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean nameMatch(Context c){
		if(c.getName().equals("")){
			return "".equals(this.getName());
		}
		else{
			return c.getName().equals("/" + name);
		}
		
		
		
		
	}

	public void stopConext() {
		int port = this.getTomcat().getPort();
		Bootstrap b = BootstrapManager.getBoostrap(port);

		if (null == b) {
			return;
		}
		List<Context> cs = b.listContexts();
		System.out.println("cs:"+cs);
		Context matchedContext = null;
		for (Context c : cs) {
			if (  this.nameMatch(c) ) {
				try {
					c.stop();
					matchedContext= c;
					System.out.println("matchedContext:"+matchedContext);
					System.out.println(c.getState());
				} catch (LifecycleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		if(matchedContext!=null)
			cs.remove(matchedContext);
	}

	public boolean isDeploy() {
		return deploy;
	}

	public void setDeploy(boolean deploy) {
		this.deploy = deploy;
	}

	public void openTestPage() {
		String pageFormat;
		String url;
		if (!StringUtils.isEmpty(this.getName())) {
			pageFormat = "http://localhost:%d/%s/%s";
			url = String.format(pageFormat, this.getTomcat().getPort(), this.getName(), this.getTestPage());
		} else {
			pageFormat = "http://localhost:%d/%s";
			url = String.format(pageFormat, this.getTomcat().getPort(), this.getTestPage());
		}

		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

	}

	 public void startContext() {
		// TODO Auto-generated method stub
			
			SwingWorker worker = new SwingWorker() {
				protected Object doInBackground() throws Exception {
					BootstrapManager.getBoostrap(getTomcat().getPort()).deployContexts();
					return null;
				}
			};
			worker.execute();
		 
	}

}

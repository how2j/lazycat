package cn.how2j.lazycat.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.catalina.Container;
import org.apache.catalina.Host;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.startup.Bootstrap;
import org.apache.commons.io.FileUtils;

public class CatalinaUtil {

	static StandardHost host = null;

	static {

		new Thread() {
			public void run() {
				while (true) {
					if (true)
						return;
					try {
						Thread.sleep(1000);

						if (null != host) {
							Container[] csa = host.findChildren();
							// System.out.println(host);
							// System.out.println(csa);
							// System.out.println(csa.length);
							for (Container c : csa) {
								StandardContext sc = (StandardContext) c;
								System.out.println(sc.getState());
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public static int getPort(Host h) {
		int port = -1;
		StandardHost host = (StandardHost) h;
		CatalinaUtil.host = (StandardHost) h;

		StandardEngine se = (StandardEngine) host.getParent();
		StandardService ss = (StandardService) se.getService();

		Connector[] cs = ss.findConnectors();
		for (Connector c : cs) {

			if (c.getProtocolHandlerClassName().contains("Http11Protocol"))
				port = c.getPort();
		}
		return port;
	}

	public static void register(int port, Bootstrap bootstrap) {

	}

	public static void init() {
		File confFolder = new File("conf");
		Collection<File> serverXMLs = FileUtils.listFiles(confFolder, new String[] { "xml" }, false);

		for (File f : serverXMLs) {
			if (f.getName().startsWith("server-") && !f.getName().equals("server-module.xml")) {
				f.delete();
			}
		}

		File catalinaFolder = new File("conf/catalina");
		File[] localhostFolders = catalinaFolder.listFiles();
		for (File f : localhostFolders) {
			if (f.isDirectory()) {
				try {
					FileUtils.deleteDirectory(f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}

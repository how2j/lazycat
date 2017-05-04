package cn.how2j.lazycat.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUpload;

public class ServerXMLGenerator {

	public static void main(String[] args) {
		generate(80);
	}

	public static void generate(int port) {

		int port80 = port;

		File f = new File("conf/server-module.xml");
		File destFile = new File(String.format("conf/server-%d.xml", port));

		try {
			String fc = FileUtils.readFileToString(f);
			fc = StringUtils.replace(fc, "#80#", String.valueOf(port));
			FileUtils.write(destFile, fc);
			// System.out.println(fc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

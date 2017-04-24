package com.how2java.lazycat.gui.util;



import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.how2java.lazycat.gui.panel.ConsolePanel;

public class LogPrintStream extends PrintStream{

	public static boolean log = true;
	public LogPrintStream(OutputStream out) {
		super(out);
	}
	public LogPrintStream() {
		this(System.out);
	}
	
	public static void closeLog(){
		log= false;
	}
	
	
	public void print(String line){
			super.print(line);
			ConsolePanel.instance.ta.append(line+"\n");
			GUIUtil.setCaretPosition(ConsolePanel.instance.ta);
	
	}
	
	public void println(String line){
		
		if(!log){
			super.println(line);
			return;
		}
		try {
			throw new Exception();
		} catch (Exception e) {
			
			String msg = "(%s:%d) - %s";
			StackTraceElement s= e.getStackTrace()[1];
			String time=new SimpleDateFormat("HH:mm:ss").format(new Date());
			super.println(String.format(msg,s.getFileName(),s.getLineNumber(),line));
			
//			
//			super.println(time+" "+StringUtils.substringBetween(e.getStackTrace()[1].toString(), "(", ")")+"\t"+line);
		}
		ConsolePanel.instance.ta.append(line+"\r\n");
		GUIUtil.setCaretPosition(ConsolePanel.instance.ta);
		
		
		

		
		
	}
	
	

}

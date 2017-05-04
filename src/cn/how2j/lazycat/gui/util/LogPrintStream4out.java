package cn.how2j.lazycat.gui.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.how2j.lazycat.gui.panel.ConsolePanel;
import cn.how2j.lazycat.util.Where;

public class LogPrintStream4out extends PrintStream {

	private LogPrintStream4out(OutputStream out) {
		super(out);
	}

	public static void main(String[] args) {
		System.setOut(new LogPrintStream4out());
		System.setErr(new LogPrintStream4err());
		System.out.println("xxx");
	}
	
	
	public LogPrintStream4out() {
		this(System.out);
	}
	
	public void print(Object o){
		if(null==o)
			println("null");
		else
			print(o.toString());
	}
	public void println(Object o){
		if(null==o)
			println("null");
		else
			print(o.toString());
	}

	public void print(String line) {
//		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
//		line = time + "\t" + line;
//		super.print(line);
//		ConsolePanel.instance.ta.append(line + "\n");
//		GUIUtil.setCaretPosition(ConsolePanel.instance.ta);
		println(line);
	}

	public void println(String line) {

		try {
			throw new Exception();
		} catch (Exception e) {

			String msg = "%s: (%s:%d) - %s%n";
			StackTraceElement s = getTriggerTraceElement( e.getStackTrace());
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			line = String.format(msg,time, s.getFileName(), s.getLineNumber(), line);
			super.print(line);

			//
			// super.println(time+"
			// "+StringUtils.substringBetween(e.getStackTrace()[1].toString(),
			// "(", ")")+"\t"+line);
		}

		ConsolePanel.instance.ta.append(line + "\r\n");
		GUIUtil.setCaretPosition(ConsolePanel.instance.ta);

	}
	private StackTraceElement getTriggerTraceElement(StackTraceElement[] stes) {
		for (StackTraceElement s : stes) {
			if(!s.getClassName().equals(this.getClass().getName())){
				return s;
			}
		}
		return null;
		
	}
	
	
}

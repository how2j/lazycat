package cn.how2j.lazycat.gui.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.how2j.lazycat.gui.panel.ConsolePanel;

public class LogPrintStream4err extends PrintStream {

	File f = new File("error.txt");
	PrintStream fileWriter = null;

	private LogPrintStream4err(OutputStream out) {
		super(out);
	}

	public LogPrintStream4err() {
		this(System.err);

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(f);
			fileWriter = new PrintStream(fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void print(String line) {
		fileWriter.print(line);
		fileWriter.print("\r\n");
		fileWriter.flush();
		super.print(line);
		ConsolePanel.instance.ta.append(line + "\n");
		GUIUtil.setCaretPosition(ConsolePanel.instance.ta);

	}

	public void println(String line) {
		fileWriter.print(line);
		fileWriter.flush();
		try {
			throw new Exception();
		} catch (Exception e) {

			String msg = "(%s:%d) - %s";
			StackTraceElement s = e.getStackTrace()[1];
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			super.println(String.format(msg, s.getFileName(), s.getLineNumber(), line));

			//
			// super.println(time+"
			// "+StringUtils.substringBetween(e.getStackTrace()[1].toString(),
			// "(", ")")+"\t"+line);
		}

		ConsolePanel.instance.ta.append(line + "\r\n");
		GUIUtil.setCaretPosition(ConsolePanel.instance.ta);

	}

}

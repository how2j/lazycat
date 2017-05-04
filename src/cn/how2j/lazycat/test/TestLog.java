package cn.how2j.lazycat.test;

import cn.how2j.lazycat.gui.util.LogPrintStream4err;
import cn.how2j.lazycat.gui.util.LogPrintStream4out;
import cn.how2j.lazycat.starter.GUIStart;

public class TestLog {

	public static void main(String[] args) throws Exception {
		GUIStart.main(args);
//		System.setOut(new LogPrintStream4out());
//		System.setErr(new LogPrintStream4err());
		System.out.println("xxx");
		
	}
}

package cn.how2j.lazycat.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public abstract class HotKeyFor8080 implements HotkeyListener {

	public HotKeyFor8080() {
		initHotkey();
	}

	public abstract void onHotKey(int key);

	public void initHotkey() {
		// JIntellitype.getInstance().registerHotKey(1,JIntellitype.MOD_CONTROL,(int)'/'
		// );
		JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'K');
		JIntellitype.getInstance().addHotKeyListener(this);
	}

	public static void main(String[] args) throws InterruptedException {
		HotKeyFor8080 k = new HotKeyFor8080() {

			@Override
			public void onHotKey(int key) {
				// TODO Auto-generated method stub
//				
//				String command = "D:\\tomcat-8080\\bin\\restart.bat";
//				try {
//					Desktop.getDesktop().open(new File(command));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				try {
					String rarCommand = "D:\\tomcat-8080\\bin\\restart.bat";
					Runtime run = Runtime.getRuntime();
					Process pro = run.exec(rarCommand);
					pro.waitFor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				System.out.println(111);
//				WebAppPanel.instance.bStart.doClick();
			}
			Throwable t;
		};

	}

}

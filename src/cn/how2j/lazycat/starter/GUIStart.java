package cn.how2j.lazycat.starter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import cn.how2j.lazycat.exception.PortException;
import cn.how2j.lazycat.gui.dialog.InitDialog;
import cn.how2j.lazycat.gui.dialog.ProgressDialog;
import cn.how2j.lazycat.gui.frame.MainFrame;
import cn.how2j.lazycat.gui.panel.MainPanel;
import cn.how2j.lazycat.gui.panel.TomcatPanel;
import cn.how2j.lazycat.gui.panel.WebAppPanel;
import cn.how2j.lazycat.gui.util.GUIUtil;
import cn.how2j.lazycat.gui.util.LogPrintStream4err;
import cn.how2j.lazycat.gui.util.LogPrintStream4out;
import cn.how2j.lazycat.pojo.Tomcat;
import cn.how2j.lazycat.pojo.WebApp;
import cn.how2j.lazycat.util.CatalinaUtil;
import cn.how2j.lazycat.util.HotKey;
import cn.how2j.lazycat.util.MapperUtil;
import cn.how2j.lazycat.util.PortUtil;

public class GUIStart {
	static {

		GUIUtil.useLNF();
	}

	static int initTimeGap = 80;
	public static void showProgress(String msg) throws InterruptedException{
		InitDialog.instance.lInitText.setText(msg);
		Thread.sleep(initTimeGap);
	}
	public static void main(String[] args) throws Exception {
		
		//启动初始化界面
		try {
			PortUtil.usePort(PortUtil.application_port);
		} catch (PortException e1) {
			JOptionPane.showMessageDialog(null, "已经启动");
			return;
		}

		InitDialog.instance.setVisible(true);
		showProgress("开始初始化...");
		
		showProgress("重定向日志输出");
		
		System.setOut(new LogPrintStream4out());
		System.setErr(new LogPrintStream4err());
		
		showProgress("注册热键");
		
		registerHotkey();


		
		showProgress("初始化数据库");
		List<Tomcat> ts =MapperUtil.tomcatMapper.list();
		
		showProgress("初始化基本配置");
		CatalinaUtil.init();
		
		for (Tomcat t : ts) {
			
			//不要在这里启动tomcat,会有些奇怪的问题
//			showProgress("自动启动tomcat，端口号:" +t.getPort());
//			BootstrapManager.startTomcat(t);
//			Thread.sleep(initTimeGap);
		}
		
		showProgress("初始化成功");
		
		Thread.sleep(500);
		
		MainFrame.instance.repaint();
		
		InitDialog.instance.setVisible(false);
		

		try {

			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					MainFrame.instance.setVisible(true);
					MainPanel.instance.workingPanel.show(TomcatPanel.instance);
					// MainPanel.instance.workingPanel.show(WebAppPanel.instance);
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void registerHotkey() {
		// TODO Auto-generated method stub
		HotKey k = new HotKey() {

			@Override
			public void onHotKey(int key) {
				// TODO Auto-generated method stub
				
				
//				ContextConfig.java
				WebApp w= WebAppPanel.instance.getSelectedWebApp();
				if(null==w){
					JOptionPane.showMessageDialog(null, "当前没有选中任何Web应用");
					return;					
				}
					
				
				
				
				if(!w.getStatus().equals(WebApp.loaded)){
					JOptionPane.showMessageDialog(null, "只有启动成功的Web应用，才能使用该热键进行重新启动");
					return;
				}
					
				WebAppPanel.instance.bRestart.doClick();
				Tomcat t = w.getTomcat();
				String titleFormat = "重启进度： 端口(%d)下的的Web应用： %s";
				String title =String.format(titleFormat, t.getPort(),w.getName());
				ProgressDialog.instance.setTitle(title);
				ProgressDialog.instance.start();
			}
		};
	}
}
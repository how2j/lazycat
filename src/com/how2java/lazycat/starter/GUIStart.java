package com.how2java.lazycat.starter;

 
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.tomcat.PeriodicEventListener;

import com.how2java.lazycat.exception.PortException;
import com.how2java.lazycat.gui.frame.MainFrame;
import com.how2java.lazycat.gui.panel.MainPanel;
import com.how2java.lazycat.gui.panel.TomcatPanel;
import com.how2java.lazycat.gui.panel.WebAppPanel;
import com.how2java.lazycat.gui.util.GUIUtil;
import com.how2java.lazycat.util.CatalinaUtil;
import com.how2java.lazycat.util.HotKey;
import com.how2java.lazycat.util.MapperUtil;
import com.how2java.lazycat.util.PortUtil;

public class GUIStart {
	static{
		GUIUtil.useLNF();
	}

    public static void main(String[] args) throws Exception{
    	registerHotkey();
    	
//    	System.setOut(new LogPrintStream(System.out));
    	

//		FileOutputStream fos  = new FileOutputStream("error.log");  
//        PrintStream ps = new PrintStream(fos);  
//        System.setErr(ps);
//        System.setOut(ps);
        
//    	System.setErr(new LogPrintStream(System.err));
    	try {
			PortUtil.usePort(PortUtil.application_port);
		} catch (PortException e1) {
			JOptionPane.showMessageDialog(null, "已经启动");
			return;
		}
    	
    	MapperUtil.tomcatMapper.list();
    	CatalinaUtil.init();
    	

    	
    	try {
        	
			SwingUtilities.invokeAndWait(new Runnable() {
			    @Override
			    public void run() {
			        MainFrame.instance.setVisible(true);
			        MainPanel.instance.workingPanel.show(TomcatPanel.instance);
//			        MainPanel.instance.workingPanel.show(WebAppPanel.instance);
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
		HotKey k = new HotKey(){

			@Override
			public void onHotKey(int key) {
				// TODO Auto-generated method stub
				System.out.println(SwingUtilities.isEventDispatchThread());
				
				
				System.out.println("模仿重启");
				WebAppPanel.instance.bRestart.doClick();

			}
		};
	}
}
package com.how2java.lazycat.starter;

 
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.how2java.lazycat.exception.PortException;
import com.how2java.lazycat.gui.frame.MainFrame;
import com.how2java.lazycat.gui.panel.MainPanel;
import com.how2java.lazycat.gui.panel.TomcatPanel;
import com.how2java.lazycat.gui.util.GUIUtil;
import com.how2java.lazycat.gui.util.LogPrintStream;
import com.how2java.lazycat.util.CatalinaUtil;
import com.how2java.lazycat.util.MapperUtil;
import com.how2java.lazycat.util.PortUtil;

public class GUIStart {
	static{
		GUIUtil.useLNF();
	}

    public static void main(String[] args) throws Exception{
    	
    	System.setOut(new LogPrintStream(System.out));
    	System.setErr(new LogPrintStream(System.err));
    	try {
			PortUtil.usePort(PortUtil.application_port);
		} catch (PortException e1) {
			JOptionPane.showMessageDialog(null, "ÒÑ¾­Æô¶¯");
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
}
package com.how2java.lazycat.gui.listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.how2java.lazycat.gui.panel.ConsolePanel;
import com.how2java.lazycat.gui.panel.ErrorLogPanel;
import com.how2java.lazycat.gui.panel.MainPanel;
import com.how2java.lazycat.gui.panel.TomcatPanel;
import com.how2java.lazycat.gui.panel.WebAppPanel;
 
public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel p = MainPanel.instance;
        JButton b = (JButton) e.getSource();
        if (b == p.bTomcat)
            p.workingPanel.show(TomcatPanel.instance);
        if (b == p.bErrorLog)
        	p.workingPanel.show(ErrorLogPanel.instance);
        if (b == p.bConsole)
        	p.workingPanel.show(ConsolePanel.instance);
        if (b == p.bWebApp){
        	if(TomcatPanel.instance.ctm.cs.isEmpty()){
        		JOptionPane.showMessageDialog(p, "…–Œ¥≈‰÷√Tomcat");
        		return;
        	}
        	p.workingPanel.show(WebAppPanel.instance);
        }
        	
        	
    }
}
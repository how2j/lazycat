package com.how2java.lazycat.gui.panel;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.catalina.startup.Bootstrap;

import com.how2java.lazycat.gui.listener.TomcatListener;
import com.how2java.lazycat.gui.listener.TomcatMouseListener;
import com.how2java.lazycat.gui.renderer.TomcatTableStatusRender;
import com.how2java.lazycat.gui.util.ColorUtil;
import com.how2java.lazycat.gui.util.GUIUtil;
import com.how2java.lazycat.pojo.Tomcat;
 
public class ConsolePanel extends JPanel {
	
	
	

    public static ConsolePanel instance = new ConsolePanel();
 
  
    
    
    public JTextArea ta = new JTextArea();
    
    public JScrollPane sp = new JScrollPane(ta);
    
    public ConsolePanel() {
    	int gap=50;
		this.setLayout(new GridLayout(1, 2, gap, gap));
    	
//    	ta.setText("xxx");
    	ta.setLineWrap(true);
        this.add(sp);
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(ConsolePanel.instance);
//        TomcatPanel.instance.freezeButtons();
        
       

    }
 
   
    
 
}
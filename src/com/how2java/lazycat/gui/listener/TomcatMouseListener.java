package com.how2java.lazycat.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.how2java.lazycat.gui.panel.MainPanel;
import com.how2java.lazycat.gui.panel.TomcatPanel;
import com.how2java.lazycat.gui.panel.WebAppPanel;

public class TomcatMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		MainPanel p = MainPanel.instance;
		if(e.getClickCount()==2){
        	if(TomcatPanel.instance.ctm.cs.isEmpty()){
        		JOptionPane.showMessageDialog(p, "…–Œ¥≈‰÷√Tomcat");
        		return;
        	}
        	p.workingPanel.show(WebAppPanel.instance);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

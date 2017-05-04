package cn.how2j.lazycat.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import cn.how2j.lazycat.gui.panel.MainPanel;
import cn.how2j.lazycat.gui.panel.TomcatPanel;
import cn.how2j.lazycat.gui.panel.WebAppPanel;

public class TomcatMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		MainPanel p = MainPanel.instance;
		if (e.getClickCount() == 2) {
			if (TomcatPanel.instance.ctm.cs.isEmpty()) {
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

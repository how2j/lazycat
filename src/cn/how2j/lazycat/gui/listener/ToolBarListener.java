package cn.how2j.lazycat.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import cn.how2j.lazycat.gui.panel.AboutPanel;
import cn.how2j.lazycat.gui.panel.ConsolePanel;
import cn.how2j.lazycat.gui.panel.ErrorLogPanel;
import cn.how2j.lazycat.gui.panel.MainPanel;
import cn.how2j.lazycat.gui.panel.TomcatPanel;
import cn.how2j.lazycat.gui.panel.WebAppPanel;

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
		if (b == p.bAbout)
			p.workingPanel.show(AboutPanel.instance);
		if (b == p.bWebApp) {
			if (TomcatPanel.instance.ctm.cs.isEmpty()) {
				JOptionPane.showMessageDialog(p, "…–Œ¥≈‰÷√Tomcat");
				return;
			}
			p.workingPanel.show(WebAppPanel.instance);
		}

	}
}
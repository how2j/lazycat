package cn.how2j.lazycat.gui.panel;

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

import cn.how2j.lazycat.gui.listener.TomcatListener;
import cn.how2j.lazycat.gui.listener.TomcatMouseListener;
import cn.how2j.lazycat.gui.renderer.TomcatTableStatusRender;
import cn.how2j.lazycat.gui.util.ColorUtil;
import cn.how2j.lazycat.gui.util.GUIUtil;
import cn.how2j.lazycat.pojo.Tomcat;

public class ErrorLogPanel extends JPanel {

	public static ErrorLogPanel instance = new ErrorLogPanel();

	public JTextArea ta = new JTextArea();

	public JScrollPane sp = new JScrollPane(ta);

	public ErrorLogPanel() {
		int gap = 50;
		this.setLayout(new GridLayout(1, 2, gap, gap));

		// ta.setText("xxx");
		ta.setLineWrap(false);
		this.add(sp);
	}

	public static void main(String[] args) {
		GUIUtil.showPanel(ErrorLogPanel.instance);
		// TomcatPanel.instance.freezeButtons();

	}

}
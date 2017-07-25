package cn.how2j.lazycat.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import cn.how2j.lazycat.gui.listener.AboutListener;
import cn.how2j.lazycat.gui.util.GUIUtil;

public class AboutPanel extends WorkingPanel  {

	public static AboutPanel instance = new AboutPanel();

	public JLabel l = new JLabel("",SwingUtilities.CENTER);
	public JButton b = new JButton("点击查看git开源地址");
	

	public AboutPanel() {
		
		this.setLayout(new BorderLayout());

		l.setText("lazycat 由how2j.cn 制作开发，并且开源");
		
		this.add(l,BorderLayout.CENTER);
		b.setPreferredSize(new Dimension(80, 30));
		this.add(b,BorderLayout.SOUTH);
		
		addListener();
	}

	public static void main(String[] args) {
		GUIUtil.showPanel(AboutPanel.instance);
		// TomcatPanel.instance.freezeButtons();

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		b.addActionListener(new AboutListener());
	}

}
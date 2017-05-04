package cn.how2j.lazycat.gui.frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import cn.how2j.lazycat.gui.panel.MainPanel;

public class MainFrame extends JFrame {
	public static MainFrame instance = new MainFrame();

	private MainFrame() {
		this.setSize(800, 600);
		this.setTitle("Tomcat 多服务器管理器 - powered by how2j.cn");
		this.setContentPane(MainPanel.instance);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(MainFrame.instance, "要退出吗？"))
					System.exit(1);
				else
					MainFrame.this.setState(JFrame.ICONIFIED);
			}
		});

	}

	public static void main(String[] args) {
		instance.setVisible(true);
	}

}
package cn.how2j.lazycat.gui.dialog;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class InitDialog extends JDialog {

	public static InitDialog instance = new InitDialog();
	
	public JLabel lInitText = new JLabel("初始化中",SwingConstants.CENTER);
	
	private InitDialog(){
		this.setSize(300, 100);
		this.setTitle("Tomcat 多服务器管理器 初始化中");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.add(lInitText);
	}
	
	
	
	
	
	public static void main(String[] args) {
		instance.setVisible(true);
	}
	
}

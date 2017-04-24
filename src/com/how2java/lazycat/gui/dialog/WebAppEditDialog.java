package com.how2java.lazycat.gui.dialog;
 
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.how2java.lazycat.gui.frame.MainFrame;
import com.how2java.lazycat.gui.listener.WebAppEditListener;
import com.how2java.lazycat.gui.util.GUIUtil;
import com.how2java.lazycat.pojo.WebApp;
 
public class WebAppEditDialog extends JDialog {
	

    
    public static WebAppEditDialog instance = new WebAppEditDialog();
 
    public JButton bSubmit= new JButton("修改");
    
    public JLabel lName = new JLabel("名称");
    public JLabel lPath = new JLabel("硬盘路径");
    public JLabel lTestPage = new JLabel("启动地址");
    public JLabel lStartup = new JLabel("自动打开测试页");
    public JLabel lDeploy = new JLabel("自动部署");
    
    public JTextField tfName = new JTextField("");
    public JTextField tfPath = new JTextField("");
    public JTextField tfTestPage = new JTextField("");
    public JCheckBox cbStartup = new JCheckBox();
    public JCheckBox cbDeploy = new JCheckBox();
    
    

 
    public WebAppEditDialog() {
    	super(MainFrame.instance);
       
    	 this.setModal(true);
         int gap = 50;
         this.setLayout(null);
         
         this.setSize(600, 500);
         
         JPanel pInput = new JPanel();
         JPanel pSubmit = new JPanel();

         pInput.setLayout(new GridLayout(5, 2, gap, gap));
         pInput.add(lName);
         pInput.add(tfName);
         pInput.add(lPath);
         pInput.add(tfPath);
         pInput.add(lTestPage);
         pInput.add(tfTestPage);
         pInput.add(lStartup);
         pInput.add(cbStartup);
         
         pInput.add(lDeploy);
         pInput.add(cbDeploy);
         
         pSubmit.add(bSubmit);

         

         pInput.setBounds(50, 20, 400, 350);
         pSubmit.setBounds(50, 380, 400, 150);

         this.add(pInput);
         this.add(pSubmit);
         this.setLocationRelativeTo(MainFrame.instance);
         addListener();
    }
 
    public static void main(String[] args) {
        instance.setVisible(true);
    }
 

     
    public void setVisible(boolean show,WebApp w){
    	
    	tfName.setText(w.getName());
    	tfPath.setText(w.getPath());
    	tfTestPage.setText(w.getTestPage());
    	cbStartup.setSelected(w.isStartup());
    	cbDeploy.setSelected(w.isDeploy());
    	tfName.grabFocus();
    	super.setVisible(show);
    }
   
     
    public void addListener() {
    	WebAppEditListener listener = new WebAppEditListener();
        bSubmit.addActionListener(listener);
    }
 
}
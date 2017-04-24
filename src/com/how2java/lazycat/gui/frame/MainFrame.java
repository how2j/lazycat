package com.how2java.lazycat.gui.frame;
 
import javax.swing.JFrame;

import com.how2java.lazycat.gui.panel.MainPanel;
 
public class MainFrame extends JFrame{
    public static MainFrame instance = new MainFrame();
     
    private MainFrame(){
        this.setSize(800,600);
        this.setTitle("Tomcat 多服务器管理器");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     
    public static void main(String[] args) {
        instance.setVisible(true);
    }
     
}
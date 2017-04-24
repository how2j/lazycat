package com.how2java.lazycat.gui.panel;
 
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.how2java.lazycat.gui.listener.ToolBarListener;
import com.how2java.lazycat.gui.util.CenterPanel;
import com.how2java.lazycat.gui.util.GUIUtil;
 
public class MainPanel extends JPanel {

    public static MainPanel instance = new MainPanel();
    public JToolBar tb = new JToolBar();
    public JButton bTomcat = new JButton();
    public JButton bWebApp = new JButton();
    public JButton bConsole = new JButton();
    public JButton bErrorLog = new JButton();
 
    public CenterPanel workingPanel;
 
    private MainPanel() {
 
        GUIUtil.setImageIcon(bTomcat, "server.png", "服务器");
        GUIUtil.setImageIcon(bWebApp, "webapp.png", "WEB应用");
        GUIUtil.setImageIcon(bConsole, "console.png", "控制台输出");
        
        GUIUtil.setImageIcon(bErrorLog, "error.png", "启动失败日志");
        
 

        tb.setFloatable(false);
        tb.add(bTomcat);
        tb.add(bWebApp);
        tb.add(bConsole);
        tb.add(bErrorLog);
 
        workingPanel = new CenterPanel(0.8);
 
        setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);
         
        addListener();
    }
 
    private void addListener() {
        ToolBarListener listener = new ToolBarListener();
         
        bTomcat.addActionListener(listener);
        bWebApp.addActionListener(listener);
        bConsole.addActionListener(listener);
        bErrorLog.addActionListener(listener);
        
         
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
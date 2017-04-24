package com.how2java.lazycat.gui.util;
 
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
 
public class GUIUtil {
    private static String imageFolder = "e:/project/lazycat/img";
 
    public static void setCaretPosition(JTextArea ta){
    	ta.setCaretPosition(ta.getDocument().getLength());

    }
    
    public static void setImageIcon(JButton b, String fileName, String tip) {
    	File f =new File(imageFolder, fileName);
    	ImageIcon i=null;
    	
    	
    	if(f.exists()){
    		 i= new ImageIcon(f.getAbsolutePath());
    	}
    	else{
        	URL u =ClassLoader.getSystemResource("img/"+fileName);

        	System.out.println(u);
        	 i= new ImageIcon(u);
    	}
    	


        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }
 
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }
 
    /**
     * 
     * @param p
     * @param strechRate 拉伸比例1表示满屏幕
     */
    public static void showPanel(JPanel p,double strechRate) {
//        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }
     
    public static void showPanel(JPanel p) {
        showPanel(p,0.85);
    }   
 
    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            tf.grabFocus();
            return false;
        }
    }
 
    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input))
            return false;
        String text = tf.getText().trim();
 
        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }
 
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
 
    }
    public static boolean checkAlphaNumeric(JTextField tf, String input) {
    	String text = tf.getText().trim();
    	if(text.length()==0)
    		return true;
    	
    	if(!StringUtils.isAlphanumeric(text)){
    		JOptionPane.showMessageDialog(null, input + "只能是英文和数字组成，并且只能以应为开头");
    		tf.grabFocus();
    		return false;
    	}
    	if(text.length()!=0 && !StringUtils.isAlpha(String.valueOf( text. charAt(0)))){
    		JOptionPane.showMessageDialog(null, input + "只能是英文和数字组成，并且只能英文开头");
    		tf.grabFocus();
    		return false;
    	}
    	return true;
    	
    }
 
    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }
 
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public static void setEnable(boolean b, JButton... bs) {
		for (JButton button : bs) {
			button.setEnabled(b);
		}
		
	}
}
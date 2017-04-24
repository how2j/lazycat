package com.how2java.lazycat.gui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.catalina.startup.Bootstrap;

public class TomcatTableStatusRender extends DefaultTableCellRenderer {
	public void setValue(Object value) { // 重写setValue方法，从而可以动态设置列单元字体颜色
		
		switch (value.toString()) {
		case Bootstrap.status_started:
			setForeground(Color.decode("#006400"));
			break;
			
		case Bootstrap.status_stopped:
			setForeground(Color.darkGray);
			break;
		case Bootstrap.status_starting:
			setForeground(Color.decode("#CD853F"));
			break;
		case Bootstrap.status_stopping:
			setForeground(Color.decode("#CD853F"));
			break;
		default:
			setForeground(Color.decode("#F08080"));
			break;

		}
		Font f = new Font(Font.SANS_SERIF,Font.BOLD,12);
		setFont(f);
		setText(value.toString());

	}

	
}
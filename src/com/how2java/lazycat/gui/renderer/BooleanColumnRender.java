package com.how2java.lazycat.gui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.catalina.startup.Bootstrap;

public class BooleanColumnRender extends DefaultTableCellRenderer {
	public void setValue(Object value) { // 重写setValue方法，从而可以动态设置列单元字体颜色
		
		switch (value.toString()) {
		case "true":
			setForeground(Color.decode("#006400"));
			break;
			
		case "false":
			setForeground(Color.decode("#F08080"));
			break;


		}
		Font f = new Font(Font.SANS_SERIF,Font.BOLD,12);
		setFont(f);
		setText(value.toString());

	}

	
}
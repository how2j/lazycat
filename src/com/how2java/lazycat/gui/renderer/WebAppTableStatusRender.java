package com.how2java.lazycat.gui.renderer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;

import com.how2java.lazycat.pojo.WebApp;

public class WebAppTableStatusRender extends DefaultTableCellRenderer {
	public void setValue(Object value) { // 重写setValue方法，从而可以动态设置列单元字体颜色
		
		switch (value.toString()) {
		case WebApp.loaded:
			setForeground(Color.decode("#006400"));
			break;
			
		case WebApp.loading:
			setForeground(Color.decode("#CD853F"));
			break;
		case WebApp.failed:
			setForeground(Color.decode("#F08080"));
			break;
		default:
			setForeground(Color.darkGray);
			break;

		}
		Font f = new Font(Font.SANS_SERIF,Font.BOLD,12);
		setFont(f);
		setText(value.toString());

	}

	
}
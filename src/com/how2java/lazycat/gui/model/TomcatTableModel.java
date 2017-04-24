package com.how2java.lazycat.gui.model;

  
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.how2java.lazycat.mapper.TomcatMapper;
import com.how2java.lazycat.pojo.Tomcat;
import com.how2java.lazycat.util.MapperUtil;
import com.how2java.lazycat.util.PortUtil;
  
public class TomcatTableModel extends AbstractTableModel {
	
	TomcatMapper tomcatMapper = MapperUtil.tomcatMapper;
	
    String[] columnNames = new String[] { "编号", "Tomcat端口", "状态"};
  
    // 使用从Service返回的List作为TableModel的数据
  
    public List<Tomcat> cs = tomcatMapper.list();
  
     public int getRowCount() {
         
        return cs.size();
    }
  
    public int getColumnCount() {
         
        return columnNames.length;
    }
  
    public String getColumnName(int columnIndex) {
         
        return columnNames[columnIndex];
    }
  
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
  
    // 先通过cs.get(rowIndex)获取行对应的Category对象
    // 然后根据columnIndex返回对应的属性
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tomcat h = cs.get(rowIndex);
        if (0 == columnIndex)
            return h.getId();
        if (1 == columnIndex)
            return h.getPort();
        
        if (2 == columnIndex)
        	return h.getStatus();
        
        	
 
        return null;
    }
  
}
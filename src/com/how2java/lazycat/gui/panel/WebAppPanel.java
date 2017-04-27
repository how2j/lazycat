package com.how2java.lazycat.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.how2java.lazycat.gui.listener.WebAppListener;
import com.how2java.lazycat.gui.model.WebAppTableModel;
import com.how2java.lazycat.gui.renderer.BooleanColumnRender;
import com.how2java.lazycat.gui.renderer.WebAppTableStatusRender;
import com.how2java.lazycat.gui.util.ColorUtil;
import com.how2java.lazycat.gui.util.GUIUtil;
import com.how2java.lazycat.mapper.WebAppMapper;
import com.how2java.lazycat.pojo.WebApp;
import com.how2java.lazycat.util.MapperUtil;

public class WebAppPanel extends WorkingPanel implements Runnable {

	WebAppMapper webAppMapper = MapperUtil.webAppMapper;

	public static WebAppPanel instance = new WebAppPanel();

	public JButton bAdd = new JButton("ÐÂÔö");
	public JButton bEdit = new JButton("±à¼­");
	public JButton bDelete = new JButton("É¾³ý");
	public JButton bStart = new JButton("Æô¶¯");
	public JButton bStop = new JButton("Ð¶ÔØ");
	public JButton bRestart = new JButton("ÖØÆôctrl+alt+k");
	
	public JButton buttons[] = new JButton[]{bAdd,bEdit,bDelete,bStart,bStop,bRestart};

	public WebAppTableModel ctm = new WebAppTableModel();
	public JTable t = new JTable(ctm);

	TableColumn tableColumn = t.getColumn("×´Ì¬");
	public WebAppPanel() {
        tableColumn.setCellRenderer(new WebAppTableStatusRender());
        
        setBooleanColumnRender(t);

		
		GUIUtil.setColor(ColorUtil.blueColor, buttons);
		JScrollPane sp = new JScrollPane(t);
		JPanel pLife = new JPanel();
		
		
		JPanel pSubmit = new JPanel();
		pSubmit.add(bAdd);
		pSubmit.add(bEdit);
		pSubmit.add(bDelete);
		pLife.add(bStart);
		pLife.add(bStop);
		pLife.add(bRestart);
		
		bStart.setPreferredSize(new Dimension(60, 40));
		bStop.setPreferredSize(new Dimension(60, 40));
		bRestart.setPreferredSize(new Dimension(160, 40));

		this.setLayout(new BorderLayout());
		this.add(pLife, BorderLayout.NORTH);
		this.add(sp, BorderLayout.CENTER);
		this.add(pSubmit, BorderLayout.SOUTH);

		addListener();

		start();
	}

	private void setBooleanColumnRender(JTable t) {
		Enumeration e= t.getColumnModel().getColumns();
		while(e.hasMoreElements()){
			TableColumn c= (TableColumn) e.nextElement();
			if(c.getHeaderValue().toString().startsWith("ÊÇ·ñ")){
				c.setCellRenderer(new BooleanColumnRender());	
			}
		}
	}

	private void start() {
		new Thread(this).start();

	}

	public static void main(String[] args) {
		GUIUtil.showPanel(WebAppPanel.instance);
	}

	public WebApp getSelectedWebApp() {
		int index = t.getSelectedRow();
		if(-1==index)
			return null;
		return ctm.cs.get(index);
	}

	public void updateDataWithoutDB() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				t.updateUI();
				syncStatus();
				
			}
		});
	}
	
	public void syncStatus(){
		
		 if(0==ctm.cs.size()){
	        	GUIUtil.setEnable(false, buttons);
	            GUIUtil.setEnable(true, bAdd);
	        }
	        else{
	        	WebApp w = getSelectedWebApp();
	        	if(null==w)
	        		return;
	        	if(w.getStatus().equals(WebApp.loading)){
	        		GUIUtil.setEnable(false, buttons);
	        		
	        		
	        	}
	        	else if(w.getStatus().equals(WebApp.not_loaded)){
	        		GUIUtil.setEnable(true, buttons);
	        		
	        		GUIUtil.setEnable(false, bStop);
	        		GUIUtil.setEnable(false, bRestart);
	        		
	        	}    
	        	else if(w.getStatus().equals(WebApp.loaded)){
	        		GUIUtil.setEnable(true, buttons);
	        		GUIUtil.setEnable(false, bEdit);
	        		GUIUtil.setEnable(false, bStart);
	        		
	        	}
	        	else if(w.getStatus().equals(WebApp.failed)){
	        		GUIUtil.setEnable(true, buttons);
	        		GUIUtil.setEnable(false, bStop);
	        		GUIUtil.setEnable(false, bRestart);
	        		
	        	}
	        }
		
	}

	public void updateData() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ctm.cs = webAppMapper.listByTomcat(TomcatPanel.instance.getSelectedTomcat());
				t.updateUI();
				int index = t.getSelectedRow();
				System.out.println("index:"+index);
				if (-1 == index || index>ctm.cs.size() -1)
					t.getSelectionModel().setSelectionInterval(0, 0);

				syncStatus();
			}
		});
		
		
		
	}

	public void addListener() {
		WebAppListener listener = new WebAppListener();
		bAdd.addActionListener(listener);
		bEdit.addActionListener(listener);
		bDelete.addActionListener(listener);
		bStart.addActionListener(listener);
		bStop.addActionListener(listener);
		bRestart.addActionListener(listener);
		 t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					WebAppPanel.instance.syncStatus();
					
				}
			});
	        		
	}

	@Override
	public void run() {
		while (true) {
			try {
				updateDataWithoutDB();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

}
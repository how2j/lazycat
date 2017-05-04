package cn.how2j.lazycat.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import org.apache.catalina.startup.Bootstrap;

import cn.how2j.lazycat.gui.listener.TomcatListener;
import cn.how2j.lazycat.gui.listener.TomcatMouseListener;
import cn.how2j.lazycat.gui.model.TomcatTableModel;
import cn.how2j.lazycat.gui.renderer.TomcatTableStatusRender;
import cn.how2j.lazycat.gui.util.ColorUtil;
import cn.how2j.lazycat.gui.util.GUIUtil;
import cn.how2j.lazycat.mapper.TomcatMapper;
import cn.how2j.lazycat.pojo.Tomcat;
import cn.how2j.lazycat.util.MapperUtil;

public class TomcatPanel extends WorkingPanel {

	TomcatMapper tomcatMapper = MapperUtil.tomcatMapper;

	public static TomcatPanel instance = new TomcatPanel();

	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	public JButton bStart = new JButton("启动");
	public JButton bStop = new JButton("停止");

	public JButton buttons[] = new JButton[] { bAdd, bEdit, bDelete, bStart, bStop };

	String columNames[] = new String[] { "分类名称", "消费次数" };

	public TomcatTableModel ctm = new TomcatTableModel();
	public JTable t = new JTable(ctm);
	TableColumn tableColumn = t.getColumn("状态");

	public TomcatPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete, bStart, bStop);

		tableColumn.setCellRenderer(new TomcatTableStatusRender());

		JScrollPane sp = new JScrollPane(t);
		JPanel pLifecycle = new JPanel();
		JPanel pSubmit = new JPanel();
		pSubmit.add(bAdd);
		pSubmit.add(bEdit);
		pSubmit.add(bDelete);
		pLifecycle.add(bStart);
		pLifecycle.add(bStop);

		bStart.setPreferredSize(new Dimension(60, 40));
		bStop.setPreferredSize(new Dimension(60, 40));

		this.setLayout(new BorderLayout());
		this.add(pLifecycle, BorderLayout.NORTH);
		this.add(sp, BorderLayout.CENTER);
		this.add(pSubmit, BorderLayout.SOUTH);

		addListener();

		new Thread() {
			public void run() {
				for (JButton b : buttons) {

				}
			}
		}.start();
	}

	public static void main(String[] args) {
		GUIUtil.showPanel(TomcatPanel.instance);
		// TomcatPanel.instance.freezeButtons();

	}

	public Tomcat getSelectedTomcat() {
		int index = t.getSelectedRow();
		return ctm.cs.get(index);
	}

	public void updateDataWithoutDB() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				t.updateUI();
			}
		});
	}

	public void updateData() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ctm.cs = tomcatMapper.list();
				t.updateUI();
				int index = t.getSelectedRow();
				if (-1 == index || index >= ctm.cs.size() - 1)
					t.getSelectionModel().setSelectionInterval(0, 0);

				syncStatus();
			}
		});

	}

	public void syncStatus() {
		if (0 == ctm.cs.size()) {
			GUIUtil.setEnable(false, buttons);
			GUIUtil.setEnable(true, bAdd);
		} else {
			Tomcat t = getSelectedTomcat();
			if (t.getStatus().equals(Bootstrap.status_stopped)) {
				GUIUtil.setEnable(true, buttons);
				GUIUtil.setEnable(false, bStop);
			} else if (t.getStatus().equals(Bootstrap.status_started)) {
				GUIUtil.setEnable(false, buttons);
				GUIUtil.setEnable(true, bStop);
				GUIUtil.setEnable(true, bAdd);
			} else {
				GUIUtil.setEnable(true, buttons);
				GUIUtil.setEnable(false, bStop);

			}
		}
	}

	public void addListener() {
		TomcatListener listener = new TomcatListener();
		bAdd.addActionListener(listener);
		bEdit.addActionListener(listener);
		bDelete.addActionListener(listener);
		bStart.addActionListener(listener);
		bStop.addActionListener(listener);

		t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				TomcatPanel.instance.syncStatus();

			}
		});

		t.addMouseListener(new TomcatMouseListener());
	}

	public void freeze() {
		for (JButton b : buttons) {

			b.setEnabled(false);

		}

		MainPanel.instance.bTomcat.setEnabled(false);
		MainPanel.instance.bWebApp.setEnabled(false);
		// MainPanel.instance.bConsole.setEnabled(false);
		// MainPanel.instance.bErrorLog.setEnabled(false);
		t.setEnabled(false);

	}

	public void unfreeze() {
		for (JButton b : buttons) {

			b.setEnabled(true);
		}
		MainPanel.instance.bTomcat.setEnabled(true);
		MainPanel.instance.bWebApp.setEnabled(true);
		MainPanel.instance.bConsole.setEnabled(true);
		MainPanel.instance.bErrorLog.setEnabled(true);
		t.setEnabled(true);
	}

}
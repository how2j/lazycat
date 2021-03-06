package cn.how2j.lazycat.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.catalina.startup.Bootstrap;

import cn.how2j.lazycat.gui.dialog.ProgressDialog;
import cn.how2j.lazycat.gui.dialog.WebAppAddDialog;
import cn.how2j.lazycat.gui.dialog.WebAppEditDialog;
import cn.how2j.lazycat.gui.panel.WebAppPanel;
import cn.how2j.lazycat.pojo.WebApp;
import cn.how2j.lazycat.util.BootstrapManager;
import cn.how2j.lazycat.util.MapperUtil;

public class WebAppListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		WebAppPanel p = WebAppPanel.instance;
		JButton b = (JButton) e.getSource();
		System.out.println(b.isEnabled());
		if (!b.isEnabled())
			return;

		if (b == p.bAdd) {

			WebAppAddDialog.instance.setVisible(true);

		}
		if (b == p.bEdit) {

			WebAppEditDialog.instance.setVisible(true, p.getSelectedWebApp());

		}

		if (b == p.bDelete) {
			WebApp t = p.getSelectedWebApp();
			if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
				return;
			t.getXMLFile().delete();
			t.stopConext();
			// BootstrapManager.getBoostrap(t.getTomcat().getPort()).undeployContexts();

			MapperUtil.webAppMapper.delete(t.getId());
		}

		if (b == p.bStop) {
			WebApp w = p.getSelectedWebApp();
			// if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p,
			// "确认要卸载？"))
			// return;

			w.stopConext();
			// BootstrapManager.getBoostrap(w.getTomcat().getPort()).undeployContexts();
			w.getXMLFile().delete();

		}

		if (b == p.bStart) {

			WebApp w = p.getSelectedWebApp();
			if (w.getTomcat().getStatus().equals(Bootstrap.status_stopped)) {

				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(p, "对应的Tomcat尚未启动，要启动Tomcat吗？")) {

					SwingWorker worker = new SwingWorker() {
						protected Object doInBackground() throws Exception {
							// 长耗时任务
							w.createXML();
							WebAppPanel.instance.updateDataWithoutDB();
							BootstrapManager.startTomcat(w.getTomcat());
							return null;
						}
					};
					worker.execute();

				} else {
					return;
				}

			} else {
				w.stopConext();
				w.getXMLFile().delete();
				w.createXML();
				w.startContext();
			}
			WebAppPanel.instance.updateDataWithoutDB();
		}

		if (b == p.bRestart) {
			WebApp w = p.getSelectedWebApp();
			if (null == w)
				return;
			w.stopConext();
			int port = w.getTomcat().getPort();
			Bootstrap b2 = BootstrapManager.getBoostrap(port);

			w.getXMLFile().delete();
			WebAppPanel.instance.updateDataWithoutDB();
			w.createXML();
			w.startContext();
			
		}
		p.updateData();
	}

}

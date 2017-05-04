package cn.how2j.lazycat.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import cn.how2j.lazycat.gui.dialog.WebAppAddDialog;
import cn.how2j.lazycat.gui.dialog.WebAppEditDialog;
import cn.how2j.lazycat.gui.frame.MainFrame;
import cn.how2j.lazycat.gui.panel.TomcatPanel;
import cn.how2j.lazycat.gui.panel.WebAppPanel;
import cn.how2j.lazycat.gui.util.GUIUtil;
import cn.how2j.lazycat.pojo.WebApp;
import cn.how2j.lazycat.util.MapperUtil;

public class WebAppEditListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		WebAppEditDialog d = WebAppEditDialog.instance;

		if (!GUIUtil.checkAlphaNumeric(d.tfName, "名称"))
			return;
		if (!GUIUtil.checkEmpty(d.tfPath, "硬盘路径"))
			return;

		String name = d.tfName.getText();
		String path = d.tfPath.getText();
		String testPage = d.tfTestPage.getText();
		boolean startup = d.cbStartup.isSelected();
		boolean deploy = d.cbDeploy.isSelected();

		WebApp w = WebAppPanel.instance.getSelectedWebApp();
		String oldName = w.getName();
		w.setName(name);
		w.setPath(path);
		w.setTestPage(testPage);
		w.setStartup(startup);
		w.setDeploy(deploy);
		Map m = new HashMap();

		m.put("name", name);
		m.put("tomcat", TomcatPanel.instance.getSelectedTomcat());

		if ("ROOT".equalsIgnoreCase(w.getName().trim())) {
			JOptionPane.showMessageDialog(MainFrame.instance, "不能使用ROOT作为名称");
			return;
		}

		WebApp wa = MapperUtil.webAppMapper.getByTomcatAndName(m);
		if (!name.equals(oldName) && null != wa) {
			JOptionPane.showMessageDialog(MainFrame.instance, "名称已经被使用");
			WebAppAddDialog.instance.tfName.grabFocus();
			return;
		} else {
			List<WebApp> ws = MapperUtil.webAppMapper.listByTomcat(TomcatPanel.instance.getSelectedTomcat());
			if (w.isStartup()) {
				for (WebApp bean : ws) {
					if (bean.getId() == w.getId())
						continue;
					bean.setStartup(false);
					MapperUtil.webAppMapper.update(bean);
				}
			}

			MapperUtil.webAppMapper.update(w);
			WebAppPanel.instance.updateData();
		}

		d.setVisible(false);

	}

}

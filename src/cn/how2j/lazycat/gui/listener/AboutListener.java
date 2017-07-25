package cn.how2j.lazycat.gui.listener;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			Desktop.getDesktop().browse(new URI("http://how2j.cn/k/opensource/opensource-lazycat/1154.html#step4610"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}

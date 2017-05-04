package cn.how2j.lazycat.util;

import com.alibaba.druid.sql.dialect.postgresql.ast.PGOrderBy;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import cn.how2j.lazycat.gui.dialog.ProgressDialog;
import cn.how2j.lazycat.gui.panel.WebAppPanel;

public abstract class HotKey implements HotkeyListener {

	public HotKey() {
		initHotkey();
	}

	public abstract void onHotKey(int key);

	public void initHotkey() {
		// JIntellitype.getInstance().registerHotKey(1,JIntellitype.MOD_CONTROL,(int)'/'
		// );
		JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'K');
		JIntellitype.getInstance().addHotKeyListener(this);
	}

	public static void main(String[] args) throws InterruptedException {
		HotKey k = new HotKey() {

			@Override
			public void onHotKey(int key) {
				// TODO Auto-generated method stub

				
//				WebAppPanel.instance.bStart.doClick();
			}
		};

	}

}

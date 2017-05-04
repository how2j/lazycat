package cn.how2j.lazycat.starter;

import cn.how2j.lazycat.util.BootstrapManager;

public class Starter {

	public static void main(String[] args) throws Exception {
		int port = 90;

		BootstrapManager.startBootstrap(port);

	}
}

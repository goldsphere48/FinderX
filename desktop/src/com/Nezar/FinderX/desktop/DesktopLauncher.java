package com.Nezar.FinderX.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Nezar.FinderX.Main;
import com.Nezar.FinderX.MainApplicationlistener;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "FinderX";
	    config.width = 768/2;
	    config.height = 1280/2;
	    config.vSyncEnabled = true;
	    if(config.width < 1080)
	    	config.samples = 9;
		new LwjglApplication(new Main(), config);
	}
}

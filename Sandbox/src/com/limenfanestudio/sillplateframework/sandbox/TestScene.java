package com.limenfanestudio.sillplateframework.sandbox;

import com.limenfanestudio.sillplateframework.application.Application;
import com.limenfanestudio.sillplateframework.application.Scene;
import com.limenfanestudio.sillplateframework.data.Vector2D;
import com.limenfanestudio.sillplateframework.logging.LogLevel;
import com.limenfanestudio.sillplateframework.logging.LogSource;

public class TestScene extends Scene {
	
	private LogSource logSource = new LogSource("Test Scene");
	
	private long startTime = 0L;
	private boolean[] flags = null;

	@Override
	public void enter(Scene lastScene) {
		Application.Log.write(logSource, LogLevel.Information, "Entering test ",
				"scene");
		startTime = System.currentTimeMillis();
		flags = new boolean[64];
		for (int i = 0; i < flags.length; i++) {
			flags[i] = false;
		}
	}
	@Override
	public boolean processInput() {
		if (System.currentTimeMillis() - startTime > 2000L && !flags[0]) {
			flags[0] = true;
			Application.Window.setTitle("Test");
			Application.Log.write(logSource, LogLevel.Information, "Flag 1");
		}
		if (System.currentTimeMillis() - startTime > 4000L && !flags[1]) {
			flags[1] = true;
			Application.Window.setTitle("Test 2");
			Application.Log.write(logSource, LogLevel.Information, "Flag 2");
		}
		if (System.currentTimeMillis() - startTime > 6000L && !flags[2]) {
			flags[2] = true;
			Application.Window.setDimensions(new Vector2D(500.0d, 500.0d));
			Application.Log.write(logSource, LogLevel.Information, "Flag 3");
		}
		if (System.currentTimeMillis() - startTime > 8000L && !flags[3]) {
			flags[3] = true;
			Application.Window.setFullscreen(true);
			Application.Log.write(logSource, LogLevel.Information, "Flag 4");
		}
		if (System.currentTimeMillis() - startTime > 10000L && !flags[4]) {
			flags[4] = true;
			Application.Window.setDimensions(new Vector2D(600.0d, 300.0d));
			Application.Log.write(logSource, LogLevel.Information, "Flag 5");
		}
		if (System.currentTimeMillis() - startTime > 12000L && !flags[5]) {
			flags[5] = true;
			Application.Window.setFullscreen(false);
			Application.Log.write(logSource, LogLevel.Information, "Flag 6");
		}
		if (System.currentTimeMillis() - startTime > 14000L && !flags[6]) {
			flags[6] = true;
			Application.Log.write(logSource, LogLevel.Information,
					Application.Window.getMonitors());
			Application.Log.write(logSource, LogLevel.Information, "Flag 7");
		}
		if (System.currentTimeMillis() - startTime > 16000L && !flags[7]) {
			flags[7] = true;
			Application.Window.setMonitorIndex(1);
			Application.Log.write(logSource, LogLevel.Information, "Flag 8");
		}
		if (System.currentTimeMillis() - startTime > 18000L && !flags[8]) {
			flags[8] = true;
			Application.Window.setFullscreen(true);
			Application.Log.write(logSource, LogLevel.Information, "Flag 9");
		}
		if (System.currentTimeMillis() - startTime > 20000L && !flags[9]) {
			flags[9] = true;
			Application.Window.setMonitorIndex(2);
			Application.Log.write(logSource, LogLevel.Information, "Flag 10");
		}
		if (System.currentTimeMillis() - startTime > 22000L && !flags[10]) {
			flags[10] = true;
			Application.Window.setFullscreen(false);
			Application.Log.write(logSource, LogLevel.Information, "Flag 11");
		}
		if (System.currentTimeMillis() - startTime > 24000L && !flags[11]) {
			flags[11] = true;
			Application.Window.setFullscreen(true);
			Application.Log.write(logSource, LogLevel.Information, "Flag 12");
		}
		return true;
	}
	@Override
	public void leave(Scene nextScene) {
		Application.Log.write(logSource, LogLevel.Information, "Leaving test ",
				"scene");
	}

}

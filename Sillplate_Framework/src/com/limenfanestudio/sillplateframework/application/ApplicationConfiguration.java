/*
 * File:		ApplicationConfiguration.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.26
 * Purpose:		Defines a configuration data structure for Sillplate Framework
 * 				applications with default settings
 */

package com.limenfanestudio.sillplateframework.application;

import java.util.Arrays;
import java.util.List;

import com.limenfanestudio.sillplateframework.data.Vector2D;

// Configuration structure with pre-set defaults for a Sillplate Framework
// application's components
public class ApplicationConfiguration {
	
	// Configuration structure with pre-set defaults for the Sillplate
	// Framework's logging system
	public class LogConfiguration {
		
		// Whether console output should be enabled for the logging system
		public boolean consoleEnabled = false;
		// The timestamp for the logging system to use
		public String timestampFormat = "";
		// The file names / paths of the files to output logs to
		public List<String> fileNames = null;
		
		/*
		 * Construct a logging system configuration structure with defaults
		 */
		public LogConfiguration() {
			consoleEnabled = true;
			timestampFormat = "yyyy.MM.dd@HH:mm:ss.SSS";
			fileNames = Arrays.asList("Sillplate_Framework.log");
		}
		
	}
	
	// Configuration structure with pre-set defaults for the Sillplate
	// Framework's windowing system
	public class WindowConfiguration {
		
		// The title for the window
		public String title = "";
		// The dimensions of the window in pixels
		public Vector2D dimensions = null;
		// Whether the window should appear in fullscreen mode
		public boolean fullscreen = false;
		// The index of the monitor for the window to appear on when in
		// fullscreen mode
		public int monitorIndex = 0;
		
		/*
		 * Construct a windowing system configuration structure with defaults
		 */
		public WindowConfiguration() {
			title = "Sillplate Framework";
			dimensions = new Vector2D(960.0d, 540.0d);
			fullscreen = false;
			monitorIndex = 0;
		}
		
	}
	
	// Logging system configuration structure
	public LogConfiguration log = null;
	// Windowing system configuration structure
	public WindowConfiguration window = null;
	// The initial scene for the application
	public Scene initialScene = null;
	
	/*
	 * Construct an application configuration structure with defaults
	 */
	public ApplicationConfiguration(Scene initialScene) {
		log = new LogConfiguration();
		window = new WindowConfiguration();
		this.initialScene = initialScene;
	}

}

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
		 * Initialize a logging system configuration structure with defaults
		 */
		public LogConfiguration() {
			consoleEnabled = true;
			timestampFormat = "yyyy.MM.dd@HH:mm:ss.SSS";
			fileNames = Arrays.asList("Sillplate_Framework.log");
		}
		
	}
	
	// Logging system configuration structure
	public LogConfiguration log = null;
	
	/*
	 * Initialize an application configuration structure with defaults
	 */
	public ApplicationConfiguration() {
		log = new LogConfiguration();
	}

}

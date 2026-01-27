/*
 * File:		Application.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.26
 * Purpose:		The main class of the Sillplate Framework, provides access to
 * 				all Framework features
 */

package com.limenfanestudio.sillplateframework.application;

import com.limenfanestudio.sillplateframework.logging.LogLevel;
import com.limenfanestudio.sillplateframework.logging.LogManager;
import com.limenfanestudio.sillplateframework.logging.LogSource;

// The main class of the Sillplate Framework - a general application giving
// static access to all Framework components
public class Application {
	
	// The Sillplate Framework's logging system
	public static LogManager Log = null;
	
	/*
	 * Initialize the Sillplate Framework application
	 * Parameter: ApplicationConfiguration configuration - The configuration
	 * settings for the application's components
	 * Returns: boolean - Whether the application was initialized successfully
	 */
	public static boolean Initialize(ApplicationConfiguration configuration) {
		Log = new LogManager();
		if (!Log.initialize(configuration.log)) {
			return false;
		}
		Log.write(LogSource.Application, LogLevel.Information, "Initialized ",
				"log manager");
		
		Log.write(LogSource.Application, LogLevel.Information, "Initialized ",
				"Sillplate Framework application");
		return true;
	}
	/*
	 * Free the Sillplate Framework application's memory
	 * Returns: boolean - Whether the application was destroyed successfully
	 */
	public static boolean Destroy() {
		Log.write(LogSource.Application, LogLevel.Information, "Destroying ",
				"Sillplate Framework application");
		boolean success = true;
		Log.write(LogSource.Application, LogLevel.Information, "Destroying ",
				"log manager");
		if (!Log.destroy()) {
			success = false;
		}
		return success;
	}

}

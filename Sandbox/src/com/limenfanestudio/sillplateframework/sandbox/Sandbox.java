/*
 * File:		Sandbox.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.21
 * Purpose:		The main class of the Sandbox project for testing the Sillplate
 * 				Framework
 */

package com.limenfanestudio.sillplateframework.sandbox;

import com.limenfanestudio.sillplateframework.application.Application;
import com.limenfanestudio.sillplateframework.application
		.ApplicationConfiguration;
import com.limenfanestudio.sillplateframework.logging.LogLevel;

public class Sandbox {
	
	public static void main(String[] args) {
		System.out.println("Starting Sandbox");
		ApplicationConfiguration configuration = new ApplicationConfiguration();
		System.out.println("Created configuration");
		if (!Application.Initialize(configuration)) {
			System.out.println("Failed to initialize application");
			return;
		}
		Application.Log.write("Test 1");
		Application.Log.setConsoleEnabled(false);
		Application.Log.write("Test 2");
		Application.Log.setConsoleEnabled(true);
		if (!Application.Log.setTimestampFormat("bad timestamp format")) {
			Application.Log.write(LogLevel.Warning, "Failed to set timestamp ",
					"format");
		}
		Application.Log.setTimestampFormat("yyyy");
		Application.Log.write("Test 3");
		if (!Application.Log.addFileName("Sillplate_Framework.log")) {
			Application.Log.write(LogLevel.Warning, "Failed to add new file");
		}
		Application.Log.addFileName("TestLog.log");
		Application.Log.write("Test 4");
		if (!Application.Log.removeFileName("Non existant file.log")) {
			Application.Log.write(LogLevel.Warning, "Failed to remove old ",
					"file");
		}
		Application.Log.removeFileName("Sillplate_Framework.log");
		Application.Log.write("Test 5");
		System.out.println("Stopping Sandbox");
		if (!Application.Destroy()) {
			System.out.println("Failed to destroy application");
		}
	}
	
}

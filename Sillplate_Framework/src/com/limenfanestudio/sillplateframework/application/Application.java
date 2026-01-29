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
import com.limenfanestudio.sillplateframework.windowing.WindowManager;

// The main class of the Sillplate Framework - a general application giving
// static access to all Framework components
public class Application {
	
	// The Sillplate Framework's logging system
	public static LogManager Log = null;
	// The Sillplate Framework's windowing system
	public static WindowManager Window = null;
	// The current scene displayed by the Sillplate Framework application
	private static Scene CurrentScene = null;
	
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
		Window = new WindowManager();
		if (!Window.initialize(configuration.window)) {
			Log.write(LogSource.Application, LogLevel.Error, "Failed to ",
					"initialize window manager");
			return false;
		}
		Log.write(LogSource.Application, LogLevel.Information, "Initialized ",
				"window manager");
		if (configuration.initialScene == null) {
			Log.write(LogSource.Application, LogLevel.Information, "No ",
					"initial scene provided");
			return false;
		}
		SetCurrentScene(configuration.initialScene);
		Log.write(LogSource.Application, LogLevel.Information, "Set initial ",
				"scene");
		Log.write(LogSource.Application, LogLevel.Information, "Initialized ",
				"Sillplate Framework application");
		return true;
	}
	/*
	 * Run the main loop of the Sillplate Framework application
	 */
	public static void Run() {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < 25000L
				&& !Window.isClosing()) {
			if (!CurrentScene.processInput()) {
				return;
			}
		}
	}
	/*
	 * Free the Sillplate Framework application's memory
	 * Returns: boolean - Whether the application was destroyed successfully
	 */
	public static boolean Destroy() {
		Log.write(LogSource.Application, LogLevel.Information, "Destroying ",
				"Sillplate Framework application");
		boolean success = true;
		Log.write(LogSource.Application, LogLevel.Information, "Leaving final ",
				"scene");
		SetCurrentScene(null);
		Log.write(LogSource.Application, LogLevel.Information, "Destroying ",
				"window manager");
		Window.destroy();
		Log.write(LogSource.Application, LogLevel.Information, "Destroying ",
				"log manager");
		if (!Log.destroy()) {
			success = false;
		}
		return success;
	}
	
	/*
	 * Get the current scene displayed by the Sillplate Framework
	 * Returns: Scene - The current scene
	 */
	public static Scene GetCurrentScene() {
		return CurrentScene;
	}
	/*
	 * Set the current scene to be displayed by the Sillplate Framework
	 * application
	 * Parameter: Scene scene - The new scene to display
	 */
	public static void SetCurrentScene(Scene scene) {
		if (CurrentScene != null) {
			CurrentScene.leave(scene);
		}
		if (scene != null) {
			scene.enter(CurrentScene);
		}
		CurrentScene = scene;
	}

}

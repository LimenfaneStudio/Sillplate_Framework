/*
 * File:		WindowManager.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.27
 * Purpose:		Manages a single window's size/resolution, title, fullscreen
 * 				mode and monitor for the Sillplate Framework application
 */

package com.limenfanestudio.sillplateframework.windowing;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import com.limenfanestudio.sillplateframework.application.Application;
import com.limenfanestudio.sillplateframework.application
		.ApplicationConfiguration;
import com.limenfanestudio.sillplateframework.data.Vector2D;
import com.limenfanestudio.sillplateframework.logging.LogLevel;
import com.limenfanestudio.sillplateframework.logging.LogSource;

// The main class of the Sillplate Framework's windowing system
public class WindowManager {
	
	// Java AWT window handle for this window manager
	private Frame frame = null;
	// Canvas for displaying graphics in the window handle
	private Canvas canvas = null;
	// Whether the window has requested to close
	private boolean closing = false;
	// The current title of the window
	private String title = "";
	// The dimensions of the window when in windowed mode
	private Vector2D dimensions = null;
	// Whether the window is currently in fullscreen mode
	private boolean fullscreen = false;
	// The index of the monitor the window appears on when in fullscreen mode
	private int monitorIndex = 0;
	
	/*
	 * Open the Sillplate Framework application's window and set its parameters
	 * Parameter: ApplicationConfiguration.WindowConfiguration configuration -
	 * The parameters used to open the application's window
	 * Returns: boolean - Whether the window could be opened and all parameters
	 * are valid
	 */
	public boolean initialize(
			ApplicationConfiguration.WindowConfiguration configuration) {
		canvas = new Canvas();
		createFrame();
		setTitle(configuration.title);
		setDimensions(configuration.dimensions);
		setMonitorIndex(configuration.monitorIndex);
		setFullscreen(configuration.fullscreen);
		Application.Log.write(LogSource.Window, LogLevel.Information, "Opened ",
				"window");
		return true;
	}
	/*
	 * Close the Sillplate Framework application's window and free its memory
	 */
	public void destroy() {
		frame.dispose();
		frame = null;
		canvas = null;
		closing = false;
		fullscreen = false;
		monitorIndex = 0;
	}
	/*
	 * Create a new AWT frame, add the canvas to it, and set basic parameters
	 */
	private void createFrame() {
		frame = new Frame();
		setTitle(title);
		frame.setResizable(false);
		frame.add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closing = true;
			}
		});
	}
	
	/*
	 * Test whether the window has requested to close
	 * Returns: boolean - Whether the window has requested to close
	 */
	public boolean isClosing() {
		return closing;
	}
	/*
	 * Get the current title of the window
	 * Returns: String - The title of the window
	 */
	public String getTitle() {
		return frame.getTitle();
	}
	/*
	 * Set the title of the window
	 * Parameter: String title - The new title for the window
	 */
	public void setTitle(String title) {
		frame.setTitle(title);
		this.title = title;
		Application.Log.write(LogSource.Window, LogLevel.Information, "Set ",
				"window title \"", title, "\"");
	}
	/*
	 * Get the current dimensions of the window in pixels
	 * Returns: Vector2D - The dimensions of the window
	 */
	public Vector2D getDimensions() {
		return new Vector2D((double)canvas.getWidth(),
				(double)canvas.getHeight());
	}
	/*
	 * Set the dimensions of the window for when in windowed mode
	 * Parameter: Vector2D dimensions - The dimensions for the window in pixels
	 */
	public void setDimensions(Vector2D dimensions) {
		this.dimensions = dimensions;
		Application.Log.write(LogSource.Window, LogLevel.Information, "Set ",
				"window dimensions (", dimensions.x, ", ", dimensions.y, ")");
		if (fullscreen) {
			Application.Log.write(LogSource.Window, LogLevel.Warning, "Window ",
					"is currently in fullscreen mode");
			return;
		}
		canvas.setPreferredSize(new Dimension((int)dimensions.x,
				(int)dimensions.y));
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	/*
	 * Test whether the window is currently in fullscreen mode
	 * Returns: boolean - Whether the window is fullscreen
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
	/*
	 * Set whether the window should appear in fullscreen mode
	 * Parameter: boolean fullscreen - Whether the window should appear in
	 * fullscreen mode
	 */
	public void setFullscreen(boolean fullscreen) {
		Vector2D windowedDimensions = getDimensions();
		if (this.fullscreen != fullscreen) {
			frame.dispose();
			createFrame();
		} else {
			return;
		}
		if (fullscreen) {
			GraphicsDevice device = GraphicsEnvironment
					.getLocalGraphicsEnvironment()
					.getScreenDevices()[monitorIndex];
			int w = device.getDisplayMode().getWidth();
			int h = device.getDisplayMode().getHeight();
			frame.setUndecorated(true);
			setDimensions(new Vector2D((double)w, (double)h));
			dimensions = windowedDimensions;
			frame.setLocation(device.getDefaultConfiguration().getBounds().x,
					device.getDefaultConfiguration().getBounds().y);
			this.fullscreen = true;
			Application.Log.write(LogSource.Window, LogLevel.Information,
					"Set window to fullscreen mode");
		} else {
			this.fullscreen = false;
			setDimensions(dimensions);
			Application.Log.write(LogSource.Window, LogLevel.Information,
					"Set window to windowed mode");
		}
		frame.setVisible(true);
	}
	/*
	 * Get the set of available monitors for the window, their index numbers and
	 * descriptions
	 * Returns: Map<Integer, String> - The set of available monitors' indices
	 * mapped to description strings
	 */
	public Map<Integer, String> getMonitors() {
		Map<Integer, String> monitors = new HashMap<Integer, String>();
		GraphicsDevice[] devices = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getScreenDevices();
		for (int i = 0; i < devices.length; i++) {
			monitors.put(i, devices[i].getIDstring());
		}
		return monitors;
	}
	/*
	 * Get the index of the current monitor the window will appear on when in
	 * fullscreen mode
	 * Returns: int - The current monitor index
	 */
	public int getMonitorIndex() {
		return monitorIndex;
	}
	/*
	 * Set the index of the monitor the window will appear on when in fullscreen
	 * mode
	 * Parameter: int monitorIndex - The monitor index
	 * Returns: boolean - Whether the given monitor index is valid
	 */
	public boolean setMonitorIndex(int monitorIndex) {
		if (!getMonitors().keySet().contains(monitorIndex)) {
			Application.Log.write(LogSource.Window, LogLevel.Warning,
					"Invalid monitor index ", monitorIndex);
			return false;
		}
		this.monitorIndex = monitorIndex;
		if (fullscreen) {
			setFullscreen(false);
			setFullscreen(true);
		}
		Application.Log.write(LogSource.Window, LogLevel.Information, "Set ",
				"fullscreen monitor index ", monitorIndex);
		return true;
	}

}

/*
 * File:		LogLevel.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.26
 * Purpose:		Defines a type for level/severity flags for log messages
 */

package com.limenfanestudio.sillplateframework.logging;

// Defines a type for log level flags and static levels for the Sillplate
// Framework - wrapper for a string
public class LogLevel {
	
	// Information log level
	public static LogLevel Information = new LogLevel("Info");
	// Warning log level
	public static LogLevel Warning = new LogLevel("Warning");
	// Error log level
	public static LogLevel Error = new LogLevel("Error");
	
	// The name of this log level flag
	protected String name = "";
	
	/*
	 * Initializes a log level flag with a name
	 * Parameter: String name - The name of this log level flag
	 */
	public LogLevel(String name) {
		this.name = name;
	}
	
	/*
	 * Get the name of this log level flag
	 * Returns: String - The name of this log level flag
	 */
	public String getName() {
		return name;
	}

}

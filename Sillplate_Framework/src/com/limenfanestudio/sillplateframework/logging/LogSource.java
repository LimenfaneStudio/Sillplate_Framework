/*
 * File:		LogSource.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.26
 * Purpose:		Defines a type for source flags for log messages
 */

package com.limenfanestudio.sillplateframework.logging;

// Defines a type for a log source flags and static sources for the Sillplate
// Framework's components - wrapper for a string
public class LogSource {
	
	// General log source
	public static LogSource General = new LogSource("General");
	// Application log source
	public static LogSource Application = new LogSource("Application");
	// Logging system log source
	public static LogSource Log = new LogSource("Log Manager");
	
	// The name of this log source flag
	protected String name = "";
	
	/*
	 * Initializes a log source flag with a name
	 * Parameter: String name - The name of this log source flag
	 */
	public  LogSource(String name) {
		this.name = name;
	}
	/*
	 * Get the name of this log source flag
	 * Returns: String - The name of this log source flag
	 */
	public String getName() {
		return name;
	}
	
}

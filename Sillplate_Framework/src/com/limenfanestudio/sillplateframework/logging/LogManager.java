/*
 * File:		LogManager.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.26
 * Purpose:		Manages output of Framework logs to the console and/or one or
 * 				more files
 */

package com.limenfanestudio.sillplateframework.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.limenfanestudio.sillplateframework.application.Application;
import com.limenfanestudio.sillplateframework.application
		.ApplicationConfiguration;

// The main class of the Sillplate Framework's logging system
public class LogManager {
	
	// Whether console output is enabled for the logging system
	private boolean consoleEnabled = false;
	// Format to create timestamps in for logs
	private String timestampFormat = "";
	// Set of output files for the logging system associated with their file
	// names / paths
	private Map<String, FileWriter> files = null;
	
	/*
	 * Initialize the Sillplate Framework's logging system
	 * Parameter: ApplicationConfiguration.LogConfiguration configuration - The
	 * configuration settings for the logging system
	 * Returns: boolean - Whether the logging system was initialized
	 * successfully
	 */
	public boolean initialize(
			ApplicationConfiguration.LogConfiguration configuration) {
		setConsoleEnabled(configuration.consoleEnabled);
		if (!setTimestampFormat(configuration.timestampFormat)) {
			return false;
		}
		files = new HashMap<String, FileWriter>();
		for (String fileName : configuration.fileNames) {
			if (!addFileName(fileName)) {
				return false;
			}
		}
		Application.Log.write(LogSource.Log, LogLevel.Information,
				"Initialized logging system");
		return true;
	}
	/*
	 * Write a set of objects to the log with source and level flags
	 * Parameter: LogSource source - The source flag for this log
	 * Parameter: LogLevel level - The level flag for this log
	 * Parameter: Object... data - The objects to write to the log
	 */
	public void write(LogSource source, LogLevel level, Object... data) {
		String log = getTimestamp() + ": [" + source.getName() + "] ["
				+ level.getName() + "] ";
		for (Object obj : data) {
			log += obj.toString();
		}
		log += "\n";
		if (consoleEnabled) {
			System.out.print(log);
		}
		for (Map.Entry<String, FileWriter> file : files.entrySet()) {
			try {
				file.getValue().write(log);
				file.getValue().flush();
			} catch (IOException e) {
				continue;
			}
		}
	}
	/*
	 * Write a set of objects to the log with the default source flag and a
	 * level flag
	 * Parameter: LogLevel level - The level flag for this log
	 * Parameter: Object... data - The objects to write to the log
	 */
	public void write(LogLevel level, Object... data) {
		write(LogSource.General, level, data);
	}
	/*
	 * Write a set of objects to the log with the default source and level flags
	 * Parameter: Object... data - The objects to write to the log
	 */
	public void write(Object... data) {
		write(LogLevel.Information, data);
	}
	/*
	 * Free the memory of the Sillplate Framework's logging system
	 * Returns: boolean - Whether the logging system was destroyed successfully
	 */
	public boolean destroy() {
		Application.Log.write(LogSource.Log, LogLevel.Information,
				"Destroying logging system");
		boolean success = true;
		for (Map.Entry<String, FileWriter> file : files.entrySet()) {
			try {
				file.getValue().close();
			} catch (IOException e) {
				success = false;
			}
		}
		timestampFormat = "";
		consoleEnabled = false;
		return success;
	}
	/*
	 * Generate a current timestamp and the timestamp format
	 * Returns: String - The current timestamp
	 */
	private String getTimestamp() {
		ZonedDateTime time = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				timestampFormat);
		return time.format(formatter);
	}
	
	/*
	 * Test whether console output is enabled for the logging system
	 * Returns: boolean - Whether console output is enabled
	 */
	public boolean isConsoleEnabled() {
		return consoleEnabled;
	}
	/*
	 * Set whether console output is enabled for the logging system
	 * Parameter: boolean consoleEnabled - Whether console output should be
	 * enabled
	 */
	public void setConsoleEnabled(boolean consoleEnabled) {
		this.consoleEnabled = consoleEnabled;
	}
	/*
	 * Get the current timestamp format used by the logging system
	 * Returns: String - The current timestamp format
	 */
	public String getTimestampFormat() {
		return timestampFormat;
	}
	/*
	 * Set the timestamp format to be used by the logging system
	 * Parameter: String timestampFormat - The timestamp format to use
	 * Returns: boolean - Whether the given timestamp format is valid/usable
	 */
	public boolean setTimestampFormat(String timestampFormat) {
		try {
			DateTimeFormatter.ofPattern(timestampFormat);
		} catch (IllegalArgumentException e) {
			return false;
		}
		this.timestampFormat = timestampFormat;
		return true;
	}
	/*
	 * Get the file names of the set of files the logging system is writing to
	 * Returns: List<String> - The set of file names in use by the logging
	 * system
	 */
	public List<String> getFileNames() {
		return new ArrayList<String>(files.keySet());
	}
	/*
	 * Add a file by name for the logging system to write to
	 * Parameter: String fileName - The file name of the file to write to
	 * Returns: boolean - Whether the file name is valid and not already in use
	 */
	public boolean addFileName(String fileName) {
		if (files.containsKey(fileName)) {
			return false;
		}
		try {
			files.put(fileName, new FileWriter(fileName));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	/*
	 * Remove a file by name from the logging system
	 * Parameter: String fileName - The file name to remove
	 * Returns: boolean - Whether the file name was in use and was successfully
	 * removed
	 */
	public boolean removeFileName(String fileName) {
		boolean success = true;
		if (!files.containsKey(fileName)) {
			return false;
		}
		try {
			files.get(fileName).close();
		} catch (IOException e) {
			success = false;
		}
		files.remove(fileName);
		return success;
	}
	
}

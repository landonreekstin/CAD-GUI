package csci240.prinCad.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controls error and exception logging
 */
public class Log {
	
	private LoggingLevel loggingLevel;
	
	// Base interface for logging - exposes error and info logging
		private interface Logging {
			// Write error to log file
			public void error(String errorText);
			
			// Write exception to log file
			public void error(String text, Exception ex);
			
			// Write info to log file
			public void info(String infoText);
			
		}
		
		// No logging
		private class LogNone implements Logging {
			
			// Write error to log file
			public void error(String errorText) {
				
			}
			
			// Write exception to log file
			public void error(String text, Exception ex) {
				
			}
			
			// Write info to log file
			public void info(String infoText) {
				
			}
			
		}
		

		// Error logging
		private class LogError implements Logging {
			
			// Write error to log file
			public void error(String errorText) {
				write(errorText);
			}
			
			// Write exception to log file
			public void error(String text, Exception ex) {
				// 
				String errorText = String.format("%s %s", text, ex.toString());
				error(errorText);
			}
			
			// Write info to log file
			public void info(String infoText) {
				
			}

		}
		
		// Info logging
		private class LogInfo implements Logging {
			
			// Write error to log file
			public void error(String errorText) {
				write(errorText);
			}
			
			// Write exception to log file
			public void error(String text, Exception ex) {
				// 
				String errorText = String.format("%s %s", text, ex.toString());
				error(errorText);
			}
			
			// Write info to log file
			public void info(String infoText) {
				write(infoText);
			}

		}
		
	// Logging level enum values
		public enum LoggingLevel {
			None,
			Error,
			Information
		}
		
		// The one and only instance of Log object - singleton design pattern
		private static Log _instance = new Log();
		
		// The logging level instance – result of the factor design pattern
		Logging _logging;
		

		// private constructor to create the correct logging level object
		private Log() {
			_logging = new LogInfo();	
		}
		
		// Set logging level
		public void setLoggingLevelInstance(LoggingLevel loggingLevel) {
			
			// Factory design pattern
			switch (loggingLevel) {
			case None:
				_logging = new LogNone();
				break;
			case Error:
				_logging = new LogError();
				break;
			case Information:
				_logging = new LogInfo();
				break;
			}
		}
		
		// Set logging level
		public static void setLoggingLevel(LoggingLevel loggingLevel) {
			_instance.setLoggingLevelInstance(loggingLevel);
		}

		
		// Write error to log file
		public static void error(String errorText) {
			_instance._logging.error(errorText);
		}
		
		// Write exception to log file
		public static void error(String text, Exception ex) {
			_instance._logging.error(text, ex);
		}
		
		// Write info to log file
		public static void info(String infoText) {
			_instance._logging.info(infoText);
		}

		// Path to log file
		final static String LogFilePath = "PrinCad.log";
		
		// Create formatter
		final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		

	// Write text to log file
	private static void write(String text) {
		
		try {
			File file = new File(LogFilePath);
			FileWriter fw = new FileWriter(file, true); // append to file
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);
			System.out.println("Log file created");
			 
			//Local date time instance
			LocalDateTime localDateTime = LocalDateTime.now();
			 
			//Get formatted String
			String ldtString = dateTimeFormatter.format(localDateTime);
			
			out.println(String.format("%s - %s", ldtString, text));
			
			out.flush();
			out.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

}


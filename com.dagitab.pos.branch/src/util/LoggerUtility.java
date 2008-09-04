package util;

import org.apache.log4j.Logger;

public class LoggerUtility {
	private static LoggerUtility loggerUtility = new LoggerUtility();
	private static Logger logger = Logger.getLogger(LoggerUtility.class);
	private LoggerUtility(){
		
	}
	
	public static  LoggerUtility getInstance(){
		return loggerUtility;
	}
	
	public void logStackTrace(Throwable e){
		do { 
			logger.error("Caused by: "+e); 
			for (StackTraceElement el : e.getStackTrace()) { logger.error("\t"+el.toString()); } } 
		while ((e =  (Exception) e.getCause()) != null);
	}
}

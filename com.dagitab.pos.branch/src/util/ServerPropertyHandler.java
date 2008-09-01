package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ServerPropertyHandler {
	static File file;
	static Properties properties;
	private static Logger logger = Logger.getLogger(ServerPropertyHandler.class);

	public static void main(String [] args){
		logger.info(ServerPropertyHandler.getServerIP());
		logger.info(ServerPropertyHandler.getServerPort());
		ServerPropertyHandler.setServerConfig("192.168.10.1", "8889");
		logger.info(ServerPropertyHandler.getServerIP());
		logger.info(ServerPropertyHandler.getServerPort());
	}
	
	static{
		file = new File("server.properties");
		if(!file.exists())
		{
			try{
				file.createNewFile();
			}catch(IOException ex){
				logger.info(ex.getMessage());
			}
		}
		properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	public ServerPropertyHandler(){
		
	
	}
	public static void setServerConfig(String ip, String port){
		properties.setProperty("server.ip", ip);
		properties.setProperty("server.port", port);
		try {
			properties.store(new FileOutputStream(file), "Server Configuration");
		} catch (FileNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
	}
	
	
	public static String getServerIP(){
		return properties.getProperty("server.ip");
	}
	
	public static String getServerPort(){
		return properties.getProperty("server.port");
	}
	
			
}

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerPropertyHandler {
	static File file;
	static Properties properties;

	public static void main(String [] args){
		System.out.println(ServerPropertyHandler.getServerIP());
		System.out.println(ServerPropertyHandler.getServerPort());
		ServerPropertyHandler.setServerConfig("192.168.10.1", "8889");
		System.out.println(ServerPropertyHandler.getServerIP());
		System.out.println(ServerPropertyHandler.getServerPort());
	}
	
	static{
		file = new File("server.properties");
		if(!file.exists())
		{
			try{
				file.createNewFile();
			}catch(IOException ex){
				System.out.println(ex.getMessage());
			}
		}
		properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static String getServerIP(){
		return properties.getProperty("server.ip");
	}
	
	public static String getServerPort(){
		return properties.getProperty("server.port");
	}
	
			
}

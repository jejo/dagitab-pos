package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;


public class FtpUtility {
	
	private String host;
	private String username;
	private String password;
	private String workingDirectory;
	private String remoteDirectory;
	private FTPClient ftp;
	private static Logger logger = Logger.getLogger(FtpUtility.class);
	
	public FtpUtility(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.workingDirectory = "";
		this.remoteDirectory = "/";
		this.ftp = new FTPClient();
	}
	
	public FtpUtility(String host, String username, String password, String workingDirectory) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.workingDirectory = workingDirectory;
		this.remoteDirectory = "/";
		this.ftp = new FTPClient();
	}
	
	public FtpUtility(String host, String username, String password, String workingDirectory, String remoteDirectory) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.workingDirectory = workingDirectory;
		this.remoteDirectory = remoteDirectory;
		this.ftp = new FTPClient();
		
	}
	
	public List<String> getRemoteFileNames(){
		try {
			ftp.changeWorkingDirectory(remoteDirectory);
			FTPFile[] ftpFiles = ftp.listFiles();
			ArrayList<String> fileNames = new ArrayList<String>();
			for(FTPFile ftpFile : ftpFiles){
				String fileName = ftpFile.getName();
				if(!fileName.equals(".") && !fileName.equals("..") && fileName.contains(".")){
					fileNames.add(fileName);
				}
				
			}
			return fileNames;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FTPClient getFtp() {
		return ftp;
	}

	public void setFtp(FTPClient ftp) {
		this.ftp = ftp;
	}
	
	public void sendFile(String file) {
		sendFile(file, getWorkingDirectory());
	}
	
	public void sendFile(String file, String workingDirectory) {
		logger.info("Transferring " + file + "...");
		try {
			ftp.changeWorkingDirectory(remoteDirectory);
			ftp.storeFile(file, new FileInputStream(workingDirectory + file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info(file + " has been transferred.");
	}

	public boolean connect() {
		FTPClient ftp = getFtp();
		boolean error = false;
		try {
			int reply;
			ftp.connect(getHost());
			
			ftp.login(getUsername(), getPassword());
			
			logger.info("Connected to " + getHost() + ".");
			System.out.print(ftp.getReplyString());
			
			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();
			
			if(!FTPReply.isPositiveCompletion(reply)) {
				this.disconnect();
				System.err.println("FTP server refused connection.");
				error = true;
			}
		} catch(IOException e) {
			error = true;
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return !error;
	}
	
	public void disconnect() {
		FTPClient ftp = getFtp();
		if(ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch(IOException ioe) {
				// do nothing
			}
		}
	}
	
	public static void main(String[] args) {
		String hostAddress = "ftp.dagitab.com";
		String username = "rocky@dagitab.com";
		String password = "b4ti64d";
		String workingDirectory = "C:\\Program Files\\Warcraft III\\";	//where the file(s) are placed
		
		FtpUtility ftp = new FtpUtility(hostAddress, username, password,workingDirectory,"wikilicious");
		if(ftp.connect()) {
//			ftp.sendFile("CustomKeysSample.txt");
			
			for(String s: ftp.getRemoteFileNames()){
				if(!s.equals(".") && !s.equals("..") && s.contains(".")){
					logger.info(s);
				}
			}
			
			ftp.disconnect();
		}
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public String getRemoteDirectory() {
		return remoteDirectory;
	}

	public void setRemoteDirectory(String remoteDirectory) {
		this.remoteDirectory = remoteDirectory;
	}
	
	
	
	
}

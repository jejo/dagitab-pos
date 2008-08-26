package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;


public class FtpUtility {
	
	private String host;
	private String username;
	private String password;
	private String workingDirectory;
	private String remoteDirectory;
	private FTPClient ftp;
	
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
		System.out.println("Transferring " + file + "...");
		try {
			ftp.changeWorkingDirectory(remoteDirectory);
			ftp.storeFile(file, new FileInputStream(workingDirectory + file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file + " has been transferred.");
	}

	public boolean connect() {
		FTPClient ftp = getFtp();
		boolean error = false;
		try {
			int reply;
			ftp.connect(getHost());
			
			ftp.login(getUsername(), getPassword());
			
			System.out.println("Connected to " + getHost() + ".");
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
			e.printStackTrace();
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
		
		FtpUtility ftp = new FtpUtility(hostAddress, username, password, workingDirectory);
		if(ftp.connect()) {
			ftp.sendFile("CustomKeysSample.txt");
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

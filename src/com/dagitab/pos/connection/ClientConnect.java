package com.dagitab.pos.connection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import org.apache.log4j.Logger;

import com.dagitab.pos.forms.InstallProgress;
import com.dagitab.pos.forms.MainWindow;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


@Deprecated
public class ClientConnect {
	Socket s;
	ObjectInputStream in;
	ObjectOutputStream out;
	private KeyPair myPair;
	private Key symKey;
	static DataUtil du = new DataUtil();
	LogHandler lw = new LogHandler();
	private static Logger logger = Logger.getLogger(ClientConnect.class);
	
	MainWindow form;
	public static void main(String args[]) throws IOException {
//		ClientConnect dc = new ClientConnect();
//		dc.connect("192.168.0.100", "8888", "", "");
	}
	
	public ClientConnect(){
		
	}
	
	public ClientConnect(MainWindow form) {
		this.form = form;
		
	}
	
	public void connect(String ip, String port, int storeCode) {
		try {
			logger.info("Store Code: "+storeCode+" connecting to "+ip+":"+port);
//			form.jLabel3.setText("Connecting");
			s = new Socket(ip, Integer.parseInt(port));
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
//			form.jLabel3.setText("Connected");
			//generate key pair and send to server the public key
			SecureRandom random = new SecureRandom();
	      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	      generator.initialize(512, random);
	      myPair = generator.generateKeyPair();
	      
	      logger.info("Writing int storecode = "+storeCode);
	      out.writeInt(storeCode);
	      out.flush();
	      out.write(myPair.getPublic().getEncoded());
	      out.flush();
	      
			logger.info("Client public key: length = "+ myPair.getPublic().getEncoded().length + " plain = "+ new String(myPair.getPublic().getEncoded()));
	      
	      //get the encrypted symmetric key from server and decrypt with client's private key
	      byte[] buffer = new byte[1000];
	      int len = in.read(buffer);
	      
	      Cipher xCipher = Cipher.getInstance("RSA");
	      xCipher.init(Cipher.DECRYPT_MODE,myPair.getPrivate());
	      byte[] xBuffer = xCipher.doFinal(buffer,0,len);
	      
	      symKey = new SecretKeySpec(xBuffer, "DES");
	      
	      //initialize cipher objects, with this symmetric key
			Cipher decrypter = Cipher.getInstance("DES");
			Cipher encrypter = Cipher.getInstance("DES");
			decrypter.init(Cipher.DECRYPT_MODE,symKey);
			encrypter.init(Cipher.ENCRYPT_MODE,symKey);
			
			//getfile content and send to server
			BufferedReader br = lw.readFromFile();
			String str;
			
			
			ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM products_lu LIMIT 1");
			 
			try {
				if(!rs.next()){
//					form.jLabel3.setText("Installing data");
					logger.info("Install");
					out.writeBoolean(true);
					out.flush();
					
					//INSTALL
//					getupdates from server and synchronize to database
					int num = in.readInt();
					buffer = new byte[num];

					File sqlfile = new File("installdata.sql");
					if(!sqlfile.exists())
					{
						try{
							sqlfile.createNewFile();
						}catch(IOException ex){
							logger.info("Creating sql file error: "+ex.getMessage());
						}
					}
					Progress prog = new Progress();
					prog.start();
					
					
					FileOutputStream fos = new FileOutputStream(sqlfile);
					byte b;
					
						prog.setLabel("Receving data...");
						while((b = (byte)in.read()) != -1){
							
							fos.write(b);
						}
					
					BufferedReader br1 = new BufferedReader(new FileReader("installdata.sql"));
					String s;
					
					String sqlstatement = "";
					
					while((s=br1.readLine())!= null ){
						//logger.info(s);
						if(s.endsWith(";")){
							if(!s.startsWith("INSERT")){
								s = System.getProperty("line.separator")+s;
							}
							sqlstatement += s;
							prog.setName(sqlstatement);
							Main.getDBManager().executeUpdate(sqlstatement);
							if(sqlstatement.substring(12,20).equals("clerk_lu")){
								String[] splitter = sqlstatement.split(",");
								String[] splitter2 = splitter[0].split("VALUES");
								String clerk_code = splitter2[1].substring(4,splitter2[1].length()-1);
//								logger.info("this is the clerkcode : "+ clerk_code);
								logger.info("Configuring password encryption of clerk_code "+clerk_code);
								ResultSet res = Main.getDBManager().executeQuery("SELECT password FROM clerk_lu WHERE clerk_code = "+clerk_code);
//								logger.info("SELECT password FROM clerk_lu WHERE clerk_code = "+clerk_code);
								if(res.next()){
									Main.getDBManager().executeUpdate("UPDATE clerk_lu SET password = '"+res.getString("password")+"' WHERE clerk_code = "+clerk_code);
//									db.executeUpdate("UPDATE clerk_lu SET password = AES_ENCRYPT('"+res.getString("password")+"','babyland') WHERE clerk_code = "+clerk_code);
//									logger.info("UPDATE clerk_lu SET password = AES_ENCRYPT('"+res.getString("password")+"','babyland') WHERE clerk_code = "+clerk_code);
								}
								
							}
							sqlstatement = "";
						}
						else{
							sqlstatement += s;
						}
					}
					prog.b2 = false;
					File f = new File("installdata.sql");
					PrintWriter pw = new PrintWriter(new FileWriter(f));
				}
				else{
					out.writeBoolean(false);
					out.flush();
					
//					form.jLabel3.setText("Sending Data");
					while((str=br.readLine())!=null)
					{
						byte[] data = encrypter.doFinal(str.getBytes());
						out.writeInt(data.length);
						out.flush();
						out.write(data);
						out.flush();
					}
					byte[] data =encrypter.doFinal("EOF".getBytes()); 
					out.writeInt(data.length);
					out.flush();
					out.write(data);
					out.flush();
					logger.info("Sent data");
					File f = new File("1.CAC");
					PrintWriter pw = new PrintWriter(new FileWriter(f));
					//getupdates from server and synchronize to database
//					form.jLabel3.setText("Receiving Data");
					while(true)
					{
						int num = in.readInt();
						buffer = new byte[num];
						in.read(buffer);
						String msg = new String(decrypter.doFinal(buffer,0,num));
//						logger.info(msg);
						if(msg.equals("EOF")){
							break;
						}
						else{
							try{
								logger.info(msg);
								DataUtil.dataToRow(msg);
							}catch(Exception ex){
								LoggerUtility.getInstance().logStackTrace(ex);
							}
						}
					}
					logger.info("Received Data");
//					form.jLabel3.setText("Finished Synchronization");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
				
			
			
			s.close();
//			form.jLabel3.setText("Not Connected");
			
			
			
//			int num = in.readInt();
//			logger.info("This is the number " + num);
//			byte[] rbuffer = new byte[num];
//		    in.read(rbuffer);
		    
//		    logger.info(new String(decrypter.doFinal(rbuffer,0,num)));
			
		} catch (NumberFormatException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (UnknownHostException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		
		} catch (ConnectException e){
//			form.jLabel3.setText("Not Connected");
			JOptionPane.showMessageDialog(null, 
					"Cannot connect to server. " +
					"Please try again or contact network administrator.", 
					"Warning",JOptionPane.ERROR_MESSAGE);
			LoggerUtility.getInstance().logStackTrace(e);
		}catch(NoRouteToHostException e){
//			form.jLabel3.setText("Not Connected");
			JOptionPane.showMessageDialog(null, 
					"Cannot connect to server. " +
					"Please try again or contact network administrator.", 
					"Warning",JOptionPane.ERROR_MESSAGE);
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
	}
	
	private class Progress extends Thread {
		boolean b2;	
		JFrame frame = new JFrame();
		InstallProgress dialog = new InstallProgress(frame);
		public Progress(){
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
		
			@Override
			public void run() {
				b2 = true;
				while(b2){
					
				}
				dialog.stop();
				JOptionPane.showMessageDialog(null, 
						"Finished Installation of Data. ",
						"Success",JOptionPane.INFORMATION_MESSAGE);
				dialog.setVisible(false);
				
			}
			
			public void setLabel(String name){
				dialog.jLabel2.setText(name);
			}
	}
	
	
	
	
	
}

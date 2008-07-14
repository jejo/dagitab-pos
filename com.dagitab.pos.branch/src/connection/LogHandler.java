package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogHandler {
	
	File file;
	DataUtil du; 
	public LogHandler(){
		file = new File("1.CAC");
		du = new DataUtil();
		
		if(!file.exists())
		{
			try{
				file.createNewFile();
			}catch(IOException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		LogHandler lh = new LogHandler();
		BufferedReader br = lh.readFromFile();
		String s;
		while((s=br.readLine()) != null)
		{
			System.out.println(s);
		}
		lh.deleteContent();
	}
	
	public void writeToFile(String s){
		try{
			synchronized(file)
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter("1.CAC",true));
				bw.append(s+"\r\n");
				bw.close();
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public BufferedReader readFromFile(){
		try{
			synchronized(file)
			{
				BufferedReader br = new BufferedReader(new FileReader("1.CAC"));
				return br;
			}
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public void deleteContent(){
		file.delete();
		try {
			file.createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

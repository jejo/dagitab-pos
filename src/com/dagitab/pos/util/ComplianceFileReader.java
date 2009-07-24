package com.dagitab.pos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ComplianceFileReader {
	private static ComplianceFileReader complianceFileReader = new ComplianceFileReader();
	
	private ComplianceFileReader(){
		
	}
	
	public static ComplianceFileReader getComplianceFileReader(){
		return complianceFileReader;
	}
	
	public String getFileContents(File file){
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String s;
			while( (s = br.readLine()) != null){
				sb.append(s+"\n");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to locate file "+file.getName(), "System Error", JOptionPane.ERROR_MESSAGE);
			LoggerUtility.getInstance().logStackTrace(e);
		} 
		return sb.toString();
	}
	
	
}

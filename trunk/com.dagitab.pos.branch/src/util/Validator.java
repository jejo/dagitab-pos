package util;

import javax.swing.JOptionPane;

public class Validator {
	
	public static Boolean isNumeric(String s){
		for(int i =0; i < s.length(); i++){
			String c = s.charAt(i)+"";
			if(!c.matches("[0-9]")){
				JOptionPane.showMessageDialog(null, "Please input numeric", "Invalid Input Format", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
}

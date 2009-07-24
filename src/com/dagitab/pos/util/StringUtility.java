package com.dagitab.pos.util;

public class StringUtility {
	public static String zeroFill(String s, int numChars){
		int remaining = numChars - s.length();
		for(int i = 0; i<remaining; i++){
			s = "0"+s;
		}
		return s;
	}
}

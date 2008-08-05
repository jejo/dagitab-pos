package util;

import java.util.ArrayList;

public class ReceiptUtilities {
	
	private static ReceiptUtilities receiptUtilities = new ReceiptUtilities();
	private ReceiptUtilities(){}
	
	public int findNormalAmountXPos(String s){
		return 190 - (s.length()*5);
	}
	
	public int findItemAmountXPos(String s){
		//205 - string size*4
		return 205 - (s.length()*4);
	}
	
	public String[] splitString(String s){
		String[] temp = s.split(" ");
		ArrayList<String> ss = new ArrayList<String>();
		int MAX = 32;
		int countChar = 0;
		String sTemp = "";
		for(int i=0; i<temp.length; i++){
			countChar += temp[i].length()+1;
			sTemp+=temp[i]+" ";
			if(countChar >= MAX){
				countChar = 0;
				ss.add(sTemp);
				sTemp="";
			}
			else if(countChar < MAX && i==temp.length-1){
				ss.add(sTemp);
			}
		}
		String[] out = new String[ss.size()];
		for(int i=0; i<ss.size();i++){
			out[i] = ss.get(i);
			
		}
		return out;
		
		
	}
	public Double roundDown(Double num){
		String numS = String.format("%.4f",num);
		System.out.println(numS);
		int index = numS.indexOf(".");
		String  out = numS.substring(0,index+3);
		return Double.parseDouble(out);
	}
	
	public String convertDate(String date){
		String [] dateSplit = date.split("-");
		String year = dateSplit[0];
		String month = dateSplit[1];
		String day = dateSplit[2];
		return month+"/"+day+"/"+year;
	}

	public static ReceiptUtilities getReceiptUtilities() {
		return receiptUtilities;
	}
	
	
}

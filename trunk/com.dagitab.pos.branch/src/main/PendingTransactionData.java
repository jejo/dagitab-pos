package main;

import java.util.Vector;

public class PendingTransactionData {

	/**
	 * @param args
	 */
	public String date; 
	public String time; 
	public Vector<Vector<String>> prodItems;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public PendingTransactionData(String date, String time, Vector<Vector<String>> prodItems ){
		this.date = date;
		this.time = time;
		this.prodItems = prodItems;
		System.out.println(prodItems.size()+"rcvd data");
		
	}
	
	public Vector<Vector<String>> getProdItems(){
		System.out.println(prodItems.size()+"xprt data");
		return prodItems;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getTime(){
		return time;
	}

}

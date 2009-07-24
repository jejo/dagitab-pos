package com.dagitab.pos.reports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;


import org.apache.log4j.Logger;

import com.dagitab.pos.main.DBManager;
import com.dagitab.pos.util.LoggerUtility;


public class FestivalDailyReport {

	/**
	 * @param args
	 */
	
	DBManager db;
	String storeCode;
	String tenantCode;
	String termNum;
	String dirName;
	String fileName; 
	String batchDownloadNum;
	private static Logger logger = Logger.getLogger(FestivalDailyReport.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBManager db = new DBManager();
		FestivalDailyReport fdreport = new FestivalDailyReport(db,"001","C:/Desktop",new Date());

	}
	
	public FestivalDailyReport(DBManager db, String storeCode, String dirName, Date date)
	{
		this.db = db;
		this.storeCode = storeCode;
		this.dirName = dirName;
		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("data/.tenantno"));
			tenantCode = br.readLine().trim();
			
			BufferedReader br2 = new BufferedReader(new FileReader("data/.termno"));
			termNum = br2.readLine().trim();
			
			BufferedReader br3 = new BufferedReader(new FileReader("data/.batchno"));
			batchDownloadNum = br3.readLine().trim();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		
		
		/*FILENAME GENERATION*/
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		String month = (cal.get(Calendar.MONTH) + 1)+"";
		String day = cal.get(Calendar.DAY_OF_MONTH)+"";
		String year = cal.get(Calendar.YEAR)+"";
		
		if(Integer.parseInt(month)== 10){
			month="A";
		}
		else if(Integer.parseInt(month)== 11){
			month="B";
		}
		
		else if(Integer.parseInt(month)== 12){
			month="C";
		}
		
		fileName = dirName+"\\"+"S"+tenantCode.substring(0,4)+termNum+batchDownloadNum+"."+month+day;
		logger.info("File Name: "+fileName);
	}
	
	public void createFile(){
		File f = new File(fileName);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
	}
	
	public void insertData(Date date){
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(fileName));

			
			br.write("01"+tenantCode); //tenant
			br.newLine();
			br.write("02"+termNum); //termnum
			br.newLine();
			
			//date
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			
			String month = (cal.get(Calendar.MONTH) + 1)+"";
			String day = cal.get(Calendar.DAY_OF_MONTH)+"";
			String year = cal.get(Calendar.YEAR)+"";
			
			if(month.length() == 1){
				month = "0"+month;
			}
			if(day.length() == 1){
				day = "0"+day;
			}
			br.write("03"+month+day+year);
			br.newLine();
			
			
			//old accumulated total
			cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			
			year = cal.get(Calendar.YEAR)+"";
			month = (cal.get(Calendar.MONTH)+1)+"";
			day = cal.get(Calendar.DAY_OF_MONTH)+"";

			if(Integer.parseInt(month) <10){
				month = "0"+month;
			}
			if(Integer.parseInt(day) < 10){
				day = "0"+day;
			}
			ResultSet rs = db.executeQuery("SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			try {
				while(rs.next()){
					String amt = String.format("%.2f", rs.getDouble(1));
					int posOfDot = amt.indexOf(".");
					String newAmt = amt.substring(0,posOfDot) + amt.substring(posOfDot+1);
//					logger.info("New Amt: "+ newAmt);
					br.write("04"+newAmt);
					br.newLine();
					

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			
//			new accumulated total
			cal = new GregorianCalendar();
			cal.setTime(date);
			
			year = cal.get(Calendar.YEAR)+"";
			month = (cal.get(Calendar.MONTH)+1)+"";
			day = cal.get(Calendar.DAY_OF_MONTH)+"";
			
			if(Integer.parseInt(month) <10){
				month = "0"+month;
			}
			if(Integer.parseInt(day) < 10){
				day = "0"+day;
			}
		
			rs = db.executeQuery("SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			try {
				while(rs.next()){
					String amt = String.format("%.2f", rs.getDouble(1));
					int posOfDot = amt.indexOf(".");
					String newAmt = amt.substring(0,posOfDot) + amt.substring(posOfDot+1);
					br.write("05"+newAmt) ;
					br.newLine();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			//Taxable Sales Amount Gross of VAT NET of Discounts
			rs = db.executeQuery("SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			
			try {
				while(rs.next()){
					String amt = String.format("%.2f", rs.getDouble(1));
					int posOfDot = amt.indexOf(".");
					String newAmt = amt.substring(0,posOfDot) + amt.substring(posOfDot+1);
					br.write("06"+newAmt);
					br.newLine();
					 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			/*NON TAXABLE SALES AMOUNT*/
			br.write("070");
			br.newLine();
			
			/*TOTAL SENIOR CITIZEN DISCOUNT*/
			br.write("080");
			br.newLine();
			
			/*TOTAL PROMO DISCOUNT*/
			br.write("090");
			br.newLine();
			
			/*TOTAL OTHER DISCOUNT*/
			rs = db.executeQuery("SELECT i.SELL_PRICE, d.DISC_RATE, i.QUANTITY FROM invoice_item i, discount_lu d, invoice o " +
					"WHERE i.DISC_CODE = d.DISC_NO AND " +
					"MONTH (o.TRANS_DT) = '"+month+"' && " +
					"YEAR(o.TRANS_DT) = '"+year+"' && " +
					"DAY(o.TRANS_DT) = '"+day+"' " +
					"AND i.OR_NO = o.OR_NO " +
					"AND o.STORE_CODE = '"+storeCode+"'");
			
			double totdisc = 0;
			try {
			
			while(rs.next()){
				double discRateInverse = 1- rs.getDouble(2);
				double origPrice = (rs.getDouble(1)*rs.getDouble(3))/discRateInverse;
				
				totdisc += (origPrice - (rs.getDouble(1)*rs.getDouble(3)));
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
			}
			
			String amt = String.format("%.2f", totdisc);
			int posOfDot = amt.indexOf(".");
			String newAmt = amt.substring(0,posOfDot) + amt.substring(posOfDot+1);
			
			br.write("10"+newAmt);
			br.newLine();
			
			/*TOTAL REFUND*/
			
			br.write("110");
			br.newLine();
			
			/*TOTAL VOID AMOUNT */
			br.write("120");
			br.newLine();
			
			/*CONTROL NUMBER*/
			BufferedReader reader = new BufferedReader(new FileReader("data/.controlno"));
			String line = reader.readLine();
			br.write("13"+line);
			br.newLine();
			
			/*TOTAL NO OF SALES TRANS*/
			rs = db.executeQuery("SELECT COUNT(o.OR_NO) " +
					"FROM  invoice o " +
					"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
					"YEAR(o.TRANS_DT) = '"+year+"' && " +
					"DAY(o.TRANS_DT) = '"+day+"' AND " +
					"o.STORE_CODE = '"+storeCode+"'");
			
			try {
				if(rs.next()){
					br.write("14"+rs.getInt(1));
					br.newLine();
					
					/*TOTAL NO OF TRANSACTION*/
					br.write("15"+rs.getInt(1));
					br.newLine();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			/*SALES TYPE*/
			br.write("16NF");
			br.newLine();
			
			
			/*NET OF VAT & SERVICE CHARGE*/
			rs = db.executeQuery("SELECT VAT FROM global");
			double subtractor = 0;
			try {
				if(rs.next()){
					String temp = "1."+rs.getString(1);
					subtractor = Double.parseDouble(temp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			
			rs = db.executeQuery("SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
			
			double VAT = 0;
			double dlysale = 0;
			
			try {
				while(rs.next()){
					double salesamt = rs.getDouble(1);
					dlysale = salesamt/subtractor;
					
					String temp = String.format("%.2f", dlysale);
					int dot = temp.indexOf(".");
					String finalAmt = temp.substring(0,dot) + temp.substring(dot+1);
					
					br.write("17"+finalAmt);
					br.newLine();
					
					VAT = salesamt - dlysale; 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			/*TAX*/
			amt = String.format("%.2f", VAT);
			posOfDot = amt.indexOf(".");
			newAmt = amt.substring(0,posOfDot) + amt.substring(posOfDot+1);
			
			br.write("18"+newAmt);
			br.newLine();
			
			
			/*SERVICE CHARGE*/
			br.write("190");
			br.newLine();
			
			/*ADJUSTMENTS*/
			br.write("200");
			br.newLine();
			
			br.flush();
			
			
			reader = new BufferedReader(new FileReader("data/.controlno"));
			line = reader.readLine();
			int newcontrolnum = Integer.parseInt(line)+1;
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("data/.controlno"));
			writer.write(newcontrolnum+"");
			writer.flush();
			
			JOptionPane.showMessageDialog(null,"You have exported a compliance report","Success",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
			JOptionPane.showMessageDialog(null,"You have not exported a compliance report","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

}

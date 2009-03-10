package com.dagitab.pos.reports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import com.svcon.jdbf.DBFWriter;
import com.svcon.jdbf.JDBFException;
import com.svcon.jdbf.JDBField;

public class AyalaCompliance {
	DBManager db;
	String storeCode;
	String tenantCode;
	String dirName = "C:\\Ayala\\";
	String fileName; 
	private static Logger logger = Logger.getLogger(AyalaCompliance.class);
	
	public AyalaCompliance(DBManager db, String storeCode){
		this.db = db;
		this.storeCode = storeCode;
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/.tenantno"));
			tenantCode = br.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	public void checkDirectory(){
		ResultSet rs = db.executeQuery("SELECT YEAR(CURRENT_TIMESTAMP)");
		try {
			if(rs.next()){
				dirName += rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		boolean created = new File(dirName).mkdir();
		logger.info("Directory created: "+ created);
	}
	
	public void createDBFFile(Date date){
		
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
		
		fileName = dirName+"\\"+tenantCode+month+day+".dbf";
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
		
		JDBField[] fields = new JDBField[21];
		try {
			fields[0] = new JDBField("TRANDATE",'D',8,0);
			fields[1] = new JDBField("OLDGT",'N',15,2);
			fields[2] = new JDBField("NEWGT",'N',15,2);
			fields[3] = new JDBField("DLYSALE",'N',11,2);
			fields[4] = new JDBField("TOTDISC",'N',11,2);
			fields[5] = new JDBField("TOTREF",'N',11,2);
			fields[6] = new JDBField("TOTCAN",'N',11,2);
			fields[7] = new JDBField("VAT",'N',11,2);
			fields[8] = new JDBField("TENTNAME",'C',10,0);
			fields[9] = new JDBField("BEGINV",'N',6,0);
			fields[10] = new JDBField("ENDINV",'N',6,0);
			fields[11] = new JDBField("BEGOR",'N',6,0);
			fields[12] = new JDBField("ENDOR",'N',6,0);
			fields[13] = new JDBField("TRANCNT",'N',9,0);
			fields[14] = new JDBField("LOCALTX",'N',11,2);
			fields[15] = new JDBField("SERVCHARGE",'N',11,2);
			fields[16] = new JDBField("NOTAXSALE",'N',11,2);
			fields[17] = new JDBField("RAWGROSS",'N',11,2);
			fields[18] = new JDBField("DLYLOCTAX",'N',11,2);
			fields[19] = new JDBField("OTHERS",'N',11,2);
			fields[20] = new JDBField("TERMNUM",'N',3,0);
			
			DBFWriter writer = new DBFWriter(fileName,fields);
			Object[] dataRecord = new Object[21];
			dataRecord[0] = date;
			
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			
			String year = cal.get(Calendar.YEAR)+"";
			String month = (cal.get(Calendar.MONTH)+1)+"";
			String day = cal.get(Calendar.DAY_OF_MONTH)+"";

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
					dataRecord[1] = rs.getDouble(1);
					logger.info(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
		
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
					dataRecord[2] = rs.getDouble(1);
					logger.info(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
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
					double amt = rs.getDouble(1);
					dlysale = amt/subtractor;
					dataRecord[3] = dlysale;
					
					VAT = amt - dlysale; 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
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
			
			dataRecord[4] = totdisc;
			
			dataRecord[5] = 0;
			dataRecord[6] = 0;
			
			dataRecord[7] = VAT;
			
			dataRecord[8] = tenantCode;
			
			dataRecord[9] = 0;
			dataRecord[10] = 0;
			
			
			rs = db.executeQuery("SELECT i.OR_NO " +
								"FROM invoice_item i, invoice o " +
								"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
								"YEAR(o.TRANS_DT) = '"+year+"' && " +
								"DAY(o.TRANS_DT) = '"+day+"' AND " +
								"i.OR_NO = o.OR_NO AND " +
								"o.STORE_CODE = '"+storeCode+"' ORDER BY i.OR_NO ASC LIMIT 1");
			
			
			try {
				if(rs.next()){
					int sto = Integer.parseInt(storeCode);
					int num = rs.getInt(1);
					int length = ((sto+"").length())+((num+"").length());
					String orno = sto+"";
					
					for(int i = 0; i<6-length; i++){
						orno+="0";
					}
					
					orno+=num;
					
					logger.info(orno);
					
					dataRecord[11] = Integer.parseInt(orno); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
				
			rs = db.executeQuery("SELECT i.OR_NO " +
					"FROM invoice_item i, invoice o " +
					"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
					"YEAR(o.TRANS_DT) = '"+year+"' && " +
					"DAY(o.TRANS_DT) = '"+day+"' AND " +
					"i.OR_NO = o.OR_NO AND " +
					"o.STORE_CODE = '"+storeCode+"' ORDER BY i.OR_NO DESC LIMIT 1");


			try {
				if(rs.next()){
					int sto = Integer.parseInt(storeCode);
					int num = rs.getInt(1);
					int length = ((sto+"").length())+((num+"").length());
					String orno = sto+"";
					
					for(int i = 0; i<6-length; i++){
						orno+="0";
					}
					
					orno+=num;
					
					logger.info(orno);
					
					dataRecord[12] = Integer.parseInt(orno); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			
			rs = db.executeQuery("SELECT COUNT(o.OR_NO) " +
					"FROM  invoice o " +
					"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
					"YEAR(o.TRANS_DT) = '"+year+"' && " +
					"DAY(o.TRANS_DT) = '"+day+"' AND " +
					"o.STORE_CODE = '"+storeCode+"'");
			
			try {
				if(rs.next()){
					dataRecord[13] = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
			
			dataRecord[14] = 0;
			dataRecord[15] = 0;
			dataRecord[16] = 0;
			
			dataRecord[17] =(dlysale + totdisc +VAT);
			dataRecord[18] = dlysale;
			dataRecord[19] = 0;
			dataRecord[20] = 1;
			    
			writer.addRecord(dataRecord);
			writer.close();
			
			JOptionPane.showMessageDialog(null,"You have exported a compliance report","Success",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (JDBFException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"You have not exported a compliance report","Error",JOptionPane.ERROR_MESSAGE);
			LoggerUtility.getInstance().logStackTrace(e);
		}

	}
	
	public static void main(String[] args){
//		Calendar c = new GregorianCalendar();
//		logger.info(c.FEBRUARY);
	}
}

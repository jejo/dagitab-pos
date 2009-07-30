package com.dagitab.pos.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;



public class DailySales {

	private  int topMarker = 7;
	private  HSSFWorkbook wb;
	
	private  Double cash = 0d;
	private  Double check = 0d;
	private  Double card = 0d;
	private  Double gc = 0d;
	private  Double others = 0d;
	private  Double cashTotal = 0d;
	private  Double checkTotal = 0d;
	private  Double cardTotal = 0d;
	private  Double gcTotal = 0d;
	private  Double othersTotal=0d;
	private  Double totalTotal=0d;
	
	private  Integer rowCounter = topMarker;
	
	public  void main(String[] args){

	}


public  boolean generate(String fileName, String startDate, String endDate) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/DailySales.xls"));

			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = ReportUtility.getSheet(wb);

			//page generated on:
			ReportUtility.writeDateGenerated(wb);
			
			//branch
			ReportUtility.writeBranch(wb);
			
			//date
			ReportUtility.writeDateRange(startDate, endDate, wb);
			
			String branchClause=" && i.store_code="+Main.getStoreCode();
			ResultSet rs = Main.getDBManager().executeQuery("SELECT i.or_no,i.store_code,i.cust_no,i.encoder_code FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' "+branchClause);
			while(rs.next()) {
			
				HSSFRow row = sheet.createRow(rowCounter);
		
				cell = HSSFUtil.createStringCell(wb,row,(short)0,false,true);
				cell.setCellValue(rs.getString(1));
				
				ResultSet rs2 = Main.getDBManager().executeQuery("SELECT CONCAT(first_name,' ',last_name) FROM clerk_lu WHERE clerk_code="+rs.getInt(4));
				cell = HSSFUtil.createStringCell(wb,row,(short)1,false,true);
				if(rs2.next()) {	
					cell.setCellValue(rs2.getString(1));					
				}
				else {
					cell.setCellValue("N/A");
				}
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Cash\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)2,false,true);
				if(rs2.next()) {
					cash=rs2.getDouble(1);
					cashTotal+=cash;
				}
				else {
					cash=0d;
				}
				
				cell.setCellValue(cash);
				
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Credit Card\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)3,false,true);
				if(rs2.next()) {
					card=rs2.getDouble(1);
					cardTotal+=card;
				}
				else {
					card=0d;

				}
				cell.setCellValue(card);
				
//				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
//				cell = HSSFUtil.createAmountCell(wb,row,(short)4,false,true);
//				if(rs2.next()) {
//					gc=rs2.getDouble(1);
//					gcTotal+=gc;
//				}
//				else {
//					gc=0d;
//
//				}
//				cell.setCellValue(gc);
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Bank Check\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)4,false,true);
				if(rs2.next()) {
					check=rs2.getDouble(1);
					checkTotal+=check;
				}
				else {
					check=0d;
				}
				cell.setCellValue(check);
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name!=\"Bank Check\" && ptype.name!=\"Cash\" && ptype.name!=\"Credit Card\" && ptype.name!=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)5,false,true);
				if(rs2.next()) {
					others=rs2.getDouble(1);
					othersTotal+=others;
				}
				else {
					others=0d;
				}
				cell.setCellValue(others);

				double total= cash+check+card+others+gc; //restored gc...
				totalTotal += total;
				
				cell = HSSFUtil.createAmountCell(wb,row,(short)6,false,true);
				cell.setCellValue(total);
				
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
			}
			
			//totals
			writeTotals();
			
			// Write the output to a file
			ReportUtility.writeOutputToFile(wb, fileName);
			return true;
			
		} catch (FileNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}

	}
	
	public  void writeTotals() {
		HSSFRow row = ReportUtility.getSheet(wb).createRow(rowCounter+2);
		
		HSSFCell cell = HSSFUtil.createAmountCell(wb,row,(short)2,true,true);
		cell.setCellValue(cashTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)3,true,true);
		cell.setCellValue(cardTotal);
//		cell = HSSFUtil.createAmountCell(wb,row,(short)4,true,true);
//		cell.setCellValue(gcTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)4,true,true);
		cell.setCellValue(checkTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)5,true,true);
		cell.setCellValue(othersTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)6,true,true);
		cell.setCellValue(totalTotal);
	}
}

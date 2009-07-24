package com.dagitab.pos.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dagitab.pos.main.DBManager;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;



public class CurrentInventory {
	
	public static void main(String[] args){
		//generate("This.xls");
	}
	
	public boolean generate( String fileName, JTable table, String branch ){
		HSSFCell cell;
		POIFSFileSystem fs;
		int topMarker = 9;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/currentinventory.xls"));
		
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
	
	
			// page generated
			HSSFRow row = sheet.getRow(1);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
	
			SimpleDateFormat format = new SimpleDateFormat(
					"MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
			
//			 branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			cell.setCellValue(branch.split("-")[1]);
					
			int rowCounter=topMarker;
			
			for(int i=0; i<table.getRowCount(); i++){
				row = sheet.createRow(rowCounter);
				
				//product code
				cell = HSSFUtil.createStringCell(wb,row,(short)0,false,true);
				cell.setCellValue(table.getValueAt(i, 0).toString());
				
				//product name
				cell = HSSFUtil.createStringCell(wb,row,(short)1,false,true);
				cell.setCellValue(table.getValueAt(i, 1).toString());
				
				
				
				//selling price
				cell = HSSFUtil.createAmountCell(wb,row,(short)2,false,true);
				cell.setCellValue(table.getValueAt(i, 2).toString());
				
				//available qty
				cell = HSSFUtil.createIntCell(wb,row,(short)3,false,true);
				cell.setCellValue(table.getValueAt(i, 3).toString());
				
				//deferred qty
				cell = HSSFUtil.createIntCell(wb,row,(short)4,false,true);
				cell.setCellValue(table.getValueAt(i, 4).toString());
				
				rowCounter++;
				
//				 Write the output to a file
				FileOutputStream fileOut = new FileOutputStream(fileName);
				wb.write(fileOut);
				fileOut.close();
			}
			
			return true;
			
		} catch (FileNotFoundException e) {

			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
		
		
	}
	
	
	public static boolean generate(String fileName,Vector<Vector<String>> data, DBManager db, String storeCode) {
		HSSFCell cell;
		POIFSFileSystem fs;
		int topMarker = 9;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/currentinventory.xls"));
		
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
	
	
			// page generated
			HSSFRow row = sheet.getRow(1);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
	
			SimpleDateFormat format = new SimpleDateFormat(
					"MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
			
//			 branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = db.executeQuery("SELECT name FROM store_lu WHERE store_code="+storeCode);
			try {
				if(rs1.next()) {
					cell.setCellValue(rs1.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}		
			
			int rowCounter=topMarker;
			
			for(int i = 0; i<data.size(); i++){
				row = sheet.createRow(rowCounter);
				int colnum = 0;
				for(int j=0; j<data.get(i).size();j++){
					if(j != 4 && j!=5){
						if(j == 3){
							cell = HSSFUtil.createAmountCell(wb,row,(short)colnum,false,true);
							cell.setCellValue(Double.parseDouble(data.get(i).get(j)));
						}
						else{
							cell = HSSFUtil.createStringCell(wb,row,(short)colnum,false,true);
							cell.setCellValue(data.get(i).get(j));
						}
						colnum++;
					}
				}
				rowCounter++;
			}
			
//			 Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();
			
			return true;
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
	}
	
	
}

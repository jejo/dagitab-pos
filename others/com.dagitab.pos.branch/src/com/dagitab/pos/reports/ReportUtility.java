package com.dagitab.pos.reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;



public class ReportUtility {
	static void writeDateGenerated(HSSFWorkbook wb) {
		HSSFRow row = getSheet(wb).getRow(1);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);

		SimpleDateFormat format = new SimpleDateFormat(
				"MMMM dd, yyyy hh:mm:ss a");
		cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
	}
	
	static void writeBranch(HSSFWorkbook wb) {
		HSSFRow row = getSheet(wb).getRow(2);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
		
		ResultSet rs1 = Main.getDBManager().executeQuery("SELECT name FROM store_lu WHERE store_code="+Main.getStoreCode());
		try {
			if(rs1.next()) {
				cell.setCellValue(rs1.getString(1));
			} else {
				cell.setCellValue("ALL BRANCHES");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	static void writeDateRange(String startDate, String endDate, HSSFWorkbook wb) {
		HSSFRow row = getSheet(wb).getRow(3);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
		cell.setCellValue(startDate+ " - "+endDate);
	}
	
	static HSSFSheet getSheet(HSSFWorkbook wb) {
		return wb.getSheetAt(0);
	}
	
	public static void writeOutputToFile(HSSFWorkbook wb, String fileName) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(fileName);
			try {
				wb.write(fileOut);
				fileOut.close();
			} catch (IOException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File is in use.", "Error", JOptionPane.ERROR_MESSAGE);
			LoggerUtility.getInstance().logStackTrace(e);
		}		
	}
}

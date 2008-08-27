package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



import main.DBManager;
import main.Main;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class DailySales {

	private static int topMarker = 7;
	private static HSSFWorkbook wb;
	
	private static Double cash = 0d;
	private static Double check = 0d;
	private static Double card = 0d;
	private static Double gc = 0d;
	private static Double others = 0d;
	private static Double cashTotal = 0d;
	private static Double checkTotal = 0d;
	private static Double cardTotal = 0d;
	private static Double gcTotal = 0d;
	private static Double othersTotal=0d;
	private static Double totalTotal=0d;
	
	private static Integer rowCounter = topMarker;
	
	public static void main(String[] args){

	}


public static boolean generate(String fileName, String startDate, String endDate) {
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
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)4,false,true);
				if(rs2.next()) {
					gc=rs2.getDouble(1);
					gcTotal+=gc;
				}
				else {
					gc=0d;

				}
				cell.setCellValue(gc);
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Bank Check\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)5,false,true);
				if(rs2.next()) {
					check=rs2.getDouble(1);
					checkTotal+=check;
				}
				else {
					check=0d;
				}
				cell.setCellValue(check);
				
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name!=\"Bank Check\" && ptype.name!=\"Cash\" && ptype.name!=\"Credit Card\" && ptype.name!=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = HSSFUtil.createAmountCell(wb,row,(short)6,false,true);
				if(rs2.next()) {
					others=rs2.getDouble(1);
					othersTotal+=others;
				}
				else {
					others=0d;
				}
				cell.setCellValue(others);

				double total= cash+check+card+others; //removed gc
				totalTotal += total;
				
				cell = HSSFUtil.createAmountCell(wb,row,(short)7,false,true);
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
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static void writeTotals() {
		HSSFRow row = ReportUtility.getSheet(wb).createRow(rowCounter+2);
		
		HSSFCell cell = HSSFUtil.createAmountCell(wb,row,(short)2,true,true);
		cell.setCellValue(cashTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)3,true,true);
		cell.setCellValue(cardTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)4,true,true);
		cell.setCellValue(gcTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)5,true,true);
		cell.setCellValue(checkTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)6,true,true);
		cell.setCellValue(othersTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)7,true,true);
		cell.setCellValue(totalTotal);
	}
}
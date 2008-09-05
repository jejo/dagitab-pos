package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;

import main.Main;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import util.LoggerUtility;

public class TotalMerchandise {
	private int topMarker = 7;
	private HSSFWorkbook wb;
	private Logger logger = Logger.getLogger(TotalMerchandise.class);
	
	public boolean generate(String fileName, String startDate, String endDate) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/totalmerchandise.xls"));

			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = ReportUtility.getSheet(wb);

			//page generated on:
			ReportUtility.writeDateGenerated(wb);
			
			//branch
			ReportUtility.writeBranch(wb);
			
			//date
			ReportUtility.writeDateRange(startDate, endDate, wb);
			
			String branchClause=" && a.STO_TO_CODE="+Main.getStoreCode();
			String dateClause = " && DATE(a.ISSUE_DT) >= \""+startDate+"\" && DATE(a.ISSUE_DT) <= \""+endDate+"\" ";

			
			int rowCounter=topMarker;
			String query = "SELECT a.ISSUE_DT, c.NAME, a.DEL_NO, CONCAT(e.FIRST_NAME, e.LAST_NAME), b.PROD_CODE, b.NAME, d.ACCEPTED_QTY, d.MISSING_QTY, d.DAMAGED_QTY, d.QUANTITY, b.SELL_PRICE, d.ACCEPTED_QTY * b.SELL_PRICE FROM deliveries a, products_lu b, supplier_lu c, delivery_items d, clerk_lu e WHERE a.DEL_NO = d.DEL_NO && d.PROD_CODE = b.PROD_CODE && b.SUPPLIER_CODE = c.SUPPLIER_CODE && d.PROD_CODE = b.PROD_CODE && d.RCVD_CLERK = e.CLERK_CODE "+dateClause+branchClause;
			ResultSet rs = Main.getDBManager().executeQuery(query);
			
			
			//data
			while(rs.next()) {
				HSSFRow row = sheet.createRow(rowCounter);
				
				for(int i=0; i<6; i++){
					cell = HSSFUtil.createStringCell(wb,row,(short)i,false,true);
					cell.setCellValue(rs.getString(i+1));
				}
				
				for(int i =6; i<12; i++){
					cell = HSSFUtil.createAmountCell(wb,row,(short)i,false,true);
					cell.setCellValue(rs.getDouble(i+1));
				}
				
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
			}
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

	public boolean generatePerClerk(String fileName, String startDate, String endDate, int[] clerk_codes) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/clerksales.xls"));

			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = ReportUtility.getSheet(wb);

			//page generated on:
			ReportUtility.writeDateGenerated(wb);
			
			//branch
			ReportUtility.writeBranch(wb);
			
			//date
			ReportUtility.writeDateRange(startDate, endDate, wb);
			
			String branchClause=" && a.store_code="+Main.getStoreCode();
			String dateClause = " DATE(a.TRANS_DT) >= \""+startDate+"\" && DATE(a.TRANS_DT) <= \""+endDate+"\" ";

			
			int rowCounter=topMarker;
			String query = "SELECT * FROM invoice a, clerk_lu b WHERE a.ENCODER_CODE = b.CLERK_CODE AND  "+dateClause+branchClause+" GROUP by DATE(a.TRANS_DT)";
			
			ResultSet rs = Main.getDBManager().executeQuery(query);
			
			//data
			while(rs.next()) {
				HSSFRow row = sheet.createRow(rowCounter);
		
				cell = HSSFUtil.createStringCell(wb,row,(short)0,false,true);
				cell.setCellValue(rs.getString("ENCODER_CODE"));
				
				cell = HSSFUtil.createStringCell(wb,row,(short)1,false,true);
				cell.setCellValue(rs.getString("LAST_NAME"));
				
				cell = HSSFUtil.createStringCell(wb,row,(short)2,false,true);
				cell.setCellValue(rs.getString("FIRST_NAME"));
				
				String[] dd = rs.getString("TRANS_DT").split(" ");
				
				cell = HSSFUtil.createStringCell(wb,row,(short)3,false,true);
				cell.setCellValue(dd[0]);
				
				String query2 = "SELECT SUM(b.AMT) FROM invoice a, payment_item b WHERE a.OR_NO = b.OR_NO && DATE(a.TRANS_DT) = \""+dd[0]+"\""+branchClause;
				logger.info(query2);
				ResultSet rs2 = Main.getDBManager().executeQuery(query2);
				if(rs2.next()){
					cell = HSSFUtil.createAmountCell(wb,row,(short)4,false,true);
					cell.setCellValue(String.format("%.2f",rs2.getDouble(1)));
				}
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
			}
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
}

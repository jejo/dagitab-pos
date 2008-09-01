package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import main.DBManager;
import main.Main;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import util.LoggerUtility;
import forms.reports.SalesClerkProductionDialog;

public class SalesCerkProduction {
	private static int topMarker = 7;
	private static HSSFWorkbook wb;
	private static Vector<Vector<String>> grid = new Vector<Vector<String>>();
	private static Logger logger = Logger.getLogger(SalesClerkProductionDialog.class);
	
	
	public static boolean generate(String fileName, DBManager db, String storeCode, String startDate, String endDate) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/clerksales.xls"));

			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);


			// page generated
			HSSFRow row = sheet.getRow(1);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);

			SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));

			// branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = db.executeQuery("SELECT name FROM store_lu WHERE store_code="+storeCode);
			if(rs1.next()) {
				cell.setCellValue(rs1.getString(1));
			}			
			
				
			// start date
			row = sheet.getRow(3);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			cell.setCellValue(startDate+ " - "+endDate);
			
			String branchClause=" && a.store_code="+storeCode;
			String dateClause = " DATE(a.TRANS_DT) >= \""+startDate+"\" && DATE(a.TRANS_DT) <= \""+endDate+"\" ";

			
			int rowCounter=topMarker;
			String query = "SELECT * FROM invoice a, clerk_lu b WHERE a.ENCODER_CODE = b.CLERK_CODE AND  "+dateClause+branchClause+" GROUP by DATE(a.TRANS_DT)";
			
			ResultSet rs = db.executeQuery(query);
			
			
			//data
			while(rs.next()) {
			
				row = sheet.createRow(rowCounter);
		
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
				ResultSet rs2 = db.executeQuery(query2);
				if(rs2.next()){
					cell = HSSFUtil.createAmountCell(wb,row,(short)4,false,true);
					cell.setCellValue(String.format("%.2f",rs2.getDouble(1)));
					
				}
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
			}
			
			
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

			return true;
		} catch (FileNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}

	}

	public static boolean generatePerClerk(String fileName,String startDate, String endDate, String[] clerk_codes) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/salesclerkproduction.xls"));

			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);


			// page generated
			HSSFRow row = sheet.getRow(1);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);

			SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));

			// branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = Main.getDBManager().executeQuery("SELECT name FROM store_lu WHERE store_code="+Main.getStoreCode());
			if(rs1.next()) {
				cell.setCellValue(rs1.getString(1));
			}			
			
				
			// start date
			row = sheet.getRow(3);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			cell.setCellValue(startDate+ " - "+endDate);
			
			String branchClause=" && a.store_code="+Main.getStoreCode();
			String dateClause = " DATE(a.TRANS_DT) >= \""+startDate+"\" && DATE(a.TRANS_DT) <= \""+endDate+"\" ";

			//data
			String clerkPhrase = "";
			for(int i=0; i<clerk_codes.length; i++){
				
				if(i == clerk_codes.length-1){
					clerkPhrase += clerk_codes[i];
				}
				else{
					clerkPhrase += clerk_codes[i]+", ";
				}
			}
			
			Calendar c = Calendar.getInstance();
			String[] dd = startDate.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			c.setTime(sdf.parse(startDate));
			
			String date = sdf.format(c.getTime());
			
			
			 
			while(!date.equals(endDate)){
				
				String q = "select clerk.clerk_code clerk,  date_format(pi.trans_dt,'%Y-%m-%d') df, sum(pi.amt) total " +
				" from clerk_lu clerk, invoice inv, payment_item pi" +
				" where clerk.clerk_code = inv.encoder_code and pi.or_no = inv.or_no " +
				" and clerk.clerk_code in ("+clerkPhrase+") and pi.trans_dt <  adddate(str_to_date('"+date+"','%Y-%m-%d'),1) " +
				" and pi.trans_dt > str_to_date('"+date+"','%Y-%m-%d') " +
				" group by clerk, df order by clerk.clerk_code;";
				
				populateVector(q, clerk_codes, date);
				
				c.add(Calendar.DAY_OF_YEAR, 1);
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.format(c.getTime());
				
			}
			
			//call again
			String q = "select clerk.clerk_code clerk,  date_format(pi.trans_dt,'%Y-%m-%d') df, sum(pi.amt) total " +
			" from clerk_lu clerk, invoice inv, payment_item pi" +
			" where clerk.clerk_code = inv.encoder_code and pi.or_no = inv.or_no " +
			" and clerk.clerk_code in ("+clerkPhrase+") and pi.trans_dt <  adddate(str_to_date('"+date+"','%Y-%m-%d'),1) " +
			" and pi.trans_dt > str_to_date('"+date+"','%Y-%m-%d') " +
			" group by clerk, df order by clerk.clerk_code;";
			
			logger.info(q);
			
			populateVector(q, clerk_codes, date);
			
			
			String q2 = "select concat(clerk.first_name, clerk.last_name)  from clerk_lu clerk where clerk_code in ("+clerkPhrase+") order by clerk_code";
			ResultSet rs2 = Main.getDBManager().executeQuery(q2);
			int col = 1;
			
			row = sheet.getRow(5);
			while(rs2.next()){
				
				cell = HSSFUtil.createStringCell(wb,row,(short)col,false,true);
				cell.setCellValue(rs2.getString(1));
				col++;
			}
			cell = HSSFUtil.createStringCell(wb,row,(short)col,false,true);
			cell.setCellValue("Total");
			
			
			int rowCounter=topMarker;
			
			for(Vector<String> dataRow : grid){
				row = sheet.createRow(rowCounter);
				col = 0;
				double dateTotals = 0;
				for(String s : dataRow){
					if(col > 0){
						cell = HSSFUtil.createAmountCell(wb,row,(short)col,false,true);
						cell.setCellValue(Double.parseDouble(s));
						dateTotals+=Double.parseDouble(s);
					}
					else{
						cell = HSSFUtil.createStringCell(wb,row,(short)col,false,true);
						cell.setCellValue(s);
					}
					col++;
				}
				
				cell = HSSFUtil.createAmountCell(wb,row,(short)col,false,true);
				cell.setCellValue(dateTotals);
				
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
			}
			
			double clerkTotals [] = new double[clerk_codes.length];
			Arrays.fill(clerkTotals, 0);
			for(int i =0; i< grid.size(); i++){
				for(int j=1; j<grid.get(i).size(); j++){
					clerkTotals[j-1] += Double.parseDouble(grid.get(i).get(j));
				}
			}
			
			row = sheet.createRow(rowCounter);
			for(int i=0; i<clerkTotals.length; i++){
				cell = HSSFUtil.createAmountCell(wb,row,(short)(i+1),false,true);
				cell.setCellValue(clerkTotals[i]);
			}
			
			
//			 Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();
			
			

			return true;
		} catch (FileNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}

	}
	
	private static void populateVector(String q, String [] clerk_codes, String date) throws NumberFormatException, SQLException{
		
		
		ResultSet rs = Main.getDBManager().executeQuery(q);

		if(rs.next()){
			Vector<String> dataRow = new Vector<String>();
			dataRow.add(date);
			for(String ss : clerk_codes){
					
				if(Integer.parseInt(ss)== Integer.parseInt((rs.getString("clerk")))){
					dataRow.add(rs.getString("total"));
					rs.next();
				}
				else{
					dataRow.add("0");
				}
			}
			grid.add(dataRow);
		}
		else{
			Vector<String> dataRow = new Vector<String>();
			dataRow.add(date);
			for(String ss : clerk_codes){
				dataRow.add("0");
			}
			grid.add(dataRow);
		}
	}
	
	
}

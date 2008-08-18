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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PullOutReport {
	private static int topMarker = 7;
	private static HSSFWorkbook wb;
	
	private static Integer rowCounter = topMarker;
	
	public static boolean generate(String fileName, String startDate, String endDate) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/PullOuts.xls"));

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

			String query = "SELECT a.ISSUE_DT, a.PULL_OUT_NO, b.PROD_CODE,d.NAME, b.QUANTITY, d.SELL_PRICE, b.QUANTITY*d.SELL_PRICE, c.NAME FROM pull_outs a, pull_out_items b, pull_out_reason_lu c, products_lu d WHERE a.PULL_OUT_NO = b.PULL_OUT_NO && a.PO_REASON_CODE = c.PO_REASON_CODE && b.PROD_CODE = d.PROD_CODE"+dateClause+branchClause;
			ResultSet rs = Main.getDBManager().executeQuery(query);
			
			//data
			while(rs.next()) {
				
				HSSFRow row = sheet.createRow(rowCounter);
				
				for(int i=0; i<4; i++){
					cell = HSSFUtil.createStringCell(wb,row,(short)i,false,true);
					cell.setCellValue(rs.getString(i+1));
				}
				cell = HSSFUtil.createAmountCell(wb,row,(short)4,false,true);
				cell.setCellValue(rs.getInt(5));
				
				
				for(int i=5; i<7; i++){
					cell = HSSFUtil.createAmountCell(wb,row,(short)i,false,true);
					cell.setCellValue(rs.getDouble(i+1));
				}
				
				cell = HSSFUtil.createStringCell(wb,row,(short)7,false,true);
				cell.setCellValue(rs.getString(8));
				
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
				
			}
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

	public static boolean generatePerClerk(String fileName, DBManager db, String storeCode, String startDate, String endDate, int[] clerk_codes) {
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
				System.out.println(query2);
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
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

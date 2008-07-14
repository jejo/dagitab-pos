package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.DBManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class TotalMerchandise {
	private static int topMarker = 7;
	private static HSSFWorkbook wb;
	
	public static boolean generate(String fileName, DBManager db, String storeCode, String startDate, String endDate) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/totalmerchandise.xls"));

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
			
			String branchClause=" && a.STO_TO_CODE="+storeCode;
			String dateClause = " && DATE(a.ISSUE_DT) >= \""+startDate+"\" && DATE(a.ISSUE_DT) <= \""+endDate+"\" ";

			
			int rowCounter=topMarker;
			String query = "SELECT a.ISSUE_DT, c.NAME, a.DEL_NO, CONCAT(e.FIRST_NAME, e.LAST_NAME), b.PROD_CODE, b.NAME, d.ACCEPTED_QTY, d.MISSING_QTY, d.DAMAGED_QTY, d.QUANTITY, b.SELL_PRICE, d.ACCEPTED_QTY * b.SELL_PRICE FROM deliveries a, products_lu b, supplier_lu c, delivery_items d, clerk_lu e WHERE a.DEL_NO = d.DEL_NO && d.PROD_CODE = b.PROD_CODE && b.SUPPLIER_CODE = c.SUPPLIER_CODE && d.PROD_CODE = b.PROD_CODE && d.RCVD_CLERK = e.CLERK_CODE "+dateClause+branchClause;
			ResultSet rs = db.executeQuery(query);
			
			
			//data
			while(rs.next()) {
				
				row = sheet.createRow(rowCounter);
				
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
			for(int i=0; i<clerk_codes.length; i++){
				String q = "SELECT * FROM invoice a, clerk_lu b WHERE a .ENCODER_CODE = b.CLERK_CODE &&";
			}
			
			
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

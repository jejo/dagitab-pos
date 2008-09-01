package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import util.LoggerUtility;

public class DailySalesReport {
	
	private static int topMarker = 8;
	private static HSSFWorkbook wb;
	
	private static Integer rowCounter = topMarker;
	
	private static Integer totalQty = 0;
	private static Double totalSellPrice = 0d;
	private static Double totalDiscPrice = 0d;
	private static Double totalSubtotal =0d;
	private static Double totalTotal = 0d;
	
	public static boolean generate(String fileName, String startDate, String endDate){
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/daily_sales.xls"));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = ReportUtility.getSheet(wb);

			//page generated on:
			ReportUtility.writeDateGenerated(wb);
			
			//branch
			ReportUtility.writeBranch(wb);
			
			//date
			ReportUtility.writeDateRange(startDate, endDate, wb);
			
			//report data
			String query = "SELECT * FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' && i.store_code="+Main.getStoreCode();
			ResultSet rs = Main.getDBManager().executeQuery(query);
			while(rs.next())
			{
				int countRows = 0; //count row
				String queryCount = "SELECT COUNT(1) FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
				ResultSet rs2 = Main.getDBManager().executeQuery(queryCount);
				if(rs2.next()){
					countRows = rs2.getInt(1);
				}
					
				int currentRow = 0; //mark current row
				
				query = "SELECT * FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
				
				rs2 = Main.getDBManager().executeQuery(query);
				while(rs2.next())
				{
					HSSFRow row = sheet.getRow(rowCounter);
					
					if(currentRow == 0)
					{
						//OR no				
						cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,false);
						cell.setCellValue(rs.getString("OR_NO"));
					
						//invoice no
						cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
						cell.setCellValue(rs.getString("INVOICE_NO"));
					}
					
					//product code
					cell = HSSFUtil.createStringCell(wb,row, (short) 2,false,false);
					cell.setCellValue(rs2.getString("PROD_CODE"));
					
					query = "SELECT * FROM products_lu WHERE PROD_CODE = \""+rs2.getString("PROD_CODE")+"\"";
					ResultSet rs3 = Main.getDBManager().executeQuery(query);
					if(rs3.next())
					{
						//product name
						cell = HSSFUtil.createStringCell(wb,row, (short) 3,false,false);
						cell.setCellValue(rs3.getString("NAME"));
						
						//unit cost
						cell = HSSFUtil.createAmountCell(wb,row, (short) 5,false,false);
						cell.setCellValue(String.format("%.2f", rs3.getDouble("SELL_PRICE")));
						totalSellPrice += rs3.getDouble("SELL_PRICE");
					}
					
					
					cell = HSSFUtil.createStringCell(wb,row, (short) 4,false,false);
					cell.setCellValue(rs2.getInt("QUANTITY"));
					totalQty+=rs2.getInt("QUANTITY");
					
					//disc
					query = "SELECT * FROM discount_lu WHERE DISC_NO = "+rs2.getString("DISC_CODE");
					rs3 = Main.getDBManager().executeQuery(query);
					if(rs3.next())
					{
						//qty
						cell = HSSFUtil.createStringCell(wb,row, (short) 6,false,false);
						cell.setCellValue(rs3.getString("DISC_RATE")+"%");
					}
					
					//disc unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
					cell.setCellValue(String.format("%.2f",rs2.getDouble("SELL_PRICE")));
					totalDiscPrice += rs2.getDouble("SELL_PRICE");
					
					
					//subtotal
					double subtotal = Double.parseDouble(rs2.getString("SELL_PRICE"))*Integer.parseInt(rs2.getString("QUANTITY"));
					String stotal = String.format("%.2f", subtotal);
					cell = HSSFUtil.createAmountCell(wb,row, (short) 8,false,false);
					cell.setCellValue(stotal);
					totalSubtotal += subtotal;
					
					
					currentRow++;
					rowCounter++;
					sheet.shiftRows(rowCounter+1,rowCounter+2,1);
					
					if(currentRow == countRows){
						//TOTAL
						query = "SELECT SUM(SELL_PRICE*QUANTITY) FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
						ResultSet rs4 = Main.getDBManager().executeQuery(query);
						if(rs4.next())
						{
							cell = HSSFUtil.createAmountCell(wb,row, (short) 9,false,false);
							cell.setCellValue(String.format("%.2f",Double.parseDouble(rs4.getString(1))));
							totalTotal +=Double.parseDouble(rs4.getString(1));
						}
						
						//cashier id
						cell = HSSFUtil.createStringCell(wb,row, (short) 10,false,false);
						cell.setCellValue(rs.getString("ENCODER_CODE"));
						
						//sales specialist
						cell = HSSFUtil.createStringCell(wb,row, (short) 11,false,false);
						cell.setCellValue(rs.getString("ASSIST_CODE"));
					}
				}
			}
			
			//totals
			writeTotals();
			
			// Write the output to a file
			ReportUtility.writeOutputToFile(wb, fileName);
			return true;
			
		} catch (FileNotFoundException e) {
			
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}

	}
	
	public static void writeTotals() {
		HSSFRow row = ReportUtility.getSheet(wb).getRow(rowCounter+2);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,false);
		cell.setCellValue("TOTAL");
		cell = HSSFUtil.createStringCell(wb,row, (short) 4,false,false);
		cell.setCellValue(totalQty);
		cell = HSSFUtil.createStringCell(wb,row, (short) 5,false,false);
		cell.setCellValue(String.format("%.2f",totalSellPrice));
		cell = HSSFUtil.createStringCell(wb,row, (short) 7,false,false);
		cell.setCellValue(String.format("%.2f",totalDiscPrice));
		cell = HSSFUtil.createStringCell(wb,row, (short) 8,false,false);
		cell.setCellValue(String.format("%.2f",totalSubtotal));
		cell = HSSFUtil.createStringCell(wb,row, (short) 9,false,false);
		cell.setCellValue(String.format("%.2f",totalTotal));
	}
}

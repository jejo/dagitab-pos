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
import bus.InvoiceItemService;

public class DailySalesReport {
	
	private int topMarker = 8;
	private HSSFWorkbook wb;
	private Integer rowCounter = topMarker;
	private Integer totalQty = 0;
	private Double totalSellPrice = 0d;
	private Double totalDiscPrice = 0d;
	private Double totalSubtotal =0d;
	private Double totalTotal = 0d;
	
	public boolean generate(String fileName, String startDate, String endDate){
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
				double totalPerInvoice=0.0d;
				while(rs2.next())
				{
					HSSFRow row = sheet.getRow(rowCounter);
					
					if(currentRow == 0)
					{
						//OR no				
						cell = HSSFUtil.createAmountCell(wb,row, (short) 0,false,false);
						cell.setCellValue(rs.getString("OR_NO"));
					
						//invoice no
						cell = HSSFUtil.createAmountCell(wb,row, (short) 1,false,false);
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
					
					//qty
					cell = HSSFUtil.createAmountCell(wb,row, (short) 4,false,false);
					cell.setCellValue(rs2.getInt("QUANTITY"));
					totalQty+=rs2.getInt("QUANTITY");
					
					//disc
					query = "SELECT * FROM discount_lu WHERE DISC_NO = "+rs2.getString("DISC_CODE");
					rs3 = Main.getDBManager().executeQuery(query);
					if(rs3.next())
					{
						
						cell = HSSFUtil.createAmountCell(wb,row, (short) 6,false,false);
						cell.setCellValue(rs3.getString("DISC_RATE")+"%");
					}
					
					//disc unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
					Double discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(rs2.getLong("OR_NO"),rs2.getString("PROD_CODE"));
					cell.setCellValue(String.format("%.2f",discountedAmount));
					totalDiscPrice += discountedAmount;
					
					
					//subtotal
					double subtotal = discountedAmount*rs2.getInt("QUANTITY");
					String stotal = String.format("%.2f", subtotal);
					cell = HSSFUtil.createAmountCell(wb,row, (short) 8,false,false);
					cell.setCellValue(stotal);
					totalSubtotal += subtotal;
					totalPerInvoice += subtotal;
					
					
					currentRow++;
					rowCounter++;
					sheet.shiftRows(rowCounter+1,rowCounter+2,1);
					
					if(currentRow == countRows){
						//TOTAL
						cell = HSSFUtil.createAmountCell(wb,row, (short) 9,false,false);
						cell.setCellValue(String.format("%.2f",totalPerInvoice));
						totalTotal += totalPerInvoice;
						
						//cashier id
						cell = HSSFUtil.createIntCell(wb,row, (short) 10,false,false);
						cell.setCellValue(rs.getString("ENCODER_CODE"));
						
						//sales specialist
						cell = HSSFUtil.createIntCell(wb,row, (short) 11,false,false);
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
	
	public  void writeTotals() {
		HSSFRow row = ReportUtility.getSheet(wb).getRow(rowCounter+2);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,false);
		cell.setCellValue("TOTAL");
		cell = HSSFUtil.createAmountCell(wb,row, (short) 4,false,false);
		cell.setCellValue(totalQty);
		cell = HSSFUtil.createAmountCell(wb,row, (short) 5,false,false);
		cell.setCellValue(String.format("%.2f",totalSellPrice));
		cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
		cell.setCellValue(String.format("%.2f",totalDiscPrice));
		cell = HSSFUtil.createAmountCell(wb,row, (short) 8,false,false);
		cell.setCellValue(String.format("%.2f",totalSubtotal));
		cell = HSSFUtil.createAmountCell(wb,row, (short) 9,false,false);
		cell.setCellValue(String.format("%.2f",totalTotal));
	}
}

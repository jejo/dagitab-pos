package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.Main;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import util.LoggerUtility;
import bus.DiscountService;
import bus.InvoiceItemService;
import bus.ProductService;
import bus.ReportService;
import domain.Invoice;
import domain.InvoiceItem;
import domain.Product;
import domain.ReturnItem;

public class DailySalesReport {
	
	private int topMarker = 8;
	private HSSFWorkbook wb;
	private Integer rowCounter = topMarker;
	private Integer totalQty = 0;
	private Double totalSellPrice = 0d;
	private Double totalDiscPrice = 0d;
	private Double totalSubtotal =0d;
	private Double totalTotal = 0d;
	private static Logger logger = Logger.getLogger(DailySalesReport.class);
	
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
			
			List<Invoice> invoiceList = ReportService.getInstance().getInvoice(startDate, endDate);
			
			for(Invoice invoice: invoiceList){
				
				int totalRowSize = invoice.getInvoiceItems().size()+invoice.getReturnedItems().size();
				int currentRowSize = 0;
				
				for(InvoiceItem invoiceItem: invoice.getInvoiceItems()){
					HSSFRow row = sheet.getRow(rowCounter);
					
					if(currentRowSize == 0){
						//OR no				
						cell = HSSFUtil.createIntCell(wb,row, (short) 0,false,false);
						cell.setCellValue(invoice.getOrNo());
					
						//invoice no
						cell = HSSFUtil.createIntCell(wb,row, (short) 1,false,false);
						cell.setCellValue(invoice.getInvoiceNo());
					}
					
					
					
					Product product = ProductService.getProductById(invoiceItem.getProductCode());
					
					//product code
					cell = HSSFUtil.createStringCell(wb,row, (short) 2,false,false);
					cell.setCellValue(product.getProdCode());
					
					//product name
					cell = HSSFUtil.createStringCell(wb,row, (short) 3,false,false);
					cell.setCellValue(product.getName());
					
					//qty
					cell = HSSFUtil.createIntCell(wb,row, (short) 4,false,false);
					cell.setCellValue(invoiceItem.getQuantity());
					
					
					//unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 5,false,false);
					cell.setCellValue(Double.parseDouble(String.format("%.2f", product.getSellPrice())));
					
					//disc
					cell = HSSFUtil.createPercentCell(wb,row, (short) 6,false,false);
					cell.setCellValue(DiscountService.getDiscountPercentage(invoiceItem.getDiscountCode()));
					
					//disc unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
					Double discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(invoiceItem.getOrNo(),invoiceItem.getProductCode());
					discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
					cell.setCellValue(discountedAmount);
					
					//subtotal
					double subTotal = discountedAmount*invoiceItem.getQuantity();
					subTotal = Double.valueOf(String.format("%.2f", subTotal));
					cell = HSSFUtil.createAmountCell(wb,row, (short) 8,false,false);
					cell.setCellValue(subTotal);
					
					rowCounter++;
					currentRowSize++;
					sheet.shiftRows(rowCounter+1,rowCounter+2,1);
					
					if(currentRowSize == totalRowSize){
						//TOTAL
						cell = HSSFUtil.createAmountCell(wb,row, (short) 9,false,false);
						Double total = Double.valueOf(String.format("%.2f",ReportService.getInstance().getTotalPerInvoice(invoice)));
						if(total < 0){
							total = 0.0d;
						}
						cell.setCellValue(total);
						
						//cashier id
						cell = HSSFUtil.createIntCell(wb,row, (short) 10,false,false);
						cell.setCellValue(invoice.getEncoderCode());
						
						//sales specialist
						cell = HSSFUtil.createIntCell(wb,row, (short) 11,false,false);
						cell.setCellValue(invoice.getAssistantCode());
					}
				}
				
				
				for(ReturnItem returnItem: invoice.getReturnedItems()){
					
					HSSFRow row = sheet.getRow(rowCounter);
					
					if(currentRowSize == 0){
						//OR no				
						cell = HSSFUtil.createIntCell(wb,row, (short) 0,false,false);
						cell.setCellValue(invoice.getOrNo());
					
						//invoice no
						cell = HSSFUtil.createIntCell(wb,row, (short) 1,false,false);
						cell.setCellValue(invoice.getInvoiceNo());
					}
					
					Product product = ProductService.getProductById(returnItem.getProductCode());
					
					//product code
					cell = HSSFUtil.createStringCell(wb,row, (short) 2,false,false);
					cell.setCellValue(product.getProdCode());
					
					//product name
					cell = HSSFUtil.createStringCell(wb,row, (short) 3,false,false);
					cell.setCellValue(product.getName());
					
					//qty
					cell = HSSFUtil.createIntCell(wb,row, (short) 4,false,false);
					cell.setCellValue(returnItem.getQuantity()*-1); //negative to mark as change
					
					
					//unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 5,false,false);
					cell.setCellValue(Double.parseDouble(String.format("%.2f", returnItem.getSellPrice()))*-1);
					
					//disc
					cell = HSSFUtil.createStringCell(wb,row, (short) 6,false,false);
					cell.setCellValue("N/A");
					
					//disc unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
					Double discountedAmount = returnItem.getSellPrice()*-1; //discounted price is negative when returned
					discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
					cell.setCellValue(discountedAmount);
					
					//subtotal
					double subTotal = discountedAmount*returnItem.getQuantity();
					subTotal = Double.valueOf(String.format("%.2f", subTotal));
					cell = HSSFUtil.createAmountCell(wb,row, (short) 8,false,false);
					cell.setCellValue(subTotal);
					
					
					
					
					rowCounter++;
					currentRowSize++;
					sheet.shiftRows(rowCounter+1,rowCounter+2,1);
					
					if(currentRowSize == totalRowSize){
						//TOTAL
						cell = HSSFUtil.createAmountCell(wb,row, (short) 9,false,false);
						Double total = Double.valueOf(String.format("%.2f",ReportService.getInstance().getTotalPerInvoice(invoice)));
						if(total < 0){
							total = 0.0d;
						}
						cell.setCellValue(total);
						
						//cashier id
						cell = HSSFUtil.createIntCell(wb,row, (short) 10,false,false);
						cell.setCellValue(invoice.getEncoderCode());
						
						//sales specialist
						cell = HSSFUtil.createIntCell(wb,row, (short) 11,false,false);
						cell.setCellValue(invoice.getAssistantCode());
					}
					
				}
				
				
				
			}
			
			
			writeTotalAmounts(topMarker,rowCounter);
			
			
			// Write the output to a file
			ReportUtility.writeOutputToFile(wb, fileName);
			return true;
			
		} catch (FileNotFoundException e) {
			
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
	}
	
	public boolean generateTemp(String fileName, String startDate, String endDate){
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
				
				queryCount = "SELECT COUNT(1) FROM returned_items WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
				
				rs2 = Main.getDBManager().executeQuery(queryCount);
				if(rs2.next()){
					countRows += rs2.getInt(1);
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
					discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
					cell.setCellValue(discountedAmount);
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
				
				//query return and display negative
				String queryReturn = "SELECT * FROM returned_items WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
				ResultSet resultSet = Main.getDBManager().executeQuery(queryReturn);
				
				while(resultSet.next())
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
					cell.setCellValue(resultSet.getString("PROD_CODE"));
					
					query = "SELECT * FROM products_lu WHERE PROD_CODE = \""+resultSet.getString("PROD_CODE")+"\"";
					ResultSet rs3 = Main.getDBManager().executeQuery(query);
					if(rs3.next())
					{
						//product name
						cell = HSSFUtil.createStringCell(wb,row, (short) 3,false,false);
						cell.setCellValue(rs3.getString("NAME"));
						
						
					}
					//unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 5,false,false);
					cell.setCellValue(String.format("%.2f", resultSet.getDouble("SELL_PRICE")*-1));
					totalSellPrice += resultSet.getDouble("SELL_PRICE")*-1;
					
					//qty
					cell = HSSFUtil.createAmountCell(wb,row, (short) 4,false,false);
					cell.setCellValue(resultSet.getInt("QUANTITY")*-1);
					//totalQty+=resultSet.getInt("QUANTITY");
					
					
					//DISCOUNT
					cell = HSSFUtil.createAmountCell(wb,row, (short) 6,false,false);
					cell.setCellValue("N/A");
					
					
					//discounted price is negative is it is returned
					cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
					Double discountedAmount = resultSet.getDouble("SELL_PRICE")*-1;
					cell.setCellValue(String.format("%.2f",discountedAmount));
					totalDiscPrice += discountedAmount;
					
					
					//subtotal
					double subtotal = discountedAmount*resultSet.getInt("QUANTITY");
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
	
	public void writeTotalAmounts(int firstRow, int lastRow){
		HSSFRow row = ReportUtility.getSheet(wb).getRow(rowCounter+2);
		
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,true);
		cell.setCellValue("TOTAL");
		
		String totalQtyFormula = "SUM(E"+firstRow+":E"+lastRow+")";
		cell = HSSFUtil.createFormulaCell(wb,row, (short) 4,totalQtyFormula,false,false);
		
		String totalSellPriceFormula = "SUM(F"+firstRow+":F"+lastRow+")";
		cell = HSSFUtil.createFormulaCell(wb,row, (short) 5,totalSellPriceFormula,false,false);
		
		String totalDiscPriceFormula = "SUM(H"+firstRow+":H"+lastRow+")";
		cell = HSSFUtil.createFormulaCell(wb,row, (short) 7,totalDiscPriceFormula,false,false);
		
		String totalSubtotalFormula = "SUM(I"+firstRow+":I"+lastRow+")";
		cell = HSSFUtil.createFormulaCell(wb,row, (short) 8,totalSubtotalFormula,false,false);
		
		String totalTotalFormula = "SUM(J"+firstRow+":J"+lastRow+")";
		cell = HSSFUtil.createFormulaCell(wb,row, (short) 9,totalTotalFormula,false,false);
		
	}
}

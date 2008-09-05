package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;

import main.Main;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import bus.ComplianceService;
import bus.VatService;

import util.LoggerUtility;

public class ZSummaryReport {
	
	
	private  int topMarker = 7;
	private  HSSFWorkbook wb;
	
	//AMOUNTS
	private  Double cash = 0d;
	private  Double check = 0d;
	private  Double card = 0d;
	private  Double gc = 0d;
	private  Double others = 0d;
	private  Double grossAmount = 0d;
	private  Double discountAmount = 0d;
	private  Double netAmount = 0d;
	private  Double vatAmount = 0d;
	
	
	
	//TOTALS
	private  Double cashTotal = 0d;
	private  Double checkTotal = 0d;
	private  Double cardTotal = 0d;
	private  Double gcTotal = 0d;
	private  Double othersTotal=0d;
	private  Double grossAmountTotal = 0d;
	private  Double discountAmountTotal = 0d;
	private  Double netAmountTotal = 0d;
	private  Double vatAmountTotal = 0d;
	private  Double totalTotal=0d;
	
	private  Integer rowCounter = topMarker;
	
	
	public  boolean generate(String fileName, String startDate, String endDate) {
		HSSFCell cell;
		POIFSFileSystem fs;
		rowCounter = topMarker;
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/zsum.xls"));

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
				
				
				//CASH PAYMENT
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
				
				
				//CARD PAYMENT
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
				
				
				//GC PAYMENT
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
				
				
				
				//CHECK PAYMENT
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
				
				
				//OTHER PAYMENT
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
				
				//GROSS AMOUNT
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(it.SELL_PRICE * it.QUANTITY) FROM invoice_item it WHERE it.or_no = "+rs.getInt(1));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 7, false, true);
				if(rs2.next()){
					grossAmount = rs2.getDouble(1);
					grossAmountTotal+=grossAmount;
				}
				else{
					grossAmount = 0d;
				}
				cell.setCellValue(grossAmount);
				
				
				
				//DISCOUNT AMOUNT
				rs2 = Main.getDBManager().executeQuery("SELECT sum(d.DISC_RATE/100 *i.sell_price*i.quantity) FROM discount_lu d, invoice_item i WHERE d.disc_no = i.disc_code AND i.or_no = "+rs.getInt(1));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 8, false, true);
				if(rs2.next()){
					discountAmount = rs2.getDouble(1);
					discountAmountTotal+=discountAmount;
				}
				else{
					discountAmount = 0d;
				}
				cell.setCellValue(discountAmount);
				
				//NET AMOUNT
				cell = HSSFUtil.createAmountCell(wb, row, (short) 9, false, true);
				netAmount = (grossAmount - discountAmount) / VatService.getVatRate();
				netAmountTotal+=netAmount;
				cell.setCellValue(netAmount);
				
				
				//VAT AMOUNT
				cell = HSSFUtil.createAmountCell(wb, row, (short) 10, false, true);
				vatAmount = (grossAmount - discountAmount) * (VatService.getVatAmount() / 100);
				vatAmountTotal+=vatAmount;
				cell.setCellValue(vatAmount);
				
				

				double total= cash+check+card+others; //removed gc
				totalTotal += total;
				
				cell = HSSFUtil.createAmountCell(wb,row,(short)11,false,true);
				cell.setCellValue(total);
				
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);
			}
			
			//totals
			writeTotals();
			writeTotalAmountReturn(startDate);
			writePreviousAccumulatedGrandTotal(startDate);
			writeCurrentAccumulatedGrandTotal(startDate);
			
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
	
	public  void writeTotals() {
		rowCounter += 2;
		HSSFRow row = ReportUtility.getSheet(wb).createRow(rowCounter);
		
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
		cell.setCellValue(grossAmountTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)8,true,true);
		cell.setCellValue(discountAmountTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)9,true,true);
		cell.setCellValue(netAmountTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)10,true,true);
		cell.setCellValue(vatAmountTotal);
		cell = HSSFUtil.createAmountCell(wb,row,(short)11,true,true);
		cell.setCellValue(totalTotal);
//		cell = HSSFUtil.createAmountCell(wb,row,(short)11,true,true);
	}
	
	public  void writeTotalAmountReturn(String startDate){
		String[] date = startDate.split("-");
		rowCounter += 2;
		HSSFRow row = ReportUtility.getSheet(wb).createRow(rowCounter);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row,(short)0,true,true);
		cell.setCellValue("Total Amount Return");
		cell = HSSFUtil.createAmountCell(wb,row,(short)1,true,true);
		cell.setCellValue(ComplianceService.getComplianceService().getReturnedItemsAmount(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode())));
	}
	
	public  void writePreviousAccumulatedGrandTotal(String startDate){
		String[] date = startDate.split("-");
		rowCounter += 1;
		HSSFRow row = ReportUtility.getSheet(wb).createRow(rowCounter);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row,(short)0,true,true);
		cell.setCellValue("Previous Accumulated Grand Total");
		cell = HSSFUtil.createAmountCell(wb,row,(short)1,true,true);
		cell.setCellValue(ComplianceService.getComplianceService().getOldGT(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode())));
	}
	
	public  void writeCurrentAccumulatedGrandTotal(String startDate){
		String[] date = startDate.split("-");
		rowCounter += 1;
		HSSFRow row = ReportUtility.getSheet(wb).createRow(rowCounter);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row,(short)0,true,true);
		cell.setCellValue("Current Accumulated Grand Total");
		cell = HSSFUtil.createAmountCell(wb,row,(short)1,true,true);
		cell.setCellValue(ComplianceService.getComplianceService().getNewGT(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode())));
	}
}

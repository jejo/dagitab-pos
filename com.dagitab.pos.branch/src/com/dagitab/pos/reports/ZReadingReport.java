package com.dagitab.pos.reports;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;

import com.dagitab.pos.bus.ComplianceService;
import com.dagitab.pos.bus.VatService;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;

public class ZReadingReport {
	
	public static final String TAB = "\t";
	public static final String NEW_LINE = "\r\n";
	public static final String COMMA = ", ";
	
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
	
	
	public  boolean generate(String fileName, String startDate, String endDate) {
		
		try{
			File file = new File(fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			
			
			StringBuffer text = new StringBuffer();
	
			//headers
			text.append("BABYLAND - Z-Reading"+NEW_LINE);
			text.append("DATE: "+startDate+NEW_LINE);
			
			text.append(NEW_LINE);
			
			//column headers
			text.append("Invoice No"+COMMA+"Gross Amount"+COMMA+"Net"+COMMA+"Vat"+COMMA+"Total Payment"+NEW_LINE);
			
			
			String branchClause=" && i.store_code="+Main.getStoreCode();
			ResultSet rs = Main.getDBManager().executeQuery("SELECT i.or_no,i.store_code,i.cust_no,i.encoder_code FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' "+branchClause);
			while(rs.next()) {
			
				//Invoice No
				String invoiceNo = rs.getString(1);
				text.append(invoiceNo+COMMA);
				
				//CASH PAYMENT
				ResultSet  rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Cash\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				if(rs2.next()) {
					cash=rs2.getDouble(1);
					cashTotal+=cash;
				}
				else {
					cash=0d;
				}
				
				
				
				//CARD PAYMENT
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Credit Card\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				if(rs2.next()) {
					card=rs2.getDouble(1);
					cardTotal+=card;
				}
				else {
					card=0d;
				}
				
				//GC PAYMENT
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				if(rs2.next()) {
					gc=rs2.getDouble(1);
					gcTotal+=gc;
				}
				else {
					gc=0d;
				}
				
				
				//CHECK PAYMENT
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Bank Check\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				if(rs2.next()) {
					check=rs2.getDouble(1);
					checkTotal+=check;
				}
				else {
					check=0d;
				}
				
				//OTHER PAYMENT
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name!=\"Bank Check\" && ptype.name!=\"Cash\" && ptype.name!=\"Credit Card\" && ptype.name!=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				if(rs2.next()) {
					others=rs2.getDouble(1);
					othersTotal+=others;
				}
				else {
					others=0d;
				}
				
				
				//GROSS AMOUNT
				rs2 = Main.getDBManager().executeQuery("SELECT SUM(it.SELL_PRICE * it.QUANTITY) FROM invoice_item it WHERE it.or_no = "+rs.getInt(1));
				if(rs2.next()){
					grossAmount = rs2.getDouble(1);
					grossAmountTotal+=grossAmount;
				}
				else{
					grossAmount = 0d;
				}
				text.append(String.format("%.2f",grossAmount)+COMMA);
				
				//DISCOUNT AMOUNT
				rs2 = Main.getDBManager().executeQuery("SELECT sum(d.DISC_RATE/100 *i.sell_price*i.quantity) FROM discount_lu d, invoice_item i WHERE d.disc_no = i.disc_code AND i.or_no = "+rs.getInt(1));
				if(rs2.next()){
					discountAmount = rs2.getDouble(1);
					discountAmountTotal+=discountAmount;
				}
				else{
					discountAmount = 0d;
				}
				
				
				//NET AMOUNT
				netAmount = (grossAmount - discountAmount) / VatService.getVatRate();
				netAmountTotal+=netAmount;
				text.append(String.format("%.2f",netAmount)+COMMA);
				
				
				//VAT AMOUNT
				vatAmount = (grossAmount - discountAmount) * (VatService.getVatAmount() / 100);
				vatAmountTotal+=vatAmount;
				text.append(String.format("%.2f",vatAmount)+COMMA);
				
				double total= cash+check+card+others; //removed gc
				totalTotal += total;
				text.append(String.format("%.2f",total)+COMMA);
				
				text.append(NEW_LINE);
			}
			text.append(NEW_LINE);
			
			//TOTALS
			text.append("Gross Amount Total"+COMMA+String.format("%.2f", grossAmountTotal)+COMMA+NEW_LINE);
			text.append("Net Total"+COMMA+String.format("%.2f",netAmountTotal)+COMMA+NEW_LINE);
			text.append("Vat Total"+COMMA+String.format("%.2f",vatAmountTotal)+COMMA+NEW_LINE);
			text.append("Total Payments:"+COMMA+String.format("%.2f",totalTotal)+COMMA+NEW_LINE);
			
			text.append(NEW_LINE);
			//OTHER TOTALS
			
			String[] date = startDate.split("-");
			Double totalAmountReturn = ComplianceService.getComplianceService().getReturnedItemsAmount(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode()));
			text.append("Total Amount Return:"+COMMA+totalAmountReturn+COMMA+NEW_LINE);
			
			Double previousAccumulatedGrandTotal = ComplianceService.getComplianceService().getOldGT(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode()));
			text.append("Previous Accumulated Grand Total:"+COMMA+previousAccumulatedGrandTotal+COMMA+NEW_LINE);
			
			Double currentAccumulatedGrandTotal = ComplianceService.getComplianceService().getNewGT(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode()));
			text.append("Current Accumulated Grand Total: "+COMMA+currentAccumulatedGrandTotal+COMMA+NEW_LINE);
			
			
			writer.write(text.toString());
			writer.flush();
			
		}
		catch(Exception e){
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
		
		return true;
		
	}
}

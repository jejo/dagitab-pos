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
			text.append("Invoice No"+COMMA+"Gross Amount"+COMMA+"Net Sale"+COMMA+"Vat Amount"+COMMA+"Total Payment"+NEW_LINE);
			
			
			String branchClause=" && i.store_code="+Main.getStoreCode();
			ResultSet rs = Main.getDBManager().executeQuery("SELECT i.or_no,i.store_code,i.cust_no,i.encoder_code FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' "+branchClause);
			while(rs.next()) {
			
				//Invoice No
				String invoiceNo = rs.getString(1);
				text.append(invoiceNo+COMMA);
				
				//CASH PAYMENT
				ResultSet  rs2 = Main.getDBManager().executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				double total;
				if(rs2.next()) {
					total=rs2.getDouble(1);
				}
				else {
					total=0d;
				}
				
				//GROSS AMOUNT
				grossAmount = ComplianceService.getComplianceService().getRawGross(rs.getInt(1), rs.getInt(2));
				grossAmountTotal+=grossAmount;
				
				text.append(String.format("%.2f",grossAmount)+COMMA);
				
				//NET AMOUNT
				netAmount = ComplianceService.getComplianceService().getNetSales(rs.getInt(1), rs.getInt(2));
				netAmountTotal+=netAmount;
				text.append(String.format("%.2f",netAmount)+COMMA);
				
				
				//VAT AMOUNT
				vatAmount = ComplianceService.getComplianceService().getVat(rs.getInt(1), rs.getInt(2));
				vatAmountTotal+=vatAmount;
				text.append(String.format("%.2f",vatAmount)+COMMA);
				
				totalTotal += total;
				text.append(String.format("%.2f",total));
				
				text.append(NEW_LINE);
			}
			text.append(NEW_LINE);
			
			//TOTALS
			text.append("Gross Amount Total: "+String.format("%.2f", grossAmountTotal)+NEW_LINE);
			text.append("Net Total: "+String.format("%.2f",netAmountTotal)+NEW_LINE);
			text.append("Vat Total: "+String.format("%.2f",vatAmountTotal)+NEW_LINE);
			text.append("Total Payments: "+String.format("%.2f",totalTotal)+NEW_LINE);
			
			text.append(NEW_LINE);
			//OTHER TOTALS
			
			String[] date = startDate.split("-");
			Double previousAccumulatedGrandTotal = ComplianceService.getComplianceService().getOldGT(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode()));
			text.append("Previous Accumulated Grand Total:"+previousAccumulatedGrandTotal+NEW_LINE);
			
			Double currentAccumulatedGrandTotal = ComplianceService.getComplianceService().getNewGT(Integer.parseInt(date[1]),Integer.parseInt(date[2]) , Integer.parseInt(date[0]), Integer.parseInt(Main.getStoreCode()));
			text.append("Current Accumulated Grand Total: "+currentAccumulatedGrandTotal+NEW_LINE);
			
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

package com.dagitab.pos.reports;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;

import org.apache.commons.lang.StringUtils;

import com.dagitab.pos.bus.ComplianceService;
import com.dagitab.pos.bus.VatService;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.StorePropertyHandler;

public class ZReadingReport {
	
	public static final String TAB = "\t";
	public static final String NEW_LINE = "\r\n";
	public static final String COMMA = ", ";
	
	public  boolean generate(String fileName, String startDate, String endDate) {
		
		try{
			File file = new File(fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			
			
			StringBuffer text = new StringBuffer();
	
			// only get last two digits, left padded by 0
			// storeNumber = storeNumber.substring(storeNumber.length() - 2);
			String tenantsId = StorePropertyHandler.getTenantNo();
			
			String terminalNumber = StringUtils.leftPad(StorePropertyHandler.getTerminalNo(),2,"0");
			
			//headers
			text.append("               Z-READING                "+NEW_LINE);
			text.append("----------------------------------------"+NEW_LINE);
			text.append("Tenant:              BABYLAND"+NEW_LINE);
			text.append("Terminal No:         " +terminalNumber + NEW_LINE);
			text.append("Level:               3rd Floor"+NEW_LINE);
			text.append("Mall:                Festival, Alabang"+NEW_LINE);
			text.append("Date:                "+startDate+NEW_LINE);
			text.append("----------------------------------------"+NEW_LINE);
			
			//TOTALS
			String[] date = startDate.split("-");
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			int year = Integer.parseInt(date[0]);
			int storeCode = Integer.parseInt(Main.getStoreCode());
			
			Double grossSalesAmount = ComplianceService.getComplianceService().getRawGross(month, day, year, storeCode);
			Double netSalesAmount = ComplianceService.getComplianceService().getNetSales(month, day, year, storeCode);
			Double otherDiscountAmount = grossSalesAmount - netSalesAmount;
			Double netSalesWithoutVat = ComplianceService.getComplianceService().getNetSalesWithoutVat(month, day, year, storeCode);
			Double vatAmountTotal = ComplianceService.getComplianceService().getVat(month, day, year, storeCode);
			text.append("Discount:            " + String.format("%.2f",otherDiscountAmount) + NEW_LINE);
			text.append("Refund:              0.00" + NEW_LINE );
			text.append("Void:                0.00"  + NEW_LINE);
			text.append("Vatable Gross Sales: "+String.format("%.2f", netSalesAmount)+NEW_LINE);
			text.append("Net of VAT:          "+String.format("%.2f",netSalesWithoutVat)+NEW_LINE);
			text.append("Tax Amount:          "+String.format("%.2f",vatAmountTotal)+NEW_LINE);
			
			text.append("----------------------------------------"+NEW_LINE);
			//OTHER TOTALS
			
			Double previousAccumulatedGrandTotal = ComplianceService.getComplianceService().getOldGT(month,day,year, storeCode );
			text.append("Old Grand Total:     "+previousAccumulatedGrandTotal+NEW_LINE);
			
			Double currentAccumulatedGrandTotal = ComplianceService.getComplianceService().getNewGT(month,day,year, storeCode );
			text.append("New Grand Total:     "+currentAccumulatedGrandTotal+NEW_LINE);
			
			writer.write(text.toString());
			writer.flush();
			
		}
		catch(Exception e){
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
		
		return true;
		
	}
	
	public String removeDecimalPoint(Double number, int noOfDecimalPlaces) {
		return String.format("%." + noOfDecimalPlaces +"f", number).replace(".", "");
	}
}

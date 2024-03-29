package com.dagitab.pos.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dagitab.pos.bus.ComplianceService;
import com.dagitab.pos.bus.ReportService;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;

public class EastwoodSalesReport {
	private int topMarker = 8;
	private Integer rowCounter = topMarker;
	private HSSFWorkbook wb;
	
	private static Logger logger = Logger.getLogger(RobinsonsSalesReport.class);
	
	
	private Double totalGrossSales = 0.0d;
	private Double totalDiscounts = 0.0d;
	private Double totalCashSales = 0.0d;
	private Double totalCreditSales = 0.0d;
	private Double totalGCSales = 0.0d;
	private Double totalNetSales = 0.0d;
	
	public boolean generate(String fileName, int month, int year) throws Exception{
		
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/eastwoodsales.xls"));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			ReportUtility.writeDateGenerated(wb);
			logger.debug("JEJO MONTH PASSED: "+month+" "+year);
			ArrayList<String> dates = ReportService.getInstance().getEastwoodInvoiceDates(month, year);
			Calendar calendar = new GregorianCalendar();
			
			for(int i = 0; i<dates.size(); i++){
				
				HSSFRow row = sheet.createRow(rowCounter);
				System.out.println("Dates: "+dates.get(i));
				cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
				cell.setCellValue(dates.get(i));
				
				calendar.set(Calendar.MONTH, Integer.parseInt(dates.get(i).split("-")[1])-1);
				logger.debug(dates.get(i).split("-")[1]);
				calendar.set(Calendar.YEAR, Integer.parseInt(dates.get(i).split("-")[0]));
				logger.debug(dates.get(i).split("-")[0]);
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates.get(i).split("-")[2]));
				logger.debug(dates.get(i).split("-")[2]);
				
				
				calendar.set(Calendar.HOUR_OF_DAY, 9); // set to 9am
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				
				logger.debug("JEJO DATE GENERATED: "+calendar.getTime().toString());
				
				Double cashSales = ComplianceService.getComplianceService().getSalesForPaymentTypeWithoutDiscount(calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),  Integer.parseInt(Main.getStoreCode()), 1);
				logger.debug("JEJO CASH SALES AT: "+(calendar.get(Calendar.MONTH)+1)+" "+calendar.get(Calendar.DAY_OF_MONTH)+": "+cashSales);
				cell = HSSFUtil.createAmountCell(wb, row, (short) 1, false, true);
				cell.setCellValue(cashSales);
				
				
				Double creditSales = ComplianceService.getComplianceService().getSalesForPaymentTypeWithoutDiscount(calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),  Integer.parseInt(Main.getStoreCode()), 3);
				cell = HSSFUtil.createAmountCell(wb, row, (short) 2, false, true);
				cell.setCellValue(creditSales);
				
				Double gcSales = ComplianceService.getComplianceService().getSalesForPaymentTypeWithoutDiscount(calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),  Integer.parseInt(Main.getStoreCode()), 4);
				cell = HSSFUtil.createAmountCell(wb, row, (short) 3, false, true);
				cell.setCellValue(gcSales);
				
				Double grossSalesAmount = ComplianceService.getComplianceService().getRawGross(calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),  Integer.parseInt(Main.getStoreCode()));
				Double netSalesAmount = ComplianceService.getComplianceService().getNetSales(calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),  Integer.parseInt(Main.getStoreCode()));
				Double totalDiscount = grossSalesAmount - netSalesAmount;
				
				cell = HSSFUtil.createAmountCell(wb, row, (short) 4, false, true);
				cell.setCellValue(totalDiscount);
				
				cell = HSSFUtil.createAmountCell(wb, row, (short) 5, false, true);
				cell.setCellValue(0.0d);
				
				cell = HSSFUtil.createAmountCell(wb, row, (short) 6, false, true);
				cell.setCellValue(0.0d);
				
				Double grossSales = ReportService.getInstance().getGrossSales(dates.get(i));
//				Double grossSales = ReportService.getInstance().getNetSalesBeforeTax(dates.get(i));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 7, false, true);
				cell.setCellValue(grossSales);
				
				
				Double netSales = ComplianceService.getComplianceService().getNetSales(calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),  Integer.parseInt(Main.getStoreCode()));
//				Double grossSales = ReportService.getInstance().getNetSalesBeforeTax(dates.get(i));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 8, false, true);
				cell.setCellValue(netSales);
				
				
				totalGrossSales += grossSales;
				totalDiscounts += totalDiscount;
				
				totalCashSales += cashSales;
				totalGCSales += gcSales;
				totalCreditSales += creditSales;
				totalNetSales += netSales;
				
				rowCounter++;
				
			}
			
			
			
			
			HSSFRow row = sheet.createRow(rowCounter++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
			cell.setCellValue("Totals");
			cell = HSSFUtil.createAmountCell(wb, row, (short) 1,false,true);
			cell.setCellValue(totalCashSales);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 2,false,true);
			cell.setCellValue(totalCreditSales);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 3,false,true);
			cell.setCellValue(totalGCSales);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 4,false,true);
			cell.setCellValue(totalDiscounts);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 5,false,true);
			cell.setCellValue(0.0d);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 6,false,true);
			cell.setCellValue(0.0d);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 7,false,true);
			cell.setCellValue(totalGrossSales);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 8,false,true);
			cell.setCellValue(totalNetSales);
			rowCounter++;
			
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
}

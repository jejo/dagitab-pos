package com.dagitab.pos.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dagitab.pos.bus.ReportService;
import com.dagitab.pos.bus.RobinsonsComplianceService;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.DateUtility;
import com.dagitab.pos.util.LoggerUtility;


public class RobinsonsSalesReport {
	private int topMarker = 8;
	private Integer rowCounter = topMarker;
	private HSSFWorkbook wb;
	
	private static Logger logger = Logger.getLogger(RobinsonsSalesReport.class);
	
	
	private Double totalGrossSales = 0.0d;
	private Double totalPromoWithApproval = 0.0d;
	private Double totalNetSales = 0.0d;
	private Double totalPartialSales = 0.0d;
	private Double totalVipAmount = 0.0d;
	public boolean generate(String fileName, int month, int year) throws Exception{
		
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("xls/robinsonssales.xls"));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			writeDateGenerated(wb);
			
			ArrayList<String> dates = ReportService.getInstance().getRobinsonsInvoiceDates(month, year);
			
			for(int i = 0; i<dates.size(); i++){
				
				HSSFRow row = sheet.createRow(rowCounter);
				System.out.println("Dates: "+dates.get(i));
				cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
				cell.setCellValue(dates.get(i));
				Calendar calendar = Calendar.getInstance();
				
				calendar.set(Calendar.MONTH, Integer.parseInt(dates.get(i).split("-")[1])-1);
				calendar.set(Calendar.YEAR, Integer.parseInt(dates.get(i).split("-")[0]));
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates.get(i).split("-")[2]));
				
				calendar.set(Calendar.HOUR_OF_DAY, 9); // set to 9am
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				
				Date transDate = calendar.getTime();
				
				Date eodDate = RobinsonsComplianceService.getInstance().getEodDateBasedOnTransDate(transDate);
				java.sql.Timestamp trans = new java.sql.Timestamp(DateUtility.getDateUtility().convertUtilDateToSqlDate(transDate).getTime());
				java.sql.Timestamp eod = new java.sql.Timestamp(DateUtility.getDateUtility().convertUtilDateToSqlDate(eodDate).getTime());
				logger.info("ReportService.getInstance().getMinOrNo("+trans+","+ eod +", "+Integer.parseInt(Main.getStoreCode()));
				Integer minOrNo = ReportService.getInstance().getMinOrNo(trans, eod, Integer.parseInt(Main.getStoreCode()));
				if(minOrNo.equals(0)){
					cell = HSSFUtil.createStringCell(wb, row, (short) 1, false, true);
					cell.setCellValue("-");
				}
				else{
					cell = HSSFUtil.createIntCell(wb, row, (short) 1, false, true);
					cell.setCellValue(minOrNo);
				}
				
				Integer maxOrNo = ReportService.getInstance().getMaxOrNo(trans, eod, Integer.parseInt(Main.getStoreCode()));
				if(maxOrNo.equals(0)){
					cell = HSSFUtil.createStringCell(wb, row, (short) 2, false, true);
					cell.setCellValue("-");
				}
				else{
					cell = HSSFUtil.createIntCell(wb, row, (short) 2, false, true);
					cell.setCellValue(maxOrNo);
				}
				
				
				Double partialSales = ReportService.getInstance().getPartialTransactionTotal(trans, eod, Integer.parseInt(Main.getStoreCode()));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 3, false, true);
				cell.setCellValue(partialSales);
				
				Double netSales = ReportService.getInstance().getNetSalesBeforeTax(trans, eod, Integer.parseInt(Main.getStoreCode()));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 4, false, true);
				cell.setCellValue(netSales);
				
				Double promoWithApproval = ReportService.getInstance().getApprovedDiscounts(trans, eod, Integer.parseInt(Main.getStoreCode()));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 5, false, true);
				cell.setCellValue(promoWithApproval);
				
				cell = HSSFUtil.createAmountCell(wb, row, (short) 6, false, true);
				cell.setCellValue(0.0d);
				
				cell = HSSFUtil.createAmountCell(wb, row, (short) 7, false, true);
				cell.setCellValue(0.0d);
				
				Double vipAmount = ReportService.getInstance().getTotalVipDiscount(trans, eod, Integer.parseInt(Main.getStoreCode()));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 8, false, true);
				cell.setCellValue(vipAmount);
				
				Double grossSales = ReportService.getInstance().getGrossSales(trans, eod, Integer.parseInt(Main.getStoreCode()));
				cell = HSSFUtil.createAmountCell(wb, row, (short) 9, false, true);
				cell.setCellValue(grossSales);
				
				
				totalGrossSales += grossSales;
				totalPromoWithApproval += promoWithApproval;
				totalNetSales += netSales;
				totalPartialSales += partialSales;
				totalVipAmount += vipAmount;
//				
				rowCounter++;
				
			}
			
			
			
			
			HSSFRow row = sheet.createRow(rowCounter++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
			cell.setCellValue("Totals");
			cell = HSSFUtil.createAmountCell(wb, row, (short) 3,false,true);
			cell.setCellValue(totalPartialSales);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 4,false,true);
			cell.setCellValue(totalNetSales);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 5,false,true);
			cell.setCellValue(totalPromoWithApproval);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 6,false,true);
			cell.setCellValue(0.0d);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 7,false,true);
			cell.setCellValue(0.0d);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 8,false,true);
			cell.setCellValue(totalVipAmount);
			cell = HSSFUtil.createAmountCell(wb, row, (short) 9,false,true);
			cell.setCellValue(totalGrossSales);
			
			rowCounter++;
			
			row = sheet.createRow(rowCounter++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
			cell.setCellValue("Total");
			
			cell = HSSFUtil.createAmountCell(wb, row, (short) 9,false,true);
			cell.setCellValue(totalGrossSales);
			
			
			row = sheet.createRow(rowCounter++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
			cell.setCellValue("Less: Promo Discounts with Approval");
			
			cell = HSSFUtil.createAmountCell(wb, row, (short) 9,false,true);
			cell.setCellValue(totalPromoWithApproval);
			
			
			row = sheet.createRow(rowCounter++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
			cell.setCellValue("Net Sales before Tax");
			
			cell = HSSFUtil.createAmountCell(wb, row, (short) 9,false,true);
			cell.setCellValue(totalNetSales);
			
			row = sheet.createRow(rowCounter++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,true);
			cell.setCellValue("Less 12% VAT");
			
			
			Double totalNetSalesLessVat = ReportService.getInstance().getTotalNetSalesLessVat(totalNetSales);
			
			cell = HSSFUtil.createAmountCell(wb, row, (short) 9,false,true);
			cell.setCellValue(totalNetSalesLessVat);
			
			logger.info("total gross-sales: "+totalGrossSales);
			logger.info("total gross-promo-with-approval: "+totalPromoWithApproval);
			logger.info("difference: "+(totalGrossSales-totalPromoWithApproval));
			
			logger.info("total net sales: "+totalNetSales);
			
//			if(totalGrossSales-totalPromoWithApproval != totalNetSales){
//				throw new Exception("Total Net Sales is not equal with Total Gross Sales minus Total Promo Approval. Please contact pos vendor");
				
//			}
			
			
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
	
	public void writeDateGenerated(HSSFWorkbook wb) {
		HSSFRow row = wb.getSheetAt(0).getRow(3);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);

		SimpleDateFormat format = new SimpleDateFormat(
				"MMMM dd, yyyy hh:mm:ss a");
		cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
	}
	
	public void writeDateRange(String startDate, String endDate, HSSFWorkbook wb) {
		HSSFRow row = wb.getSheetAt(0).getRow(4);
		HSSFCell cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
		cell.setCellValue(startDate+ " - "+endDate);
		
		
	}
	
	
}

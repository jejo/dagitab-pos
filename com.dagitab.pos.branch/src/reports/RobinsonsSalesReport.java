package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import bus.ReportService;

import util.LoggerUtility;

public class RobinsonsSalesReport {
	private int topMarker = 8;
	private Integer rowCounter = topMarker;
	private HSSFWorkbook wb;
	
	private static Logger logger = Logger.getLogger(RobinsonsSalesReport.class);
	
	
	public boolean generate(String fileName, int month, int year){
		
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
				cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
				cell.setCellValue(dates.get(i));
//				
				rowCounter++;
				
			}
			
			
			
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

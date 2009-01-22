package util;

import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import reports.HSSFUtil;
import reports.ReportUtility;

public class ReceiptXLSUtility {
	private HSSFWorkbook wb;
	private static Logger logger = Logger.getLogger(ReceiptXLSUtility.class);
	private int rowMarker = 3;
	
	public static void main(String[] args){
		ReceiptXLSUtility receiptXLSUtility = new ReceiptXLSUtility();
		boolean generated = receiptXLSUtility.generate("receipt.xls");
		if(generated){
			System.out.println("Successfully generated Receipt XLS...");
		}
		else{
			System.out.println("Cannot generate Receipt XLS...");
		}
		
		try {
			InputStream is = new BufferedInputStream(new FileInputStream("receipt.xls"));
			Doc doc = new SimpleDoc(is,DocFlavor.INPUT_STREAM.AUTOSENSE,null);
			print(doc);
		} catch (FileNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	
	public static void print(Doc doc){	
		 PrinterJob printJob = PrinterJob.getPrinterJob();
		 if (printJob.printDialog()) {
				
					DocPrintJob job = printJob.getPrintService().createPrintJob();
					
					PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
					aset.add(new Copies(1));
					aset.add(OrientationRequested.PORTRAIT);
					aset.add(Sides.ONE_SIDED);
					aset.add(MediaSizeName.INVOICE);
					
					
				    try {
						job.print(doc, aset);
					} catch (PrintException e) {
						LoggerUtility.getInstance().logStackTrace(e);
					}
				 
			}
	}
	
	public boolean generate(String fileName){
		
		
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/receipt.xls"));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			HSSFRow row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style);
			cell.setCellValue("Unit 2044, Level B Shoppesville"); //Address Line 1
			
			row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			cell.setCellStyle(style);
			cell.setCellValue("Arcade, Greenhills, Sn Juan, M.M."); //Address Line 2
			
			row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			cell.setCellStyle(style);
			cell.setCellValue("TIN -000-051-689-0006-VAT"); //TIN
			
			row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			cell.setCellStyle(style);
			cell.setCellValue("OR NO: 004-0000000785"); //OR
			
			rowMarker++;
			
			row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			cell.setCellStyle(style);
			cell.setCellValue("Date"); //DATE
			
			row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			cell.setCellStyle(style);
			cell.setCellValue("Served By"); //Served By
			
			row = sheet.createRow(rowMarker++);
			cell = HSSFUtil.createStringCell(wb, row, (short) 0,false,false);
			cell.setCellStyle(style);
			cell.setCellValue("Time"); //Time
			
			
			
			ReportUtility.writeOutputToFile(wb, fileName);
			
			
		} catch (FileNotFoundException e) {
		
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		} catch (IOException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
		
		
		return true;
	}
}

/* 
 * Copyright (C) 2009 Dagitab IT.
 * 
 *  DATE			AUTHOR			MAJOR.MINOR.PATCH	CHANGES
 *  Jan 28, 2009	James Faeldon	v0.0.1				Created
 *
 * 
 */

package com.dagitab.pos.util;

import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterResolution;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 * Prints Receipt see main() method for example usage
 * 
 * @author user
 *
 */
public class ReceiptPrinter {
	
	public static final int COMPANY_NAME = 0;
	public static final int ADDRESS_LINE_1 = 1;
	public static final int ADDRESS_LINE_2 = 2;
	public static final int TIN = 3;
	public static final int DATE = 4;
	public static final int TIME = 5;
	public static final int CASHIER = 6;
	public static final int WEBSITE = 7;
	public static final int FOOTER_MESSAGE_1 = 8;
	public static final int FOOTER_MESSAGE_2 = 9;
	public static final int DISCOUNT = 10;
	public static final int VAT_PERCENT = 11;
	public static final int VAT_VALUE = 12;
	public static final int VATABLE_SALE = 13;
	public static final int TOTAL_AMOUNT = 14;
	public static final int TOTAL_QTY = 15;
	public static final int CHANGE = 16;
	public static final int OR_NO = 17;
	public static final int GC_AMOUNT = 18;
	public static final int SUB_TOTAL = 19;
	
	private List<String[]> items;
	private List<String[]> payments;
	private String[] details;
	
	public ReceiptPrinter(List<String[]> items, List<String[]> payments, String[] details) {
		this.items = items;
		this.payments = payments;
		this.details = details;
	}
	
	public void printReceipt() throws PrintException {
		byte[] data = convertToByteArray(items, payments, details);
		print(data);
	}
	
	
	
	private byte[] convertToByteArray(List<String[]> items, List<String[]> payments, String[] details) {
		StringBuilder out = new StringBuilder();
		
		out.append(center(details[ReceiptPrinter.COMPANY_NAME], 40)+"\n");
		out.append(center(details[ReceiptPrinter.COMPANY_NAME], 40)+"\n\n");
		out.append(center(details[ReceiptPrinter.ADDRESS_LINE_1], 40)+"\n");
		out.append(center(details[ReceiptPrinter.ADDRESS_LINE_2], 40)+"\n");
		out.append(center(details[ReceiptPrinter.TIN], 40)+"\n");
		out.append(center("OR NO: "+details[ReceiptPrinter.OR_NO], 40)+"\n");
		out.append(center("Date: "+details[ReceiptPrinter.DATE], 40)+"\n");
		out.append(center("Served by: "+details[ReceiptPrinter.CASHIER], 40)+"\n");
		out.append(center("Time: "+details[ReceiptPrinter.TIME], 40)+"\n\n");
		out.append("----------------------------------------\n");
		out.append("DESCRIPTION         QTY  UNIT     AMOUNT");
		out.append('\n');
		for (String[] values : items) {
			
			out.append(values[0].substring(0, 20 < values[0].length()? 20 :values[0].length()));
			for (int i = 0; i < (20 - (values[0].length())); i++) {
				out.append(' ');
			}
			out.append(values[1].substring(0, 5 < values[1].length()? 5 :values[1].length()));
			for (int i = 0; i < (5 - (values[1].length())); i++) {
				out.append(' ');
			}
			out.append(values[2].substring(0, 7 < values[2].length()? 7 :values[2].length()));
			for (int i = 0; i < (7 - (values[2].length())); i++) {
				out.append(' ');
			}
			out.append(right(values[3], 8));
			out.append('\n');
			out.append(values[4].substring(0, 40 < values[4].length()? 40 :values[4].length()));
			out.append('\n');
		}
		out.append("----------------------------------------\n");
		out.append("Vatable Sale               "+right(details[ReceiptPrinter.VATABLE_SALE],13)+"\n");
		out.append("Discount                   "+right(details[ReceiptPrinter.DISCOUNT],13)+"\n");
		out.append("VAT("+details[ReceiptPrinter.VAT_PERCENT]+"%)                 "+right(details[ReceiptPrinter.VAT_VALUE],13)+"\n");
		out.append("VAT-Excempt Sale                        \n");
		
		if(details[ReceiptPrinter.GC_AMOUNT].length() > 0){
			out.append(left("Sub-Total",20)+right(details[ReceiptPrinter.SUB_TOTAL],20)+"\n");
			out.append(left("Less GC Payment",20)+right(details[ReceiptPrinter.GC_AMOUNT],20)+"\n");
		}
		out.append("\n");
		out.append("=================TOTAL==================\n");
		out.append(left("No of items ("+details[ReceiptPrinter.TOTAL_QTY]+")",20)+right(details[ReceiptPrinter.TOTAL_AMOUNT],20)+"\n");
		out.append("========================================\n\n");
		out.append("PAYMENT DETAILS                   AMOUNT\n");
		
		for (String[] values : payments) {
			out.append(values[0].substring(0, 27 < values[0].length()? 27 :values[0].length()));
			for (int i = 0; i < (27 - (values[0].length())); i++) {
				out.append(' ');
			}
			out.append(right(values[1], 13));
			out.append('\n');
		}
		out.append('\n');
		out.append("CHANGE"+right(details[ReceiptPrinter.CHANGE], 34)+"\n\n");
		out.append(center(details[ReceiptPrinter.FOOTER_MESSAGE_1], 40)+"\n");
		out.append(center(details[ReceiptPrinter.FOOTER_MESSAGE_2], 40)+"\n\n");
		out.append(center(details[ReceiptPrinter.COMPANY_NAME], 40)+"\n");
		out.append(center(details[ReceiptPrinter.WEBSITE], 40)+"\n\n\n\n\n\n\n");
		return out.toString().getBytes();
	}
	
	private void print(byte[] data) throws PrintException {
        // Find the default service
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        // Create the print job
        DocPrintJob job = service.createPrintJob();
        PrintJobAttributeSet set = job.getAttributes();
        Doc doc = new SimpleDoc(data, flavor, null);

        // Monitor print job events; for the implementation of PrintJobWatcher,
        PrintJobWatcher pjDone = new PrintJobWatcher(job);

        // Print it
        
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new PrinterResolution(20,20,PrinterResolution.DPI));
		aset.add(new MediaPrintableArea(0,0,30,100,MediaPrintableArea.MM ));
        job.print(doc, null);
        
        pjDone.waitForDone();

	}
	
	private String center(String msg, int width) {
		int s = msg.length();
		if (s > width) {
			return msg.substring(0, width);
		}
		else {
			StringBuilder out = new StringBuilder();
			int leading_whitespace = (width - s)/2;
			int trailing_whitespace = (width - s) - leading_whitespace;
			for (int i=0; i<leading_whitespace; i++) {
				out.append(' ');
			}
			out.append(msg);
			for (int i=0; i<trailing_whitespace; i++) {
				out.append(' ');
			}
			return out.toString();
		}
	}
	
	private String right(String msg, int width) {
		int s = msg.length();
		if (s > width) {
			return msg.substring(0, width);
		}
		else {
			StringBuilder out = new StringBuilder();
			int leading_whitespace = width - s;
			for (int i=0; i<leading_whitespace; i++) {
				out.append(' ');
			}
			out.append(msg);
			return out.toString();
		}
		
	}
	private String left(String msg, int width) {
		int s = msg.length();
		if (s > width) {
			return msg.substring(0, width);
		}
		else {
			StringBuilder out = new StringBuilder();
			int trailing_whitespace = width - s;
			out.append(msg);
			for (int i=0; i<trailing_whitespace; i++) {
				out.append(' ');
			}
			return out.toString();
		}
		
	}
	
	public static void main(String[] args) {
        try {
        	// Random Items Data
        	int numberOfItems = 10;
        	List<String[]> items = new ArrayList<String[]>();
        	for (int i = 0; i < numberOfItems; i++) {
        		String[] values = new String[5];
        		values[0] = generateRandomText(20);
        		values[1] = generateRandomText(5);
        		values[2] = generateRandomText(6);
        		values[3] = generateRandomText(7);
        		values[4] = generateRandomText(40);
        		items.add(values);
        	}
        	
        	
        	// Example Payments Data
        	List<String[]> payments = new ArrayList<String[]>();
        	String[] payment = new String[2];
        	payment[0] = "Credit Card";
        	payment[1] = "1179.50";
        	payments.add(payment);
        	// Assign The Details
        	String[] details = new String[18];
        	details[ReceiptPrinter.COMPANY_NAME] = "BABYLAND, INC";
        	details[ReceiptPrinter.ADDRESS_LINE_1] = "Unit 2044, Level B Shoppesville";
        	details[ReceiptPrinter.ADDRESS_LINE_2] = "Arcade, Greenhills, Sn. Juan, M.M.";
        	details[ReceiptPrinter.TIN] = "TIN-000-051-689-006-VAT";
        	details[ReceiptPrinter.OR_NO] = "OR1236781002";
        	details[ReceiptPrinter.DATE] = "01/28/2009";
        	details[ReceiptPrinter.TIME] = "15:51:52";
        	details[ReceiptPrinter.CASHIER] = "User";
        	details[ReceiptPrinter.WEBSITE] = "www.babyland.com.ph";
        	details[ReceiptPrinter.FOOTER_MESSAGE_1] = "This serves as your official receipt";
        	details[ReceiptPrinter.FOOTER_MESSAGE_2] = "Thank you for shopping!";
        	details[ReceiptPrinter.VATABLE_SALE] = "1053.13";
        	details[ReceiptPrinter.DISCOUNT] = "0.0";
        	details[ReceiptPrinter.VAT_PERCENT] = "12.0";
        	details[ReceiptPrinter.VAT_VALUE] = "126.38";
        	details[ReceiptPrinter.TOTAL_AMOUNT] = "1179.50";
        	details[ReceiptPrinter.TOTAL_QTY] = "5";
        	details[ReceiptPrinter.CHANGE] = "0.00";

        	// Create printer instance
        	ReceiptPrinter printer = new ReceiptPrinter(items, payments, details);
        	
        	// Print
        	printer.printReceipt();
        
        } 
        catch (PrintException e) {
        	e.printStackTrace();
        } 
	}
	
	private static String generateRandomText(int length) {
		int size = (int) Math.floor(Math.random() * length);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < size; i++) {
			out.append('a');
		}
		out.append("*");
		return out.toString();
	}
}

class PrintJobWatcher {
    // true iff it is safe to close the print job's input stream
    boolean done = false;

    PrintJobWatcher(DocPrintJob job) {
        // Add a listener to the print job
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printJobCanceled(PrintJobEvent pje) {
                allDone();
            }
            public void printJobCompleted(PrintJobEvent pje) {
                allDone();
            }
            public void printJobFailed(PrintJobEvent pje) {
                allDone();
            }
            public void printJobNoMoreEvents(PrintJobEvent pje) {
                allDone();
            }
            void allDone() {
                synchronized (PrintJobWatcher.this) {
                    done = true;
                    PrintJobWatcher.this.notify();
                }
            }
        });
    }
    public synchronized void waitForDone() {
        try {
            while (!done) {
                wait();
            }
        } catch (InterruptedException e) {
        }
    }
}

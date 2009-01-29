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
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class ItemsPrinter {
	
	
	/*
	 *  String[] values
	 *  0: code
	 *  1: quantity
	 *  2: unit price
	 *  3: amount
	 *  4: description
	 */
	public void printItems(List<String[]> items) throws PrintException {
		byte[] data = convertToByteArray(items);
		print(data);
	}
	
	private byte[] convertToByteArray(List<String[]> items) {
		StringBuilder out = new StringBuilder();
		
		out.append("DESCRIPTION         QTY  UNIT   AMOUNT  ");
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
			out.append(values[3].substring(0, 8 < values[3].length()? 8 :values[3].length()));
			for (int i = 0; i < (8 - (values[3].length())); i++) {
				out.append(' ');
			}
			out.append('\n');
			out.append(values[4].substring(0, 40 < values[4].length()? 40 :values[4].length()));
			out.append('\n');
		}
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
        job.print(doc, null);
        
        pjDone.waitForDone();

	}
	public static void main(String[] args) {
        try {
        	ItemsPrinter printer = new ItemsPrinter();
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
        	printer.printItems(items);
        } catch (PrintException e) {
        	e.printStackTrace();
        } 
	}
	
	public static String generateRandomText(int length) {
		int size = (int) Math.floor(Math.random() * length);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < size; i++) {
			out.append('a');
		}
		out.append("*");
		return out.toString();
	}
}


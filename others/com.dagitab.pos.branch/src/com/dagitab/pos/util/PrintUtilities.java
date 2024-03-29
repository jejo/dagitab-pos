package com.dagitab.pos.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PresentationDirection;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;

import org.apache.log4j.Logger;

/** A simple utility class that lets you very simply print
 *  an arbitrary component. Just pass the component to the
 *  PrintUtilities.printComponent. The component you want to
 *  print doesn't need a print method and doesn't have to
 *  implement any interface or do anything special at all.
 *  <P>
 *  If you are going to be printing many times, it is marginally more 
 *  efficient to first do the following:
 *  <PRE>
 *    PrintUtilities printHelper = new PrintUtilities(theComponent);
 *  </PRE>
 *  then later do printHelper.print(). But this is a very tiny
 *  difference, so in most cases just do the simpler
 *  PrintUtilities.printComponent(componentToBePrinted).
 *
 *  7/99 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 *  May be freely used or adapted.
 */

public class PrintUtilities implements Printable {
  private Component componentToBePrinted;
  private static Logger logger = Logger.getLogger(PrintUtilities.class);

  public static void printComponent(Component c) {
    new PrintUtilities(c).print();
  }
  
  public PrintUtilities(Component componentToBePrinted) {
    this.componentToBePrinted = componentToBePrinted;
  }
  
  public void print() {
    PrinterJob printJob = PrinterJob.getPrinterJob();
    PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
    try {
		printJob.setPrintService(printService);
	} catch (PrinterException e) {
		JOptionPane.showMessageDialog(null, "Cannot find default printer. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
    printJob.setPrintable(this);
//    if (printJob.printDialog()) {
    	
		try {
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(PrintQuality.DRAFT);
			aset.add(PresentationDirection.TOTOP_TOLEFT);
			aset.add(Chromaticity.MONOCHROME);
			aset.add(new PrinterResolution(170,170,PrinterResolution.DPI));
			aset.add(new MediaPrintableArea(0,0,76,100,MediaPrintableArea.MM ));
			
			printJob.print(aset);
			
		  } catch(PrinterException pe) {
		    logger.info("Error printing: " + pe);
		  }
//	}
  }

//  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
//    if (pageIndex > 0) {
//      return(NO_SUCH_PAGE);
//    } else {
//      Graphics2D g2d = (Graphics2D)g;
//      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//      disableDoubleBuffering(componentToBePrinted);
//      componentToBePrinted.paint(g2d);
//      enableDoubleBuffering(componentToBePrinted);
//      return(PAGE_EXISTS);
//    }
//  }
  
  	public int print(Graphics g, PageFormat pf, int pageIndex) {
	    int response = NO_SUCH_PAGE;

	    Graphics2D g2 = (Graphics2D) g;

	    //  for faster printing, turn off double buffering
	    disableDoubleBuffering(componentToBePrinted);

	    Dimension d = componentToBePrinted.getSize(); //get size of document
	    double panelWidth = d.width; //width in pixels
	    double panelHeight = d.height; //height in pixels

	    double pageHeight = pf.getImageableHeight(); //height of printer page
	    double pageWidth = pf.getImageableWidth(); //width of printer page

	    double scale = pageWidth / panelWidth;
	    int totalNumPages = (int) Math.ceil(scale * panelHeight / pageHeight);

	    //  make sure not print empty pages
	    if (pageIndex >= totalNumPages) {
	      response = NO_SUCH_PAGE;
	    }
	    else {

	      //  shift Graphic to line up with beginning of print-imageable region
	      g2.translate(pf.getImageableX(), pf.getImageableY());

	      //  shift Graphic to line up with beginning of next page to print
	      g2.translate(0f, -pageIndex * pageHeight);

	      //  scale the page so the width fits...
	      g2.scale(scale, scale);

	      componentToBePrinted.paint(g2); //repaint the page for printing

	      enableDoubleBuffering(componentToBePrinted);
	      response = Printable.PAGE_EXISTS;
	    }
	    return response;
	}

  /** The speed and quality of printing suffers dramatically if
   *  any of the containers have double buffering turned on.
   *  So this turns if off globally.
   *  @see enableDoubleBuffering
   */
  public static void disableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(false);
  }

  /** Re-enables double buffering globally. */
  
  public static void enableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(true);
  }
}

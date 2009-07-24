package com.dagitab.pos.forms.receipts;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.dagitab.pos.bus.ClerkService;
import com.dagitab.pos.bus.DiscountService;
import com.dagitab.pos.bus.InvoiceItemService;
import com.dagitab.pos.bus.InvoiceService;
import com.dagitab.pos.bus.PaymentTypeService;
import com.dagitab.pos.bus.ProductService;
import com.dagitab.pos.bus.ReturnItemService;
import com.dagitab.pos.bus.StoreService;
import com.dagitab.pos.bus.VatService;
import com.dagitab.pos.domain.Clerk;
import com.dagitab.pos.domain.GCItem;
import com.dagitab.pos.domain.Invoice;
import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.domain.PaymentItem;
import com.dagitab.pos.domain.Product;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.ReceiptUtilities;
import com.dagitab.pos.util.StorePropertyHandler;
import com.dagitab.pos.util.StringUtility;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class ReceiptPanel extends javax.swing.JPanel {
	private JLabel babylandHeader1Label;
	private JLabel dateLabel;
	private JLabel addressLine1Label;
	private JLabel tinNoLabel;
	private JLabel clerkServedLabel;
	private JLabel orNoLabel;
	private JLabel itemAmountLabel;
	private JLabel itemDescriptionLabel;
	private JLabel timeLabel;
	private String vatAmount;
	private JLabel babylandHeader2Label;
	private JLabel unitLabel;
	private JLabel quantityLabel;
	private JLabel addressLine2Label;
	private Invoice invoice;
	private List<InvoiceItem> invoiceItems;
	private List<PaymentItem> paymentItems;
	private List<GCItem> gcItems = new ArrayList<GCItem>();
 	private String changeAmount = "0";
	private static Logger logger = Logger.getLogger(ReceiptPanel.class);
	
	
	public ReceiptPanel(Invoice invoice, List<InvoiceItem> invoiceItems, List<PaymentItem> paymentItems, String change) {
		super();
		this.invoice = invoice;
		this.invoiceItems = invoiceItems;
		this.paymentItems = paymentItems;
		this.changeAmount = change;
		
		initGUI();
	}
	
	public ReceiptPanel(Invoice invoice, List<InvoiceItem> invoiceItems, List<PaymentItem> paymentItems, List<GCItem> gcItems, String change){
		super();
		this.invoice = invoice;
		this.invoiceItems = invoiceItems;
		this.paymentItems = paymentItems;
		this.changeAmount = change;
		this.gcItems = gcItems;
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			int height = 450;
			
			height = height+ (28*(invoiceItems.size()-1));
			height = height+ (28*(paymentItems.size()-1)); //?

			String[] address = ReceiptUtilities.getReceiptUtilities().splitString(StoreService.getStoreAddress(Integer.parseInt(Main.getStoreCode())));
			this.setPreferredSize(new java.awt.Dimension(203, height));
			this.setLayout(null);
			this.setBackground(new java.awt.Color(255,255,255));
//			this.setBorder(BorderFactory.createTitledBorder(""));
			this.setBorder(BorderFactory.createEmptyBorder());
			{
				//1st label for babyland, inc.
				babylandHeader1Label = new JLabel();
				this.add(babylandHeader1Label);
				babylandHeader1Label.setText("BABYLAND, INC.");
				babylandHeader1Label.setBounds(42, 0, 126, 28);
				babylandHeader1Label.setFont(new java.awt.Font("Arial",1,14));
			}
			{
				//2nd label for babyland, inc.
				babylandHeader2Label = new JLabel();
				this.add(babylandHeader2Label);
				babylandHeader2Label.setText("BABYLAND, INC.");
				babylandHeader2Label.setFont(new java.awt.Font("Arial",1,14));
				babylandHeader2Label.setBounds(42, 21, 126, 21);
			}
			{
				dateLabel = new JLabel();
				this.add(dateLabel);
				dateLabel.setText("Date: "+ReceiptUtilities.getReceiptUtilities().convertDate(InvoiceService.getTransactionDateOfOR(invoice.getOrNo())));
				dateLabel.setBounds(7, 98, 189, 28);
				dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
				dateLabel.setFont(new java.awt.Font("Arial",0,10));
			}
			{
				addressLine1Label = new JLabel();
				this.add(addressLine1Label);
				addressLine1Label.setText(address[0]);
				addressLine1Label.setBounds(7, 42, 189, 14);
				addressLine1Label.setFont(new java.awt.Font("Arial",0,10));
				addressLine1Label.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				//if there are more address line, create a new label for the 2nd line
				if(address.length > 1){
					addressLine2Label = new JLabel();
					this.add(addressLine2Label);
					
					addressLine2Label.setText(address[1]);
					addressLine2Label.setBounds(7, 56, 189, 14);
					addressLine2Label.setFont(new java.awt.Font("Arial",0,10));
					addressLine2Label.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
			{
				tinNoLabel = new JLabel();
				this.add(tinNoLabel);
				tinNoLabel.setText(StorePropertyHandler.getTinNo());
				tinNoLabel.setBounds(7, 70, 189, 14);
				tinNoLabel.setFont(new java.awt.Font("Arial",0,10));
				tinNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				clerkServedLabel = new JLabel();
				this.add(clerkServedLabel);
				Clerk clerk = ClerkService.getClerkByID(invoice.getEncoderCode());
				clerkServedLabel.setText("Served by: "+clerk.getFirstName()+" "+clerk.getLastName());
				clerkServedLabel.setFont(new java.awt.Font("Arial",0,10));
				clerkServedLabel.setBounds(7, 108, 189, 28);
				clerkServedLabel.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				timeLabel = new JLabel();
				this.add(timeLabel);
				timeLabel.setText("Time: "+InvoiceService.getTransactionTimeOfOR(invoice.getOrNo()));
				timeLabel.setFont(new java.awt.Font("Arial",0,10));
				timeLabel.setBounds(7, 122, 189, 28);
				timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				itemDescriptionLabel = new JLabel();
				this.add(itemDescriptionLabel);
				itemDescriptionLabel.setText("DESCRIPTION");
				itemDescriptionLabel.setBounds(7, 154, 189, 28);
				itemDescriptionLabel.setFont(new java.awt.Font("Tahoma",1,8));
			}
			{
				itemAmountLabel = new JLabel();
				this.add(itemAmountLabel);
				itemAmountLabel.setText("AMOUNT");
				itemAmountLabel.setFont(new java.awt.Font("Tahoma",1,8));
				itemAmountLabel.setBounds(161, 154, 189, 28);
			}
			{
				orNoLabel = new JLabel();
				this.add(orNoLabel);
				orNoLabel.setText("OR NO: "+StringUtility.zeroFill(Main.getStoreCode(), 3)+"-"+StringUtility.zeroFill(invoice.getOrNo().toString(), 10));
				orNoLabel.setFont(new java.awt.Font("Arial",0,10));
				orNoLabel.setBounds(7, 77, 189, 28);
				orNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				quantityLabel = new JLabel();
				this.add(quantityLabel);
				quantityLabel.setText("QTY");
				quantityLabel.setBounds(84, 154, 189, 28);
				quantityLabel.setFont(new java.awt.Font("Tahoma",1,8));
			}
			{
				unitLabel = new JLabel();
				this.add(unitLabel);
				unitLabel.setText("UNIT");
				unitLabel.setBounds(119, 154, 189, 28);
				unitLabel.setFont(new java.awt.Font("Tahoma",1,8));
			}
			
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	//input dynamic content
	@Override
	public void paintComponent(Graphics g) {	
		 super.paintComponent(g);
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 8));
		 
		 g.drawLine(5, 149, 190, 149); //line after Description, Amount Heading
		 
		 
		 int topMarker = 189;
		 double currentSubTotal = 0;
		 double sellingSubTotal = 0;
		 
		 /***VAT**/
		 String vatValue = VatService.getVatAmount().toString();
		 
		
		 for(int i = 0; i<invoiceItems.size(); i++){
			 
			InvoiceItem invoiceItem = invoiceItems.get(i); 
			Product product = ProductService.getProductById(invoiceItem.getProductCode());
			
			//issue if selling price of product has changed
			double currentPriceQuantityAmount = product.getSellPrice() * invoiceItem.getQuantity();
			double sellingPriceQuantityAmount = 0;
			if(!invoiceItem.getIsReturned()){
				sellingPriceQuantityAmount = Double.valueOf(String.format("%.2f", InvoiceItemService.getInstance().getDiscountedAmount(invoiceItem.getOrNo(), invoiceItem.getProductCode()))) * invoiceItem.getQuantity();
			}
			else{
				sellingPriceQuantityAmount = Double.parseDouble(String.format("%.2f",ReturnItemService.getDiscountedAmount(invoiceItem.getOrNo(), invoiceItem.getProductCode()))) * invoiceItem.getQuantity();
			}
			
//			logger.info("Product Code: "+product.getProdCode()+" Product Name: "+product.getName());
//			logger.info("current price*quantity: "+currentPriceQuantityAmount);
//			logger.info("selling price*quantity: "+sellingPriceQuantityAmount);
			
			currentSubTotal+=currentPriceQuantityAmount;
			sellingSubTotal += sellingPriceQuantityAmount;
			g.setFont(new Font("Arial", Font.PLAIN, 9));
			
			//Ellipsis on product code more than 13 chars.
			String productCode =  invoiceItem.getProductCode();
			if(productCode.length() > 13){
				productCode = productCode.substring(0, 13)+"..";
			}
			g.drawString(productCode,8,topMarker);
			
			String qty = invoiceItem.getQuantity().toString();
			if(Integer.parseInt(qty) < 0){
				//eliminate "-" if negative value;
				qty = "("+qty.substring(1)+")"; 
			}
			else{
				//put a discount @ at item qty if there is
				if(DiscountService.getDiscRate(invoiceItem.getDiscountCode()) > 0){
					qty = qty+"@"+DiscountService.getDiscountPercentage(invoiceItem.getDiscountCode());
				}
			}
			
			
			g.drawString(qty, 80, topMarker);
			 
			
			//if not returned set unit price to product selling price, else set unit price to price sold.
			String unitPrice = String.format("%.2f", product.getSellPrice());
			if(invoiceItem.getIsReturned()){
				unitPrice = String.format("%.2f", invoiceItem.getSellPrice());
			}
			
			
			int newXpos = 140 - (unitPrice.length() * 4);
			
			g.drawString(unitPrice, newXpos, topMarker);
			
			

			String currentPriceAmount="";
			if(!invoiceItem.getIsReturned()){
				//update amount to combine discount
				Double discountedPriceQuantityAmount = InvoiceItemService.getInstance().getDiscountedAmount(invoiceItem.getOrNo(), invoiceItem.getProductCode());
				discountedPriceQuantityAmount = discountedPriceQuantityAmount*invoiceItem.getQuantity();
				currentPriceAmount = String.format("%.2f", discountedPriceQuantityAmount);
			}
			else{ //if returned
				currentPriceAmount = String.format("%.2f", invoiceItem.getSellPrice()*invoiceItem.getQuantity());
				currentPriceAmount ="("+Math.abs(invoiceItem.getSellPrice()*invoiceItem.getQuantity())+")";
			}
			
			
//			String currentPriceAmount = String.format("%.2f", Math.abs(currentPriceQuantityAmount));  
//			if(currentPriceQuantityAmount < 0){
//				currentPriceAmount = "("+ currentPriceAmount +")";
//			}
//			else {
//				
//			}
			
			newXpos = 190 - (currentPriceAmount.length()*4);
			g.drawString(currentPriceAmount, newXpos, topMarker);
			
			
			
			topMarker+=10;
			
			g.setFont(new Font("Arial", Font.PLAIN, 8));
			
			g.drawString(product.getName(), 8, topMarker);
			topMarker+=15;
			
		 }
		 
		 
		 
		 g.drawLine(5, topMarker, 190, topMarker); //line after Items
		 
		 topMarker+=20;
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 
		 /*** SUBTOTAL ***/
		 g.drawString("Vatable Sale",5,topMarker); //Subtotal label
		 //	for right justified compute 190 - string size*5 as first position
		 
		 if(currentSubTotal < 0) currentSubTotal = 0;
		 if(sellingSubTotal < 0) sellingSubTotal = 0;
		 
		 
		 
//		 Double netSaleAmount = Double.parseDouble(String.format("%.2f",currentSubTotal)) - Double.parseDouble(String.format("%.2f",totalDiscount));
//		 logger.info("Net Sale Amount: "+netSaleAmount);
//		 logger.info("SUB TOTAL:"+sellingSubTotal);
		 
		 Double vatRate = VatService.getVatRate();
		 sellingSubTotal = Double.parseDouble(String.format("%.2f", sellingSubTotal));
		 double vatablePurchase = sellingSubTotal/vatRate;
//		 		vatablepurchase = roundDown(vatablepurchase);
		 int xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", vatablePurchase));
		 
		 g.drawString(String.format("%.2f", vatablePurchase), xpos, topMarker); //subtotal amt
		 
		 topMarker+=15;
		 
		 /***DISCOUNT**/
		 g.drawString("Discount",5,topMarker);
		 
		 //	for right justified compute 190 - string size*5 as first position
		 //derive totalDiscount
//		 Double totalDiscount = currentSubTotal - sellingSubTotal;
		 Double totalDiscount = InvoiceService.getTotalDiscountAmount(invoice.getOrNo());
		 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", totalDiscount));
		 g.drawString(String.format("%.2f", totalDiscount), xpos, topMarker); //disc amount
		 topMarker+=15;
		
		 //vat amount 12% of Vatable Sale
		 
//			for right justified compute 190 - string size*5 as first position
		 g.drawString("VAT("+vatValue+"%)",5,topMarker);
		 
		 
		 
		 
		 
		 
		 Double totalAmount = Double.parseDouble(String.format("%.2f", sellingSubTotal));
		 
		 vatAmount = String.format("%.2f", totalAmount - Double.valueOf(String.format("%.2f", vatablePurchase)));
		 
		 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(vatAmount);
		 
		 g.drawString(vatAmount, xpos, topMarker); //disc amount
		 
		 topMarker+=15;
		 
		 g.drawString("VAT-Exempt Sale",5,topMarker);
		 
		 topMarker+=20;
		 
		 if(gcItems.size() > 0){
			 g.setFont(new Font("Arial", Font.PLAIN, 10));
			 g.drawString("SUBTOTAL",5,topMarker);
		 }
		 else{
			 g.setFont(new Font("Arial", Font.BOLD, 11));
			 g.drawString("TOTAL",5,topMarker);
		 }
		 
		 /***TOTAL**/
		
		 
//		 logger.info("Total Amount:" +sellingSubTotal);
		 
		 //	for right justified compute 190 - string size*5 as first position
//		 double Total = Double.parseDouble(String.format("%.2f", vatablepurchase))+Double.parseDouble(vatAmount);
		 
		 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", totalAmount));
		 g.drawString(String.format("%.2f", totalAmount), xpos, topMarker); //disc amount
		 topMarker+=15;
		 
		 
		 if(gcItems.size() > 0){
			 g.drawString("LESS",5,topMarker);
			 topMarker+=15;
			 
			 g.setFont(new Font("Arial", Font.PLAIN, 10));
			 
			 Double totalGCAmount = 0.0d;
			 for(int i = 0; i< gcItems.size(); i++){
				 GCItem gcItem = gcItems.get(i);
				 totalGCAmount += gcItem.getAmount();
				 
			 }
			 
			 g.drawString("Gift Certificate", 10, topMarker); //Hard coded for GC payment type
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f",totalGCAmount));
			 g.drawString(String.format("%.2f", totalGCAmount), xpos, topMarker); //payment amount
			 topMarker+=20;
			 
			 /***GRAND TOTAL**/
			 g.setFont(new Font("Arial", Font.BOLD, 11));
			 g.drawString("TOTAL",5,topMarker);
			 //	for right justified compute 190 - string size*5 as first position
//			 double Total = Double.parseDouble(String.format("%.2f", vatablepurchase))+Double.parseDouble(vatAmount);
			 Double grandTotal = totalAmount - totalGCAmount; 
			 if(grandTotal < 0) grandTotal = 0.0d;
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", grandTotal));
			 g.drawString(String.format("%.2f", grandTotal), xpos, topMarker); //disc amount
			 topMarker+=15;
		 }
		 
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 
		 
		 /***NO OF ITEMS**/
		 
		 int itemCount = 0;
		 for(int i = 0; i<invoiceItems.size(); i++){
			 int num = invoiceItems.get(i).getQuantity();
			 if(num>0)itemCount+=num; 
		}
		 g.drawString("No of Items ("+itemCount+")",5,topMarker);
		 
		 topMarker+=25;
		 
		 /***PAYMENT DETAILS**/
		 g.setFont(new Font("Arial", Font.BOLD, 11));
		 g.drawString("PAYMENT DETAILS",5,topMarker);
		 g.drawString("AMOUNT", 148, topMarker);
		 topMarker+=15;
		 
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 
		 for(int i = 0; i<paymentItems.size(); i++){
			 PaymentItem paymentItem = paymentItems.get(i);
			 if(!paymentItem.getPaymentCode().equals(4)){
				 g.drawString(PaymentTypeService.getPaymentName(paymentItem.getPaymentCode()),10,topMarker);//Payment Types
	//				for right justified compute 190 - string size*5 as first position
				 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f",paymentItem.getAmount()));
				 g.drawString(String.format("%.2f",paymentItem.getAmount()), xpos, topMarker); //payment amount
				 topMarker+=20;
			 }
		 }
		 
		
		 
		 //Partial Transactions don't have change, change means balance amount
		 //partial
		 if(invoice.getIsPartial() == 1){ 
			 String change = String.format("%.2f", Double.parseDouble(changeAmount));
			 if(Double.parseDouble(changeAmount) < 0 ){
				 change = "("+Math.abs(Double.parseDouble(changeAmount))+")";
			 }
			 g.drawString("BALANCE", 10, topMarker);
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", Double.parseDouble(changeAmount)));
			 g.drawString(change, xpos, topMarker);
			 topMarker+=30;
		 }
		 else{
			 g.drawString("CHANGE", 10, topMarker);
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", Double.parseDouble(changeAmount)));
			 g.drawString(String.format("%.2f", Double.parseDouble(changeAmount)), xpos, topMarker);
			 topMarker+=30;
		 }
		 
		
		 /**FOOTER**/
		 if(invoice.getIsPartial() == 1){
			 g.drawString("This serves as your partial receipt", 20, topMarker);
		 }
		 else{
			 g.drawString("This serves as your official receipt", 20, topMarker);
		 }
		 
		 topMarker+=10;
		 g.drawString("Thank you for shopping…", 40, topMarker);
		 topMarker+=20;
		 g.setFont(new Font("Arial", Font.BOLD, 11));
		 g.drawString("Babyland", 70, topMarker);
		 topMarker+=10;
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 g.drawString("www.babyland.com.ph", 45, topMarker);
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public void setPaymentItems(List<PaymentItem> paymentItems) {
		this.paymentItems = paymentItems;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}
	
	public List<InvoiceItem> getInvoiceItems(){
		return this.invoiceItems;
	}
	
	public List<PaymentItem> getPaymentItems(){
		return this.paymentItems;
	}
	
	public Invoice getInvoice(){
		return this.invoice;
	}
	
	public String getChangeAmount(){
		return this.changeAmount;
	}
	
	public List<GCItem> getGCItems(){
		return this.gcItems;
	}

	

}

package forms.receipts;

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import main.DBManager;
import main.Main;
import util.ReceiptUtilities;
import util.StorePropertyHandler;
import util.StringUtility;
import bus.ClerkService;
import bus.InvoiceService;
import bus.PaymentTypeService;
import bus.ProductService;
import bus.StoreService;
import bus.VatService;
import domain.Clerk;
import domain.Invoice;
import domain.InvoiceItem;
import domain.PaymentItem;
import domain.Product;

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
	private String changeAmount;
	private JLabel babylandHeader2Label;
	private JLabel unitLabel;
	private JLabel quantityLabel;
	private JLabel addressLine2Label;
	
	private Invoice invoice;
	private List<InvoiceItem> invoiceItems;
	private List<PaymentItem> paymentItems;
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DBManager db = new DBManager();
		db.connect();
		Vector<String> headerData = new Vector<String>();
			//		Address Branch, Tin, OR_NO, Served Name, Current Date, Current Time
		
		System.out.println();
		headerData.add("3048 New Wing, Alabang Town Ctr. Exp. Zapote Rd.Muntinlupa"); //address
		headerData.add("TIN 000-051-689-000 VAT"); 
		headerData.add("001-000000001");
		headerData.add("ALEX ODAL");
		headerData.add("4/18/2007");
		headerData.add("13:45");
		
		Vector<Vector<String>> itemData = new Vector<Vector<String>>();
			Vector<String> itemRowData = new Vector<String>();
			itemRowData.add("xxx");
			itemRowData.add("ASS WOODEN CASTANETS 24’S");
			itemRowData.add("75.00");
			itemRowData.add("75.00");
			itemRowData.add("4");
//			prod code, prod name, current price, selling price, qty,
			
			Vector<String> itemRowData2 = new Vector<String>();
			itemRowData2.add("xxx");
			itemRowData2.add("ASS WOODEN CASTANETS 24’S");
			itemRowData2.add("75.00");
			itemRowData2.add("75.00");
			itemRowData2.add("1");
			
			Vector<String> itemRowData3 = new Vector<String>();
			itemRowData3.add("xxx");
			itemRowData3.add("ASS WOODEN CASTANETS 24’S");
			itemRowData3.add("100.00");
			itemRowData3.add("100.00");
			itemRowData3.add("1");
			
			
			Vector<String> itemRowData4 = new Vector<String>();
			itemRowData4.add("xxx");
			itemRowData4.add("ASS WOODEN CASTANETS 24’S");
			itemRowData4.add("120.00");
			itemRowData4.add("120.00");
			itemRowData4.add("1");
			
			
			Vector<String> itemRowData5 = new Vector<String>();
			itemRowData5.add("xxx");
			itemRowData5.add("ASS WOODEN CASTANETS 24’S");
			itemRowData5.add("15.00");
			itemRowData5.add("15.00");
			itemRowData5.add("3");
			
			
		itemData.add(itemRowData);
		itemData.add(itemRowData2);
		itemData.add(itemRowData3);
		itemData.add(itemRowData4);
		itemData.add(itemRowData5);
			
		
		Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
			Vector<String> paymentRowData = new Vector<String>();
			//name of payment, amount, change
			paymentRowData.add("Cash");
			paymentRowData.add("700.00");
			paymentRowData.add("60");
		paymentData.add(paymentRowData);
		String vatAmount = "571.42";
		String changeAmount = "60.00";
		ReceiptPanel rp = new ReceiptPanel(headerData,itemData,paymentData,vatAmount,changeAmount, db,"par");
		frame.getContentPane().add(rp);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public ReceiptPanel() {
		super();
		initGUI();
	}
	
	@Deprecated
	public ReceiptPanel(Vector<String> headerData, 
							Vector<Vector<String>> itemData, 
							Vector<Vector<String>> paymentData,String vatAmount,
							String changeAmount, DBManager db, String status) {
		super();
//		this.headerData = headerData; 
			//Address Branch, Tin, OR_NO, Served Name, Current Date, Current Time
	
			//prod code, prod name, current price, selling price, qty, 
	
			//name of payment, amount,
		//this.vatAmount = vatAmount;
		//this.changeAmount = changeAmount;
		
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
			this.setBorder(BorderFactory.createTitledBorder(""));
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
			e.printStackTrace();
		}
	}
	
	//input dynamic content
	@Override
	public void paintComponent(Graphics g) {	
		 super.paintComponent(g);
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 9));
		 
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
			double sellingPriceQuantityAmount = invoiceItem.getSellPrice() * invoiceItem.getQuantity();
			System.out.println("Product Code: "+product.getProdCode()+" Product Name: "+product.getName());
			System.out.println("current price*quantity: "+currentPriceQuantityAmount);
			System.out.println("selling price*quantity: "+sellingPriceQuantityAmount);
			currentSubTotal+=currentPriceQuantityAmount;
			sellingSubTotal += sellingPriceQuantityAmount;
			g.setFont(new Font("Arial", Font.PLAIN, 9));
			
			g.drawString(invoiceItem.getProductCode(),8,topMarker);
			
			String qty = invoiceItem.getQuantity().toString();
			if(Integer.parseInt(qty) < 0){
				//eliminate "-" if negative value;
				qty = "("+qty.substring(1)+")"; 
			}
			
			g.drawString(qty, 85, topMarker);
			
			String unitPrice = String.format("%.2f", product.getSellPrice());
			
			int newXpos = 140 - (unitPrice.length() * 4);
			
			g.drawString(unitPrice, newXpos, topMarker);
			
			String currentPriceAmount = String.format("%.2f", Math.abs(currentPriceQuantityAmount));  
			if(currentPriceQuantityAmount < 0){
				currentPriceAmount = "("+ currentPriceAmount +")";
			}
			
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
		 
		 
		 Double totalDiscount = currentSubTotal - sellingSubTotal;
		 Double netSaleAmount = Double.parseDouble(String.format("%.2f",currentSubTotal)) - Double.parseDouble(String.format("%.2f",totalDiscount));
		 System.out.println("Net Sale Amount: "+netSaleAmount);
		 System.out.println("NEW SUB TOTAL:"+sellingSubTotal);
		 
		 Double vatRate = VatService.getVatRate();
		 double vatablePurchase = sellingSubTotal/vatRate;
//		 		vatablepurchase = roundDown(vatablepurchase);
		 int xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", vatablePurchase));
		 
		 g.drawString(String.format("%.2f", vatablePurchase), xpos, topMarker); //subtotal amt
		 
		 topMarker+=15;
		 
		 /***DISCOUNT**/
		 g.drawString("Discount",5,topMarker);
		 
		 //	for right justified compute 190 - string size*5 as first position
		 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", totalDiscount));
		 g.drawString(String.format("%.2f", totalDiscount), xpos, topMarker); //disc amount
		 topMarker+=15;
		
		 //vat amount 12% of Vatable Sale
		 vatAmount = String.format("%.2f",vatablePurchase * Double.parseDouble("0."+vatValue));
		 g.drawString("VAT("+vatValue+"%)",5,topMarker);
		 
		 //	for right justified compute 190 - string size*5 as first position
		 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(vatAmount);
		 g.drawString(vatAmount, xpos, topMarker); //disc amount
		 
		 topMarker+=15;
		 
		 g.drawString("VAT-Exempt Sale",5,topMarker);
		 
		 topMarker+=20;
		 
		 g.setFont(new Font("Arial", Font.BOLD, 11));
		 
		 /***TOTAL**/
		 g.drawString("TOTAL",5,topMarker);
		 
		 System.out.println("Total Amount:" +sellingSubTotal);
		 //	for right justified compute 190 - string size*5 as first position
//		 double Total = Double.parseDouble(String.format("%.2f", vatablepurchase))+Double.parseDouble(vatAmount);
		 Double totalAmount = Double.parseDouble(String.format("%.2f", sellingSubTotal));
		 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f", totalAmount));
		 g.drawString(String.format("%.2f", totalAmount), xpos, topMarker); //disc amount
		 topMarker+=15;
		  
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
			 g.drawString(PaymentTypeService.getPaymentName(paymentItem.getPaymentCode()),10,topMarker);//Payment Types
//				for right justified compute 190 - string size*5 as first position
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(String.format("%.2f",paymentItem.getAmount()));
			 g.drawString(String.format("%.2f",paymentItem.getAmount()), xpos, topMarker); //payment amount
			 topMarker+=20;
		 }
		 
		 /*CHANGE OR BALANCE*/
		 
		 //Partial Transactions don't have change, change means balance amount
		 //partial
		 if(invoice.getIsPartial() == 1){ 
			 String change = String.format("%.2f", Double.parseDouble(changeAmount));
			 if(Double.parseDouble(changeAmount) < 0 ){
				 change = "("+change+")";
			 }
			 g.drawString("BALANCE", 10, topMarker);
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(changeAmount);
			 g.drawString(change, xpos, topMarker);
			 topMarker+=30;
		 }
		 else{
			 g.drawString("CHANGE", 10, topMarker);
			 xpos = ReceiptUtilities.getReceiptUtilities().findNormalAmountXPos(changeAmount);
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

	

}

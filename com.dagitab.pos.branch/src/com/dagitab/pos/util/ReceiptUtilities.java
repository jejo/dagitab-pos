package com.dagitab.pos.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bus.InvoiceService;

import com.dagitab.pos.bus.ClerkService;
import com.dagitab.pos.bus.DiscountService;
import com.dagitab.pos.bus.InvoiceItemService;
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

public class ReceiptUtilities {
	
	private static ReceiptUtilities receiptUtilities = new ReceiptUtilities();
	private static Logger logger = Logger.getLogger(ReceiptUtilities.class);
	private ReceiptUtilities(){}
	
	
	public String[] getReceiptDetails(Invoice invoice, List<InvoiceItem> invoiceItems, String changeAmount){
		String[] details = new String[18];
    	details[ReceiptPrinter.COMPANY_NAME] = "BABYLAND, INC";
    	String[] address = ReceiptUtilities.getReceiptUtilities().splitString(StoreService.getStoreAddress(Integer.parseInt(Main.getStoreCode())));
    	details[ReceiptPrinter.ADDRESS_LINE_1] = address[0];
    	if(address.length > 1) details[ReceiptPrinter.ADDRESS_LINE_2] = address[1];
    	details[ReceiptPrinter.TIN] = StorePropertyHandler.getTinNo();
    	details[ReceiptPrinter.OR_NO] = StringUtility.zeroFill(Main.getStoreCode(), 3)+"-"+StringUtility.zeroFill(invoice.getOrNo().toString(), 10);
    	details[ReceiptPrinter.DATE] = ReceiptUtilities.getReceiptUtilities().convertDate(InvoiceService.getTransactionDateOfOR(invoice.getOrNo()));
    	details[ReceiptPrinter.TIME] = InvoiceService.getTransactionTimeOfOR(invoice.getOrNo());
    	Clerk clerk = ClerkService.getClerkByID(invoice.getEncoderCode());
    	details[ReceiptPrinter.CASHIER] = clerk.getFirstName()+" "+clerk.getLastName();
    	details[ReceiptPrinter.WEBSITE] = "www.babyland.com.ph";
    	details[ReceiptPrinter.FOOTER_MESSAGE_1] = "This serves as your official receipt";
    	details[ReceiptPrinter.FOOTER_MESSAGE_2] = "Thank you for shopping!";
    	details[ReceiptPrinter.VATABLE_SALE] = getVatableSale(invoiceItems);
    	details[ReceiptPrinter.DISCOUNT] = getDiscount(invoice);
    	details[ReceiptPrinter.VAT_PERCENT] =  VatService.getVatAmount().toString();
    	details[ReceiptPrinter.VAT_VALUE] = getVatAmount(Double.valueOf(getTotalAmount(invoiceItems)),Double.valueOf(getVatableSale(invoiceItems)));
    	details[ReceiptPrinter.TOTAL_AMOUNT] = getTotalAmount(invoiceItems);
    	details[ReceiptPrinter.TOTAL_QTY] = getTotalQty(invoiceItems);
    	details[ReceiptPrinter.CHANGE] = changeAmount;
    	
    	return details;
		
	}
	
	public String getTotalQty(List<InvoiceItem> invoiceItems){
		int itemCount = 0;
		 for(int i = 0; i<invoiceItems.size(); i++){
			 int num = invoiceItems.get(i).getQuantity();
			 if(num>0)itemCount+=num; 
		}
		return itemCount+"";
	}
	
	public String getTotalAmount(List<InvoiceItem> invoiceItems){
		double currentSubTotal = 0;
		double sellingSubTotal = 0;
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
			
			
			currentSubTotal+=currentPriceQuantityAmount;
			sellingSubTotal += sellingPriceQuantityAmount;
			
		}
	 
		 if(currentSubTotal < 0) currentSubTotal = 0;
		 if(sellingSubTotal < 0) sellingSubTotal = 0;
		 
		 return String.format("%.2f", sellingSubTotal);
	}
	
	public String getVatAmount(Double totalAmount, Double vatablePurchase){
		 Double vatAmount = totalAmount - vatablePurchase;
		 return String.format("%.2f",vatAmount);
	}
	
	public String getDiscount(Invoice invoice){
		Double totalDiscount = InvoiceService.getTotalDiscountAmount(invoice.getOrNo());
		return String.format("%.2f", totalDiscount);
	}
	
	public String getVatableSale(List<InvoiceItem> invoiceItems){
		double currentSubTotal = 0;
		double sellingSubTotal = 0;
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
			
			
			currentSubTotal+=currentPriceQuantityAmount;
			sellingSubTotal += sellingPriceQuantityAmount;
			
		}
	 
		 if(currentSubTotal < 0) currentSubTotal = 0;
		 if(sellingSubTotal < 0) sellingSubTotal = 0;
		 
		 Double vatRate = VatService.getVatRate();
		 sellingSubTotal = Double.parseDouble(String.format("%.2f", sellingSubTotal));
		 double vatablePurchase = sellingSubTotal/vatRate;
		 
		 return String.format("%.2f", vatablePurchase);
	}
	
	public List<String[]> getPayments(List<PaymentItem> paymentItems, List<GCItem> gcItems){
		List<String[]> results = new ArrayList<String[]>();
		 for(int i = 0; i<paymentItems.size(); i++){
			 String[] row = new String[2];
			 PaymentItem paymentItem = paymentItems.get(i);
			 row[0] = PaymentTypeService.getPaymentName(paymentItem.getPaymentCode());
			 row[1] = String.format("%.2f",paymentItem.getAmount());
			 results.add(row);
		 }
		 
		 for(int i = 0; i< gcItems.size(); i++){
			 String[] row = new String[2];
			 GCItem gcItem = gcItems.get(i);
			 row[0] = "Gift Certificate";
			 row[1] = String.format("%.2f",gcItem.getAmount());
			 results.add(row);
		 }
		 
		 return results;
	}
	
	public List<String[]> getItems(List<InvoiceItem> invoiceItems){
		
		List<String[]> results = new ArrayList<String[]>();
		
		
		for(int i = 0; i<invoiceItems.size(); i++){
			 
			String[] row = new String[5];
			
			
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
			
			//Ellipsis on product code more than 13 chars.
			String productCode =  invoiceItem.getProductCode();
			if(productCode.length() > 13){
				productCode = productCode.substring(0, 13)+"..";
			}
			
			
			row[0] = productCode;
//			g.drawString(productCode,8,topMarker);
			
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
			
			row[1] = qty;
//			g.drawString(qty, 80, topMarker);
			 
			
			//if not returned set unit price to product selling price, else set unit price to price sold.
			String unitPrice = String.format("%.2f", product.getSellPrice());
			if(invoiceItem.getIsReturned()){
				unitPrice = String.format("%.2f", invoiceItem.getSellPrice());
			}
			
			
//			int newXpos = 140 - (unitPrice.length() * 4);
			
			row[2] = unitPrice;
//			g.drawString(unitPrice, newXpos, topMarker);
			
			

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
			
//			newXpos = 190 - (currentPriceAmount.length()*4);
			row[3] = currentPriceAmount;
//			g.drawString(currentPriceAmount, newXpos, topMarker);
			
			
//			g.setFont(new Font("Arial", Font.PLAIN, 8));
			
			row[4] = product.getName();
//			g.drawString(product.getName(), 8, topMarker);
			
			results.add(row);
		}
		
		return results;
	}
	
	public int findNormalAmountXPos(String s){
		return 190 - (s.length()*5);
	}
	
	public int findItemAmountXPos(String s){
		//205 - string size*4
		return 205 - (s.length()*4);
	}
	
	public String[] splitString(String s){
		String[] temp = s.split(" ");
		ArrayList<String> ss = new ArrayList<String>();
		int MAX = 32;
		int countChar = 0;
		String sTemp = "";
		for(int i=0; i<temp.length; i++){
			countChar += temp[i].length()+1;
			sTemp+=temp[i]+" ";
			if(countChar >= MAX){
				countChar = 0;
				ss.add(sTemp);
				sTemp="";
			}
			else if(countChar < MAX && i==temp.length-1){
				ss.add(sTemp);
			}
		}
		String[] out = new String[ss.size()];
		for(int i=0; i<ss.size();i++){
			out[i] = ss.get(i);
			
		}
		return out;
		
		
	}
	public Double roundDown(Double num){
		String numS = String.format("%.4f",num);
		logger.info(numS);
		int index = numS.indexOf(".");
		String  out = numS.substring(0,index+3);
		return Double.parseDouble(out);
	}
	
	public String convertDate(String date){
		String [] dateSplit = date.split("-");
		String year = dateSplit[0];
		String month = dateSplit[1];
		String day = dateSplit[2];
		return month+"/"+day+"/"+year;
	}

	public static ReceiptUtilities getReceiptUtilities() {
		return receiptUtilities;
	}
	
	
}

package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;

import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class InvoiceItemService {
	
	private static Logger logger = Logger.getLogger(InvoiceItemService.class);
	private static InvoiceItemService invoiceItemService = new InvoiceItemService();
	private InvoiceItemService(){
		
	}
	public static InvoiceItemService getInstance(){
		return invoiceItemService;
	}
	
	public ResultSet fetchAllDeferredInvoiceItems() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT invoice_item.OR_NO, INVOICE_NO, products_lu.PROD_CODE, products_lu.NAME, QUANTITY, TRANS_DT FROM invoice_item, invoice, products_lu WHERE invoice_item.OR_NO=invoice.OR_NO AND invoice_item.STORE_CODE = invoice.STORE_CODE AND invoice_item.PROD_CODE = products_lu.PROD_CODE AND invoice_item.DEFERRED = 1 AND invoice_item.STORE_CODE = "+Main.getStoreCode());		
		return rs;
	}
	
	public InvoiceItem toInvoiceItemObject(ResultSet rs) throws SQLException {
		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setCost(rs.getDouble("COST"));
		invoiceItem.setDiscountCode(rs.getInt("DISC_CODE"));
		invoiceItem.setIsDeferred(rs.getInt("DEFERRED"));
		invoiceItem.setOrNo(rs.getLong("OR_NO"));
		invoiceItem.setProductCode(rs.getString("PROD_CODE"));
		invoiceItem.setQuantity(rs.getInt("QUANTITY"));
		invoiceItem.setSellPrice(rs.getDouble("SELL_PRICE"));
		invoiceItem.setStoreNo(rs.getInt("STORE_CODE"));
		return invoiceItem;
	}
	
	public ResultSet fetchInvoiceItem(String orNo){
	//	"Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension"
		//TODO: Check selling price should be the price with discount
		ResultSet rs = Main.getDBManager().executeQuery("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, products_lu.SELL_PRICE, invoice_item.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE from invoice_item, products_lu where invoice_item.OR_NO = '"+orNo+"' and invoice_item.PROD_CODE = products_lu.PROD_CODE");
		logger.info("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, products_lu.SELL_PRICE, invoice_item.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE from invoice_item, products_lu where invoice_item.OR_NO = '"+orNo+"' and invoice_item.PROD_CODE = products_lu.PROD_CODE");
		return rs;
		
	}
	
	public ResultSet fetchDiscountedInvoiceItem(String orNo){
		ResultSet rs = Main.getDBManager().executeQuery("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY,  round(invoice_item.SELL_PRICE  - (invoice_item.SELL_PRICE * (discount_lu.DISC_RATE * .01)),3), round(products_lu.SELL_PRICE,2) , invoice_item.DEFERRED, invoice_item.DISC_CODE, round(invoice_item.quantity * invoice_item.SELL_PRICE,2) from invoice_item, products_lu, discount_lu where invoice_item.OR_NO = "+orNo+" and invoice_item.PROD_CODE = products_lu.PROD_CODE and invoice_item.DISC_CODE = discount_lu.DISC_NO and invoice_item.STORE_CODE = "+Main.getStoreCode());
		logger.info("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY,  round(invoice_item.SELL_PRICE  - (invoice_item.SELL_PRICE * (discount_lu.DISC_RATE * .01)),2), round(products_lu.SELL_PRICE,2) , invoice_item.DEFERRED, invoice_item.DISC_CODE, round(invoice_item.quantity * invoice_item.SELL_PRICE,2) from invoice_item, products_lu, discount_lu where invoice_item.OR_NO = "+orNo+" and invoice_item.PROD_CODE = products_lu.PROD_CODE and invoice_item.DISC_CODE = discount_lu.DISC_NO");
//		logger.info("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, products_lu.SELL_PRICE, invoice_item.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE from invoice_item, products_lu where invoice_item.OR_NO = '"+orNo+"' and invoice_item.PROD_CODE = products_lu.PROD_CODE");
		return rs;
	}
	
	/**
	 * Updates invoice_item entry marking deferred
	 * Deduct quantity to store's inventory_lu
	 * @param invoiceItem
	 * @return affected row
	 */
	public int processDeferredItem(Long orNo, String prodCode, String storeCode){
		String[] columns = new String[]{"DEFERRED"};
		String[] columnValues = new String[]{"0"};
		String[] whereColumns = new String[]{"OR_NO","PROD_CODE","STORE_CODE"};
		String[] whereValues = new String[]{orNo.toString(),prodCode, storeCode};
		int result = Main.getDBManager().update(columns, columnValues, "invoice_item", whereColumns, whereValues);
		InvoiceItem invoiceItem = getInvoiceItemObject(orNo, prodCode);
		InventoryService.getInstance().deductFromInventory(invoiceItem.getQuantity(), invoiceItem.getProductCode());
		return result;
	}
	
	/**
	 * Adds entry to invoice item 
	 * Deduct quantity to store's inventory_lu
	 * @param invoiceItem
	 * @return affected rows
	 */
	public int insert(InvoiceItem invoiceItem){
		String[] columns = new String[]{"OR_NO","PROD_CODE","DISC_CODE","QUANTITY","DEFERRED","SELL_PRICE","COST","STORE_CODE"};
		String[] columnValues = new String[]{invoiceItem.getOrNo().toString(),
											 invoiceItem.getProductCode().toString(),
											 invoiceItem.getDiscountCode().toString(),
											 invoiceItem.getQuantity().toString(),
											 invoiceItem.getIsDeferred().toString(),
											 invoiceItem.getSellPrice().toString(),
											 invoiceItem.getCost().toString(),
											 invoiceItem.getStoreNo().toString()
											 };
		Integer result = Main.getDBManager().insert(columns, columnValues, "invoice_item", null, null);
		logger.info("Added new invoice item entry. Affected: "+result);
		if(invoiceItem.getIsDeferred()==0){
			InventoryService.getInstance().deductFromInventory(invoiceItem.getQuantity(), invoiceItem.getProductCode());
		}
		
		return result;
	}
	
	public List<InvoiceItem> findInvoiceItemByOR(Long orNo){
		
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM invoice_item WHERE OR_NO	= "+orNo+" AND STORE_CODE = "+Main.getStoreCode() );
		try {
			ArrayList<InvoiceItem> invoiceItemList = new ArrayList<InvoiceItem>();
			while(rs.next()){
				InvoiceItem invoiceItem = new InvoiceItem();
				invoiceItem.setCost(rs.getDouble("COST"));
				invoiceItem.setSellPrice(rs.getDouble("SELL_PRICE"));
				invoiceItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
				invoiceItem.setQuantity(rs.getInt("QUANTITY"));
				invoiceItem.setDiscountCode(rs.getInt("DISC_CODE"));
				invoiceItem.setProductCode(rs.getString("PROD_CODE"));
				invoiceItem.setOrNo(rs.getLong("OR_NO"));
				invoiceItem.setIsDeferred(rs.getInt("DEFERRED"));
				invoiceItemList.add(invoiceItem);
			}
			return invoiceItemList;
		} catch (SQLException e) {
			
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public  Double getInvoiceItemAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(SELL_PRICE * QUANTITY) FROM invoice_item WHERE OR_NO	= "+orNo+" AND STORE_CODE = "+Main.getStoreCode() );
		try {
			if(rs.next()){
				return Double.parseDouble(String.format("%.2f", rs.getDouble(1)));
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0.0d;
	}
	public  Double getDiscountedInvoiceItemAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM((invoice_item.SELL_PRICE  - (invoice_item.SELL_PRICE * (discount_lu.DISC_RATE * .01))) * invoice_item.QUANTITY) FROM invoice_item, discount_lu WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode() +" AND invoice_item.DISC_CODE = discount_lu.DISC_NO");
		System.out.println("SELECT SUM((invoice_item.SELL_PRICE  - (invoice_item.SELL_PRICE * (discount_lu.DISC_RATE * .01))) * invoice_item.QUANTITY) FROM invoice_item, discount_lu WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode() +" AND invoice_item.DISC_CODE = discount_lu.DISC_NO");
		
		try {
			if(rs.next()){
				return Double.parseDouble(String.format("%.2f", rs.getDouble(1)));
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0.0d;
	}
	
	public ResultSet getInvoiceItem(Long orNo, String productCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, products_lu.SELL_PRICE, invoice_item.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE FROM invoice_item, products_lu WHERE invoice_item.PROD_CODE = '"+productCode+"' AND invoice_item.OR_NO = "+orNo+" AND invoice_item.PROD_CODE = products_lu.PROD_CODE");
		return rs;
	}
	
	public ResultSet getDiscountedInvoiceItem(Long orNo, String productCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, invoice_item.SELL_PRICE - (invoice_item.SELL_PRICE * (discount_lu.DISC_RATE * .01)) ,products_lu.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE FROM invoice_item, products_lu, discount_lu WHERE invoice_item.PROD_CODE = '"+productCode+"' AND invoice_item.OR_NO = "+orNo+" AND invoice_item.PROD_CODE = products_lu.PROD_CODE AND invoice_item.DISC_CODE = discount_lu.DISC_NO");
		return rs;
	}
	
	public InvoiceItem getInvoiceItemObject(Long orNo, String productCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM invoice_item WHERE OR_NO = "+orNo+" AND PROD_CODE = '"+productCode+"' AND STORE_CODE = "+Main.getStoreCode() );
		logger.info("SELECT * FROM invoice_item WHERE OR_NO = "+orNo+" AND PROD_CODE = '"+productCode+"' AND STORE_CODE = "+Main.getStoreCode());
		try {
			if(rs.next()){
				return toInvoiceItemObject(rs);
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	
	public Double getDiscountedAmount(Long orNo, String productCode){
		InvoiceItem invoiceItem = getInvoiceItemObject(orNo, productCode);
		Double discRate = DiscountService.getDiscRate(invoiceItem.getDiscountCode());
		Double discountedAmount = invoiceItem.getSellPrice() - (invoiceItem.getSellPrice() * discRate);
		return discountedAmount;
	}
	
	public Double getDiscountAmount(Long orNo, String productCode){
		InvoiceItem invoiceItem = getInvoiceItemObject(orNo, productCode);
		Double discRate = DiscountService.getDiscRate(invoiceItem.getDiscountCode());
		Double discountedAmount = invoiceItem.getSellPrice() * discRate;
		return discountedAmount;
	}
}

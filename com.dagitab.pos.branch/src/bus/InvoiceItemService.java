package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import domain.InvoiceItem;

public class InvoiceItemService {
	public static ResultSet fetchAllDeferredInvoiceItems() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT invoice_item.OR_NO, INVOICE_NO, products_lu.PROD_CODE, products_lu.NAME, QUANTITY, TRANS_DT FROM invoice_item, invoice, products_lu WHERE invoice_item.OR_NO=invoice.OR_NO AND invoice_item.STORE_CODE = invoice.STORE_CODE AND invoice_item.PROD_CODE = products_lu.PROD_CODE AND invoice_item.DEFERRED = 1 AND invoice_item.STORE_CODE = "+Main.getStoreCode());		
		return rs;
	}
	
	public static InvoiceItem toInvoiceItemObject(ResultSet rs) throws SQLException {
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
	
	public static ResultSet fetchPartialInvoiceItem(String orNo){
	//	"Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension"
		ResultSet rs = Main.getDBManager().executeQuery("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, products_lu.SELL_PRICE, invoice_item.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE from invoice_item, products_lu where invoice_item.OR_NO = '"+orNo+"' and invoice_item.PROD_CODE = products_lu.PROD_CODE");
		System.out.println("Select invoice_item.PROD_CODE, products_lu.name, invoice_item.QUANTITY, products_lu.SELL_PRICE, invoice_item.SELL_PRICE, invoice_item.DEFERRED, invoice_item.DISC_CODE, invoice_item.quantity * invoice_item.SELL_PRICE from invoice_item, products_lu where invoice_item.OR_NO = '"+orNo+"' and invoice_item.PROD_CODE = products_lu.PROD_CODE");
		return rs;
		
	}
	
	public static int processDeferredItem(Long orNo, String prodCode, String storeCode){
		int result = Main.getDBManager().executeUpdate("UPDATE invoice_item SET DEFERRED = 0 WHERE OR_NO = "+orNo+" AND PROD_CODE = '"+prodCode+"' AND STORE_CODE = "+storeCode);
		System.out.println("UPDATE invoice_item SET DEFERRED = 1 WHERE OR_NO = "+orNo+" AND PROD_CODE = '"+prodCode+"' AND STORE_CODE = "+storeCode);
		System.out.println("Result From Process Deferred Item: "+result);
		return result;
		
	}
	
	
	public static int insert(InvoiceItem invoiceItem){
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
		return result;
	}
	
	
}

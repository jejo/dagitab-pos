package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import main.Main;

import domain.InvoiceItem;

public class InvoiceItemService {
	public static ResultSet fetchAllDeferredInvoiceItems() {
//		List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT invoice_item.OR_NO, INVOICE_NO, products_lu.PROD_CODE, products_lu.NAME, QUANTITY, TRANS_DT FROM invoice_item left outer join invoice on invoice_item.OR_NO = invoice.OR_NO left outer join products_lu on invoice_item.PROD_CODE = products_lu.PROD_CODE WHERE invoice_item.DEFERRED = 1");		
//		
//		try {
//			while(rs.next()){
//				invoiceItems.add(toInvoiceItemObject(rs));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return invoiceItems;
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
}

package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import domain.Invoice;

import main.Main;
import util.StorePropertyHandler;

public class InvoiceService {
	
	public static String getNextORNumber(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT (MAX(`OR_NO`) + 1) FROM invoice WHERE store_code = '"+StorePropertyHandler.getStoreNo()+"'");
		
		try {
			if (rs.next()){
				return rs.getString(1);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static ResultSet fetchAllPartialInvoices(){
//		List<Invoice> invoices = new ArrayList<Invoice>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM invoice WHERE PARTIAL = '1'");		
//		
//		try {
//			while(rs.next()){
//				invoices.add(toInvoiceObject(rs));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return invoices;
		return rs;
	}

	public static Invoice toInvoiceObject(ResultSet rs) throws SQLException {
		Invoice invoice = new Invoice();
		invoice.setStoreNo(rs.getInt("STORE_CODE"));
		invoice.setOrNo(rs.getLong("OR_NO"));
		invoice.setIsReturn(rs.getInt("RETURN"));
		invoice.setInvoiceNo(rs.getLong("INVOICE_NO"));
		invoice.setEncoderCode(rs.getInt("ENCODER_CODE"));
		invoice.setCustomerNo(rs.getInt("CUST_NO"));
		invoice.setAssistantCode(rs.getInt("ASSIST_CODE"));
		return invoice;
	}
}

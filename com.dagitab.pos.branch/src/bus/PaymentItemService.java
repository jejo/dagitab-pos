package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import domain.PaymentItem;

public class PaymentItemService {
	public static String getPaymentType(Integer id){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT NAME FROM pay_type_lu WHERE pt_code = '"+id+"'");
		try {
			if(rs.next())
			{
				return rs.getString("NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static PaymentItem getPaymentById(Long id){
		PaymentItem paymentItem = new PaymentItem();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM payment_item WHERE or_no = '"+id+"'");
		try {
			if(rs.next()){
				paymentItem.setOrNo(rs.getLong("OR_NO"));
				paymentItem.setPaymentCode(rs.getInt("PT_CODE"));
				paymentItem.setAmount(rs.getDouble("AMT"));
				paymentItem.setCardType(rs.getString("CARD_TYPE"));
				paymentItem.setCardNo(rs.getString("CARD_NO"));
				paymentItem.setGcNo(rs.getString("GC_NO"));
				paymentItem.setCheckNo(rs.getString("CHECK_NO"));
				paymentItem.setStoreNo(rs.getInt("STORE_CODE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paymentItem;
	}

}

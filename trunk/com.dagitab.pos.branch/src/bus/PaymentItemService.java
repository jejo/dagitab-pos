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
	
	
	public static PaymentItem getPaymentItemById(Integer id){
		PaymentItem paymentItem = new PaymentItem();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM payment_item WHERE or_no = '"+id+"'");
		System.out.println("SELECT * FROM payment_item WHERE or_no = '"+id+"'");
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
	
	public static int insert(PaymentItem paymentItem){
		String[] columns = new String[]{"OR_NO","PT_CODE","AMT","CARD_TYPE","CARD_NO","GC_NO","CHECK_NO","STORE_CODE"};
		String[] columnValues = new String[]{paymentItem.getOrNo().toString(),
											 paymentItem.getPaymentCode().toString(),
											 paymentItem.getAmount().toString(),
											 paymentItem.getCardType(),
											 paymentItem.getCardNo(),
											 paymentItem.getGcNo(),
											 paymentItem.getCheckNo(),
											 paymentItem.getStoreNo().toString()};
		
		Integer result = Main.getDBManager().insert(columns, columnValues, "payment_item", null, null);
		return result;
	}
	
	
	public static Double getTotalPaymentAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(AMT) FROM payment_item WHERE OR_NO	= "+orNo+" AND STORE_CODE = "+Main.getStoreCode() );
		try {
			if(rs.next()){
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0d;
	}
	
	public static ResultSet findPaymentItems(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT pi.PT_CODE, NAME, AMT, CARD_TYPE, CARD_NO, CHECK_NO, GC_NO FROM payment_item pi, pay_type_lu pt WHERE pi.PT_CODE = pt.PT_CODE AND OR_NO = "+orNo);
		System.out.println("SELECT pi.PT_CODE, NAME, AMT, CARD_TYPE, CARD_NO, CHECK_NO, GC_NO FROM payment_item pi, pay_type_lu pt WHERE pi.PT_CODE = pt.PT_CODE AND OR_NO = "+orNo);
		return rs;
	}
}

package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public class PaymentTypeService {
	public static String getPaymentName(Integer paymentCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM pay_type_lu WHERE PT_CODE = "+paymentCode);
		try {
			if(rs.next()){
				return rs.getString("NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer getPaymentCode(String paymentName){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM pay_type_lu WHERE NAME = '"+paymentName+"'");
		try {
			if(rs.next()){
				return rs.getInt("PT_CODE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

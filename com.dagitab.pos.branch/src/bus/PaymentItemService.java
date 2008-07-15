package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import domain.Product;

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

}

package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
}

package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public class StoreService {
	
	public static String getStoreName(Integer storeNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM store_lu WHERE store_code = "+storeNo);
		try {
			if(rs.next()){
				return rs.getString("NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

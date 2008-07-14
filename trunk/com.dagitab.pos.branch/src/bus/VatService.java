package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public class VatService {
	public static double getVatRate(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT from global");
		try {
			if(rs.next()){
				double rate = rs.getDouble(1) * .01;
				return 1+rate;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return 0;
	}
}

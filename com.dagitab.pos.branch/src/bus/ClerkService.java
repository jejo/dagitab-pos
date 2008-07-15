package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public class ClerkService {
	
	public static ResultSet getAllClerks(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu");
		return rs;
	}
	
	public static ResultSet filterClerk(String s){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu " +
				"WHERE CLERK_CODE LIKE '%"+s+"%' " +
				"OR LAST_NAME LIKE '%"+s+"%' " +
				"OR FIRST_NAME LIKE '%"+s+"%'");
		return rs;
	}
	
	public static Boolean isValid(String clerkCode, String password){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT COUNT(*) FROM clerk_lu WHERE CLERK_CODE = "+clerkCode+" AND password = '"+password+"'");
		try {
			if(rs.next()){
				if(rs.getInt(1) > 0){
					return true;
				}
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return false;
	}
}

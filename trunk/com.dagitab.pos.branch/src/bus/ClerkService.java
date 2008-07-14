package bus;

import java.sql.ResultSet;

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
}

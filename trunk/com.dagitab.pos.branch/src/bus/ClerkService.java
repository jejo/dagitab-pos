package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import domain.Clerk;

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
	
	public static Clerk getClerkByID(Integer clerkID){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu WHERE clerk_code = "+clerkID);
		Clerk clerk = new Clerk();
		try {
			if(rs.next()){
				clerk.setClerkCode(rs.getLong("CLERK_CODE"));
				clerk.setAddress(rs.getString("ADDRESS"));
				clerk.setBirthDate(rs.getString("BIRTH_DT"));
				clerk.setClerkCode(rs.getLong("CLERK_CODE"));
				clerk.setFirstName(rs.getString("FIRST_NAME"));
				clerk.setLastName(rs.getString("LAST_NAME"));
				clerk.setNickName(rs.getString("NICK_NAME"));
				clerk.setPassword(rs.getString("PASSWORD"));
				return clerk;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int updateClerkPassword(Clerk clerk){
		String[] columns = new String[]{"PASSWORD"};
		String[] columnValues = new String[]{clerk.getPassword()};
		String[] whereColumns = new String[]{"CLERK_CODE"};
		String[] whereValues = new String[]{clerk.getClerkCode().toString()};
		return Main.getDBManager().update(columns, columnValues, "clerk_lu", whereColumns, whereValues);
	}
}

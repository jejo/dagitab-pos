package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import util.LoggerUtility;
import domain.ReturnReason;

public class ReturnReasonService {
	
	public static List<ReturnReason> getAllReturnReasons(){
		ArrayList<ReturnReason> listReturnReason = new ArrayList<ReturnReason>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM return_reason_lu");
		try {
			while(rs.next()){
				ReturnReason returnReason = new ReturnReason();
				returnReason.setReturnCode(rs.getInt("RETURN_CODE"));
				returnReason.setName(rs.getString("NAME"));
				returnReason.setDescription(rs.getString("DESC"));
				listReturnReason.add(returnReason);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return listReturnReason;
	}
	
	public static ReturnReason findReturnReasonByName(String name){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM return_reason_lu WHERE NAME = '"+name+"'");
		try {
			if(rs.next()){
				ReturnReason returnReason = new ReturnReason();
				returnReason.setReturnCode(rs.getInt("RETURN_CODE"));
				returnReason.setName(rs.getString("NAME"));
				returnReason.setDescription(rs.getString("DESC"));
				return returnReason;
				
			}
				
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
		
	}
	
}

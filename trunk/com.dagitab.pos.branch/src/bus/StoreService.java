package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static String getStoreAddress(Integer storeNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT ADDRESS FROM store_lu WHERE store_code = "+storeNo );
		try {
			if(rs.next()){
				return rs.getString("ADDRESS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[] getAllStores(){
		ArrayList<String> storeList = new ArrayList<String>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM store_lu");
		try {
			while(rs.next()){
				storeList.add(rs.getString("STORE_CODE")+"-"+rs.getString("NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] stores = new String[storeList.size()];
		for(int i = 0; i<storeList.size(); i++){
			stores[i] = storeList.get(i);
		}
		return stores;
	}
}

package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import domain.ReturnItem;

public class ReturnItemService {
	public static int insert(ReturnItem returnItem){
		String[] columns = new String[]{"OR_NO","PROD_CODE","RETURN_CODE","QUANTITY", "COST","SELL_PRICE","STORE_CODE"};
		String[] columnValues = new String[]{returnItem.getOrNo().toString(),
											 returnItem.getProductCode().toString(),
											 returnItem.getReturnCode().toString(),
											 returnItem.getQuantity().toString(),
											 returnItem.getCost().toString(),
											 returnItem.getSellPrice().toString(),
											 returnItem.getStoreCode().toString()
											 };
		Integer result = Main.getDBManager().insert(columns, columnValues, "returned_items", null, null);
		return result;
	}
	
	public static List<ReturnItem> getReturnedItems(Long orNo){
		List<ReturnItem> returnedItems = new  ArrayList<ReturnItem>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM returned_items WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		try {
			while(rs.next()){
				ReturnItem returnItem = new ReturnItem();
				returnItem.setOrNo(rs.getInt("OR_NO"));
				returnItem.setCost(rs.getDouble("COST"));
				returnItem.setProductCode(rs.getString("PROD_CODE"));
				returnItem.setQuantity(rs.getInt("QUANTITY"));
				returnItem.setReturnCode(rs.getInt("RETURN_CODE"));
				returnItem.setSellPrice(rs.getDouble("SELL_PRICE"));
				returnItem.setStoreCode(rs.getInt("STORE_CODE"));
				returnedItems.add(returnItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnedItems;
	}
	
	public static ReturnItem getReturnItem(Long orNo, String productCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM returned_items WHERE OR_NO = "+orNo+" AND PROD_CODE = "+productCode+" AND STORE_CODE = "+Main.getStoreCode());
		try {
			if(rs.next()){
				ReturnItem returnItem = new ReturnItem();
				returnItem.setOrNo(rs.getInt("OR_NO"));
				returnItem.setCost(rs.getDouble("COST"));
				returnItem.setProductCode(rs.getString("PROD_CODE"));
				returnItem.setQuantity(rs.getInt("QUANTITY"));
				returnItem.setReturnCode(rs.getInt("RETURN_CODE"));
				returnItem.setSellPrice(rs.getDouble("SELL_PRICE"));
				returnItem.setStoreCode(rs.getInt("STORE_CODE"));
				return returnItem;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//selling price in return item means discounted!
	public static Double getDiscountedAmount(Long orNo, String productCode){
		ReturnItem returnItem = getReturnItem(orNo, productCode);
		return returnItem.getSellPrice();
	}
	
	public static Double getReturnAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(SELL_PRICE) FROM returned_items WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		try {
			if(rs.next()){
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0d;
	}
}

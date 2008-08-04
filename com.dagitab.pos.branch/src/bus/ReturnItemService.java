package bus;

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
}

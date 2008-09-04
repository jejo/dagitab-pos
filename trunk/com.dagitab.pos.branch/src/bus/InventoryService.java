package bus;

import main.Main;

import org.apache.log4j.Logger;

public class InventoryService {
	private static InventoryService inventoryService = new InventoryService();
	private static Logger logger = Logger.getLogger(InventoryService.class);
	private InventoryService(){}
	
	public static InventoryService getInstance(){
		return inventoryService;
	}
	public int deductFromInventory(int quantity, String productCode){
		String sql = "UPDATE inventory_lu SET QUANTITY = QUANTITY - "+quantity+" WHERE PROD_CODE = \""+productCode+"\" AND STORE_CODE = "+Main.getStoreCode();
		Integer result = Main.getDBManager().executeUpdate(sql);
		if(result > 0){
			Main.getSyncManager().record(sql);
			logger.info("Updated inventory_lu subtracted quantity of "+quantity+" to product: "+productCode+". Affected: "+result);
		}
		return result;
	}
	public int addToInventory(int quantity, String productCode){
		String sql = "UPDATE inventory_lu SET QUANTITY = QUANTITY + "+quantity+" WHERE PROD_CODE = \""+productCode+"\" AND STORE_CODE = "+Main.getStoreCode();
		Integer result = Main.getDBManager().executeUpdate(sql);
		if(result > 0){
			Main.getSyncManager().record(sql);
			logger.info("Updated inventory_lu added quantity of "+quantity+" to product: "+productCode+". Affected: "+result);
		}
		return result;
	}
}

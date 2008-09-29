package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import util.LoggerUtility;
import domain.Product;

public class ProductService {
	public static ResultSet getAllProducts(){
		return Main.getDBManager().executeQuery("SELECT PROD_CODE, NAME, FORMAT(SELL_PRICE,2) FROM products_lu ORDER by NAME");
	}
	
	public static ResultSet filterProductById(String s){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PROD_CODE, NAME, FORMAT(SELL_PRICE,2) FROM products_lu " +
				"WHERE PROD_CODE LIKE \""+s+"%\" ORDER BY NAME");
		return rs;
	}
	
	public static ResultSet getByProductId(String s){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PROD_CODE, NAME, FORMAT(SELL_PRICE,2) FROM products_lu " +
				"WHERE PROD_CODE = \""+s+"\" ORDER BY NAME");
		return rs;
	}
	
	
	public static ResultSet filterProductByName(String s){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PROD_CODE, NAME, FORMAT(SELL_PRICE,2) FROM products_lu " +
				"WHERE NAME LIKE '%"+s+"%' ORDER BY NAME");
		return rs;
	}
	
	
	
	public static Product getProductById(String id){
		Product product = new Product();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM products_lu WHERE prod_code = '"+id+"'");
		try {
			if(rs.next()){
				product.setProdCode(rs.getString("PROD_CODE"));
				product.setName(rs.getString("NAME"));
				product.setDescription(rs.getString("DESCRIPTION"));
				product.setCatCode(rs.getInt("CAT_CODE"));
				product.setSubCatCode(rs.getInt("SUBCAT_CODE"));
				product.setSellPrice(rs.getDouble("SELL_PRICE"));
				product.setCost(rs.getDouble("COST"));
				product.setSupplierCode(rs.getInt("SUPPLIER_CODE"));
				product.setIsConsignment(rs.getInt("CONSIGNMENT"));
				product.setIsPackage(rs.getInt("PACKAGE"));
				product.setIsDeleted(rs.getInt("DELETE_FLAG"));
				return product;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static Product loadProductOfStore(String prodCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM inventory_lu i, products_lu p WHERE i.PROD_CODE = p.PROD_CODE AND i.STORE_CODE = "+Main.getStoreCode()+" AND p.PROD_CODE= \""+prodCode+"\" ORDER by p.NAME");
		Product product = new Product();
		try {
			if(rs.next()){
				product.setProdCode(rs.getString("PROD_CODE"));
				product.setName(rs.getString("NAME"));
				product.setDescription(rs.getString("DESCRIPTION"));
				product.setCatCode(rs.getInt("CAT_CODE"));
				product.setSubCatCode(rs.getInt("SUBCAT_CODE"));
				product.setSellPrice(rs.getDouble("SELL_PRICE"));
				product.setCost(rs.getDouble("COST"));
				product.setSupplierCode(rs.getInt("SUPPLIER_CODE"));
				product.setIsConsignment(rs.getInt("CONSIGNMENT"));
				product.setIsPackage(rs.getInt("PACKAGE"));
				product.setIsDeleted(rs.getInt("DELETE_FLAG"));
				return product;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static ResultSet filterProductInventory(String query, String storeCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT p.PROD_CODE, p.NAME, p.SELL_PRICE, i.QUANTITY, i.DEFERRED_QTY FROM products_lu p, inventory_lu i WHERE p.PROD_CODE = i.PROD_CODE AND (p.PROD_CODE LIKE \"%"+query+"%\" OR p.NAME LIKE \"%"+query+"%\") AND i.STORE_CODE = "+storeCode+" ORDER by p.NAME");
		
		return rs;
	}
}

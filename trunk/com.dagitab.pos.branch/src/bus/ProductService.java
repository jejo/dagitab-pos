package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Product;


import main.Main;

public class ProductService {
	public static ResultSet getAllProducts(){
		return Main.getDBManager().executeQuery("SELECT PROD_CODE, NAME, FORMAT(SELL_PRICE,2) FROM products_lu");
	}
	
	public static ResultSet filterProducts(String s){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PROD_CODE, NAME, FORMAT(SELL_PRICE,2) FROM products_lu " +
				"WHERE PROD_CODE LIKE \"%"+s+"%\" " +
						"OR NAME LIKE '%"+s+"%'");
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
}

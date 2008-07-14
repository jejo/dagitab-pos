package connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public class DataUtil {
	
	private static DataUtil dataUtil = null;
	
	public DataUtil(){
		
	}

	
	public static DataUtil getDataUtil(){
		if(dataUtil == null){
			dataUtil = new DataUtil();
		}
		return dataUtil;
	}

	public static void main(String[] args) throws Exception{
//		PrintWriter writer = new PrintWriter(new FileWriter("dump.txt"));
		BufferedReader br = new BufferedReader(new FileReader("1.CAC"));
		String s;
		while((s= br.readLine()) != null){
			DataUtil.getDataUtil().dataToRow(s);
		}
//		System.out.println(rowToData("ADD","supplier_lu",new String[] { "supplier_code"}, new String[] { "1" }));
		
//		BufferedReader reader = new BufferedReader(new FileReader("dump.txt"));
//		String s;
//		while((s=reader.readLine()) != null) {
//			DataUtil.dataToRow(s);
//		}
		
	}
	
	public static String rowToData(String action, String tableName,String[] keyFields, String[] keys) {
		String s=null;
		
		String whereClause =" WHERE ";
		
		for(int i=0;i<keyFields.length;i++) {
			whereClause += keyFields[i] +"=\"" + keys[i] +"\"";
			if(i<keyFields.length -1) {
				whereClause += " AND ";
			}
		}
		
		
		
		System.out.println("SELECT * FROM "+tableName+ whereClause + " LIMIT 1");
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM "+tableName+ whereClause + " LIMIT 1");	
		
		try {
			if(!rs.next()) {
				return s;
			}
			
			int cols = rs.getMetaData().getColumnCount();
			
			String fieldStr="";
			for(int i=0;i<keyFields.length;i++) {
				fieldStr+=keyFields[i];
				if(i < keyFields.length - 1) {
					fieldStr +=",";
				}
			}		
			
			
			String keyStr="";
			for(int i=0;i<keys.length;i++) {
				keyStr+=keys[i];				
				if(i < keys.length - 1) {
					keyStr +=",";
				}
			}
			
	

			
			s = action + "##" + tableName + "##" + fieldStr + "##" + keyStr + "##";
			
			for(int i=0;i<cols;i++) {
				if(tableName.equals("clerk_lu") && rs.getMetaData().getColumnName(i+1).equalsIgnoreCase("password")) {	
					ResultSet rs2 = Main.getDBManager().executeQuery("SELECT PASSWORD,PASSWORD FROM clerk_lu WHERE clerk_code="+rs.getString("clerk_code"));
//					ResultSet rs2 = Main.getDBManager().executeQuery("SELECT PASSWORD,AES_DECRYPT(PASSWORD,'babyland') FROM clerk_lu WHERE clerk_code="+rs.getString("clerk_code"));
					rs2.next();
					s+=rs2.getString(1);
				}
				else 	{
					s+=rs.getString(i+1).replace(System.getProperty("line.separator"), "\\");
				}
				if(i < cols - 1) {
					s+="##";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}


	
	
	public static void dataToRow(String s) throws Exception{
		boolean updated=false;
		
		String[] ss = s.split("##");
		String whereClause="";
		
		String[] keyFields = ss[2].split(",");
		String[] keys = ss[3].split(",");
		if(!ss[2].equals("")) {
			whereClause += " WHERE ";
		}
		
		for(int i=0;i<keyFields.length;i++) {
			whereClause += keyFields[i] +"=\""+keys[i]+"\"";
			if(i<keyFields.length -1) {
				whereClause += " AND ";
			}
		}
		System.out.println("SELECT * FROM "+ss[1] + whereClause + " LIMIT 1");
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM "+ss[1] + whereClause + " LIMIT 1");
//		ResultSet rs = Main.getDBManager().query("SELECT * FROM "+ss[1] + whereClause + " LIMIT 1");
		
		
		if(ss[0].equals("DELETE")) {
			if(!rs.next()) {
				return;
			}
			rs.deleteRow();
		}
		else {
			if(ss[0].equals("ADD")) {
				if(rs.next()) {
					return;
				}
				rs.moveToInsertRow();
			}
			else if(ss[0].equals("EDIT")) {
				if(!rs.next()) {
					return;
				}
			}			
			for(int i=4;i<ss.length;i++) {
				String type = rs.getMetaData().getColumnTypeName(i-3);
				Object obj = null;
				
				// check if items are already processed
				if(ss[0].equals("EDIT") && ss[1].equals("delivery_items")) {
					if(rs.getInt("processed_stat") == 1) {
						updated = true;
					}
				}
				if(ss[0].equals("EDIT") && ss[1].equals("invoice_item")) {
					if(rs.getInt("deferred") == 0) {
						updated = true;
					}
				}
				
				if(ss[0].equals("EDIT") && ss[1].equals("pull_out_items")) {
					if(rs.getInt("processed_stat") == 1) {
						updated = true;
					}
				}
				
				if(ss[i].equals("") &&  (type.equals("DATE") ||type.equals("DATETIME") ||type.equals("TIMESTAMP"))) {
					rs.updateNull(i-3);
				} else {
					rs.updateObject(i-3,ss[i].replace("\\", System.getProperty("line.separator")));
				}
			}
			if(ss[0].equals("ADD")) {
				rs.insertRow();
				rs.last();
			}
			else if(ss[0].equals("EDIT")) {
				rs.updateRow();
			}

			if(updated) {
				return;
				// update inventory for transactions, deliveries that have just been processed
			}
			
			
			if(ss[1].equals("clerk_lu")) {
//				Main.getDBManager().executeQuery("UPDATE clerk_lu SET PASSWORD = AES_ENCRYPT(\""+rs.getString("password")+"\" \"babyland\") WHERE clerk_code=" +rs.getString("clerk_code"));
				Main.getDBManager().executeQuery("UPDATE clerk_lu SET PASSWORD = \""+rs.getString("password")+"\" WHERE clerk_code=" +rs.getString("clerk_code"));
			}
				
//			if(ss[0].equals("EDIT") && ss[1].equals("delivery_items")) {
//				ResultSet rs2 = Main.getDBManager().query("SELECT sto_to_code FROM deliveries WHERE del_no="+rs.getInt("del_no"));
//				rs2.next();			
//				InventoryManager.acquire(rs2.getInt("sto_to_code"),rs.getString("prod_code"),rs.getInt("accepted_qty"));
//			}
//			
//			if(ss[0].equals("ADD") && ss[1].equals("invoice_item")) {
//				if(rs.getInt("deferred") == 0)
//					InventoryManager.withdraw(rs.getInt("store_code"),rs.getString("prod_code"),rs.getInt("quantity"));
//				else
//					InventoryManager.deferProduct(rs.getInt("store_code"),rs.getString("prod_code"),rs.getInt("quantity"));
//			}
//			
//			if(ss[0].equals("EDIT") && ss[1].equals("invoice_item")) {
//				if(rs.getInt("deferred") == 0) {
//					InventoryManager.processDeferred(rs.getInt("store_code"),rs.getString("prod_code"),rs.getInt("quantity"));
//				}
//			}
//			
//			if(ss[0].equals("EDIT") && ss[1].equals("pull_out_items")) {
//				if(rs.getInt("processed_stat") == 1) {
//					ResultSet rs2 = Main.getDBManager().query("SELECT sto_to_code FROM pull_outs WHERE pull_out_no="+rs.getInt("pull_out_no"));
//					rs2.next();			
//					
//					InventoryManager.withdraw(rs2.getInt("sto_to_code"),rs.getString("prod_code"),rs.getInt("quantity"));
//				}
//			}
				
	
		}
		
		
	}

}

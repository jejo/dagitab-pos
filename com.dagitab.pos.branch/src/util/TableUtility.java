package util;

import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class TableUtility {
	
	public static void fillTable(JTable table, ResultSet rs , String[] tableColumnsName){
		DefaultTableModel aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(tableColumnsName);
		try {
//			 Loop through the ResultSet and transfer in the Model
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while(rs.next()){
			 Object[] objects = new Object[colNo];
			
			 for(int i=0;i<colNo;i++){
			  objects[i]=rs.getObject(i+1);
			  }
			 aModel.addRow(objects);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		table.setModel(aModel);
	}
}

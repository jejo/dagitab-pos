package com.dagitab.pos.util;

import java.sql.ResultSet;
import java.sql.SQLException;

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
			
		}
		catch(Exception ex){
			LoggerUtility.getInstance().logStackTrace(ex);
		} finally{
			try {
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		table.setModel(aModel);
	}
	
	public static void fillTable(JTable table, Object[][] objects, String[] tableColumnsName){
		DefaultTableModel aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(tableColumnsName);
		try 
		{
			for(Object[] row : objects) {
				aModel.addRow(row);
			}
		}catch(Exception ex){
			LoggerUtility.getInstance().logStackTrace(ex);
		}
		table.setModel(aModel);
	}
	
	public static void clearTable(JTable table) {
		DefaultTableModel aModel = new DefaultTableModel();
		table.setModel(aModel);
	}
}

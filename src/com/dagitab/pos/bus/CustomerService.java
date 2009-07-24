package com.dagitab.pos.bus;

import java.sql.ResultSet;

import com.dagitab.pos.main.Main;


public class CustomerService {
	public static ResultSet getAllCustomers(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM customer_lu ORDER by LAST_NAME");
		return rs;
	}
	
	public static ResultSet filterCustomer(String s){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM customer_lu " +
				"WHERE CUST_NO LIKE '%"+s+"%' " +
				"OR LAST_NAME LIKE '%"+s+"%' " +
				"OR FIRST_NAME LIKE '%"+s+"%' ORDER by LAST_NAME");
		return rs;
	}
}

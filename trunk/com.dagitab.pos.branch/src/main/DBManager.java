package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import util.LoggerUtility;


public class DBManager {
	private static Statement statement;
	private static Connection connection;
	private static Logger logger = Logger.getLogger(DBManager.class);

	public DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	public static boolean connect() {
		String url = "jdbc:mysql://localhost/babyland";
		return connect(url);

	}

	public static boolean connect(String url) {
		try {
			connection = DriverManager.getConnection(url, "root", "root");
			logger.info("Connection @ " + url);
			return true;
		} catch (SQLException e) {
			logger.error("Unable to connect to: " + url + "\n");
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
	}
	
	public static boolean connect(String dbIP, String dbSchema, String dbUser, String dbPass) {
		String url = "jdbc:mysql://"+dbIP+"/"+dbSchema;
		try {
			connection = DriverManager.getConnection(url, dbUser, dbPass);
			logger.info("Connection @ " + url);
			return true;
		} catch (SQLException e) {
			logger.error("Unable to connect to: " + url );
			LoggerUtility.getInstance().logStackTrace(e);
			return false;
		}
	}
	
	public Connection getConnection(){
		return connection;
	}

	public void delete(String table, String[] whereColumns, String[] whereValues) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String whereSt = "";
			if (whereColumns != null && whereColumns.length > 0
					&& whereValues != null && whereValues.length > 0) {
				whereSt += "WHERE ";
				for (int i = 0; i < whereColumns.length; i++) {
					whereSt += whereColumns[i] + "=\"" + whereValues[i] + "\"";
					if (i < whereColumns.length - 1)
						whereSt += " AND ";
				}
			}
			String sql = "DELETE FROM `" + table + "` " + whereSt;
			logger.info(sql);
			statement.execute(sql);
			Main.getSyncManager().record(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}

	}

	public ResultSet getSpecificRow(String table, String primary, int id) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return statement.executeQuery("SELECT * FROM " + table + " WHERE "
					+ primary + "=\"" + id + "\"");

		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return null;
		}
	}

	public ResultSet searchFilter(String[] columns, String table,
			String[] whereColumns, String filter, String[] whereColumns2,
			String[] constraints) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String columnSt = "";
			for (int i = 0; i < columns.length; i++) {
				columnSt += columns[i];
				if (i < columns.length - 1)
					columnSt += ",";
			}

			String whereSt = "";
			String orderBy = " ORDER BY " + columns[0];
			if ((whereColumns != null && whereColumns.length > 0 && filter != null && filter
					.length() > 0)
					|| whereColumns2 != null
					&& whereColumns2.length > 0
					&& constraints != null && constraints.length > 0)
				whereSt += "WHERE ";

			if (whereColumns != null && whereColumns.length > 0 && filter != null
					&& filter.length() > 0) {
				whereSt += "(";
				for (int i = 0; i < whereColumns.length; i++) {
					whereSt += whereColumns[i] + " LIKE \"" + filter + "%\"";
					if (i < whereColumns.length - 1)
						whereSt += " OR ";
				}
				whereSt += ")";
			}
			if (whereColumns2 != null && whereColumns2.length > 0
					&& constraints != null && constraints.length > 0) {
				if (whereSt.length() > 6)
					whereSt += " AND ";
				for (int i = 0; i < whereColumns2.length; i++) {
					whereSt += whereColumns2[i] + "=\"" + constraints[i] + "\"";
					if (i < whereColumns2.length - 1)
						whereSt += " AND ";
				}

			}
			if (whereSt.length() > 0)
				whereSt += " AND delete_flag=0";
			else
				whereSt += " WHERE delete_flag=0";

////			logger.info("SELECT " + columnSt + " FROM `" + table + "` "
//					+ whereSt);
			return statement.executeQuery("SELECT " + columnSt + " FROM `" + table
					+ "` " + whereSt + orderBy);
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return null;
		}

	}

	public ResultSet searchFilter(String[] columns, String table,
			String[] whereColumns, String filter) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String columnSt = "";
			for (int i = 0; i < columns.length; i++) {
				columnSt += columns[i];
				if (i < columns.length - 1)
					columnSt += ",";
			}

			String whereSt = "";
			String orderBy = " ORDER BY " + columns[0];
			if (whereColumns != null && whereColumns.length > 0 && filter != null
					&& filter.length() > 0) {
				whereSt += "WHERE ";

				for (int i = 0; i < whereColumns.length; i++) {
					whereSt += whereColumns[i] + " LIKE \"" + filter + "%\"";
					if (i < whereColumns.length - 1)
						whereSt += " OR ";
				}
			}
			if (whereSt.length() > 0)
				whereSt += " AND delete_flag=0";
			else
				whereSt += " WHERE delete_flag=0";
//			logger.info("SELECT " + columnSt + " FROM `" + table + "` "
//					+ whereSt + orderBy);
			return statement.executeQuery("SELECT " + columnSt + " FROM `" + table
					+ "` " + whereSt + orderBy);
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return null;
		}

	}

	public int update(String[] columns, String[] columnValues, String table,
			String[] whereColumns, String[] whereValues) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String updateSt = "";
			if (columns != null && columns.length > 0 && columnValues != null
					&& columnValues.length > 0) {
				for (int i = 0; i < columns.length; i++) {
					updateSt += columns[i] + "=\"" + columnValues[i] + "\"";
					if (i < columns.length - 1) {
						updateSt += ", ";
					}
				}
			}
			String whereSt = "";
			if (whereColumns != null && whereColumns.length > 0
					&& whereValues != null && whereValues.length > 0) {
				whereSt += "WHERE ";
				for (int i = 0; i < whereColumns.length; i++) {
					whereSt += whereColumns[i] + "=\"" + whereValues[i] + "\"";
					if (i < whereColumns.length - 1)
						whereSt += " AND ";
				}
			}
			
			String sql = "UPDATE `" + table + "` SET " + updateSt + " "+ whereSt; 
			logger.info(sql);
			int result =  statement.executeUpdate(sql);
			if(result > 0){
				Main.getSyncManager().record(sql);
			}
			return result;
			
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return -1;
		}
	}

	public int insert(String[] columns, String[] columnValues, String table,
			String[] whereColumns, String[] whereValues) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String columnsSt = "(";
			String valuesSt = "(";
			if (columns != null && columns.length > 0 && columnValues != null
					&& columnValues.length > 0) {
				for (int i = 0; i < columns.length; i++) {
					columnsSt += columns[i];
					valuesSt += "\"" + columnValues[i] + "\"";
					if (i < columns.length - 1) {
						columnsSt += ", ";
						valuesSt += ", ";
					}
				}
			}
			columnsSt += ")";
			valuesSt += ")";

			String whereSt = "";
			if (whereColumns != null && whereColumns.length > 0
					&& whereValues != null && whereValues.length > 0) {
				whereSt += "WHERE ";
				for (int i = 0; i < whereColumns.length; i++) {
					whereSt += whereColumns[i] + "=\"" + whereValues[i] + "\"";
					if (i < whereColumns.length - 1)
						whereSt += " AND ";
				}
			}
			
			String sql = "INSERT INTO `" + table +"` "+ columnsSt + " VALUES "+ valuesSt + " " + whereSt; 
			
			logger.info(sql);
			int result =  statement.executeUpdate(sql);
			if(result > 0){
				Main.getSyncManager().record(sql);
			}
			return result;
			
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return -1;
		}
	}

	public ResultSet executeQuery(String s) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return statement.executeQuery(s);

		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return null;
		}
	}
	
	public int executeUpdate(String query){
		try {
			statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0;
	}
	


	public void execute(String s) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.execute(s);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
	}

	public ResultSet query(String[] columns, String table,
			String[] whereColumns, String[] whereValues, boolean b) {
		try {
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String columnSt = "";
			for (int i = 0; i < columns.length; i++) {
				columnSt += columns[i];
				if (i < columns.length - 1)
					columnSt += ",";
			}

			String whereSt = "";
			String orderBy="";
			if(b)
			 orderBy = " ORDER BY " + columns[0];
			if (whereColumns != null && whereColumns.length > 0
					&& whereValues != null && whereValues.length > 0) {
				whereSt += "WHERE ";
				for (int i = 0; i < whereColumns.length; i++) {
					whereSt += whereColumns[i] + "=\"" + whereValues[i] + "\"";
					if (i < whereColumns.length - 1)
						whereSt += " AND ";
				}
			}
			return statement.executeQuery("SELECT " + columnSt + " FROM `" + table
					+ "` " + whereSt+orderBy);
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			return null;
		}

	}

	
	@SuppressWarnings("static-access")
	public static void main(String args[]) throws Exception {
		DBManager dbMan = new DBManager();
		dbMan.connect();
		System.out.println(dbMan.getConnection().getMetaData().getDatabaseProductName());
	}
}

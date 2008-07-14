package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.DBManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DailySalesReport {
	
	private static int topMarker = 8;
	private static HSSFWorkbook wb;
	
	public static boolean generate2(String fileName, DBManager db, String storeCode, String startDate, String endDate){
		
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/daily_sales.xls"));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			//page generated on:
			HSSFRow row = sheet.getRow(1);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
			
			//branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = db.executeQuery("SELECT name FROM store_lu WHERE store_code="+storeCode);
			if(rs1.next()){
				cell.setCellValue(rs1.getString(1));
			}
			
			//date
			row = sheet.getRow(3);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			cell.setCellValue(startDate+" - "+endDate);
			
			
			//report data
			int rowCounter = topMarker;
			String query = "SELECT * FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' && i.store_code="+storeCode;
		
			ResultSet rs = db.executeQuery(query);
			while(rs.next())
			{
				query = "SELECT * FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+storeCode;
				
				ResultSet rs2 = db.executeQuery(query);
				while(rs2.next())
				{
					row = sheet.getRow(rowCounter);
					
					//or no
					cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,false);
					cell.setCellValue(rs.getString("OR_NO"));
				
				
					//invoice no
					cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
					cell.setCellValue(rs.getString("INVOICE_NO"));
					
					rowCounter++;
					sheet.shiftRows(rowCounter+1,rowCounter+2,1);
				}

			}
		
		
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();
			return true;
		}
		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
	}
	
	public static boolean generate(String fileName, DBManager db, String storeCode, String startDate, String endDate){
		HSSFCell cell;
		POIFSFileSystem fs;
		
		//totals 
		
		int totalQty = 0;
		double totalSellPrice = 0;
		double totalDiscPrice = 0;
		double totalSubtotal =0;
		double totalTotal = 0;
		
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/daily_sales.xls"));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			//page generated on:
			HSSFRow row = sheet.getRow(1);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
			
			//branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = db.executeQuery("SELECT name FROM store_lu WHERE store_code="+storeCode);
			if(rs1.next()){
				cell.setCellValue(rs1.getString(1));
			}
			
			//date
			row = sheet.getRow(3);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			cell.setCellValue(startDate+" - "+endDate);
			
			
			//report data
			int rowCounter = topMarker;
			String query = "SELECT * FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' && i.store_code="+storeCode;
		
			ResultSet rs = db.executeQuery(query);
			while(rs.next())
			{
				int countRows = 0; //count row
				String queryCount = "SELECT COUNT(1) FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+storeCode;
				ResultSet rs2 = db.executeQuery(queryCount);
				if(rs2.next()){
					countRows = rs2.getInt(1);
//					System.out.println(countRows);
				}
					
				int currentRow = 0; //mark current row
				
				query = "SELECT * FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+storeCode;
				
				rs2 = db.executeQuery(query);
				while(rs2.next())
				{
					row = sheet.getRow(rowCounter);
					
					if(currentRow == 0)
					{
						//OR no
						
						cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,false);
						cell.setCellValue(rs.getString("OR_NO"));
					
					
						//invoice no
						cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
						cell.setCellValue(rs.getString("INVOICE_NO"));
					}
					
//					product code
					cell = HSSFUtil.createStringCell(wb,row, (short) 2,false,false);
					cell.setCellValue(rs2.getString("PROD_CODE"));
					
					query = "SELECT * FROM products_lu WHERE PROD_CODE = \""+rs2.getString("PROD_CODE")+"\"";
					ResultSet rs3 = db.executeQuery(query);
					if(rs3.next())
					{
						//product name
						cell = HSSFUtil.createStringCell(wb,row, (short) 3,false,false);
						cell.setCellValue(rs3.getString("NAME"));
						
						//unit cost
						cell = HSSFUtil.createAmountCell(wb,row, (short) 5,false,false);
						cell.setCellValue(String.format("%.2f", rs3.getDouble("SELL_PRICE")));
						totalSellPrice += rs3.getDouble("SELL_PRICE");
						
					}
					
					
					cell = HSSFUtil.createStringCell(wb,row, (short) 4,false,false);
					cell.setCellValue(rs2.getInt("QUANTITY"));
					totalQty+=rs2.getInt("QUANTITY");
					
					//disc
					query = "SELECT * FROM discount_lu WHERE DISC_NO = "+rs2.getString("DISC_CODE");
					rs3 = db.executeQuery(query);
					if(rs3.next())
					{
//						qty
						cell = HSSFUtil.createStringCell(wb,row, (short) 6,false,false);
						cell.setCellValue(rs3.getString("DISC_RATE")+"%");
					}
					
					//disc unit cost
					cell = HSSFUtil.createAmountCell(wb,row, (short) 7,false,false);
					cell.setCellValue(String.format("%.2f",rs2.getDouble("SELL_PRICE")));
					totalDiscPrice += rs2.getDouble("SELL_PRICE");
					
					
					//subtotal
					double subtotal = Double.parseDouble(rs2.getString("SELL_PRICE"))*Integer.parseInt(rs2.getString("QUANTITY"));
					String stotal = String.format("%.2f", subtotal);
					cell = HSSFUtil.createAmountCell(wb,row, (short) 8,false,false);
					cell.setCellValue(stotal);
					totalSubtotal += subtotal;
					
					
					currentRow++;
					rowCounter++;
					sheet.shiftRows(rowCounter+1,rowCounter+2,1);
					
					if(currentRow == countRows){
						//TOTAL
						query = "SELECT SUM(SELL_PRICE*QUANTITY) FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+storeCode;
						ResultSet rs4 = db.executeQuery(query);
						if(rs4.next())
						{
							cell = HSSFUtil.createAmountCell(wb,row, (short) 9,false,false);
							cell.setCellValue(String.format("%.2f",Double.parseDouble(rs4.getString(1))));
							totalTotal +=Double.parseDouble(rs4.getString(1));
						}
						
						//cashier id
						cell = HSSFUtil.createStringCell(wb,row, (short) 10,false,false);
						cell.setCellValue(rs.getString("ENCODER_CODE"));
						
						//sales specialist
						cell = HSSFUtil.createStringCell(wb,row, (short) 11,false,false);
						cell.setCellValue(rs.getString("ASSIST_CODE"));
					}
					
					
				}
			}
			
			row = sheet.getRow(rowCounter+2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 0,false,false);
			cell.setCellValue("TOTAL");
			cell = HSSFUtil.createStringCell(wb,row, (short) 4,false,false);
			cell.setCellValue(totalQty);
			cell = HSSFUtil.createStringCell(wb,row, (short) 5,false,false);
			cell.setCellValue(String.format("%.2f",totalSellPrice));
			cell = HSSFUtil.createStringCell(wb,row, (short) 7,false,false);
			cell.setCellValue(String.format("%.2f",totalDiscPrice));
			cell = HSSFUtil.createStringCell(wb,row, (short) 8,false,false);
			cell.setCellValue(String.format("%.2f",totalSubtotal));
			cell = HSSFUtil.createStringCell(wb,row, (short) 9,false,false);
			cell.setCellValue(String.format("%.2f",totalTotal));
			
//			 Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();
			return true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	
	
}

package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;



import main.DBManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class MonthlySales {

	private static int topMarker = 7;
	private static HSSFWorkbook wb;
	
	public static void main(String[] args){
//		generate("")
	}


public static boolean generate(String fileName, DBManager db, String storeCode, String year, String month) {
		HSSFCell cell;
		POIFSFileSystem fs;
		
		try {
//			ReportUtil.setNormalFontSize((short)7);
			
			fs = new POIFSFileSystem(new FileInputStream("xls/MonthlySales.xls"));

			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);


			// page generated
			HSSFRow row = sheet.getRow(1);
			cell = createStringCell(wb,row, (short) 1,false,false);

			SimpleDateFormat format = new SimpleDateFormat(
					"MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));

			// branch
			row = sheet.getRow(2);
			cell = createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = db.executeQuery("SELECT name FROM store_lu WHERE store_code="+storeCode);
			if(rs1.next()) {
				cell.setCellValue(rs1.getString(1));
			} else {
				cell.setCellValue("ALL BRANCHES");
			}			
			
				
			// start date
			row = sheet.getRow(3);
			cell = createStringCell(wb,row, (short) 1,false,false);
			//cell.setCellValue(ResourceManager.dateFormat(filter.startDate,"MMM dd, yyyy"));
			cell.setCellValue(year+"-"+month);
			
			
			
			// test
			// row = sheet.createRow(5);
			// cell = ReportUtil.createAmountCell(wb,row, (short) 1,false);
			// cell.setCellValue(1500);
			//			
			// row = sheet.createRow(6);
			// cell = ReportUtil.createAmountCell(wb,row, (short) 1,false);
			// cell.setCellValue(0.2);
			
			// sheet.shiftRows(topMarker, topMarker+2, 1);
			//			
			// row = sheet.createRow(7);
			// cell = createFormulaCell(wb,row, (short) 1,"B6/B7",false,true);
			
//			String branchClause="";
//			if(filter.branch != 0) {
//				branchClause=" && i.store_code="+filter.branch;
//			}
			String branchClause=" && i.store_code="+storeCode;

// rs3 = Main.getDBManager().query("SELECT
// SUM(quantity),SUM(quantity*cost),SUM(quantity*sell_price), FROM invoice_item
// WHERE prod_code=\""+rs.getString("prod_code")+"\"");
			
			int rowCounter=topMarker;
			ResultSet rs = db.executeQuery("SELECT i.or_no,i.store_code,i.cust_no,i.encoder_code FROM invoice i WHERE MONTH(i.trans_dt) =\'"+month+"\' AND YEAR(i.trans_dt)=\'"+year + "\'"+branchClause);
			System.out.println("SELECT i.or_no,i.store_code,i.cust_no,i.encoder_code FROM invoice i WHERE MONTH(i.trans_dt) =\'"+month+"\' AND YEAR(i.trans_dt)=\'"+year + "\'"+branchClause);
			double cash,check,card,gc,others,cashTotal = 0,checkTotal = 0,cardTotal = 0,gcTotal = 0,othersTotal=0,totalTotal=0;
			while(rs.next()) {
			
				row = sheet.createRow(rowCounter);
		
				cell = createStringCell(wb,row,(short)0,false,true);
				cell.setCellValue(rs.getString(1));
			
				ResultSet rs2 = db.executeQuery("SELECT CONCAT(first_name,' ',last_name) FROM customer_lu WHERE cust_no="+rs.getInt(3));
				cell = createStringCell(wb,row,(short)1,false,true);
				if(rs2.next()) {	
					cell.setCellValue(rs2.getString(1));					
				}
				else {
					cell.setCellValue("N/A");
				}
				
				rs2 = db.executeQuery("SELECT CONCAT(first_name,' ',last_name) FROM clerk_lu WHERE clerk_code="+rs.getInt(4));
				cell = createStringCell(wb,row,(short)2,false,true);
				if(rs2.next()) {	
					cell.setCellValue(rs2.getString(1));					
				}
				else {
					cell.setCellValue("N/A");
				}
				
				
				rs2 = db.executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Cash\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = createAmountCell(wb,row,(short)3,false,true);
				if(rs2.next()) {
					cash=rs2.getDouble(1);
					cashTotal+=cash;
				}
				else {
					cash=0;
				}
				
				cell.setCellValue(cash);
				
				
				rs2 = db.executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Credit Card\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = createAmountCell(wb,row,(short)4,false,true);
				if(rs2.next()) {
					card=rs2.getDouble(1);
					cardTotal+=card;
				}
				else {
					card=0;

				}
				cell.setCellValue(card);
				
				rs2 = db.executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = createAmountCell(wb,row,(short)5,false,true);
				if(rs2.next()) {
					gc=rs2.getDouble(1);
					gcTotal+=gc;
				}
				else {
					gc=0;

				}
				cell.setCellValue(gc);
				
				rs2 = db.executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name=\"Bank Check\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = createAmountCell(wb,row,(short)6,false,true);
				if(rs2.next()) {
					check=rs2.getDouble(1);
					checkTotal+=check;
				}
				else {
					check=0;
				}
				cell.setCellValue(check);
				
				rs2 = db.executeQuery("SELECT SUM(amt) FROM payment_item p,pay_type_lu ptype WHERE p.pt_code=ptype.pt_code && ptype.name!=\"Bank Check\" && ptype.name!=\"Cash\" && ptype.name!=\"Credit Card\" && ptype.name!=\"Gift Certificate\" && p.or_no="+rs.getInt(1) + " && p.store_code="+rs.getInt(2));
				cell = createAmountCell(wb,row,(short)7,false,true);
				if(rs2.next()) {
					others=rs2.getDouble(1);
					othersTotal+=others;
				}
				else {
					others=0;
				}
				cell.setCellValue(others);

				double total= cash+check+card+gc+others;
				totalTotal += total;
				
				cell = createAmountCell(wb,row,(short)8,false,true);
				cell.setCellValue(total);
				
				rowCounter++;
				sheet.shiftRows(rowCounter+1,rowCounter+2,1);

			}
			row = sheet.createRow(rowCounter+2);
			cell = createAmountCell(wb,row,(short)3,true,true);
			cell.setCellValue(cashTotal);
			cell = createAmountCell(wb,row,(short)4,true,true);
			cell.setCellValue(cardTotal);
			cell = createAmountCell(wb,row,(short)5,true,true);
			cell.setCellValue(gcTotal);
			cell = createAmountCell(wb,row,(short)6,true,true);
			cell.setCellValue(checkTotal);
			cell = createAmountCell(wb,row,(short)7,true,true);
			cell.setCellValue(othersTotal);
			cell = createAmountCell(wb,row,(short)8,true,true);
			cell.setCellValue(totalTotal);
			
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

			return true;
		} catch (FileNotFoundException e) {
//			MessageDialogs.openErrorDialog(Display.getDefault().getActiveShell(),"File is in use.");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
//			MessageDialogs.openErrorDialog(Display.getDefault().getActiveShell(),"System Error.");
			e.printStackTrace();
			return false;
		}

	}

	static HSSFCell createAmountCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("0.00"));
		if (border) {
			setBorder(style, true, true, true, true);
		}
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}
	
	static HSSFCell createPercentCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("0.00 %"));
		if (border) {
			setBorder(style, true, true, true, true);
		}
		if(bold) {
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		}
		
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}
	
	static HSSFCell createIntCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
		if (border) {
			setBorder(style, true, true, true, true);
		}
		if(bold) {
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		}
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}
	
	static HSSFCell createStringCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		if (border) {
			setBorder(style, true, true, true, true);
		}
		if(bold) {
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		}
		cell.setCellStyle(style);
		return cell;
	}
	
	static HSSFCell createFormulaCell(HSSFWorkbook wb, HSSFRow row, short index,
			String formula, boolean bold, boolean isPercent) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		setBorder(style, true, true, true, true);
		cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(formula);
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("0.00 %"));
		if(bold) {
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		cell.setCellStyle(style);
		return cell;
	}
	
	static void setBorder(HSSFCellStyle style, boolean top, boolean left,
			boolean bottom, boolean right) {
		if (top) {
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		}
		if (left) {
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		}
		if (right) {
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		}
		if (top) {
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// style.setTopBorderColor(HSSFColor.BLACK.index);
		}
	}



	// static HSSFCell createTotalCell(HSSFRow row, short index) {
	// HSSFCell cell = row.createCell(index);
	//
	// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	// cell.setCellStyle(totalStyle);
	// return cell;
	// }
}

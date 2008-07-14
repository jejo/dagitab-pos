package reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import main.DBManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class CurrentInventory {
	
	public static void main(String[] args){
		//generate("This.xls");
	}
	
	
	public static boolean generate(String fileName,Vector<Vector<String>> data, DBManager db, String storeCode) {
		HSSFCell cell;
		POIFSFileSystem fs;
		int topMarker = 9;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream("xls/currentinventory.xls"));
		
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
	
	
			// page generated
			HSSFRow row = sheet.getRow(1);
			cell = createStringCell(wb,row, (short) 1,false,false);
	
			SimpleDateFormat format = new SimpleDateFormat(
					"MMMM dd, yyyy hh:mm:ss a");
			cell.setCellValue(format.format(new Date(System.currentTimeMillis())));
			
//			 branch
			row = sheet.getRow(2);
			cell = HSSFUtil.createStringCell(wb,row, (short) 1,false,false);
			
			ResultSet rs1 = db.executeQuery("SELECT name FROM store_lu WHERE store_code="+storeCode);
			try {
				if(rs1.next()) {
					cell.setCellValue(rs1.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			int rowCounter=topMarker;
			
			for(int i = 0; i<data.size(); i++){
				row = sheet.createRow(rowCounter);
				int colnum = 0;
				for(int j=0; j<data.get(i).size();j++){
					if(j != 4 && j!=5){
						if(j == 3){
							cell = createAmountCell(wb,row,(short)colnum,false,true);
							cell.setCellValue(Double.parseDouble(data.get(i).get(j)));
						}
						else{
							cell = createStringCell(wb,row,(short)colnum,false,true);
							cell.setCellValue(data.get(i).get(j));
						}
						colnum++;
					}
				}
				rowCounter++;
			}
			
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
}

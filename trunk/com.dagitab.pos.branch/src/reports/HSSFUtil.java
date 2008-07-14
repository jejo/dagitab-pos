package reports;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFUtil {
	
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

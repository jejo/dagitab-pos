package reports;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

public class ReportUtil {

	private static short normalFontSize = 7;
	private static short headerFontSize = 10;
	private static short borderStyle = HSSFCellStyle.BORDER_THIN;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static HSSFCell createAmountCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("0.00"));
		if (border)
			setBorder(style, true, true, true, true);

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);

		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}

	static HSSFCell createTimestampCell(HSSFWorkbook wb, HSSFRow row,
			short index, boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("m/d/yy h:mm AM/PM"));
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		if (border)
			setBorder(style, true, true, true, true);

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);

		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}

	static HSSFCell createDateCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("m/d/yy"));
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		if (border)
			setBorder(style, true, true, true, true);

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);

		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}
	
	
	static HSSFCell createCustomDateCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border,String dateFormat) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat(dateFormat));
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		if (border)
			setBorder(style, true, true, true, true);

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);

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

		if (border)
			setBorder(style, true, true, true, true);

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);

		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}

	static HSSFCell createIntCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
		if (border)
			setBorder(style, true, true, true, true);

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(style);
		return cell;
	}

	static HSSFCell createStringCell(HSSFWorkbook wb, HSSFRow row, short index,
			boolean bold, boolean border) {
		HSSFCell cell = row.createCell(index);
		HSSFCellStyle style = wb.createCellStyle();
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		if (border)
			setBorder(style, true, true, true, true);
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);
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
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(normalFontSize);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);
		cell.setCellStyle(style);
		return cell;
	}

	static void setBorder(HSSFCellStyle style, boolean top, boolean left,
			boolean bottom, boolean right) {
		if (top)
			style.setBorderBottom(borderStyle);
		if (left)
			style.setBorderLeft(borderStyle);
		if (right)
			style.setBorderRight(borderStyle);
		if (top)
			style.setBorderTop(borderStyle);
		// style.setTopBorderColor(HSSFColor.BLACK.index);
	}

	public static void setNormalFontSize(short i) {
		normalFontSize = i;
	}

	public static void setHeaderFontSize(short i) {
		headerFontSize = i;
	}

	public static void setBorderStyle(short i) {
		borderStyle = i;
	}
	
	public static void underLine(HSSFCell cell) {
		HSSFCellStyle style = cell.getCellStyle();
		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);

	}
}

package bus;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.DBManager;
import main.Main;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.FtpUtility;
import util.LoggerUtility;
import util.StorePropertyHandler;

public class RobinsonsComplianceService {

	private DBManager databaseManager;
	// to be updated by actual tenant's id
	private String lastFourTenantsId;
	private String tenantsId;
	private String storeNumber;
	private String terminalNumber;
	private static final int LINE_LENGTH = 16;
	private static final String COMPLIANCE_DIRECTORY = "compliance\\";
	private static Logger logger = Logger.getLogger(RobinsonsComplianceService.class);

	// unnecessarily using IODH as much as possible to familiarize myself
	// lazy load a singleton!!
	static class BabylandFileWriterHolder {
		static RobinsonsComplianceService babylandWriter = new RobinsonsComplianceService();
	}

	public static RobinsonsComplianceService getInstance() {
		return BabylandFileWriterHolder.babylandWriter;
	}

	public RobinsonsComplianceService() {
		initializeDatabaseConnection();
		initializePosDetails();

	}

	private void initializePosDetails() {
		// get store number from file
		storeNumber = Main.getStoreCode();

		// only get last two digits, left padded by 0
		// storeNumber = storeNumber.substring(storeNumber.length() - 2);
		tenantsId = StorePropertyHandler.getTenantNo();
		
		// only get the last four of the tenants id
		lastFourTenantsId = tenantsId.substring(tenantsId.length() - 4);
		terminalNumber = StringUtils.leftPad(StorePropertyHandler.getTerminalNo(),2,"0");
		

	}

	private void initializeDatabaseConnection() {
		// TODO Auto-generated method stub
		databaseManager = Main.getDBManager();
	}

	public void generateAndSendComplianceReport(Date date)
			throws FileNotFoundException {
		String fileName = generateLocalFile(date);
		sendFileThroughFtp(fileName);
		insertComplianceLogRecord(fileName, date);
	}

	private void insertComplianceLogRecord(String fileName, Date date) {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		cal.setTimeInMillis(date.getTime());

		String dateString = sdf.format(cal.getTime());

		String query = "insert into robinsons_compliance (filename, report_date, eod_counter) values ('"
				+ fileName
				+ "',str_to_date('"
				+ dateString
				+ "','%Y-%m-%d'),"
				+ (getMaxEodCounter() + 1) + ");";

		logger.info("insertComplianceReportLogRecordQuery = " + query);
		databaseManager.executeUpdate(query);
	}

	private String sendFileThroughFtp(String fileName) {
		// properties in the startup directory
		java.util.Properties props = new java.util.Properties();
		java.io.FileInputStream fis;
		try {
			fis = new java.io.FileInputStream(new java.io.File(
					"ftp.properties"));
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}

		logger.info(props);

		String hostAddress = props.getProperty("hostAddress");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		String remoteDirectory = props.getProperty("remoteDirectory");
		String workingDirectory = COMPLIANCE_DIRECTORY; // where the file(s) are
														// placed

		FtpUtility ftp = new FtpUtility(hostAddress, username, password,
				workingDirectory, remoteDirectory);

		if (ftp.connect()) {

			ftp.sendFile(fileName);
			ftp.disconnect();

		}
		return null;
	}

	private String generateLocalFile(Date date) throws FileNotFoundException {

		int year = getComponent(date, Calendar.YEAR);
		int month = getComponent(date, Calendar.MONTH) + 1; // month is zero
															// based!!
		int day = getComponent(date, Calendar.DAY_OF_MONTH);
		int storeCode = Integer.parseInt(this.storeNumber);

		String fileName = generateFileName(month, day, year);
		PrintStream out = new PrintStream(new FileOutputStream(
				COMPLIANCE_DIRECTORY + fileName));

		int lineNumber = 1; // starting line number

		// LINE#1 TENANT NUMBER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(tenantsId, LINE_LENGTH, "0"));

		// LINE#2 TERMINAL NUMBER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(terminalNumber, LINE_LENGTH, "0"));

		// LINE#3 Gross Sales QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getRawGross(month, day, year,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#4 Total TAX/VAT

		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getVat(month, day, year,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#5 Total Amount Void / Error Correct
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));

		// LINE#6 No. of Void / Error Correct Transactions
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad("", LINE_LENGTH, "0"));

		// LINE#7 Total Amount Discount
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getTotalDiscount(month, day, year, 
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#8 No. of Discounted Transactions QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(ComplianceService.getComplianceService()
						.getNoOfDisc(month, day, year, storeCode)
						+ "", LINE_LENGTH, "0"));

		// LINE#09 Total Amount Refund/Return
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(ComplianceService.getComplianceService().getReturnedItemsAmount(month, day, year, storeCode) + "", LINE_LENGTH, "0"));

		// LINE#10 No of Total Amount Refund/Return
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(ComplianceService.getComplianceService().getReturnedItemsQuantity(month, day, year, storeCode) + "", LINE_LENGTH, "0"));

		// LINE#11 Other Negative Adjustments
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));

		// LINE#12 No. of Recorded Negative Adjustments ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad("", LINE_LENGTH, "0"));

		// LINE#13 Total Service Charge ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));

		// LINE#14 PREVIOUS EOD COUNTER
		Integer maxEodCounter = getMaxEodCounter();
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(maxEodCounter + "", LINE_LENGTH, "0"));

		// LINE#15 Previous Accumulated Grand Total QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getOldGT(month, day, year,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#16 CURRENT EOD COUNTER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad((maxEodCounter + 1) + "", LINE_LENGTH,
						"0"));

		// LINE#17 CUrrent Accumulated Grand Total QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getNewGT(month, day, year,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#18 Sales Transaction Date
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		cal.setTimeInMillis(date.getTime());

		String dateString = sdf.format(cal.getTime());
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + StringUtils.leftPad(dateString,
						LINE_LENGTH, "0"));

		// LINE#19 Novelty (Promational items) ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));
		// LINE#20 Misc. (Sales Scrap and others) ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));

		// LINE#21 Local Tax Government Tax
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getVatRate() - 1)
						+ "", LINE_LENGTH, "0"));

		// LINE#22 Total Credit Sales
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getCreditSales(month, day,
								year, storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#23 Total Credit Sales VAT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getCreditSalesVat(month, day,
								year, storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#24 Total Non-Vat Sales
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));

		out.close();

		// TODO send file over FTP

		return fileName;
	}

	private String generateFileName(int month, int day, int year) {
		// Filename format
		// NNNNMMDD.TTB
		// NNNN = Last four digit of Tenant’s ID
		// MM = Sales Transaction Month
		// DD = Sales Transaction Date
		// TT = POS Terminal Number
		// B = Batch Number

		String properFileName = lastFourTenantsId
				+ StringUtils.leftPad(month + "", 2, "0")
				+ StringUtils.leftPad(day + "", 2, "0");
		String extensionFileName = terminalNumber
				+ (getSendCount(month, day, year) + 1);

		return properFileName + "." + extensionFileName;
		// String extension =
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis((new Date()).getTime());

		// calendar.add(Calendar.DAY_OF_MONTH, );

		// String fileName =
		// BabylandFileWriter.getInstance().generateFileName(calendar
		// .getTime());
		// logger.info(fileName);

		try {
//			RobinsonsCompliance.getInstance()
//					.getUnsentComplianceReports(30);
			RobinsonsComplianceService.getInstance().generateAndSendComplianceReport(new Date());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}

	}

	private int getComponent(Date date, int component) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());

		// convert to string
		return calendar.get(component);

	}

	private Integer getSendCount(int month, int day, int year) {
		// TODO Auto-generated method stub
		String query = "select count(1)" + "  from robinsons_compliance"
				+ " where " + "MONTH (report_date) = '" + month
				+ "' && YEAR(report_date) = '" + year
				+ "' && DAY(report_date) = '" + day + "'";
		logger.info("getSendCount query=" + query);
		ResultSet rs = databaseManager.executeQuery(query);

		try {
			if (rs.next()) {
				return rs.getInt(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}

		return -1;
	}

	private Integer getMaxEodCounter() {
		// TODO Auto-generated method stub

		String query = "select max(EOD_COUNTER) from robinsons_compliance";
		logger.info("getMaxEodCounter query=" + query);
		ResultSet rs = databaseManager.executeQuery(query);

		try {
			if (rs.next()) {
				return rs.getInt(1); // increment by 1 to get next send
											// count value
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}

		return -1;
	}
	
	public List<Date> getUnsentComplianceReports(int pastDays) {
		List<Date> unsentList = new ArrayList<Date>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.DAY_OF_MONTH, -1 * pastDays - 1);
		for(int i = -1 * pastDays; i <= 0; i ++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			
			Date date = cal.getTime();
			
			int year = getComponent(date, Calendar.YEAR);
			int month = getComponent(date, Calendar.MONTH) + 1; // month is zero
																// based!!
			int day = getComponent(date, Calendar.DAY_OF_MONTH);
			
			logger.info("ALEX: i="+ i + " DATE = " + year +month+day);
			if ( getSendCount(month, day, year) < 1) {
				logger.info("ALEX: i="+ i + " ADDDATE = " + date);
				unsentList.add(date);
			}
		}
		
		return unsentList;
	}
}

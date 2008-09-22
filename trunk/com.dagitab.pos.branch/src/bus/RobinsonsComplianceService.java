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

import util.DateUtility;
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

	public void generateAndSendComplianceReport(Date transDate, Date eodDate)
			throws IOException {
		
		String fileName = generateLocalFile(transDate, eodDate);
		sendFileThroughFtp(fileName);
		insertComplianceLogRecord(fileName, transDate);
		insertEodLogRecord(fileName, eodDate, eodDate);
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

	private void insertEodLogRecord(String fileName, Date transDate, Date eodDate) {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		cal.setTimeInMillis(transDate.getTime());

		String transDateString = sdf.format(cal.getTime());
		
		sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
		
		cal.setTimeInMillis(eodDate.getTime());
		
		String eodDateString = sdf.format(cal.getTime());

		String query = "insert into eod_log (trans_date, eod_time, ) values ("
				+ "str_to_date('"
				+ transDateString
				+ "','%Y-%m-%d'),"
				+ "str_to_date('"
				+ eodDateString
				+ "','%Y-%m-%d %H:%i:%S'),'Y')";
		
		logger.info("insertComplianceReportLogRecordQuery = " + query);
		databaseManager.executeUpdate(query);
	}

	private String sendFileThroughFtp(String fileName) throws IOException {
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

	public List<String> getFTPFiles(){
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
		
		List<String> files = null;
		if (ftp.connect()) {
			files = ftp.getRemoteFileNames();
			ftp.disconnect();
		}
		return files;
	}
	
	private String generateLocalFile(Date utilTransDate, Date utilEodDate) throws FileNotFoundException {

		java.sql.Date transDate = DateUtility.getDateUtility().convertUtilDateToSqlDate(utilTransDate);
		java.sql.Date eodDate = DateUtility.getDateUtility().convertUtilDateToSqlDate(utilEodDate);
		
		int year = getComponent(transDate, Calendar.YEAR);
		int month = getComponent(transDate, Calendar.MONTH) + 1; // month is zero
															// based!!
		int day = getComponent(transDate, Calendar.DAY_OF_MONTH);
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
						.getComplianceService().getRawGross(transDate, eodDate,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#4 Total TAX/VAT

		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getVat(transDate, eodDate,
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
						.getComplianceService().getTotalDiscount(transDate, eodDate,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#8 No. of Discounted Transactions QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(ComplianceService.getComplianceService()
						.getNoOfDisc(transDate, eodDate, storeCode)
						+ "", LINE_LENGTH, "0"));

		// LINE#09 Total Amount Refund/Return
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(ComplianceService.getComplianceService().getReturnedItemsAmount(transDate, eodDate, storeCode) + "", LINE_LENGTH, "0"));

		// LINE#10 No of Total Amount Refund/Return
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(ComplianceService.getComplianceService().getReturnedItemsQuantity(transDate, eodDate, storeCode) + "", LINE_LENGTH, "0"));

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
						.getComplianceService().getOldGT(transDate, eodDate,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#16 CURRENT EOD COUNTER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad((maxEodCounter + 1) + "", LINE_LENGTH,
						"0"));

		// LINE#17 CUrrent Accumulated Grand Total QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getNewGT(transDate, eodDate,
								storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#18 Sales Transaction Date
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		cal.setTimeInMillis(transDate.getTime());

		String dateString = sdf.format(cal.getTime());
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + StringUtils.leftPad(dateString,
						LINE_LENGTH, "0"));

		// LINE#19 Novelty (Promational items) ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));
		// LINE#20 Misc. (Sales Scrap and others) ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));

		// LINE#21 Local Tax Government Tax ZERO
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(".00", LINE_LENGTH, "0"));
//		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
//				+ StringUtils.leftPad(String.format("%.2f",VatService.getVatAmount())
//						+ "", LINE_LENGTH, "0"));

		// LINE#22 Total Credit Sales
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getCreditSales(transDate, eodDate, storeCode))
						+ "", LINE_LENGTH, "0"));

		// LINE#23 Total Credit Sales VAT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")
				+ StringUtils.leftPad(String.format("%.2f", ComplianceService
						.getComplianceService().getCreditSalesVat(transDate, eodDate, storeCode))
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

//		try {
////			RobinsonsCompliance.getInstance()
////					.getUnsentComplianceReports(30);
//			RobinsonsComplianceService.getInstance().generateLocalFile(new Date());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			LoggerUtility.getInstance().logStackTrace(e);
//		}

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
	
	public Integer getEodSentFlag(int month, int day, int year) {
		// TODO Auto-generated method stub
		String query = "select count(1)" + "  from eod_log"
				+ " where IS_SENT = 'Y' AND " + "MONTH (trans_date) = '" + month
				+ "' && YEAR(trans_date) = '" + year
				+ "' && DAY(trans_date) = '" + day + "'";
		logger.info("getEodSentFlag query=" + query);
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
	
	private java.sql.Date getEodLogDate(int month, int day, int year) {
		// TODO Auto-generated method stub
		String query = "select EOD_TIME" + "  from eod_log"
				+ " where IS_SENT = 'Y' AND " + "MONTH (trans_date) = '" + month
				+ "' && YEAR(trans_date) = '" + year
				+ "' && DAY(trans_date) = '" + day + "'";
		logger.info("getEodSentFlag query=" + query);
		ResultSet rs = databaseManager.executeQuery(query);

		try {
			if (rs.next()) {
				return rs.getDate(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}

		return null;
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
			if ( getEodSentFlag(month, day, year) < 1) {
				logger.info("ALEX: i="+ i + " ADDDATE = " + date);
				unsentList.add(date);
			}
		}
		
		return unsentList;
	}
	
	public Date getTransDateBasedOnEodDate(Date eodDate) {
		
		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(eodDate.getTime());

		if(cal.get(Calendar.HOUR_OF_DAY) < 4) {
			cal.add(Calendar.DAY_OF_MONTH, -1 );
			
		}
		cal.set(Calendar.HOUR_OF_DAY, 8); // set to 9am
		return cal.getTime();
		
	}
	
	public Date getEodDateBasedOnTransDate(Date transDate) {
		
		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(transDate.getTime());

		int year = getComponent(transDate, Calendar.YEAR);
		int month = getComponent(transDate, Calendar.MONTH) + 1; // month is zero
															// based!!
		int day = getComponent(transDate, Calendar.DAY_OF_MONTH);
		
		if (getEodSentFlag(month, day, year) > 0) {
			cal.setTimeInMillis((getEodLogDate(month, day, year).getTime()));
		} else {
			cal.add(Calendar.DAY_OF_MONTH, 1 );
			cal.set(Calendar.HOUR_OF_DAY, 3);
		}
		
		return cal.getTime();
		
	}
	
}

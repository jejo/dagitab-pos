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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import main.DBManager;
import main.Main;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.FtpUtility;
import util.LoggerUtility;
import util.StorePropertyHandler;

public class EastwoodComplianceService extends ComplianceService {

	private DBManager databaseManager;
	// to be updated by actual tenant's id
	private String lastFourTenantsId;
	private String firstFourTenantsId;
	private String tenantsId;
	private String storeNumber;
	private String terminalNumber;
	private static final int LINE_LENGTH = 0;
	private static Logger logger = Logger.getLogger(RobinsonsComplianceService.class);
	private static Properties props;

	// unnecessarily using IODH as much as possible to familiarize myself
	// lazy load a singleton!!
	static class BabylandFileWriterHolder {
		static EastwoodComplianceService babylandWriter = new EastwoodComplianceService();
	}

	public static EastwoodComplianceService getInstance() {
		return BabylandFileWriterHolder.babylandWriter;
	}

	public EastwoodComplianceService() {
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
		firstFourTenantsId = tenantsId.substring(0,4);
		terminalNumber = StringUtils.leftPad(StorePropertyHandler.getTerminalNo(),2,"0");
		
		
		props = new java.util.Properties();
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

	}

	private void initializeDatabaseConnection() {
		// TODO Auto-generated method stub
		databaseManager = Main.getDBManager();
	}

	// mode
	// 1 - daily sales
	// 2 - hourly sales
	// 3 - discount sales
	private void generateAndSendComplianceReport(Date date, ComplianceMode mode)
			throws FileNotFoundException {
		
		String fileName = null;
		switch(mode) {
			case DAILY:
				fileName = generateDailyLocalFile(date);
				break;
			case HOURLY:
				fileName = generateHourlyLocalFile(date);
				break;
			case DISCOUNT:
				fileName = generateDiscountLocalFile(date);
				break;
		}
		
		sendFileThroughFtp(fileName);
		insertComplianceLogRecord(fileName, date, mode);
	}
	
	public void generateEod(Date date) throws IOException {
		generateAndSendComplianceReport(date, ComplianceMode.DAILY);
		generateAndSendComplianceReport(date, ComplianceMode.HOURLY);
		generateAndSendComplianceReport(date, ComplianceMode.DISCOUNT);
	}

	public String generateDiscountLocalFile(Date date) throws FileNotFoundException {
		int year = getComponent(date, Calendar.YEAR);
		int month = getComponent(date, Calendar.MONTH) + 1; // month is zero
															// based!!
		int day = getComponent(date, Calendar.DAY_OF_MONTH);
//		int storeCode = Integer.parseInt(this.storeNumber);
		int storeCode = 1;

		String fileName = generateFileName(month, day, year, ComplianceMode.DISCOUNT);
		PrintStream out = new PrintStream(new FileOutputStream(
				props.getProperty("complianceDirectory") + fileName));

		String output = ComplianceService.getComplianceService().getDiscountTypesSales(month, day, year, storeCode);
		
		String[] rows = output.split(":");
		
		for (String values : rows) {
			String[] column = values.split(",");
			
			out.print(StringUtils.leftPad(column[0],6,"0"));
			out.print(", " + column[1]); 
			out.println(", " + String.format("%.2f", Double.parseDouble(column [2]))); // next line
			
		}
		
		
		out.close();
		
		return fileName;
	}

	public String generateHourlyLocalFile(Date date) throws FileNotFoundException {
		int year = getComponent(date, Calendar.YEAR);
		int month = getComponent(date, Calendar.MONTH) + 1; // month is zero
															// based!!
		int day = getComponent(date, Calendar.DAY_OF_MONTH);
//		int storeCode = Integer.parseInt(this.storeNumber);
		int storeCode = 1;

		String fileName = generateFileName(month, day, year, ComplianceMode.HOURLY);
		PrintStream out = new PrintStream(new FileOutputStream(
				props.getProperty("complianceDirectory") + fileName));
		
		// TODO
		// should refactor out.println into another function with linenumbers, formatting etc
		
		int lineNumber = 1; // starting line number

		// LINE#1 TENANT CODE
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + tenantsId);
		
		// LINE#2 POS TERMINAL NUMBER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + terminalNumber);
		
		// LINE#3 Sales Transaction Date
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");

		cal.setTimeInMillis(date.getTime());

		String dateString = sdf.format(cal.getTime());
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + dateString);
		
		for(int hour = 1; hour <= 24; hour++) {
			// LINE#4 HOUR
			System.out.println("HOUR = " + hour);
			Integer totalNoOfTransactions = ComplianceService.getComplianceService().getNoOfTransactions(month, day, year, storeCode, hour%24);
			
			if ( totalNoOfTransactions == 0 )
				continue;
			
			out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + "" + hour);
			// LINE#5 TOTAL NET SALES FOR GIVEN HOUR
			out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + removeDecimalPoint(ComplianceService.getComplianceService().getNetSales(month, day, year, storeCode, hour%24), 2));

//			Integer totalNoOfTransactions = ComplianceService.getComplianceService().getNoOfTransactions(month, day, year, storeCode, hour%24);
			// LINE#6 TOTAL NO OF SALES TRANSACTION / HOUR
			out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + ""  + totalNoOfTransactions);
			// LINE#7 TOTAL NO OF CUSTOMER COUNT / HOUR
			out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + ""  + totalNoOfTransactions);
		}
		
		// LINE # 8 Total NET SALES amount for the day
		Double netSalesAmount = ComplianceService.getComplianceService().getNetSales(month, day, year, storeCode);
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(netSalesAmount, 2));
		
		// LINE# 9 Total no of SALES / TRANSACTION
		Integer totalNoOfTransactions = ComplianceService.getComplianceService().getNoOfTransactions(month, day, year, storeCode);
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + ""  + totalNoOfTransactions);
		
		// LINE# 10 Total no of SALES / TRANSACTION
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0") + ""  + totalNoOfTransactions);

		return fileName;
	}
	
	private void insertComplianceLogRecord(String fileName, Date date, ComplianceMode mode) {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		cal.setTimeInMillis(date.getTime());

		String dateString = sdf.format(cal.getTime());

		String query = "insert into eastwood_compliance (filename, report_date, eod_counter, mode) values ('"
				+ fileName
				+ "',str_to_date('"
				+ dateString
				+ "','%Y-%m-%d'),"
				+ (getMaxEodCounter() + 1) + ",'" + mode.getComplianceSuffix() +"');";

		logger.info("insertComplianceReportLogRecordQuery = " + query);
		databaseManager.executeUpdate(query);
	}

	private String sendFileThroughFtp(String fileName) {
		// properties in the startup directory
		
		String hostAddress = props.getProperty("hostAddress");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		String remoteDirectory = props.getProperty("remoteDirectory");
		String workingDirectory = props.getProperty("complianceDirectory"); // where the file(s) are
														// placed

		FtpUtility ftp = new FtpUtility(hostAddress, username, password,
				workingDirectory, remoteDirectory);

		if (ftp.connect()) {

//			ftp.sendFile(fileName);
			ftp.disconnect();

		}
		return null;
	}

	private String generateDailyLocalFile(Date date) throws FileNotFoundException {

		int year = getComponent(date, Calendar.YEAR);
		int month = getComponent(date, Calendar.MONTH) + 1; // month is zero
															// based!!
		int day = getComponent(date, Calendar.DAY_OF_MONTH);
		int storeCode = Integer.parseInt(this.storeNumber);

		String fileName = generateFileName(month, day, year, ComplianceMode.DAILY);
		PrintStream out = new PrintStream(new FileOutputStream(
				props.getProperty("complianceDirectory") + fileName));

		// TODO
		// should refactor out.println into another function with linenumbers, formatting etc
		
		int lineNumber = 1; // starting line number

		// LINE#1 TENANT CODE
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ tenantsId);
		
		// LINE#2 POS TERMINAL NUMBER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ terminalNumber);
		
		// LINE#3 Sales Transaction Date
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");

		cal.setTimeInMillis(date.getTime());

		String dateString = sdf.format(cal.getTime());
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ dateString);
		
		// LINE#4 Previous Accumulated Grand Total QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(ComplianceService
						.getComplianceService().getOldGT(month, day, year,
								storeCode), 2));

		// LINE#5 Current Accumulated Grand Total QUERY
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(ComplianceService
						.getComplianceService().getNewGT(month, day, year,
								storeCode), 2));
				

		// LINE#6 Gross Sales QUERY
		Double grossSalesAmount = ComplianceService.getComplianceService().getRawGross(month, day, year, storeCode);
		Double netSalesAmount = ComplianceService.getComplianceService().getNetSales(month, day, year, storeCode);
		Double otherDiscountAmount = grossSalesAmount - netSalesAmount;
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(grossSalesAmount, 2));
		
		
		// LINE#7 TOTAL NON-TAXABLE AMOUNT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ "000");
		
		// LINE#8 SENIOR CITIZEN DISCOUNT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ "000");
		
		// LINE#9 TOTAL OTHER DISCOUNT
		 
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(otherDiscountAmount, 2));
		
		// LINE#10 TOTAL REFUND AMOUNT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ "000");
		
		// LINE#11 TOTAL TAX/VAT AMOUNT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(ComplianceService
				.getComplianceService().getVat(month, day, year,
						storeCode), 2));

		// LINE#12 TOTAL SERVICE CHARGE AMOUNT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ "000");
		
		// LINE#13 TOTAL NET SALES
		System.out.println("NET SALES AMOUNT ="  + netSalesAmount);
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(netSalesAmount, 2));
		
		// LINE#14 TOTAL CASH SALES
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(ComplianceService.getComplianceService().getSalesForPaymentTypeWithoutDiscount(month, day, year, storeCode, 1), 2));
		
		// LINE#15 TOTAL CHARGE/CREDIT SALES
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(ComplianceService.getComplianceService().getSalesForPaymentTypeWithoutDiscount(month, day, year, storeCode, 3), 2));
		
		// LINE#16 TOTAL GC SALES
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(ComplianceService.getComplianceService().getSalesForPaymentTypeWithoutDiscount(month, day, year, storeCode, 4), 2));

		// LINE#17 TOTAL VOID AMOUNT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ "000");
		
		// LINE#18 TOTAL CUSTOMER COUNT
		Integer totalNoOfTransactions =  ComplianceService.getComplianceService().getNoOfTransactions(month, day, year, storeCode);
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ ""  + totalNoOfTransactions);
		
		// LINE#19 CONTROL NUMBER
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ ""  + (getMaxEodCounter() + 1) );

		// LINE#20 TOTAL NO OF SALES TRANSACTION
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ ""  + totalNoOfTransactions);

		
		// LINE#21 SALES TYPE 02 - NON FOOD
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ "02" );
		
		// LINE#22 SALES TYPE 02 - NON FOOD - NET SALES INCLUSIVE OF VAT
		out.println(StringUtils.leftPad(lineNumber++ + "", 2, "0")+ removeDecimalPoint(netSalesAmount, 2));
		
		out.close();

		// TODO send file over FTP

		return fileName;
	}
	
	private String generateFileName(int month, int day, int year, ComplianceMode mode) {
		// Filename format
//		SNNNNTTB.X99 where
//		S	-	To Identify this is a SALES file -- getComplianceSuffix
//		NNNN	-	First four-digit of Tenants ID
//		TT	-	POS Terminal Number
//		B	-	Batch Number (No. of EOD)
//		X	-	Month (1-9 / A-C)
//		99	-	Day
		
		String properFileName = mode.getComplianceSuffix() + firstFourTenantsId + terminalNumber + (getSendCount(month,day,year, mode) + 1);
		String extensionFileName = Integer.toHexString(month).toUpperCase() + StringUtils.leftPad(day + "", 2, "0");
		
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
			EastwoodComplianceService.getInstance().generateDailyLocalFile(new Date());
			EastwoodComplianceService.getInstance().generateDiscountLocalFile(new Date());
			EastwoodComplianceService.getInstance().generateHourlyLocalFile(new Date());
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

	public Integer getSendCount(int month, int day, int year, ComplianceMode mode) {
		// TODO Auto-generated method stub
		String query = "select count(1)" + "  from eastwood_compliance"
				+ " where " + "MONTH (report_date) = '" + month
				+ "' && YEAR(report_date) = '" + year
				+ "' && DAY(report_date) = '" + day + "'"
				+ " && mode = '" + mode.getComplianceSuffix() + "'";
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

		String query = "select max(EOD_COUNTER) from eastwood_compliance";
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
	
	public List<Date> getUnsentComplianceReports(int pastDays, ComplianceMode mode) {
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
			if ( getSendCount(month, day, year, mode) < 1) {
				logger.info("ALEX: i="+ i + " ADDDATE = " + date);
				unsentList.add(date);
			}
		}
		
		return unsentList;
	}
	
	public Map<Date, String> getSentComplianceReports(int pastDays, ComplianceMode mode) {
		Map<Date, String> sentList = new HashMap<Date, String>();
		
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
				if ( getSendCount(month, day, year, mode) < 1) {
					logger.info("ALEX: i="+ i + " ADDDATE = " + date);
					sentList.put(date, generateFileName(month, day, year, mode));
				}	

		}
		
		return sentList;
	}
	
	public String removeDecimalPoint(Double number, int noOfDecimalPlaces) {
		return String.format("%." + noOfDecimalPlaces +"f", number).replace(".", "");
	}
	
	
}

package reports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import main.DBManager;

import org.apache.log4j.Logger;

import util.LoggerUtility;

public class FestivalHourlyReport {

	/**
	 * @param args
	 */
	
	DBManager db;
	String storeCode;
	String tenantCode;
	String termNum;
	String dirName;
	String fileName; 
	String batchDownloadNum;
	private static Logger logger = Logger.getLogger(FestivalHourlyReport.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public FestivalHourlyReport(DBManager db, String storeCode, String dirName, Date date){
		
		this.db = db;
		this.storeCode = storeCode;
		this.dirName = dirName;
		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("data/.tenantno"));
			tenantCode = br.readLine().trim();
			
			BufferedReader br2 = new BufferedReader(new FileReader("data/.termno"));
			termNum = br2.readLine().trim();
			
			BufferedReader br3 = new BufferedReader(new FileReader("data/.batchno"));
			batchDownloadNum = br3.readLine().trim();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		
		
		/*FILENAME GENERATION*/
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		String month = (cal.get(Calendar.MONTH) + 1)+"";
		String day = cal.get(Calendar.DAY_OF_MONTH)+"";
		String year = cal.get(Calendar.YEAR)+"";
		
		if(Integer.parseInt(month)== 10){
			month="A";
		}
		else if(Integer.parseInt(month)== 11){
			month="B";
		}
		
		else if(Integer.parseInt(month)== 12){
			month="C";
		}
		
		fileName = dirName+"\\"+"H"+tenantCode.substring(0,4)+termNum+batchDownloadNum+"."+month+day;
		logger.info("File Name: "+fileName);
	}
	
	public void createFile(){
		File f = new File(fileName);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
	}
	
	public void insertData(Date date){
			
			try {
				BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
				
				Calendar cal = new GregorianCalendar();
				cal.setTime(date);
				String year = cal.get(Calendar.YEAR)+"";
				String month = (cal.get(Calendar.MONTH)+1)+"";
				String day = cal.get(Calendar.DAY_OF_MONTH)+"";
				
				if(month.length() ==1){
					month = "0"+month;
				}
				
				if(day.length() == 1){
					day = "0"+day;
				}
				
				
				String startHour = "";
				
				logger.info("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT ASC LIMIT 1");
				ResultSet rs = db.executeQuery("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT ASC LIMIT 1");
				
				try {
					if(rs.next()){
//						logger.info(rs.getString(1));
						startHour = rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LoggerUtility.getInstance().logStackTrace(e);
				}
				
				
				String endHour = "";
				
				logger.info("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT DESC LIMIT 1");
				rs = db.executeQuery("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT DESC LIMIT 1");
				
				try {
					if(rs.next()){
//						logger.info(rs.getString(1));
						endHour = rs.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LoggerUtility.getInstance().logStackTrace(e);
				}
				
				
				
				br.write("01"+tenantCode); //tenant
				br.newLine();
				
				br.write("02"+termNum); //termnum
				br.newLine();
				
				br.write("03"+month+day+year);//date
				br.newLine();
				
				
				rs = db.executeQuery("SELECT VAT FROM global");
				
				double subtractor = 0;
				try {
					if(rs.next()){
						String temp = "1."+rs.getString(1);
						subtractor = Double.parseDouble(temp);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LoggerUtility.getInstance().logStackTrace(e);
				}
				logger.info("start hour "+startHour);
				logger.info("end hour "+endHour);
				
				if(startHour.length()== 0){
					startHour = "0";
				}
				if(endHour.length()== 0){
					endHour = "0";
				}
				for(int i = Integer.parseInt(startHour); i<= Integer.parseInt(endHour); i++){
					
					String inputhour = (i+1)+"";
					if(inputhour.length() == 1){
						inputhour = "0"+ inputhour;
					}
					br.write("04"+inputhour); //Hour
					br.newLine();
					
					rs = db.executeQuery("SELECT HOUR(o.TRANS_DT),i.SELL_PRICE, i.QUANTITY " +
							"FROM invoice o, invoice_item i " +
							"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
								   "YEAR(o.TRANS_DT) = '"+year+"' && " +
								   	"DAY(o.TRANS_DT) = '"+day+"' AND " +
								   	"o.STORE_CODE = '"+storeCode+"' AND " +
								   	"o.OR_NO = i.OR_NO AND "+
								   	" HOUR(o.TRANS_DT) = "+i+
								   		" ");
					
					
					double sales = 0;
					try {
						while(rs.next()){
							sales += (rs.getDouble(2)*rs.getDouble(3));
							logger.info(rs.getDouble(2)+" * "+rs.getDouble(3));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						LoggerUtility.getInstance().logStackTrace(e);
					}
					
					double netofVatSales  = sales/subtractor;
					
					String amt = String.format("%.2f", netofVatSales);
					int posOfDot = amt.indexOf(".");
					String newAmt = amt.substring(0,posOfDot) + amt.substring(posOfDot+1);
					
					br.write("05"+newAmt);
					br.newLine();
					
					rs = db.executeQuery("SELECT COUNT(o.OR_NO) FROM invoice o " +
							"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
							   "YEAR(o.TRANS_DT) = '"+year+"' && " +
							   	"DAY(o.TRANS_DT) = '"+day+"' AND " +
							   	"o.STORE_CODE = '"+storeCode+"' AND " +
							   	" HOUR(o.TRANS_DT) = "+i+
							   		" ");
					try {
						if(rs.next()){
							br.write("06"+rs.getInt(1));
							br.newLine();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						LoggerUtility.getInstance().logStackTrace(e);
					}
					
				}
				
				rs = db.executeQuery("SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
				logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
				
				double dlysale = 0;
				
				try {
					while(rs.next()){
						double salesamt = rs.getDouble(1);
						dlysale = salesamt/subtractor;
						
						String temp = String.format("%.2f", dlysale);
						int dot = temp.indexOf(".");
						String finalAmt = temp.substring(0,dot) + temp.substring(dot+1);
						
						br.write("07"+finalAmt);
						br.newLine();
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LoggerUtility.getInstance().logStackTrace(e);
				}
				
				
				/*TOTAL NO OF SALES TRANS*/
				rs = db.executeQuery("SELECT COUNT(o.OR_NO) " +
						"FROM  invoice o " +
						"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
						"YEAR(o.TRANS_DT) = '"+year+"' && " +
						"DAY(o.TRANS_DT) = '"+day+"' AND " +
						"o.STORE_CODE = '"+storeCode+"'");
				
				try {
					if(rs.next()){
						
						/*TOTAL NO OF TRANSACTION*/
						br.write("08"+rs.getInt(1));
						br.newLine();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LoggerUtility.getInstance().logStackTrace(e);
				}
				
				
				br.flush();
				JOptionPane.showMessageDialog(null,"You have exported a compliance report","Success",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(IOException e){
				LoggerUtility.getInstance().logStackTrace(e);
			}
	}

}

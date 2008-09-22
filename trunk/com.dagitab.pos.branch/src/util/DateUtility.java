package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.DateTime;

public class DateUtility {
	private static DateUtility dateUtility = new DateUtility();
	private static Logger logger = Logger.getLogger(DateUtility.class);
	private DateUtility(){}
	public static DateUtility getDateUtility(){
		return dateUtility;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtility.getDateUtility().getCurrentDate());
	}
	
	public  String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	
	public String formatDate(DateTime dateTime){
		String day = StringUtility.zeroFill(dateTime.getDay()+"", 2);
		String month = StringUtility.zeroFill((dateTime.getMonth()+1)+"",2); //zero based
		String year = dateTime.getYear()+"";
		return year+"-"+month+"-"+day;
	}
	
	public String[] getAllMonths(){
		return new String[]{"Jan","Feb","Mar",
							"Apr","May","Jun",
							"Jul","Aug","Sept",
							"Oct","Nov","Dec"};
	}
	public String[] getDaysOfMonth(int month, int year){
		if(month == 3 || month == 5 || month == 8 || month == 10){
			String[] days = new String[30];
			for(int i = 0; i<30; i++){
				days[i]= (i+1)+"";
			}
			return days;
		}
		else if(month == 1){
			if(year%4 == 0){ //leap year
				String[] days = new String[29];
				for(int i = 0; i<29; i++){
					days[i]= (i+1)+"";
				}
				return days;
			}
			else{
				String[] days = new String[28];
				for(int i = 0; i<28; i++){
					days[i]= (i+1)+"";
				}
				return days;
			}
		}
		else{
			String[] days = new String[31];
			for(int i = 0; i<31; i++){
				days[i]= (i+1)+"";
			}
			return days;
		}
	}
	
	public String[] getYears(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		logger.info(year-2005);
		String[] years = new String[year-2005];
		int index = 0;
		for(int i = year; i>2005; i--){
			years[index++] = i+"";
		}
		return years;
	}
	
	public java.util.Date convertSqlDateToUtilDate(java.sql.Date date) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis(date.getTime());
		
		return cal.getTime();
	}
	
	public java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis(date.getTime());
		
		return new java.sql.Date(cal.getTime().getTime());
	}
}

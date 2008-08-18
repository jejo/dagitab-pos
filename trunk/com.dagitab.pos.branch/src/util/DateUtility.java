package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.swt.widgets.DateTime;

public class DateUtility {
	private static DateUtility dateUtility = new DateUtility();
	private DateUtility(){}
	public static DateUtility getDateUtility(){
		return dateUtility;
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
}

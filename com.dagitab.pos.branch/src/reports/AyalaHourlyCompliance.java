package reports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.svcon.jdbf.DBFWriter;
import com.svcon.jdbf.JDBFException;
import com.svcon.jdbf.JDBField;

import main.DBManager;

public class AyalaHourlyCompliance {
	DBManager db;
	String storeCode;
	String tenantCode;
	String dirName = "C:\\Ayala\\";
	String fileName; 
	
	public AyalaHourlyCompliance(DBManager db, String storeCode){
		this.db = db;
		this.storeCode = storeCode;
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/.tenantno"));
			tenantCode = br.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkDirectory(){
		ResultSet rs = db.executeQuery("SELECT YEAR(CURRENT_TIMESTAMP)");
		try {
			if(rs.next()){
				dirName += rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean created = new File(dirName).mkdir();
		System.out.println("Directory created: "+ created);
	}
	
	public void createDBFFile(Date date){
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		String month = (cal.get(Calendar.MONTH)+1)+"";
		String day = cal.get(Calendar.DAY_OF_MONTH)+"";
		String year = cal.get(Calendar.YEAR)+"";
		
		if(month.length() == 1){
			month = "0"+month;
		}
		if(day.length() == 1){
			day = "0"+day;
		}
		
		fileName = dirName+"\\"+tenantCode+month+day+"H.dbf";
		File f = new File(fileName);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insertData(Date date){
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
		
		System.out.println("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT ASC LIMIT 1");
		ResultSet rs = db.executeQuery("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT ASC LIMIT 1");
		
		try {
			if(rs.next()){
//				System.out.println(rs.getString(1));
				startHour = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String endHour = "";
		
		System.out.println("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT DESC LIMIT 1");
		rs = db.executeQuery("SELECT HOUR(o.TRANS_DT) FROM invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND o.STORE_CODE = '"+storeCode+"' ORDER by TRANS_DT DESC LIMIT 1");
		
		try {
			if(rs.next()){
//				System.out.println(rs.getString(1));
				endHour = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JDBField[] fields = new JDBField[6];
		try {
			fields[0] = new JDBField("TRANDATE",'D',8,0);
			fields[1] = new JDBField("HOUR",'C',5,0);
			fields[2] = new JDBField("SALES",'N',11,2);
			fields[3] = new JDBField("TRANCNT",'N',9,0);
			fields[4] = new JDBField("TENTNAME",'C',10,0);
			fields[5] = new JDBField("TEMNUM",'N',3,0);
						
			DBFWriter writer = new DBFWriter(fileName,fields);
			
			if(startHour.length()== 0){
				startHour = "0";
			}
			if(endHour.length()== 0){
				endHour = "0";
			}
			
			for(int i = Integer.parseInt(startHour); i<= Integer.parseInt(endHour); i++){
				
				Object[] dataRecord = new Object[6];
				dataRecord[0] = date;
				if((i+"").length() ==1){
					dataRecord[1] = "0"+i+":00";
				}
				else{
					dataRecord[1] = i+":00";
				}
				
			
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
						System.out.println(rs.getDouble(2)+" * "+rs.getDouble(3));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dataRecord[2] = sales;
				
				rs = db.executeQuery("SELECT COUNT(o.OR_NO) FROM invoice o " +
									"WHERE MONTH (o.TRANS_DT) = '"+month+"' && " +
									   "YEAR(o.TRANS_DT) = '"+year+"' && " +
									   	"DAY(o.TRANS_DT) = '"+day+"' AND " +
									   	"o.STORE_CODE = '"+storeCode+"' AND " +
									   	" HOUR(o.TRANS_DT) = "+i+
									   		" ");
				try {
					if(rs.next()){
						dataRecord[3] = rs.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dataRecord[4] = tenantCode;
				dataRecord[5] = 1;
				
				writer.addRecord(dataRecord);
			}
			JOptionPane.showMessageDialog(null,"You have exported a compliance report","Success",JOptionPane.INFORMATION_MESSAGE);
			
			writer.close();
			
			
		}catch(JDBFException e){
			JOptionPane.showMessageDialog(null,"You have not exported a compliance report","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	
		
	}
	
	public static void main(String[] args){
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		System.out.println(cal.get(Calendar.MINUTE));
	}
}

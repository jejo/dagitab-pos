package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Main;

import org.apache.log4j.Logger;

import util.LoggerUtility;
import domain.PaymentItem;

public class PaymentItemService {
	
	private static PaymentItemService paymentItemService = new PaymentItemService();
	private static Logger logger = Logger.getLogger(PaymentItemService.class);
	
	private PaymentItemService(){}
	
	public static PaymentItemService getInstance(){
		return paymentItemService;
	}
	
	public String getPaymentType(Integer id){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT NAME FROM pay_type_lu WHERE pt_code = '"+id+"'");
		try {
			if(rs.next())
			{
				return rs.getString("NAME");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	
	public PaymentItem getPaymentItemById(Integer id){
		PaymentItem paymentItem = new PaymentItem();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM payment_item WHERE or_no = '"+id+"'");
		logger.info("SELECT * FROM payment_item WHERE or_no = '"+id+"'");
		try {
			if(rs.next()){
				paymentItem.setOrNo(rs.getLong("OR_NO"));
				paymentItem.setPaymentCode(rs.getInt("PT_CODE"));
				paymentItem.setAmount(rs.getDouble("AMT"));
				paymentItem.setCardType(rs.getString("CARD_TYPE"));
				paymentItem.setCardNo(rs.getString("CARD_NO"));
				paymentItem.setGcNo(rs.getString("GC_NO"));
				paymentItem.setCheckNo(rs.getString("CHECK_NO"));
				paymentItem.setStoreNo(rs.getInt("STORE_CODE"));
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return paymentItem;
	}
	
	public int insert(PaymentItem paymentItem){
		String[] columns = new String[]{"OR_NO","PT_CODE","AMT","CARD_TYPE","CARD_NO","GC_NO","CHECK_NO","STORE_CODE"};
		String[] columnValues = new String[]{paymentItem.getOrNo().toString(),
											 paymentItem.getPaymentCode().toString(),
											 paymentItem.getAmount().toString(),
											 paymentItem.getCardType(),
											 paymentItem.getCardNo(),
											 paymentItem.getGcNo(),
											 paymentItem.getCheckNo(),
											 paymentItem.getStoreNo().toString()};
		
		Integer result = Main.getDBManager().insert(columns, columnValues, "payment_item", null, null);
		return result;
	}
	
	
	public Double getTotalPaymentAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(AMT) FROM payment_item WHERE OR_NO	= "+orNo+" AND STORE_CODE = "+Main.getStoreCode() );
		try {
			if(rs.next()){
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0.0d;
	}
	
	public ResultSet findPaymentItems(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT pi.PT_CODE, NAME, AMT, CARD_TYPE, CARD_NO, CHECK_NO, GC_NO FROM payment_item pi, pay_type_lu pt WHERE pi.PT_CODE = pt.PT_CODE AND OR_NO = "+orNo+" AND pi.STORE_CODE = "+Main.getStoreCode());
		logger.info("SELECT pi.PT_CODE, NAME, AMT, CARD_TYPE, CARD_NO, CHECK_NO, GC_NO FROM payment_item pi, pay_type_lu pt WHERE pi.PT_CODE = pt.PT_CODE AND OR_NO = "+orNo);
		return rs;
	}
	
	public void removePaymentItem(Long orNo){
		String [] whereColumns = new String[]{"OR_NO","STORE_CODE"};
		String [] whereValues = new String[]{orNo.toString(),Main.getStoreCode()};
		Main.getDBManager().delete("payment_item", whereColumns, whereValues);
	}
	
	public List<PaymentItem> getPaymentItemList(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT pi.OR_NO, pi.STORE_CODE, pi.PT_CODE, NAME, AMT, CARD_TYPE, CARD_NO, CHECK_NO, GC_NO FROM payment_item pi, pay_type_lu pt WHERE pi.PT_CODE = pt.PT_CODE AND OR_NO = "+orNo);
		List<PaymentItem> paymentItemList = new ArrayList<PaymentItem>();
		try {
			while(rs.next()){
				PaymentItem paymentItem = new PaymentItem();
				paymentItem.setPaymentType(rs.getString("NAME"));
				paymentItem.setAmount(rs.getDouble("AMT"));
				paymentItem.setCardNo(rs.getString("CARD_NO"));
				paymentItem.setCardType(rs.getString("CARD_TYPE"));
				paymentItem.setCheckNo(rs.getString("CHECK_NO"));
				paymentItem.setGcNo(rs.getString("GC_NO"));
				paymentItem.setOrNo(rs.getLong("OR_NO"));
				paymentItem.setPaymentCode(rs.getInt("PT_CODE"));
				paymentItem.setStoreNo(rs.getInt("STORE_CODE"));
				paymentItemList.add(paymentItem);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		return paymentItemList;
	}
	
	public Integer checkPaymentItemByType(Integer orNo, String paymentCode, String paymentType, String amount, String ccType, String ccNum, String bcNum, String gcNum ){
		if(paymentType.equals("Cash")){
			logger.info("SELECT Count(*) FROM payment_item WHERE OR_NO = "+orNo + " and PT_Code = "+paymentCode);
			ResultSet rs = Main.getDBManager().executeQuery("SELECT Count(*) FROM payment_item WHERE OR_NO = "+orNo + " and PT_Code = "+paymentCode);
			try {
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		if(paymentType.equals("Bank Check")){
			logger.info("SELECT Count(*) FROM payment_item WHERE OR_NO = "+orNo + ", PT_Code = "+paymentCode+" and Check_No="+bcNum);
			ResultSet rs = Main.getDBManager().executeQuery("SELECT Count(*) FROM payment_item WHERE OR_NO = "+orNo + " and PT_Code = "+paymentCode+" and Check_No="+bcNum);
			try {
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		if(paymentType.equals("Credit Card")){
			ResultSet rs = Main.getDBManager().executeQuery("SELECT Count(*) FROM payment_item WHERE OR_NO = "+orNo + " and PT_Code = "+paymentCode+" and Card_no="+ccNum);
			try {
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		if(paymentType.equals("Gift Check")){
			ResultSet rs = Main.getDBManager().executeQuery("SELECT Count(*) FROM payment_item WHERE OR_NO = "+orNo + " and PT_Code = "+paymentCode+" and GC_no="+gcNum);
			try {
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		else{
			return 0;
		}
		return null;
	}
}

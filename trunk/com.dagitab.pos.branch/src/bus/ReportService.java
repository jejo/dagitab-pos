package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.Main;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.LoggerUtility;
import domain.Invoice;
import domain.InvoiceItem;
import domain.ReturnItem;

public class ReportService {
	private static ReportService reportService = new ReportService();
	private static Logger logger = Logger.getLogger(ReportService.class);
	private static LoggerUtility loggerUtility = LoggerUtility.getInstance();
	
	private ReportService(){}
	
	public static ReportService getInstance(){
		return reportService;
	}
	
	public List<Invoice> getInvoice(String startDate, String endDate){
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		String query = "SELECT * FROM invoice i WHERE DATE(i.trans_dt)>=\'"+startDate + "\' && DATE(i.trans_dt) <= \'"+endDate +"\' && i.store_code="+Main.getStoreCode();
		ResultSet rs = Main.getDBManager().executeQuery(query);
		try {
			while(rs.next()){
				Invoice invoice = InvoiceService.toInvoiceObject(rs);
				
				String queryInvoiceItem = "SELECT * FROM invoice_item WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
				ResultSet rs2 = Main.getDBManager().executeQuery(queryInvoiceItem);
				while(rs2.next()){
					InvoiceItem invoiceItem = InvoiceItemService.getInstance().toInvoiceItemObject(rs2);
					invoice.getInvoiceItems().add(invoiceItem);
				}
				
				
				String queryReturn = "SELECT * FROM returned_items WHERE OR_NO = "+rs.getString("OR_NO")+" && STORE_CODE = "+Main.getStoreCode();
				ResultSet resultSet = Main.getDBManager().executeQuery(queryReturn);
				while(resultSet.next()){
					ReturnItem returnItem = ReturnItemService.toReturnItem(resultSet);
					invoice.getReturnedItems().add(returnItem);
				}
				
				invoiceList.add(invoice);
			}
		} catch (SQLException e) {
			loggerUtility.logStackTrace(e);
		}
		return invoiceList;
	}
	
	public Integer getMaxOrNo(String date){
		String query = "SELECT max(i.OR_NO) MAX_OR_NO FROM invoice i WHERE DATE(i.trans_dt)=\'"+date + "\' && i.store_code="+Main.getStoreCode();
		ResultSet rs = Main.getDBManager().executeQuery(query);
		try {
			while(rs.next()){
				return rs.getInt("MAX_OR_NO");
			}
		} catch (SQLException e) {
			loggerUtility.logStackTrace(e);
		}
		return null;
	}
	
	public Integer getMinOrNo(String date){
		String query = "SELECT min(i.OR_NO) MIN_OR_NO FROM invoice i WHERE DATE(i.trans_dt)=\'"+date + "\' && i.store_code="+Main.getStoreCode();
		logger.info(query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		try {
			while(rs.next()){
				return rs.getInt("MIN_OR_NO");
			}
		} catch (SQLException e) {
			loggerUtility.logStackTrace(e);
		}
		return null;
	}
	
	public Double getApprovedDiscounts(String date){
		String[] dateArr = date.split("-");
		Double totaldiscounts = ComplianceService.getComplianceService().getTotalDiscount(Integer.parseInt(dateArr[1]), Integer.parseInt( dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(Main.getStoreCode()));
		return totaldiscounts;
	}
	
	public Double getGrossSales(String date) {
		String[] dateArr = date.split("-");
		Double grossSales = ComplianceService.getComplianceService().getRawGross(Integer.parseInt(dateArr[1]), Integer.parseInt( dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(Main.getStoreCode()));
		return grossSales;
	}
	
	public Double getTotalVipDiscount(String date) {
		String[] dateArr = date.split("-");
		Double totalVipdiscounts = ComplianceService.getComplianceService().getTotalDiscount(Integer.parseInt(dateArr[1]), Integer.parseInt( dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(Main.getStoreCode()), 2); // discount type of VIP is 2
		return totalVipdiscounts;
	}
	
	public Double getNetSalesBeforeTax(String date) {
		String[] dateArr = date.split("-");
		Double netSales = ComplianceService.getComplianceService().getNetSales(Integer.parseInt(dateArr[1]), Integer.parseInt( dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(Main.getStoreCode())); // discount type of VIP is 2
		return netSales;
	}
	
	public Double getVat(String date) {
		String[] dateArr = date.split("-");
		Double netSales = ComplianceService.getComplianceService().getVat(Integer.parseInt(dateArr[1]), Integer.parseInt( dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(Main.getStoreCode())); // discount type of VIP is 2
		return netSales;
	}
	
	public Double getTotalNetSalesLessVat(Double totalNetSales){
		Double vatAmount = totalNetSales/VatService.getVatRate();
		return Double.valueOf(String.format("%.2f", vatAmount));
	}
	
	public Double getPartialTransactionTotal(String date) {
		
		String query = "select sum(p.AMT) TOTAL_PARTIAL from payment_item p " +
					   " WHERE DATE(i.trans_dt)>=\'"+date + "\' " +
					   	 " AND i.store_code="+Main.getStoreCode() + 
					   	 " AND exists (select 1 from invoice i where i.or_no = p.or_no and i.store_code = p.store_code and i.partial = 1)";
		ResultSet rs = Main.getDBManager().executeQuery(query);
		try {
			while(rs.next()){
				return rs.getDouble("TOTAL_PARTIAL");
			}
		} catch (SQLException e) {
			loggerUtility.logStackTrace(e);
		}
		return null;
	}
	
	public Double getTotalPerInvoice(Invoice invoice){
		Double totalPerInvoice = 0.0d;
		for(InvoiceItem invoiceItem: invoice.getInvoiceItems()){
			Double discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(invoice.getOrNo(),invoiceItem.getProductCode());
			discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
			Double subTotal = discountedAmount*invoiceItem.getQuantity();
			totalPerInvoice+=subTotal;
		}
		for(ReturnItem returnItem: invoice.getReturnedItems()){
			Double discountedAmount = returnItem.getSellPrice()*-1; //discounted price is negative when returned
			discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
			Double subTotal = discountedAmount*returnItem.getQuantity();
			totalPerInvoice+=subTotal;
		}
		return totalPerInvoice;
	}
	
	
	public ArrayList<String> getRobinsonsInvoiceDates(int month, int year){
		int firstDay = 21;
		int lastDay = 20;
		
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH,1);
//		System.out.println("1: "+prevCalendar.getTime());
//		calendar.add(Calendar.MONTH, 1);
//		System.out.println("2: "+prevCalendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
//		System.out.println("3: "+prevCalendar.getTime());
		
		int lastDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//		System.out.println("Last Day of Month: "+lastDayOfMonth);
		ArrayList<String> dates = new ArrayList<String>();
		for(int i = firstDay; i<=lastDayOfMonth; i++){
			String date = year+"-"+StringUtils.leftPad((calendar.get(Calendar.MONTH)+1)+"",2, '0')+"-"+StringUtils.leftPad(i+"",2,'0');
			dates.add(date);
		}
		calendar.add(Calendar.MONTH, 1);
		for(int i = 1; i<=lastDay; i++){
			String date = year+"-"+StringUtils.leftPad((calendar.get(Calendar.MONTH)+1)+"",2, '0')+"-"+StringUtils.leftPad(i+"",2,'0');
			dates.add(date);
		}
		
		return dates;
	}
	
	
	public static void main(String[] args){
		ArrayList<String> dates = ReportService.getInstance().getRobinsonsInvoiceDates(11, 2008);
		for(String s: dates){
			System.out.println(s);
		}
	}
	
}

package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Main;

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
	
	public Double getTotalPerInvoice(Invoice invoice){
		Double totalPerInvoice = 0.0d;
		for(InvoiceItem invoiceItem: invoice.getInvoiceItems()){
			Double discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(invoice.getOrNo(),invoiceItem.getProductCode());
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
	
}

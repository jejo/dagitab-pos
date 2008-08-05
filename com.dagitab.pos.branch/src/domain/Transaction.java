package domain;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
	
	private Date date;
	private Invoice invoice;
	private ArrayList<InvoiceItem> invoiceItems;
	private ArrayList<PaymentItem> paymentItems;
	
	public Transaction() {
		date = new Date();
		invoiceItems = new ArrayList<InvoiceItem>();
		paymentItems = new ArrayList<PaymentItem>();
	}
	
	public Date getDate() {
		return date;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public ArrayList<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}
	public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	public ArrayList<PaymentItem> getPaymentItems() {
		return paymentItems;
	}
	public void setPaymentItems(ArrayList<PaymentItem> paymentItems) {
		this.paymentItems = paymentItems;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

package com.dagitab.pos.domain;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	private Long invoiceNo =0L;
	private Long orNo;
	private Integer encoderCode = 0;
	private Integer assistantCode = 0;
	private Integer customerNo = 0;
	private Integer storeNo;
	private Integer isPartial = 0;
	private Integer isReturn = 0;
	private String transactionDate;
	private Double changeAmount = 0.0d;
	private List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
	private List<ReturnItem> returnedItems = new ArrayList<ReturnItem>();
	
	public List<ReturnItem> getReturnedItems() {
		return returnedItems;
	}
	public void setReturnedItems(List<ReturnItem> returnedItems) {
		this.returnedItems = returnedItems;
	}
	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}
	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	public Integer getAssistantCode() {
		return assistantCode;
	}
	public Double getChangeAmount() {
		return changeAmount;
	}
	public Integer getCustomerNo() {
		return customerNo;
	}
	public Integer getEncoderCode() {
		return encoderCode;
	}
	public Long getInvoiceNo() {
		return invoiceNo;
	}
	public Integer getIsPartial() {
		return isPartial;
	}
	public Integer getIsReturn() {
		return isReturn;
	}
	public Long getOrNo() {
		return orNo;
	}
	public Integer getStoreNo() {
		return storeNo;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setAssistantCode(Integer assistantCode) {
		this.assistantCode = assistantCode;
	}
	public void setChangeAmount(Double changeAmount) {
		this.changeAmount = changeAmount;
	}
	public void setCustomerNo(Integer customerNo) {
		this.customerNo = customerNo;
	}
	public void setEncoderCode(Integer encoderCode) {
		this.encoderCode = encoderCode;
	}
	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public void setIsPartial(Integer isPartial) {
		this.isPartial = isPartial;
	}
	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}
	public void setOrNo(Long orNo) {
		this.orNo = orNo;
	}
	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
}

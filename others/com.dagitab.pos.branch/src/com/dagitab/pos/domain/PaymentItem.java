package com.dagitab.pos.domain;

public class PaymentItem implements Cloneable {
	private Long orNo;
	private Integer paymentCode;
	private Double amount;
	private String cardType;
	private String cardNo;
	private String gcNo;
	private String checkNo;
	private String paymentType;
	private Integer storeNo;
	
	public Double getAmount() {
		return amount;
	}
	public String getCardNo() {
		return cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public String getGcNo() {
		return gcNo;
	}
	public Long getOrNo() {
		return orNo;
	}
	public Integer getPaymentCode() {
		return paymentCode;
	}
	public Integer getStoreNo() {
		return storeNo;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public void setGcNo(String gcNo) {
		this.gcNo = gcNo;
	}
	public void setOrNo(Long orNo) {
		this.orNo = orNo;
	}
	public void setPaymentCode(Integer paymentCode) {
		this.paymentCode = paymentCode;
	}
	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Object clone(){
		PaymentItem pi = new PaymentItem();
		pi.setAmount(this.amount);
		pi.setCardNo(this.cardNo);
		pi.setCardType(this.cardType);
		pi.setCheckNo(this.checkNo);
		pi.setGcNo(this.gcNo);
		pi.setOrNo(this.orNo);
		pi.setPaymentCode(this.paymentCode);
		pi.setPaymentType(this.paymentType);
		pi.setStoreNo(this.storeNo);
		return pi;
	}
	
}

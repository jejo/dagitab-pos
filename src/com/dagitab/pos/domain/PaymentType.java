package com.dagitab.pos.domain;

public class PaymentType {
	
	private Integer paymentCode;
	private String name;
	private String decription;
	
	public String getDecription() {
		return decription;
	}
	public String getName() {
		return name;
	}
	public Integer getPaymentCode() {
		return paymentCode;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPaymentCode(Integer paymentCode) {
		this.paymentCode = paymentCode;
	}
}

package com.dagitab.pos.domain;

public class InvoiceSet {
	private Long parentORNo;
	private Long ORNo;
	
	public Long getORNo() {
		return ORNo;
	}
	public Long getParentORNo() {
		return parentORNo;
	}
	
	public void setORNo(Long no) {
		ORNo = no;
	}
	
	public void setParentORNo(Long parentORNo) {
		this.parentORNo = parentORNo;
	}
	
}

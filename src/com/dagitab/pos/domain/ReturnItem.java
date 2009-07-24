package com.dagitab.pos.domain;

public class ReturnItem {
	private Integer orNo;
	private Integer storeCode;
	private String productCode;
	private Integer returnCode;
	private Double cost;
	private Double sellPrice;
	private Integer quantity;
	
	public Double getCost() {
		return cost;
	}
	public Integer getOrNo() {
		return orNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Integer getReturnCode() {
		return returnCode;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public Integer getStoreCode() {
		return storeCode;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public void setOrNo(Integer orNo) {
		this.orNo = orNo;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public void setStoreCode(Integer storeCode) {
		this.storeCode = storeCode;
	}

}

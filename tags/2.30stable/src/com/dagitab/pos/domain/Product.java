package com.dagitab.pos.domain;

public class Product {
	
	private String prodCode;
	private String name;
	private String description;
	private Integer catCode;
	private Integer subCatCode;
	private Double sellPrice;
	private Double cost;
	private Integer supplierCode;
	private Integer isConsignment;
	private Integer isPackage;
	private Integer isDeleted;
	
	public Integer getCatCode() {
		return catCode;
	}
	public Double getCost() {
		return cost;
	}
	public String getDescription() {
		return description;
	}
	public Integer getIsConsignment() {
		return isConsignment;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public Integer getIsPackage() {
		return isPackage;
	}
	public String getName() {
		return name;
	}
	public String getProdCode() {
		return prodCode;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public Integer getSubCatCode() {
		return subCatCode;
	}
	public Integer getSupplierCode() {
		return supplierCode;
	}
	public void setCatCode(Integer catCode) {
		this.catCode = catCode;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setIsConsignment(Integer isConsignment) {
		this.isConsignment = isConsignment;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public void setSubCatCode(Integer subCatCode) {
		this.subCatCode = subCatCode;
	}
	public void setSupplierCode(Integer supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	
}

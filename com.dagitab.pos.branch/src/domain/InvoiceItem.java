package domain;

public class InvoiceItem {
	private Long orNo;
	private Integer storeNo;
	private String productCode;
	private Integer discountCode;
	private Integer quantity;
	private Integer isDeferred;
	private Double sellPrice;
	private Double cost;
	
	public Double getCost() {
		return cost;
	}
	
	public Integer getDiscountCode() {
		return discountCode;
	}
	public Integer getIsDeferred() {
		return isDeferred;
	}
	public Long getOrNo() {
		return orNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public Integer getStoreNo() {
		return storeNo;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public void setDiscountCode(Integer discountCode) {
		this.discountCode = discountCode;
	}
	public void setIsDeferred(Integer isDeferred) {
		this.isDeferred = isDeferred;
	}
	public void setOrNo(Long orNo) {
		this.orNo = orNo;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}
}

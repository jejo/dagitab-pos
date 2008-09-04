package domain;

public class PullOutItem {
	private  Integer pullOutItemNo;
	private  Integer pullOutNo;
	private  String productCode;
	private Integer quantity;
	private Integer processedStat;
	public Integer getProcessedStat() {
		return processedStat;
	}
	public String getProductCode() {
		return productCode;
	}
	public Integer getPullOutItemNo() {
		return pullOutItemNo;
	}
	public Integer getPullOutNo() {
		return pullOutNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setProcessedStat(Integer processedStat) {
		this.processedStat = processedStat;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setPullOutItemNo(Integer pullOutItemNo) {
		this.pullOutItemNo = pullOutItemNo;
	}
	public void setPullOutNo(Integer pullOutNo) {
		this.pullOutNo = pullOutNo;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}

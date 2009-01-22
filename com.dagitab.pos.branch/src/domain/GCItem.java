package domain;

public class GCItem implements Cloneable{
	private Long orNo;
	private Integer storeNo;
	private Double amount;
	private String gcNo;
	public Double getAmount() {
		return amount;
	}
	public String getGcNo() {
		return gcNo;
	}
	public Long getOrNo() {
		return orNo;
	}
	public Integer getStoreNo() {
		return storeNo;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setGcNo(String gcNo) {
		this.gcNo = gcNo;
	}
	public void setOrNo(Long orNo) {
		this.orNo = orNo;
	}
	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}
	
	public Object clone(){
		GCItem gcItem = new GCItem();
		gcItem.setAmount(this.amount);
		gcItem.setGcNo(this.gcNo);
		gcItem.setOrNo(this.orNo);
		gcItem.setStoreNo(this.storeNo);
		return gcItem;
	}
}

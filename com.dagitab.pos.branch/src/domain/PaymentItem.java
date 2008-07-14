package domain;

public class PaymentItem {
	private Long orNo;
	private Integer paymentCode;
	private Double amount;
	private String cardType;
	private String cardNo;
	private String gcNo;
	private String checkNo;
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
}

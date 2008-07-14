package domain;

public class Invoice {
	private Long invoiceNo;
	private Long orNo;
	private Integer encoderCode;
	private Integer assistantCode;
	private Integer customerNo;
	private Integer storeNo;
	private Integer isPartial;
	private Integer isReturn;
	
	public Integer getAssistantCode() {
		return assistantCode;
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
	public void setAssistantCode(Integer assistantCode) {
		this.assistantCode = assistantCode;
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
}

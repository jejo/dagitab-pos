package forms.interfaces;

import domain.PaymentItem;

public interface Payments {
	
	/**
	 * 
	 * @param paymentCode
	 * @return 
	 * 		true if payment list has already a cash payment
	 */
	public Boolean hasCashPayment(Integer paymentCode);
	
	/**
	 * 
	 * @param paymentCode
	 * @param cardNo
	 * @return
	 * 		true if payment list has the same credit card no
	 */
	public Boolean hasCardNo(Integer paymentCode, String cardNo);
	
	
	/**
	 * 
	 * @param paymentCode
	 * @param checkNo
	 * @return
	 * 		true if payment list has the same bank check no
	 */
	public Boolean hasCheckNo(Integer paymentCode, String checkNo);
	
	/**
	 * 
	 * @param paymentCode
	 * @param gcNo
	 * @return
	 * 		true if payment list has the same gift certificate no
	 */
	public Boolean hasGcNo(Integer paymentCode, String gcNo);
	
	/**
	 * 
	 * @param paymentItem
	 * 	adds payment item to payment list
	 */
	public void addPaymentItem(PaymentItem paymentItem);
	
	public Integer getPaymentItemRow(Integer paymentCode);
	
	public void editPaymentItem(PaymentItem paymentItem, String paymentCode);
	
	public void updatePaymentAmounts();
	
	
}

package util;

import java.util.List;

import domain.PaymentItem;

public class PaymentCalculatorUtility {
	
	public static PaymentCalculatorUtility paymentCalculatorUtility = new PaymentCalculatorUtility();
	
	private PaymentCalculatorUtility(){}
	
	public PaymentCalculatorUtility getInstance(){
		return paymentCalculatorUtility;
	}
	
	public List<PaymentItem> getCalculatedPaymentItems(List<PaymentItem> paymentItems, Double transactionAmount){
		return null;
		
		
	}
	
	
}

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
		Double amount = transactionAmount;
		Double amountToBe=null;
		for(int i=0; i<=paymentItems.size();i++){
			String payType = paymentItems.get(i).getPaymentType();
			Double paymentAmount = paymentItems.get(i).getAmount();
			if(payType.equals("Gift Certificate")){
				if(amount > paymentAmount){
					amountToBe = paymentAmount;
					amount -= paymentAmount;
				}
				else
					amountToBe = paymentAmount;
			}
			else if(payType.equals("Credit Card")){
				if(amount > paymentAmount){
					amountToBe = paymentAmount;
					amount-= paymentAmount;
				}
				else
					amountToBe = amount;
			}
			else if(payType.equals("Check")){
				if(amount > paymentAmount){
					amountToBe = paymentAmount;
					amount-=paymentAmount;
				}
				else
					amountToBe = amount;
			}
			else{
				if(amount > paymentAmount){
					amountToBe = paymentAmount;
					amount -= paymentAmount;
				}
				else 
					amountToBe = amount;
			}
			paymentItems.get(i).setAmount(amountToBe);
		}
		return paymentItems;
	}
	
	
}

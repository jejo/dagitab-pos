package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import domain.PaymentItem;

public class PaymentCalculatorUtility {
	
	
	
	public static PaymentCalculatorUtility paymentCalculatorUtility = new PaymentCalculatorUtility();
	
	private PaymentCalculatorUtility(){}
	
	public static PaymentCalculatorUtility getInstance(){
		return paymentCalculatorUtility;
	}
	
	public List<PaymentItem> getCalculatedPaymentItems(List<PaymentItem> items, Double transactionAmount){
		//Copy paymentItems to new list to eliminate pointer problem!
		List<PaymentItem> calculatedPaymentItems = copy(items);
		System.out.println(calculatedPaymentItems.size());
		sortPaymentItems(calculatedPaymentItems);
		Double amount = transactionAmount;
		Double amountToBe=null;
		
		for(int i =0; i < calculatedPaymentItems.size(); i++){
			String payType = calculatedPaymentItems.get(i).getPaymentType();
			Double paymentAmount = calculatedPaymentItems.get(i).getAmount();
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
			else if(payType.equals("Bank Check")){
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
			calculatedPaymentItems.get(i).setAmount(amountToBe);
		}
		
		for(PaymentItem paymentItem: calculatedPaymentItems){
			System.out.println(paymentItem.getPaymentType());
		}
		return calculatedPaymentItems;
	}
	
	public List<PaymentItem> copy(List<PaymentItem> paymentItems){
		List<PaymentItem> listPaymentItem = new ArrayList<PaymentItem>();
		for(PaymentItem paymentItem: paymentItems){
			PaymentItem newPaymentItem = new PaymentItem();
			newPaymentItem = paymentItem;
			listPaymentItem.add(newPaymentItem);
		}
		return listPaymentItem;
	}
	
	public void sortPaymentItems(List<PaymentItem> items){
		Collections.sort(items, new Comparator<PaymentItem>() {@Override
			public int compare(PaymentItem o1, PaymentItem o2) {
				System.out.println("comparing: "+o1.getPaymentType()+" with "+o2.getPaymentType());
				return o2.getPaymentType().compareTo(o1.getPaymentType());
			}});
	}
	
	public static void main(String[] args) {
		PaymentCalculatorUtility paymentCalculatorUtility = getInstance();
		List<PaymentItem> listPaymentItem = new ArrayList<PaymentItem>();
		PaymentItem p1 = new PaymentItem();
		p1.setPaymentType("Cash");
		PaymentItem p2 = new PaymentItem();
		p2.setPaymentType("Credit Card");
		listPaymentItem.add(p1);
		listPaymentItem.add(p2);
		
		paymentCalculatorUtility.sortPaymentItems(listPaymentItem);
		
		for(PaymentItem paymentItem: listPaymentItem){
			System.out.println(paymentItem.getPaymentType());
		}
		
	}
	
	
	
}

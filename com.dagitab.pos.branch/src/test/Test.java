package test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test {
	
	public static void main(String[] args){
//		Timer timer = new Timer();
//		TimerTask timerTask = new TimerTask(){
//
//			@Override
//			public void run() {
//				System.out.println("Alex");
//				
//			}};
//			
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR, 11);
//		calendar.set(Calendar.MINUTE, 23);
//		timer.schedule(timerTask, calendar.getTime());
		DecimalFormat df = new DecimalFormat(".00");
		
		System.out.println("ALEX" +df.format(13.77500000000));
		
		
		BigDecimal identity = new BigDecimal(1.00).setScale(2);
		BigDecimal sellingPrice = new BigDecimal(2.775);
		;
		
		System.out.println("SELL_PRICE DOUBLE VALUE: "+sellingPrice.multiply(identity).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}
	
}

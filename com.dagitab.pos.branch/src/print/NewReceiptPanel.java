package print;

import java.awt.Font;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import main.DBManager;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class NewReceiptPanel extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private Vector<String> headerData;
	private Vector<Vector<String>> itemData;
	private Vector<Vector<String>> paymentData;
	private String vatAmount;
	private String changeAmount;
	private JLabel jLabel12;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private DBManager db;
	private String status;
	private JLabel jLabel20;
	
	

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DBManager db = new DBManager();
		db.connect();
		Vector<String> headerData = new Vector<String>();
			//		Address Branch, Tin, OR_NO, Served Name, Current Date, Current Time
		
		System.out.println();
		headerData.add("3048 New Wing, Alabang Town Ctr. Exp. Zapote Rd.Muntinlupa");
		headerData.add("TIN 000-051-689-000 VAT");
		headerData.add("001-000000001");
		headerData.add("ALEX ODAL");
		headerData.add("4/18/2007");
		headerData.add("13:45");
		
		Vector<Vector<String>> itemData = new Vector<Vector<String>>();
			Vector<String> itemRowData = new Vector<String>();
			itemRowData.add("xxx");
			itemRowData.add("ASS WOODEN CASTANETS 24’S");
			itemRowData.add("75.00");
			itemRowData.add("75.00");
			itemRowData.add("4");
//			prod code, prod name, current price, selling price, qty,
			
			Vector<String> itemRowData2 = new Vector<String>();
			itemRowData2.add("xxx");
			itemRowData2.add("ASS WOODEN CASTANETS 24’S");
			itemRowData2.add("75.00");
			itemRowData2.add("75.00");
			itemRowData2.add("1");
			
			Vector<String> itemRowData3 = new Vector<String>();
			itemRowData3.add("xxx");
			itemRowData3.add("ASS WOODEN CASTANETS 24’S");
			itemRowData3.add("100.00");
			itemRowData3.add("100.00");
			itemRowData3.add("1");
			
			
			Vector<String> itemRowData4 = new Vector<String>();
			itemRowData4.add("xxx");
			itemRowData4.add("ASS WOODEN CASTANETS 24’S");
			itemRowData4.add("120.00");
			itemRowData4.add("120.00");
			itemRowData4.add("1");
			
			
			Vector<String> itemRowData5 = new Vector<String>();
			itemRowData5.add("xxx");
			itemRowData5.add("ASS WOODEN CASTANETS 24’S");
			itemRowData5.add("15.00");
			itemRowData5.add("15.00");
			itemRowData5.add("3");
			
			
		itemData.add(itemRowData);
		itemData.add(itemRowData2);
		itemData.add(itemRowData3);
		itemData.add(itemRowData4);
		itemData.add(itemRowData5);
			
		
		Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
			Vector<String> paymentRowData = new Vector<String>();
			//name of payment, amount, change
			paymentRowData.add("Cash");
			paymentRowData.add("700.00");
			paymentRowData.add("60");
		paymentData.add(paymentRowData);
		String vatAmount = "571.42";
		String changeAmount = "60.00";
		NewReceiptPanel rp = new NewReceiptPanel(headerData,itemData,paymentData,vatAmount,changeAmount, db,"par");
		frame.getContentPane().add(rp);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public NewReceiptPanel() {
		super();
		initGUI();
	}
	public NewReceiptPanel(Vector<String> headerData, 
							Vector<Vector<String>> itemData, 
							Vector<Vector<String>> paymentData,String vatAmount,
							String changeAmount, DBManager db, String status) {
		super();
		this.headerData = headerData; 
			//Address Branch, Tin, OR_NO, Served Name, Current Date, Current Time
		this.itemData = itemData;
			//prod code, prod name, current price, selling price, qty, 
		this.paymentData = paymentData;
			//name of payment, amount,
		this.vatAmount = vatAmount;
		this.changeAmount = changeAmount;
		this.db = db;
		this.status = status;
		initGUI();
	}
	
	private void initGUI() {
		try {
			int height = 450;
			
			height = height+ (28*(itemData.size()-1));
			height = height+ (28*(paymentData.size()-1));
//			height += 30;
			String[] address = splitString(headerData.get(0));
			this.setPreferredSize(new java.awt.Dimension(203, height));
			this.setLayout(null);
			this.setBackground(new java.awt.Color(255,255,255));
			this.setBorder(BorderFactory.createTitledBorder(""));
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("BABYLAND, INC.");
				jLabel1.setBounds(42, 0, 126, 28);
				jLabel1.setFont(new java.awt.Font("Arial",1,14));
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("Date: "+headerData.get(4));
				jLabel2.setBounds(7, 98, 189, 28);
				jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
				jLabel2.setFont(new java.awt.Font("Arial",0,10));
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText(address[0]);
				jLabel3.setBounds(7, 42, 189, 14);
				jLabel3.setFont(new java.awt.Font("Arial",0,10));
				jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				if(address.length > 1){
					jLabel20 = new JLabel();
					this.add(jLabel20);
					
					jLabel20.setText(address[1]);
					jLabel20.setBounds(7, 56, 189, 14);
					jLabel20.setFont(new java.awt.Font("Arial",0,10));
					jLabel20.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText(headerData.get(1));
				jLabel4.setBounds(7, 70, 189, 14);
				jLabel4.setFont(new java.awt.Font("Arial",0,10));
				jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5);
				jLabel5.setText("Served by: "+headerData.get(3));
				jLabel5.setFont(new java.awt.Font("Arial",0,10));
				jLabel5.setBounds(7, 108, 189, 28);
				jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel6 = new JLabel();
				this.add(jLabel6);
				jLabel6.setText("Time: "+headerData.get(5));
				jLabel6.setFont(new java.awt.Font("Arial",0,10));
				jLabel6.setBounds(7, 122, 189, 28);
				jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel7 = new JLabel();
				this.add(jLabel7);
				jLabel7.setText("DESCRIPTION");
				jLabel7.setBounds(7, 154, 189, 28);
				jLabel7.setFont(new java.awt.Font("Tahoma",1,8));
			}
			{
				jLabel8 = new JLabel();
				this.add(jLabel8);
				jLabel8.setText("AMOUNT");
				jLabel8.setFont(new java.awt.Font("Tahoma",1,8));
				jLabel8.setBounds(161, 154, 189, 28);
			}
			{
				jLabel9 = new JLabel();
				this.add(jLabel9);
				jLabel9.setText("OR NO: "+headerData.get(2));
				jLabel9.setFont(new java.awt.Font("Arial",0,10));
				jLabel9.setBounds(7, 77, 189, 28);
				jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel10 = new JLabel();
				this.add(jLabel10);
				jLabel10.setText("QTY");
				jLabel10.setBounds(84, 154, 189, 28);
				jLabel10.setFont(new java.awt.Font("Tahoma",1,8));
			}
			{
				jLabel11 = new JLabel();
				this.add(jLabel11);
				jLabel11.setText("UNIT");
				jLabel11.setBounds(119, 154, 189, 28);
				jLabel11.setFont(new java.awt.Font("Tahoma",1,8));
			}
			{
				jLabel12 = new JLabel();
				this.add(jLabel12);
				jLabel12.setText("BABYLAND, INC.");
				jLabel12.setFont(new java.awt.Font("Arial",1,14));
				jLabel12.setBounds(42, 21, 126, 21);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {	
		 super.paintComponent(g);
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 9));
		 
		 g.drawLine(5, 149, 190, 149); //line after Description, Amount Heading
		 
		 
		 int topMarker = 189;
		 double origSubTotal = 0;
		 double newSubTotal = 0;
		 /***VAT**/
		 ResultSet rs = db.executeQuery("SELECT VAT FROM `global`");
		 String vatval = "";
		 try {
			if(rs.next()){
				 vatval = rs.getString(1);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 for(int i = 0; i<itemData.size(); i++){
			double cqty = Double.parseDouble(itemData.get(i).get(2)) * Integer.parseInt(itemData.get(i).get(4));
			double sqty = Double.parseDouble(itemData.get(i).get(3)) * Integer.parseInt(itemData.get(i).get(4));
			System.out.println("cqty "+cqty);
			System.out.println("sqty "+sqty);
			origSubTotal+=cqty;
			newSubTotal += sqty;
			g.setFont(new Font("Arial", Font.PLAIN, 9));
			g.drawString(itemData.get(i).get(0),8,topMarker);
			
			String qty = itemData.get(i).get(4);
			if(Integer.parseInt(qty) < 0){
				qty = "("+qty.substring(1)+")"; //eliminate "-";
			}
			
			g.drawString(qty, 85, topMarker);
			
			
			
			String unitPrice =String.format("%.2f", Double.parseDouble(itemData.get(i).get(2)));
			int newXpos = 140 - (unitPrice.length()*4);
			
			g.drawString(unitPrice, newXpos, topMarker);
			
			Double preamount = Math.abs(cqty); //eliminate "-";
			String Amount = String.format("%.2f", preamount);  
			if(cqty < 0){
				Amount = "("+ Amount +")";
			}
			
			newXpos = 190 - (Amount.length()*4);
			g.drawString(Amount, newXpos, topMarker);
			
			
			topMarker+=10;
			
			g.setFont(new Font("Arial", Font.PLAIN, 8));
			
			g.drawString(itemData.get(i).get(1), 8, topMarker);
//			String lblcurPriceQty = itemData.get(i).get(2)+" X " +itemData.get(i).get(4) +"   "+String.format("%.2f", cqty);
//			int xpos = findItemAmountXPos(lblcurPriceQty);
			
//			g.drawString(lblcurPriceQty,xpos,topMarker);
			topMarker+=15;
			
		 }
//		 g.drawString("ASS WOODEN CASTANETS 24’S", 6, 175);
//		 //for right justified compute 205 - string size*4 as first position
//		 g.drawString("11159.75 X 1   59.75",125, 185);
//		 
//		 g.drawString("ASS BALL SMILEY ASTD 24’S", 6, 200);
////		for right justified compute 205 - string size*4 as first position
//		 g.drawString("11159.75 X 1   59.75",125, 210);
		 
		 g.drawLine(5, topMarker, 190, topMarker); //line after Items
		 	topMarker+=20;
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 
		 /***SUBTOTAL**/
		 g.drawString("Vatable Sale",5,topMarker); //Subtotal label
		 //	for right justified compute 190 - string size*5 as first position
		 
		 if(origSubTotal < 0){
			 origSubTotal = 0;
		 }
		 if(newSubTotal < 0){
			 newSubTotal = 0;
		 }
		 
		 double totalDisc = origSubTotal - newSubTotal;
		 double netSale = Double.parseDouble(String.format("%.2f",origSubTotal)) - Double.parseDouble(String.format("%.2f",totalDisc));
		 System.out.println("Net Sale: "+netSale);
		 System.out.println("NEW SUB TOTAL:"+newSubTotal);
		 double vatAmt = Double.parseDouble("1."+vatval);
		 double vatablepurchase = newSubTotal/vatAmt;
//		 		vatablepurchase = roundDown(vatablepurchase);
		 int xpos = findNormalAmountXPos(String.format("%.2f", vatablepurchase));
		 g.drawString(String.format("%.2f", vatablepurchase), xpos, topMarker); //subtotal amt
		 topMarker+=15;
		 
		 
		 
//		 String.format("%.2f", amount);
		 /***DISCOUNT**/
		 g.drawString("Discount",5,topMarker);
		 //	for right justified compute 190 - string size*5 as first position
		
		 xpos = findNormalAmountXPos(String.format("%.2f", totalDisc));
		 g.drawString(String.format("%.2f", totalDisc), xpos, topMarker); //disc amount
		 topMarker+=15;
		
		 //vat amount 12% of Vatable Sale
		 vatAmount = String.format("%.2f",vatablepurchase * Double.parseDouble("0."+vatval));
		 g.drawString("VAT("+vatval+"%)",5,topMarker);
		 //	for right justified compute 190 - string size*5 as first position
		 xpos = findNormalAmountXPos(vatAmount);
		 g.drawString(vatAmount, xpos, topMarker); //disc amount
		 topMarker+=15;
		 g.drawString("VAT-Exempt Sale",5,topMarker);
		 topMarker+=20;
		 
		 
		 g.setFont(new Font("Arial", Font.BOLD, 11));
		 /***TOTAL**/
		 g.drawString("TOTAL",5,topMarker);
		 
		 System.out.println("Total? :"+newSubTotal);
		 //	for right justified compute 190 - string size*5 as first position
//		 double Total = Double.parseDouble(String.format("%.2f", vatablepurchase))+Double.parseDouble(vatAmount);
		 double Total = Double.parseDouble(String.format("%.2f", newSubTotal));
		 xpos = findNormalAmountXPos(String.format("%.2f", Total));
		 g.drawString(String.format("%.2f", Total), xpos, topMarker); //disc amount
		 topMarker+=15;
		  
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 /***NO OF ITEMS**/
		 
		 int noitems = 0;
		 for(int i = 0; i<itemData.size(); i++){
			 int num = Integer.parseInt(itemData.get(i).get(4).toString());
			 if(num>0){
				 noitems+=num; 
			 }
		 }
		 g.drawString("No of Items ("+noitems+")",5,topMarker);
		 topMarker+=25;
		 
		 /***PAYMENT DETAILS**/
		 g.setFont(new Font("Arial", Font.BOLD, 11));
		 g.drawString("PAYMENT DETAILS",5,topMarker);
		 g.drawString("AMOUNT", 148, topMarker);
		 topMarker+=15;
		 
		 
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 
		 double totalPaymentAmount = 0;
		 for(int i = 0; i<paymentData.size(); i++){
			 g.drawString(paymentData.get(i).get(0),10,topMarker);//Payment Types
//				for right justified compute 190 - string size*5 as first position
			 String payamt = paymentData.get(i).get(1);
			 xpos = findNormalAmountXPos(String.format("%.2f",Double.parseDouble(payamt)));
			 g.drawString(String.format("%.2f",Double.parseDouble(payamt)), xpos, topMarker); //amount
			 topMarker+=20;
		 }
		 
		 /*CHANGE OR BALANCE*/
		 if(status.equals("par")){
			 String temp = String.format("%.2f", Double.parseDouble(changeAmount));
			 if(Double.parseDouble(changeAmount) < 0 ){
				 temp = "("+temp+")";
			 }
			 
			 g.drawString("BALANCE", 10, topMarker);
			 xpos = findNormalAmountXPos(changeAmount);
			 g.drawString(temp, xpos, topMarker);
			 topMarker+=30;
		 }
		 else{
			 g.drawString("CHANGE", 10, topMarker);
			 xpos = findNormalAmountXPos(changeAmount);
			 g.drawString(String.format("%.2f", Double.parseDouble(changeAmount)), xpos, topMarker);
			 topMarker+=30;
		 }
		 
		
		 /**FOOTER**/
		 if(status.equals("par")){
			 g.drawString("This serves as your partial receipt", 20, topMarker);
		 }
		 else{
			 g.drawString("This serves as your official receipt", 20, topMarker);
		 }
		 
		 topMarker+=10;
		 g.drawString("Thank you for shopping…", 40, topMarker);
		 topMarker+=20;
		 g.setFont(new Font("Arial", Font.BOLD, 11));
		 g.drawString("Babyland", 70, topMarker);
		 topMarker+=10;
		 g.setFont(new Font("Arial", Font.PLAIN, 10));
		 g.drawString("www.babyland.com.ph", 45, topMarker);
		 
		
	}
	
	private int findNormalAmountXPos(String s){
		
		return 190 - (s.length()*5);
		
	}
	
	private int findItemAmountXPos(String s){
		//205 - string size*4
		return 205 - (s.length()*4);
	}
	
	private String[] splitString(String s){
		String[] temp = s.split(" ");
		ArrayList<String> ss = new ArrayList<String>();
		int MAX = 32;
		int countChar = 0;
		String sTemp = "";
		for(int i=0; i<temp.length; i++){
			countChar += temp[i].length()+1;
			sTemp+=temp[i]+" ";
			if(countChar >= MAX){
				countChar = 0;
				ss.add(sTemp);
				sTemp="";
			}
			else if(countChar < MAX && i==temp.length-1){
				ss.add(sTemp);
			}
		}
		
		String[] out = new String[ss.size()];
		for(int i=0; i<ss.size();i++){
			out[i] = ss.get(i);
			
		}
		return out;
		
		
	}
	private Double roundDown(Double num){
		String numS = String.format("%.4f",num);
		System.out.println(numS);
		int index = numS.indexOf(".");
		String  out = numS.substring(0,index+3);
		return Double.parseDouble(out);
	}
//	private String[] splitString(String s){
//		int MAX = 32;
//		if(s.length() > MAX){
//			return new String[]{s.substring(0, 31), s.substring(31)};
//		}
//		else {
//			return new String[]{s};
//		}
//	}

}

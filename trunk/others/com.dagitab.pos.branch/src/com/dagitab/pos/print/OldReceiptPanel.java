package com.dagitab.pos.print;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.dagitab.pos.util.LoggerUtility;


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
@Deprecated
public class OldReceiptPanel extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private String date;
	private String time;
	private Vector<Vector<String>> data;
	private String[] others;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new OldReceiptPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public OldReceiptPanel() {
		super();
		initGUI();
	}
	
	public OldReceiptPanel(String[] others, String date, String time,Vector<Vector<String>> data) {
		super();
		this.time = time;
		this.data = data;
		this.date = date;
		this.others = others;
		initGUI();
	}
	
	
	
	private void initGUI() {
		try {
			int height = 616;
			height = height+ 15*(data.size()-1);
			this.setPreferredSize(new java.awt.Dimension(224, height));
			this.setBackground(new java.awt.Color(255,255,255));
			this.setLayout(null);
			this.setFont(new java.awt.Font("Times New Roman",0,12));
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText(others[1]);
				jLabel4.setPreferredSize(new java.awt.Dimension(119, 28));
				jLabel4.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel4.setBounds(45, 77, 127, 28);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText(others[0]);
				jLabel3.setPreferredSize(new java.awt.Dimension(126, 21));
				jLabel3.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel3.setBounds(45, 63, 134, 21);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("Operated by Babyland, Inc.");
				jLabel2.setPreferredSize(new java.awt.Dimension(161, 21));
				jLabel2.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel2.setBounds(37, 42, 172, 21);
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("babyland");
				jLabel1.setPreferredSize(new java.awt.Dimension(56, 28));
				jLabel1.setFont(new java.awt.Font("Times New Roman",1,14));
				jLabel1.setBounds(75, 14, 59, 28);
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5);
				jLabel5.setText("(Machine Model Accred. No.)");
				jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12));
				jLabel5.setPreferredSize(new java.awt.Dimension(168, 28));
				jLabel5.setBounds(30, 91, 179, 28);
			}
			{
				jLabel7 = new JLabel();
				this.add(jLabel7);
				jLabel7.setText("(Printer #)");
				jLabel7.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel7.setPreferredSize(new java.awt.Dimension(56, 28));
				jLabel7.setBounds(75, 105, 59, 28);
			}
			{
				jLabel6 = new JLabel();
				this.add(jLabel6);
				jLabel6.setText("O.R.No. "+others[2]);
				jLabel6.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel6.setPreferredSize(new java.awt.Dimension(119, 28));
				jLabel6.setBounds(15, 140, 127, 28);
			}
			{
				jLabel8 = new JLabel();
				this.add(jLabel8);
				jLabel8.setText("Staff "+others[3]);
				jLabel8.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel8.setBounds(14, 154, 161, 28);
			}
			{
				jLabel9 = new JLabel();
				this.add(jLabel9);
				jLabel9.setText("Date: "+date);
				jLabel9.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel9.setBounds(14, 175, 91, 28);
			}
			{
				jLabel10 = new JLabel();
				this.add(jLabel10);
				jLabel10.setText("Time: "+ time);
				jLabel10.setFont(new java.awt.Font("Times New Roman",0,12));
				jLabel10.setBounds(147, 175, 126, 28);
			}
			{
				jLabel11 = new JLabel();
				this.add(jLabel11);
				jLabel11.setText("Product Code");
				jLabel11.setFont(new java.awt.Font("Times New Roman",1,12));
				jLabel11.setPreferredSize(new java.awt.Dimension(70, 28));
				jLabel11.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				jLabel11.setBounds(15, 196, 75, 28);
			}
			{
				jLabel12 = new JLabel();
				this.add(jLabel12);
				jLabel12.setText("Qty");
				jLabel12.setFont(new java.awt.Font("Times New Roman",1,12));
				jLabel12.setPreferredSize(new java.awt.Dimension(28, 28));
				jLabel12.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				jLabel12.setBounds(105, 196, 28, 28);
			}
			{
				jLabel13 = new JLabel();
				this.add(jLabel13);
				jLabel13.setText("Total Price");
				jLabel13.setFont(new java.awt.Font("Times New Roman",1,12));
				jLabel13.setPreferredSize(new java.awt.Dimension(70, 28));
				jLabel13.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				jLabel13.setBounds(147, 196, 70, 28);
			}
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Font font = new Font("Times New Roman", Font.PLAIN, 12);
		  setFont(font);
		  
		  g.drawLine(13, 218, 80, 218);
		  g.drawLine(105, 218, 125, 218);
		  g.drawLine(147, 218, 206, 218);
		  
		  int pos = 235;
		  for(int i = 0; i<data.size(); i++){
			  g.drawString(data.get(i).get(0), 15,pos);
			  
			  g.drawString(data.get(i).get(1), 107, pos);
			  g.drawString(data.get(i).get(2),160,pos);
			  pos+=15;
			    
		  }
		  
		  
		  pos+=15;
		  g.drawLine(147,pos, 206, pos);
		  pos+=20;
		  g.drawString("(V) Vatable Sales", 15,pos);
		  g.drawString("("+others[4]+"/ 1.12)", 150,pos);
		  pos+=15;
		  g.drawString("VAT (12%)", 15,pos);
		  g.drawString("( V * 0.12 )", 150,pos);
		  pos+=15;
		  g.drawString("(E)Vat-Exempt Sales", 15,pos);
		  pos+=20;
		  g.drawString("Zero Rated Sales", 15,pos);
		  
		  pos+=30;
		  g.setFont(new Font("Times New Roman", Font.BOLD, 12));
		  g.drawString("AMOUNT DUE",15, pos);
		  g.drawString(others[5], 150,pos);
		  g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		  
		  pos+=30;
		  g.drawString("CASH",15, pos);
		  g.drawString(others[6], 150,pos);
		  pos+=15;
		  g.drawString("CHANGE",15, pos);
		  g.drawString(others[7], 150,pos);
		  pos+=20;
		  g.drawString("No. of Items :",15, pos);
		  g.drawString(data.size()+"",150, pos);
		  g.setFont(new Font("Times New Roman", Font.BOLD, 9));
		  pos+=35;
		  g.drawString("THIS SERVES AS YOUR OFFICIAL RECEIPT",18, pos);
		  g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		  pos+=30;
		  g.drawString("Thank you for shopping...",40, pos);
		  pos+=30;
		  g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		  g.drawString("babyland",80, pos);
		  
		  pos+=20;
		  g.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		  g.drawString("www.babyland.com.ph",50, pos);
		  
	}

}

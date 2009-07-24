package com.dagitab.pos.forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.KeyStroke;


import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.main.DBManager;
import com.dagitab.pos.reports.FestivalDailyReport;
import com.dagitab.pos.reports.FestivalHourlyReport;
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
public class FestivalComplianceDaily extends javax.swing.JDialog {
	private JLabel festivalComplianceDailyLabel;
	private JLabel jLabel2;
	private JComboBox jComboBox1;
	private JLabel jLabel3;
	private JComboBox jComboBox2;
	private JLabel jLabel4;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JComboBox jComboBox3;
	private DBManager db;
	private String storeCode;
	private String status;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		FestivalComplianceDaily inst = new FestivalComplianceDaily(frame);
//		inst.setVisible(true);
	}
	
	public FestivalComplianceDaily(MainWindow frame, DBManager db, String storeCode, String status) {
		super(frame);
		this.db = db;
		this.storeCode = storeCode;
		this.status = status;
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Festival Supermall "+status+" Sales Report");
				{
					jButton3 = new JButton();
					getContentPane().add(
						jButton3,
						new AnchorConstraint(
							870,
							947,
							975,
							786,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jButton3.setText("Close");
					jButton3.setPreferredSize(new java.awt.Dimension(63, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							FestivalComplianceDaily.this.dispose();
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(607, 804, 765, 197, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Generate Compliance For Current Date");
					jButton2.setPreferredSize(new java.awt.Dimension(238, 42));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
							JFileChooser chooser = new JFileChooser();
							chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							chooser.setAcceptAllFileFilterUsed(false);

							int returnVal = chooser.showDialog(FestivalComplianceDaily.this,"Save File Here");
						    if(returnVal == JFileChooser.APPROVE_OPTION) {
						    	String dir = chooser.getSelectedFile().getAbsolutePath();
						    	
						    	if(status.equals("Daily")){
							    	FestivalDailyReport report = new FestivalDailyReport(db,storeCode,dir,new Date());
							    	report.createFile();
							    	report.insertData(new Date());
						    	}
						    	else if(status.equals("Hourly")){
						    		FestivalHourlyReport report = new FestivalHourlyReport(db,storeCode,dir,new Date());
							    	report.createFile();
							    	report.insertData(new Date());
						    	}
						    }
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(396, 947, 501, 411, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Generate Report For Input Date");
					jButton1.setPreferredSize(new java.awt.Dimension(210, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Calendar c1 = Calendar.getInstance();
							
							
							int year =  Integer.parseInt(jComboBox3.getSelectedItem().toString());
							int m = Integer.parseInt(jComboBox1.getSelectedItem().toString());
							int d = Integer.parseInt(jComboBox2.getSelectedItem().toString());
							c1.set(year, (m-1), d);
							Date date = c1.getTime();
							
							
							JFileChooser chooser = new JFileChooser();
							chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							chooser.setAcceptAllFileFilterUsed(false);

							int returnVal = chooser.showDialog(FestivalComplianceDaily.this,"Save File Here");
						    if(returnVal == JFileChooser.APPROVE_OPTION) {
						    	String dir = chooser.getSelectedFile().getAbsolutePath();
						    	
						    	if(status.equals("Daily")){
							    	FestivalDailyReport report = new FestivalDailyReport(db,storeCode,dir,date);
							    	report.createFile();
							    	report.insertData(date);
						    	}
						    	else if(status.equals("Hourly")){
						    		FestivalHourlyReport report = new FestivalHourlyReport(db,storeCode,dir,date);
							    	report.createFile();
							    	report.insertData(date);
						    	}
						    }
						}
					});
				}
				{
					
					String[] noyears = new String[2];
					noyears[0] = "--";
					ResultSet rs = db.executeQuery("SELECT YEAR(CURRENT_TIMESTAMP)");
					if(rs.next()){
						noyears[1] = rs.getString(1);
					}
					
					ComboBoxModel jComboBox3Model = new DefaultComboBoxModel(
						noyears);
					jComboBox3 = new JComboBox();
					getContentPane().add(jComboBox3, new AnchorConstraint(212, 947, 317, 733, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox3.setModel(jComboBox3Model);
					jComboBox3.setPreferredSize(new java.awt.Dimension(84, 28));
					jComboBox3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out
								.println("jComboBox3.actionPerformed, event="
									+ evt);
							//TODO add your code for jComboBox3.actionPerformed
						}
					});
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4, new AnchorConstraint(212, 733, 317, 644, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("Year");
					jLabel4.setPreferredSize(new java.awt.Dimension(35, 28));
				}
				{
					
					String[] nodays = new String[32];
					for(int i = 0; i<32; i++){
						if(i == 0){
							nodays[i] = "--";
						}
						else{
							if(i<10){
								nodays[i] = "0"+i+"";
							}
							else{
								nodays[i] = i+"";
							}
							
						}
							
					}
					ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
						nodays);
					jComboBox2 = new JComboBox();
					getContentPane().add(jComboBox2, new AnchorConstraint(212, 608, 317, 447, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setPreferredSize(new java.awt.Dimension(63, 28));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(212, 447, 317, 376, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Day");
					jLabel3.setPreferredSize(new java.awt.Dimension(28, 28));
				}
				{
					
					String[] nomonths = new String[13];
					for(int i = 0; i<13; i++){
						if(i == 0){
							nomonths[i] = "--";
						}
						else{
							if(i<10){
								nomonths[i] = "0"+i+"";
							}
							else{
								nomonths[i] = i+"";
							}
						}
					}
					
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
						nomonths);
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1, new AnchorConstraint(212, 340, 317, 161, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setPreferredSize(new java.awt.Dimension(70, 28));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(
						jLabel2,
						new AnchorConstraint(
							212,
							197,
							317,
							36,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Month");
					jLabel2.setPreferredSize(new java.awt.Dimension(63, 28));
				}
				{
					festivalComplianceDailyLabel = new JLabel();
					getContentPane().add(festivalComplianceDailyLabel, new AnchorConstraint(54, 286, 159, 36, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					festivalComplianceDailyLabel.setText("Choose Date");
					festivalComplianceDailyLabel.setPreferredSize(new java.awt.Dimension(98, 28));
					festivalComplianceDailyLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "festivalComplianceDailyLabel");
					festivalComplianceDailyLabel.getActionMap().put("festivalComplianceDailyLabel",getFestivalComplianceLabelAction() );
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	private AbstractAction getFestivalComplianceLabelAction() {
		AbstractAction festivalComplianceLabelAction = new AbstractAction("Choose Date", null) {
			
			public void actionPerformed(ActionEvent evt) {
				FestivalComplianceDaily.this.dispose();
			}
		};
		return festivalComplianceLabelAction;
	}
	
}

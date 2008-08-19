package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

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
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import javax.swing.JFrame;
import javax.swing.JLabel;

import reports.AyalaCompliance;
import reports.AyalaHourlyCompliance;


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
public class ComplianceDaily extends javax.swing.JDialog {
	private JComboBox month;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JComboBox jComboBox2;
	private JComboBox jComboBox1;
	private JLabel complianceDailyLabel;
	DBManager db;
	String storeCode;
	String status;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ComplianceDaily inst = new ComplianceDaily(frame);
		inst.setVisible(true);
	}
	
	public ComplianceDaily(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public ComplianceDaily(MainWindow frame, DBManager db, String storeCode, String status) {
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
				this.setTitle("Compliance "+status);
				this.setModal(true);
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3, new AnchorConstraint(843, 929, 949, 769, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("Close");
					jButton3.setPreferredSize(new java.awt.Dimension(63, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ComplianceDaily.this.dispose();
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(554, 804, 712, 197, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Generate Compliance For Current Date");
					jButton2.setPreferredSize(new java.awt.Dimension(238, 42));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
							if(status.equals("Daily")){
								AyalaCompliance ac = new AyalaCompliance(db,storeCode);
								ac.checkDirectory();
								ac.createDBFFile(new Date());
								ac.insertData(new Date());
							}
							else if(status.equals("Hourly")){
								AyalaHourlyCompliance ahc = new AyalaHourlyCompliance(db,storeCode);
								ahc.checkDirectory();
								ahc.createDBFFile(new Date());
								ahc.insertData(new Date());
							}
							
							
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(370, 929, 449, 394, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Generate Compliance For Input Date");
					jButton1.setPreferredSize(new java.awt.Dimension(210, 21));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if(status.equals("Daily")){
								if(jComboBox1.getSelectedItem().toString() == "--" ||
										jComboBox2.getSelectedItem().toString() == "--"  ||
										month.getSelectedItem().toString() == "--"){
										JOptionPane.showMessageDialog(null, "Please select valid date","Error",JOptionPane.ERROR_MESSAGE);
									}
									else{
										
										
										Calendar c1 = Calendar.getInstance();
										
										
										int year =  Integer.parseInt(jComboBox2.getSelectedItem().toString());
										int m = Integer.parseInt(month.getSelectedItem().toString());
										int d = Integer.parseInt(jComboBox1.getSelectedItem().toString());
										c1.set(year, (m-1), d);
										Date date = c1.getTime();
										
										AyalaCompliance ac = new AyalaCompliance(db,storeCode);
										ac.checkDirectory();
										ac.createDBFFile(date);
										ac.insertData(date);
										
									}
							}
							else if(status.equals("Hourly")){
								if(jComboBox1.getSelectedItem().toString() == "--" ||
										jComboBox2.getSelectedItem().toString() == "--"  ||
										month.getSelectedItem().toString() == "--"){
										JOptionPane.showMessageDialog(null, "Please select valid date","Error",JOptionPane.ERROR_MESSAGE);
									}
									else{
										
										
										Calendar c1 = Calendar.getInstance();
										
										
										int year =  Integer.parseInt(jComboBox2.getSelectedItem().toString());
										int m = Integer.parseInt(month.getSelectedItem().toString());
										int d = Integer.parseInt(jComboBox1.getSelectedItem().toString());
										c1.set(year, (m-1), d);
										Date date = c1.getTime();
										
										AyalaHourlyCompliance ahc = new AyalaHourlyCompliance(db,storeCode);
										ahc.checkDirectory();
										ahc.createDBFFile(date);
										ahc.insertData(date);
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
					
					
					ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
						noyears);
					jComboBox2 = new JComboBox();
					getContentPane().add(jComboBox2, new AnchorConstraint(212, 929, 317, 679, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setPreferredSize(new java.awt.Dimension(98, 28));
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4, new AnchorConstraint(212, 644, 317, 572, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("Year");
					jLabel4.setPreferredSize(new java.awt.Dimension(28, 28));
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
					
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
						nodays);
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1, new AnchorConstraint(212, 536, 317, 394, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setPreferredSize(new java.awt.Dimension(56, 28));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(212, 394, 317, 322, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Day");
					jLabel3.setPreferredSize(new java.awt.Dimension(28, 28));
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
					complianceDailyLabel = new JLabel();
					getContentPane().add(
						complianceDailyLabel,
						new AnchorConstraint(
							54,
							197,
							159,
							36,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					complianceDailyLabel.setText("Choose Date");
					complianceDailyLabel.setPreferredSize(new java.awt.Dimension(63, 28));
					complianceDailyLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "complianceDailyLabel");
					complianceDailyLabel.getActionMap().put("complianceDailyLabel",getComplianceDailyLabelAbstractAction() );
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
					ComboBoxModel monthModel = new DefaultComboBoxModel(
						nomonths);
					month = new JComboBox();
					getContentPane().add(month, new AnchorConstraint(212, 286, 317, 144, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					month.setModel(monthModel);
					month.setPreferredSize(new java.awt.Dimension(56, 28));
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private AbstractAction getComplianceDailyLabelAbstractAction() {
		AbstractAction complianceDailyLabelAction = new AbstractAction("Choose Date", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ComplianceDaily.this.dispose();
			}
		};
		return complianceDailyLabelAction;
	}

}

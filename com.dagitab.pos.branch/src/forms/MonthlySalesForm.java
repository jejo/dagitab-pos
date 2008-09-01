package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import main.DBManager;
import main.FileFilterMaker;
import util.LoggerUtility;

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
public class MonthlySalesForm extends javax.swing.JDialog {
	private JLabel monthlySalesFormLabel;
	private MainWindow frame;
	private DBManager db;
	private JButton jButton1;
	private JButton jButton2;
	private JComboBox jComboBox2;
	private JComboBox jComboBox1;
	private JLabel jLabel2;
	private String storeCode;
	private ArrayList<String> monthNames;
	private ArrayList<String> monthNums;
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MonthlySalesForm inst = new MonthlySalesForm(frame);
		inst.setVisible(true);
	}
	
	public MonthlySalesForm(JFrame frame) {
		super(frame);
		initGUI();
	}
	public MonthlySalesForm(MainWindow frame, DBManager db, String storeCode) {
		super(frame);
		this.frame = frame;
		this.db = db;
		this.storeCode = storeCode;
		monthNames = new ArrayList<String>();
		monthNames.add("January");
		monthNames.add("February");
		monthNames.add("March");
		monthNames.add("April");
		monthNames.add("May");
		monthNames.add("June");
		monthNames.add("July");
		monthNames.add("August");
		monthNames.add("September");
		monthNames.add("October");
		monthNames.add("November");
		monthNames.add("December");
		
		monthNums = new ArrayList<String>();
		for(int i = 1; i<=12; i++) {
			if(i<10){
				monthNums.add("0"+i);	
			}
			else{
				monthNums.add(i+"");
			}
		}
			
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Monthly Sales Report");
				{
					monthlySalesFormLabel = new JLabel();
					getContentPane().add(monthlySalesFormLabel);
					monthlySalesFormLabel.setText("Monthly Sales Report");
					monthlySalesFormLabel.setBounds(7, 7, 217, 28);
					monthlySalesFormLabel.setFont(new java.awt.Font("Tahoma",1,18));
					monthlySalesFormLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "monthlySalesFormLabel");
					monthlySalesFormLabel.getActionMap().put("monthlySalesFormLabel",getMonthlySalesFormLabelAbstractAction() );
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Choose Month and Year");
					jLabel2.setBounds(7, 35, 133, 28);
				}
				{
					
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
							monthNames.toArray());
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1);
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setBounds(7, 77, 182, 28);
				}
				{
					String date ="";
					ResultSet rs = db.executeQuery("SELECT CURRENT_TIMESTAMP \"Date\"");
					if(rs.next()){
						date = rs.getString("Date");
					}
					String[] dateParts = date.split("-");
					int year = Integer.parseInt(dateParts[0]);
					ArrayList<String> yearNum = new ArrayList<String>();
					for(int i = year; i>=2005; i--){
						yearNum.add(i+"");
					}
					ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
						yearNum.toArray());
					jComboBox2 = new JComboBox();
					getContentPane().add(jComboBox2);
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setBounds(210, 77, 168, 28);
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("Export To Excel");
					jButton1.setBounds(63, 133, 133, 28);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							JFileChooser chooser = new JFileChooser();
							chooser.setDialogType(JFileChooser.SAVE_DIALOG);
							
							FileFilter filter = FileFilterMaker.createFileFilter("Excel Files Only",true,new String[]{"xls"});
						  	chooser.setFileFilter(filter);
						  	
						  	int returnVal = chooser.showSaveDialog(MonthlySalesForm.this);
						    if(returnVal == JFileChooser.APPROVE_OPTION) {
						    	String month = monthNums.get(jComboBox1.getSelectedIndex());
						    	String year = jComboBox2.getSelectedItem().toString();
						    	boolean success = reports.MonthlySales.generate(chooser.getSelectedFile().getAbsolutePath()+".xls", db, storeCode, year,month);
						    	
						    	if(success){
						    	   JOptionPane.showMessageDialog(null, 
											"The report is saved.", 
											"Saved",JOptionPane.INFORMATION_MESSAGE);
						       }
						       else{
						    	   JOptionPane.showMessageDialog(null, 
											"Cannot save file", 
											"Error",JOptionPane.ERROR_MESSAGE);
						       }
						    }
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Close");
					jButton2.setBounds(210, 133, 63, 28);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							MonthlySalesForm.this.dispose();
						}
					});
				}
			}
			this.setSize(400, 209);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	private AbstractAction getMonthlySalesFormLabelAbstractAction() {
		AbstractAction monthlySalesFormLabelAction = new AbstractAction("Monthly Sales Report", null) {
			
			public void actionPerformed(ActionEvent evt) {
				MonthlySalesForm.this.dispose();
			}
		};
		return monthlySalesFormLabelAction;
	}

}

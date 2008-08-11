package forms.lookup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.TableUtility;
import bus.ClerkService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import forms.AboutDialog;
import forms.invoice.InvoicePanel;

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
@SuppressWarnings("serial")
public class ClerkLookUp extends javax.swing.JDialog {
	private JLabel clerkLookUpLabel;
	private JLabel jLabel2;
	private JButton jButton2;
	private JTable jTable1;
	private JButton jButton3;
	private JScrollPane jScrollPane1;
	private JTextField jTextField1;
	private static Object invoker;
	private static ClerkLookUp clerkLookUp; 
	private static String fieldName;
	
	@SuppressWarnings("static-access")
	private ClerkLookUp(JFrame frame, Object invoker, String fieldName) {
		super(frame);
		this.invoker = invoker;
		this.fieldName = fieldName;
		initGUI();
	}
	
	public static ClerkLookUp getClerkLookUp(JFrame frame, Object invoker, String fieldName){
		if(clerkLookUp == null){
			clerkLookUp = new ClerkLookUp(frame,invoker, fieldName); 
		}
		else{
			if(!invoker.equals(getInvoker()) || !fieldName.equals(getFieldName())){
				clerkLookUp = null;
				clerkLookUp = new ClerkLookUp(frame,invoker, fieldName);
			}
		}
		return clerkLookUp;
	}
	
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setTitle("Search Clerk");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setModal(true);
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("OK");
				jButton2.setPreferredSize(new java.awt.Dimension(61, 27));
				jButton2.setBounds(124, 392, 61, 27);
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						String selected = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
						if(invoker instanceof InvoicePanel){
						
							InvoicePanel invoicePanel = ((InvoicePanel)invoker);
							if(fieldName.equals("Clerk")){
//								invoicePanel.setClerkID(selected);
							}
							else if(fieldName.equals("Assistant")){
								invoicePanel.setAssistantID(selected);
							}
						}
						
						clerkLookUp.setVisible(false);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(384, 282));
				jScrollPane1.setBounds(16, 86, 384, 282);
				{
					TableModel jTable1Model = new DefaultTableModel(
						new String[][] { },
						new String[] { "Clerk ID", "Clerk Last Name","Clerk First Name" });
					jTable1 = new JTable(){
						@Override
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
				}
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setPreferredSize(new java.awt.Dimension(383, 27));
				jTextField1.setBounds(18, 53, 383, 27);
				jTextField1.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent evt) {
						ResultSet rs = ClerkService.filterClerk(jTextField1.getText());
						TableUtility.fillTable(jTable1, rs, new String[]{"Clerk ID", "Clerk First Name","Clerk Last Name"});
					}
				});

			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Clerk ID/Name");
				jLabel2.setPreferredSize(new java.awt.Dimension(105, 19));
				jLabel2.setBounds(18, 34, 105, 19);
			}
			{
				clerkLookUpLabel = new JLabel();
				getContentPane().add(clerkLookUpLabel);
				clerkLookUpLabel.setText("Clerk Search");
				clerkLookUpLabel.setPreferredSize(new java.awt.Dimension(383, 28));
				clerkLookUpLabel.setFont(new java.awt.Font("Tahoma",0,18));
				clerkLookUpLabel.setBounds(15, 10, 383, 28);
				clerkLookUpLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "clerkLookUpLabel");
				clerkLookUpLabel.getActionMap().put("clerkLookUpLabel",getClerkLookUpLabelAbstractAction() );
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("Cancel");
				jButton3.setPreferredSize(new java.awt.Dimension(75, 26));
				jButton3.setBounds(206, 393, 75, 26);
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						clerkLookUp.setVisible(false);
					}
				});
			}
			this.setSize(428, 474);
			
			ResultSet rs = ClerkService.getAllClerks();
			TableUtility.fillTable(jTable1, rs, new String[]{"Clerk ID", "Clerk First Name","Clerk Last Name"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AbstractAction getClerkLookUpLabelAbstractAction() {
		AbstractAction clerkLookUpLabelAction = new AbstractAction("Clerk Search", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ClerkLookUp.this.dispose();
			}
		};
		return clerkLookUpLabelAction;
	}

	private static Object getInvoker() {
		return invoker;
	}

	public  static String getFieldName() {
		return fieldName;
	}

	

}

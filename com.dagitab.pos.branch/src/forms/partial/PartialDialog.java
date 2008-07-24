package forms.partial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import util.TableUtility;
import bus.InvoiceItemService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import connection.LogHandler;
import domain.Invoice;
import forms.MainWindow;

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
public class PartialDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JLabel jLabel4;
	private JScrollPane jScrollPane2;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JButton jButton1;
	private JLabel jLabel15;
	private JLabel jLabel16;
	private JTextField jTextField6;
	private JTextField timeTextField;
	private JLabel jLabel5;
	private JButton jButton5;
	private JTextField jTextField5;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel14;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JTextField jTextField2;
	private JTextField dateTextField;
	private MainWindow form;
	private String orNum;
	private int paymentSize;
	private Vector<Vector<String>> paymentItems;
	private LogHandler cachewriter;
	private String clerkNo;
	private Double totalAmount;
	private JTable itemTable;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Partial inst = new Partial(frame);
//		inst.setVisible(true);
	}
	
	public PartialDialog(MainWindow frame,Invoice invoice) {
		super(frame);
		this.form = frame;
		orNum = invoice.getOrNo().toString();
		totalAmount = 0.0d;
		initGUI();
		refreshItemTable();
//		this.paymentSize = jTable2.getRowCount();
		jTextField2.setText(orNum);
		cachewriter = new LogHandler();
		
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			totalAmount = 0.0d;
			
			this.setTitle("Partial Transaction");
			this.setModal(true);
			{
				jButton5 = new JButton();
				getContentPane().add(jButton5, new AnchorConstraint(897, 825, 948, 684, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton5.setText("Cancel");
				jButton5.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PartialDialog.this.dispose();
					}
				});
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4, new AnchorConstraint(897, 649, 948, 491, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton4.setText("Process");
				jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				jButton4.setPreferredSize(new java.awt.Dimension(126, 28));
				jButton4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(782, 604, 833, 534, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Add");
				jButton1.setPreferredSize(new java.awt.Dimension(56, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			{
				jPanel2 = new JPanel();
				AnchorLayout jPanel2Layout = new AnchorLayout();
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, new AnchorConstraint(468, 290, 948, 26, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel2.setPreferredSize(new java.awt.Dimension(210, 259));
				jPanel2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel2.setBackground(new java.awt.Color(255,128,255));
				{
					jTextField3 = new JTextField();
					jPanel2.add(jTextField3, new AnchorConstraint(327, 975, 408, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField3.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField3.setEditable(false);
					jTextField3.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField3.setText("0.00");
				}
				{
					jLabel14 = new JLabel();
					jPanel2.add(jLabel14, new AnchorConstraint(29, 818, 137, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel14.setText("Amount Due");
					jLabel14.setFont(new java.awt.Font("Tahoma",1,22));
					jLabel14.setPreferredSize(new java.awt.Dimension(175, 28));
				}
				{
					jLabel10 = new JLabel();
					jPanel2.add(jLabel10, new AnchorConstraint(300, 504, 408, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel10.setText("Sub Total");
					jLabel10.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel10.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel11 = new JLabel();
					jPanel2.add(jLabel11, new AnchorConstraint(436, 504, 544, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel11.setText("12% VAT");
					ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
					if(rs.next()){
						jLabel11.setText(rs.getString("VAT")+"% VAT");
					}
					jLabel11.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel11.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel12 = new JLabel();
					jPanel2.add(jLabel12, new AnchorConstraint(598, 504, 707, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel12.setText("Total Payment");
					jLabel12.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel12.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel13 = new JLabel();
					jPanel2.add(jLabel13, new AnchorConstraint(734, 504, 843, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel13.setText("Change");
					jLabel13.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel13.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel4 = new JLabel();
					jPanel2.add(jLabel4, new AnchorConstraint(137, 818, 246, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText(totalAmount.toString());
					jLabel4.setFont(new java.awt.Font("Tahoma",1,22));
					jLabel4.setPreferredSize(new java.awt.Dimension(175, 28));
				}
				{
					jTextField4 = new JTextField();
					jPanel2.add(jTextField4, new AnchorConstraint(436, 975, 517, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField4.setEditable(false);
					jTextField4.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField4.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField4.setText("0.00");
				}
				{
					jTextField5 = new JTextField();
					jPanel2.add(jTextField5, new AnchorConstraint(598, 975, 680, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField5.setEditable(false);
					jTextField5.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField5.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField5.setText("0.00");
				}
				{
					jTextField6 = new JTextField();
					jPanel2.add(jTextField6, new AnchorConstraint(734, 975, 815, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField6.setEditable(false);
					jTextField6.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField6.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField6.setText("0.00");
				}
			}
			{
				jPanel1 = new JPanel();
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, new AnchorConstraint(117, 290, 442, 26, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel1.setPreferredSize(new java.awt.Dimension(210, 175));
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setBackground(new java.awt.Color(164,222,251));
				{
					jTextField2 = new JTextField();
					jPanel1.add(jTextField2, new AnchorConstraint(202, 943, 322, 410, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField2.setEditable(false);
					jTextField2.setText(orNum);
					jTextField2.setPreferredSize(new java.awt.Dimension(119, 21));
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3, new AnchorConstraint(482, 400, 602, 35, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Date");
					jLabel3.setPreferredSize(new java.awt.Dimension(77, 21));
					jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2, new AnchorConstraint(202, 598, 322, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("OR Number");
					jLabel2.setPreferredSize(new java.awt.Dimension(126, 21));
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					dateTextField = new JTextField();
					jPanel1.add(dateTextField, new AnchorConstraint(482, 943, 602, 410, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					dateTextField.setPreferredSize(new java.awt.Dimension(119, 21));
		//			jTextField1.setText(datetime[0]);
					dateTextField.setEditable(false);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5, new AnchorConstraint(642, 400, 762, 35, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel5.setText("Time");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel5.setPreferredSize(new java.awt.Dimension(77, 21));
				}
				{
					timeTextField = new JTextField();
					jPanel1.add(timeTextField, new AnchorConstraint(642, 940, 762, 409, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		//			jTextField7.setText(datetime[1]);
					timeTextField.setEditable(false);
					timeTextField.setPreferredSize(new java.awt.Dimension(112, 21));
				}
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(156, 974, 455, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(518, 161));
				{
					TableModel jTable1Model = new DefaultTableModel( new String[][] {  }, new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
						itemTable = new JTable();
						jScrollPane1.setViewportView(itemTable);
						itemTable.setModel(jTable1Model);
				}
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(18, 353, 89, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Process Partial Transaction");
				jLabel1.setPreferredSize(new java.awt.Dimension(245, 28));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
			}
			{
				jLabel16 = new JLabel();
				getContentPane().add(jLabel16, new AnchorConstraint(104, 439, 156, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel16.setText("Items");
				jLabel16.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				jLabel16.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel16.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jLabel15 = new JLabel();
				getContentPane().add(jLabel15, new AnchorConstraint(468, 465, 520, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel15.setText("Payments");
				jLabel15.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/payments.png")));
				jLabel15.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel15.setPreferredSize(new java.awt.Dimension(112, 28));
			}
			{
				jScrollPane2 = new JScrollPane();
				getContentPane().add(jScrollPane2, new AnchorConstraint(520, 974, 767, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane2.setPreferredSize(new java.awt.Dimension(518, 133));
				{
					
				}
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(780, 693, 832, 623, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Edit");
				jButton2.setPreferredSize(new java.awt.Dimension(56, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3, new AnchorConstraint(780, 807, 832, 711, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton3.setText("Delete");
				jButton3.setPreferredSize(new java.awt.Dimension(77, 28));
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			this.setSize(806, 573);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void refreshItemTable(){
		ResultSet rs = InvoiceItemService.fetchPartialInvoiceItem(orNum);
		TableUtility.fillTable(itemTable, rs, new String[]{"Product Code", "Product Name", "Quantity", "Current Price", "Selling Price", "Deferred", "Disc Code", "Extension"});
	}
	
	

}

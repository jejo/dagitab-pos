package forms.deferred;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import util.TableUtility;
import bus.InvoiceItemService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.InvoiceItem;
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
@SuppressWarnings("serial")
public class DeferredPanel extends javax.swing.JPanel {

	private JButton refreshButton;
	private JButton processButton;
	private JScrollPane deferredScrollPane;
	private JTable deferredTable;
	private JLabel jLabel18;
	private MainWindow mainWindow;

	public DeferredPanel(MainWindow mainWindow) {
		super(); 
		setMainWindow(mainWindow);
		initGUI();
		refreshDeferredTable();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(692, 390));
			AnchorLayout jPanel4Layout = new AnchorLayout();
			this.setLayout(jPanel4Layout);
			this.setBackground(new java.awt.Color(255, 255, 255));
			this.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			{
				refreshButton = new JButton();
				this.add(refreshButton, new AnchorConstraint(795, 136, 851, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				refreshButton.setText("Refresh");
				refreshButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/search2.png")));
				refreshButton.setPreferredSize(new java.awt.Dimension(48, 17));
				refreshButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						refreshDeferredTable();
					}
				});
			}
			{
				processButton = new JButton();
				this.add(processButton, new AnchorConstraint(794,966,851,812,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
				processButton.setText("Process");
				processButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				processButton.setPreferredSize(new java.awt.Dimension(133,28));
				processButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try{
							InvoiceItem invoiceItem = new InvoiceItem();
							invoiceItem.setOrNo(Long.parseLong(deferredTable.getValueAt(deferredTable.getSelectedRow(), 0).toString()));
							invoiceItem.setProductCode(deferredTable.getValueAt(deferredTable.getSelectedRow(), 2).toString());
							int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to process this transaction?", "Deferred Prompt", JOptionPane.INFORMATION_MESSAGE);
							if(result == 0){ //yes
								int success = InvoiceItemService.getInstance().processDeferredItem(invoiceItem.getOrNo(), invoiceItem.getProductCode(), Main.getStoreCode());
								if(success > 0){
									JOptionPane.showMessageDialog(null, "Successfully processed deferred item.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
									refreshDeferredTable();
								}
								else{
									JOptionPane.showMessageDialog(null, "Cannot process deferred item. Please try again later.", "Prompt", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						catch(ArrayIndexOutOfBoundsException e){
							JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
			}
			{
				deferredScrollPane = new JScrollPane();
				this.add(deferredScrollPane, new AnchorConstraint(115, 966, 781, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deferredScrollPane.setPreferredSize(new java.awt.Dimension(380, 200));
				deferredScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel jTable3Model = new DefaultTableModel(new String[][] {}, new String[] { "OR Number", "Invoice Number", "Product Code", "Product Name", "Quantity","Transaction Date & Time" });
					deferredTable = new JTable();
					deferredScrollPane.setViewportView(deferredTable);
					deferredTable.setModel(jTable3Model);
				}
			}
			{
				jLabel18 = new JLabel();
				this.add(jLabel18, new AnchorConstraint(15, 773, 115, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel18.setText("Manage Deferred Products");
				jLabel18.setPreferredSize(new java.awt.Dimension(303, 30));
				jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshDeferredTable() {
		ResultSet rs = InvoiceItemService.getInstance().fetchAllDeferredInvoiceItems();
		TableUtility.fillTable(deferredTable, rs, new String[]{"OR Number", "Invoice No", "Product Code", "Product Name", "Quantity", "Transaction Date & Time"});
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
}

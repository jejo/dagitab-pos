package forms.returned;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractAction;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.TableUtility;

import bus.InvoiceItemService;
import bus.ReturnReasonService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Invoice;
import domain.InvoiceItem;
import domain.ReturnReason;

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
public class ReturnedItemsDialog extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JLabel descriptionLabel;
	private AbstractAction returnItemAction;
	private JLabel quantityLabel;
	private JTable itemTable;
	private JComboBox returnReasonComboBox;
	private JLabel returnReasonLabel;
	private JButton returnItemCancelButton;
	private JTextField quantityTextField;
	private JButton returnItemProcessButton;
	private JScrollPane itemTableScrollPane;
	private JTextField orNoTextField;
	private JLabel orNoLabel;
	private JLabel itemsLabel;
	private Invoice invoice;
	private Object invoker;
	

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		
	}
	
	public ReturnedItemsDialog(MainWindow frame) {
		super(frame);
		initGUI();
	}
	
	public void setInvoice(Invoice invoice){
		this.invoice = invoice;
	}
	
	public void setInvoker(Object invoker){
		this.invoker = invoker;
	}
	
	public void init(){
		orNoTextField.setText(invoice.getOrNo().toString());
		List<ReturnReason> listReturnReason = ReturnReasonService.getAllReturnReasons();
		String[] returnReasons = new String[listReturnReason.size()];
		for(int i =0; i< listReturnReason.size(); i++){
			returnReasons[i] = listReturnReason.get(i).getName();
		}
		ComboBoxModel returnReasonModel = new DefaultComboBoxModel(returnReasons);
		returnReasonComboBox.setModel(returnReasonModel);
		TableUtility.fillTable(itemTable, InvoiceItemService.fetchInvoiceItem(invoice.getOrNo().toString()), new String[]{"Product Code", "Product Name","Quantity","Price Sold","Current Price","Deferred","Disc Code","Extension"} );
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Transaction Items");
			this.setModal(true);
			{
				ComboBoxModel jComboBox1Model = new DefaultComboBoxModel();
				returnReasonComboBox = new JComboBox();
				getContentPane().add(returnReasonComboBox, new AnchorConstraint(751, 479, 809, 228, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				returnReasonComboBox.setModel(jComboBox1Model);
				returnReasonComboBox.setPreferredSize(new java.awt.Dimension(158, 28));
			}
			{
				returnItemCancelButton = new JButton();
				getContentPane().add(returnItemCancelButton, new AnchorConstraint(885, 671, 943, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				returnItemCancelButton.setText("Cancel");
				returnItemCancelButton.setPreferredSize(new java.awt.Dimension(98, 28));
				returnItemCancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ReturnedItemsDialog.this.dispose();
					}
				});
			}
			{
				quantityTextField = new JTextField();
				getContentPane().add(quantityTextField, new AnchorConstraint(753, 967, 811, 843, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				quantityTextField.setPreferredSize(new java.awt.Dimension(78, 28));
			}
			{
				returnItemProcessButton = new JButton();
				getContentPane().add(returnItemProcessButton, new AnchorConstraint(885, 495, 943, 319, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				returnItemProcessButton.setText("Return Item");
				returnItemProcessButton.setPreferredSize(new java.awt.Dimension(112, 28));
				returnItemProcessButton.setAction(getReturnItemAction());
				returnItemProcessButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			{
				itemTableScrollPane = new JScrollPane();
				getContentPane().add(itemTableScrollPane, new AnchorConstraint(261, 967, 740, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				itemTableScrollPane.setPreferredSize(new java.awt.Dimension(595, 231));
				{
					
					TableModel itemTableModel = new DefaultTableModel(new String[][]{},new String[] { "Product Code", "Product Name","Quantity","Price Sold","Current Price","Deferred","Disc Code","Extension" });
					itemTable = new JTable(){
						@Override
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
					itemTableScrollPane.setViewportView(itemTable);
					itemTable.setModel(itemTableModel);
				}
			}
			{
				orNoTextField = new JTextField();
				getContentPane().add(orNoTextField, new AnchorConstraint(113, 382, 154, 150, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				orNoTextField.setPreferredSize(new java.awt.Dimension(146, 20));
				orNoTextField.setText("");
				orNoTextField.setEditable(false);
			}
			{
				orNoLabel = new JLabel();
				getContentPane().add(orNoLabel, new AnchorConstraint(92, 150, 179, 29, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				orNoLabel.setText("OR Number");
				orNoLabel.setPreferredSize(new java.awt.Dimension(76, 42));
				orNoLabel.setFont(new java.awt.Font("Tahoma",1,11));
			}
			{
				descriptionLabel = new JLabel();
				getContentPane().add(descriptionLabel, new AnchorConstraint(48, 968, 94, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				descriptionLabel.setText("View items of the transaction indicated by the OR number. You may choose the item which will be returned.");
				descriptionLabel.setPreferredSize(new java.awt.Dimension(602, 22));
			}
			{
				titleLabel = new JLabel();
				getContentPane().add(titleLabel, new AnchorConstraint(16, 506, 63, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				titleLabel.setText("Transaction Items");
				titleLabel.setPreferredSize(new java.awt.Dimension(315, 21));
				titleLabel.setFont(new java.awt.Font("Tahoma",0,18));
			}
			{
				itemsLabel = new JLabel();
				getContentPane().add(itemsLabel, new AnchorConstraint(179, 350, 281, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				itemsLabel.setText("Items");
				itemsLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				itemsLabel.setFont(new java.awt.Font("Tahoma",1,14));
				itemsLabel.setPreferredSize(new java.awt.Dimension(200, 49));
			}
			{
				quantityLabel = new JLabel();
				getContentPane().add(quantityLabel, new AnchorConstraint(755, 837, 813, 600, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				quantityLabel.setText("Quantity to be Returned:");
				quantityLabel.setFont(new java.awt.Font("Tahoma",1,11));
				quantityLabel.setPreferredSize(new java.awt.Dimension(149, 28));
			}
			{
				returnReasonLabel = new JLabel();
				getContentPane().add(returnReasonLabel, new AnchorConstraint(753, 209, 811, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				returnReasonLabel.setText("Reason For Return:");
				returnReasonLabel.setFont(new java.awt.Font("Tahoma",1,11));
				returnReasonLabel.setPreferredSize(new java.awt.Dimension(111, 28));
			}
			this.setSize(645, 517);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAction getReturnItemAction() {
		if(returnItemAction == null) {
			returnItemAction = new AbstractAction("Return Item", null) {
				public void actionPerformed(ActionEvent evt) {
					if(invoker instanceof ReturnedPanel){
						ReturnedPanel returnedPanel = (ReturnedPanel) invoker;
						String reason = returnReasonComboBox.getSelectedItem().toString();
						InvoiceItem invoiceItem = new InvoiceItem();
						invoiceItem.setProductCode(itemTable.getValueAt(itemTable.getSelectedRow(), 0).toString());
						invoiceItem.setQuantity(Integer.parseInt(quantityTextField.getText()));
						invoiceItem.setSellPrice(Double.parseDouble(itemTable.getValueAt(itemTable.getSelectedRow(), 3).toString()));
						invoiceItem.setIsDeferred(Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 5).toString()));
						invoiceItem.setDiscountCode(Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 6).toString()));
						returnedPanel.addToReturnItemTable(invoiceItem, reason);
					}
				}
			};
		}
		return returnItemAction;
	}
}
package com.dagitab.pos.forms.returned;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;


import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.bus.InvoiceItemService;
import com.dagitab.pos.bus.ReturnReasonService;
import com.dagitab.pos.domain.Invoice;
import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.domain.ReturnReason;
import com.dagitab.pos.forms.MainWindow;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.TableUtility;
import com.dagitab.pos.util.Validator;


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
	private JLabel returnedItemsLabel;
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
	private String action;
	private static Logger logger = Logger.getLogger(ReturnedItemsDialog.class);

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		
	}
	
	public ReturnedItemsDialog(MainWindow frame) {
		super(frame);
		initGUI();
	}
	

	public void setAction(String action) {
		this.action = action;
	}
	
	public void setInvoice(Invoice invoice){
		this.invoice = invoice;
	}
	
	public void setInvoker(Object invoker){
		this.invoker = invoker;
	}
	
	public void setReturnReason(String reason){
		returnReasonComboBox.setSelectedItem(reason);
	}
	
	public void setReturnedQuantity(String quantity){
		quantityTextField.setText(quantity);
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
		if(action.equals("add")){
//			TableUtility.fillTable(itemTable, InvoiceItemService.getInstance().fetchDiscountedInvoiceItem(invoice.getOrNo().toString()), new String[]{"Product Code", "Product Name","Quantity","Price Sold","Current Price","Deferred","Disc Code","Extension"} );
			
			
			//FIX FOR Rounding off Price Sold Field 
			DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
			ResultSet rs = InvoiceItemService.getInstance().fetchDiscountedInvoiceItem(invoice.getOrNo().toString());
			try {
				while(rs.next()){
					String[] rowValues = new String[8];
					for(int i = 0; i<8; i++){
						rowValues[i]= rs.getString(i+1);
						if(i == 3){ //price sold
							rowValues[i] = String.format("%.2f", Double.valueOf(rowValues[i]));
						}
						if(i==7){
							rowValues[i] = String.format("%.2f", rs.getInt(3)*Double.valueOf(rowValues[3]));
						}
					}
					model.addRow(rowValues);
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		else{
			
//			TableUtility.fillTable(itemTable, InvoiceItemService.getInstance().getDiscountedInvoiceItem(invoice.getOrNo(), action), new String[]{"Product Code", "Product Name","Quantity","Price Sold","Current Price","Deferred","Disc Code","Extension"});
			
			//FIX FOR Rounding off Price Sold Field 
			DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
			ResultSet rs = InvoiceItemService.getInstance().fetchDiscountedInvoiceItem(invoice.getOrNo().toString());
			try {
				while(rs.next()){
					String[] rowValues = new String[8];
					for(int i = 0; i<8; i++){
						rowValues[i]= rs.getString(i+1);
						if(i == 3){ //price sold
							rowValues[i] = String.format("%.2f", Double.valueOf(rowValues[i]));
						}
						if(i==7){
							rowValues[i] = String.format("%.2f", rs.getInt(3)*Double.valueOf(rowValues[3]));
						}
					}
					model.addRow(rowValues);
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		
		if(!action.equals("add")){
			Integer index = getItemIndex(action);
			if(index != null){
				itemTable.getSelectionModel().setSelectionInterval(index, index);
			}
			
		}
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
					itemTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0), "itemTable");
					itemTable.getActionMap().put("itemTable",getTabItemScrollPaneAction() );
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
				returnedItemsLabel = new JLabel();
				getContentPane().add(returnedItemsLabel, new AnchorConstraint(16, 506, 63, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				returnedItemsLabel.setText("Transaction Items");
				returnedItemsLabel.setPreferredSize(new java.awt.Dimension(315, 21));
				returnedItemsLabel.setFont(new java.awt.Font("Tahoma",0,18));
				returnedItemsLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "returnedItemsLabel");
				returnedItemsLabel.getActionMap().put("returnedItemsLabel",getReturnedItemsLabelAbstractAction() );
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
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private AbstractAction getReturnItemAction() {
		if(returnItemAction == null) {
			returnItemAction = new AbstractAction("Return Item", null) {
				public void actionPerformed(ActionEvent evt) {
					if(invoker instanceof ReturnedPanel){
						
						if(action.equals("add")){
							//TODO: validate if theres the same return item already
							try{
								
								if(!Validator.isEmpty("Quantity", quantityTextField.getText()) && Validator.isNumeric(quantityTextField.getText())){
									if(Integer.parseInt(quantityTextField.getText()) > 0){
										ReturnedPanel returnedPanel = (ReturnedPanel) invoker;
										String reason = returnReasonComboBox.getSelectedItem().toString();
										InvoiceItem invoiceItem = new InvoiceItem();
										int quantity = Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 2).toString());
										invoiceItem.setProductCode(itemTable.getValueAt(itemTable.getSelectedRow(), 0).toString());
										invoiceItem.setQuantity(Integer.parseInt(quantityTextField.getText()));
										invoiceItem.setSellPrice(Double.parseDouble(itemTable.getValueAt(itemTable.getSelectedRow(), 3).toString()));
										invoiceItem.setIsDeferred(Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 5).toString()));
										invoiceItem.setDiscountCode(Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 6).toString()));
										if(quantity >= Integer.parseInt(quantityTextField.getText())){
											if(returnedPanel.getReturnedItemRow(invoiceItem.getProductCode()) == null){
												returnedPanel.addToReturnItemTable(invoiceItem, reason);
											}
											else{
												JOptionPane.showMessageDialog(ReturnedItemsDialog.this,"Product already exists  in the table.","Prompt",JOptionPane.ERROR_MESSAGE);
											}
										}
										else{
											JOptionPane.showMessageDialog(ReturnedItemsDialog.this,"Returned Quantity is greater than quantity of selected product.","Prompt",JOptionPane.ERROR_MESSAGE);
										}
									}
									else{
										JOptionPane.showMessageDialog(ReturnedItemsDialog.this,"Returned Quantity is zero.","Prompt",JOptionPane.ERROR_MESSAGE);
									}
								}
								else{
									JOptionPane.showMessageDialog(null,"Product Quantity not valid.","Prompt",JOptionPane.ERROR_MESSAGE);
								}
								
								
							}
							catch(ArrayIndexOutOfBoundsException e){
								JOptionPane.showMessageDialog(null, "Please select item from the list.", "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}
						else{
							int quantity = Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 2).toString());
							if(quantity >= Integer.parseInt(quantityTextField.getText())){
								ReturnedPanel returnedPanel = (ReturnedPanel) invoker;
								String reason = returnReasonComboBox.getSelectedItem().toString();
								InvoiceItem invoiceItem = new InvoiceItem();
								invoiceItem.setProductCode(itemTable.getValueAt(itemTable.getSelectedRow(), 0).toString());
								invoiceItem.setQuantity(Integer.parseInt(quantityTextField.getText()));
								invoiceItem.setSellPrice(Double.parseDouble(itemTable.getValueAt(itemTable.getSelectedRow(), 3).toString()));
								invoiceItem.setIsDeferred(Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 5).toString()));
								invoiceItem.setDiscountCode(Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 6).toString()));
								logger.info("Reason: "+reason+ " Product Code: "+ action);
								returnedPanel.editReturnedItem(invoiceItem, reason, action);
							}
							else{
								JOptionPane.showMessageDialog(ReturnedItemsDialog.this,"Returned Quantity is greater than quantity of selected product.","Prompt",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			};
		}
		return returnItemAction;
	}
	
	private AbstractAction getReturnedItemsLabelAbstractAction() {
		AbstractAction returnedItemsLabelAction = new AbstractAction("Transaction Items", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ReturnedItemsDialog.this.dispose();
			}
		};
		return returnedItemsLabelAction;
	}
	
	private AbstractAction getTabItemScrollPaneAction(){
		AbstractAction tabItemsScrollPaneAction = new AbstractAction("Search Product", null) {
			
			public void actionPerformed(ActionEvent evt) {
				returnReasonComboBox.requestFocusInWindow();
			}
		};
		return tabItemsScrollPaneAction;
	}
	
	private Integer getItemIndex(String productCode){
		DefaultTableModel defaultTableModel  = (DefaultTableModel) itemTable.getModel();
		for(int i =0; i< defaultTableModel.getRowCount(); i++){
			String itemCode = defaultTableModel.getValueAt(i, 0).toString();
			if(productCode.equals(itemCode)){
				return i; 
			}
		}
		return null;
	}

}

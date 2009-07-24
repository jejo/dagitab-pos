package com.dagitab.pos.forms.pullouts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.dagitab.pos.bus.ProductService;
import com.dagitab.pos.bus.PullOutReasonService;
import com.dagitab.pos.bus.PullOutRequestItemService;
import com.dagitab.pos.bus.PullOutRequestService;
import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.domain.Product;
import com.dagitab.pos.forms.lookup.ProductDialog;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.TableUtility;


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
public class PullOutRequestDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	private JLabel pullOutRequestDialogLabel;
	private JTabbedPane pullOutTabbedPane;
	private JLabel pullOutRequestItemLabel;
	private AbstractAction deleteAbstractAction;
	private AbstractAction editAbstractAction;
	private AbstractAction addItemAbstractAction;
	private JTable newPullOutRequestItemTable;
	private JTable pullOutRequestItemTable;
	private JTable pullOutRequestTable;
	private AbstractAction closeAbstractAction;
	private JButton createButton;
	private JLabel pullOutReasonLabel;
	private JComboBox pullOutReasonComboBox;
	private JButton deleteButton;
	private JScrollPane newPullOutRequestItemScrollPane;
	private JButton editButton;
	private JButton addButton;
	private JLabel newPullOutRequestLabel;
	private JScrollPane pullOutRequestItemScrollPane;
	private JLabel pullOutRequestLabel;
	private JScrollPane pullOutRequestScrollPane;
	private JPanel newPullOutPanel;
	private JPanel pullOutListPanel;
	private JButton closeButton;
	
	private PullOutRequestDialog pullOutRequestDialog;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				PullOutRequestDialog inst = new PullOutRequestDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public PullOutRequestDialog(JFrame frame) {
		super(frame);
		this.pullOutRequestDialog = this; 
		initGUI();
		refreshPullOutRequestTable();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Pull Out Request");
				{
					pullOutRequestDialogLabel = new JLabel();
					getContentPane().add(pullOutRequestDialogLabel);
					pullOutRequestDialogLabel.setText("Pull Outs");
					pullOutRequestDialogLabel.setBounds(12, 12, 125, 16);
					pullOutRequestDialogLabel.setFont(new java.awt.Font("Tahoma",0,18));
					pullOutRequestDialogLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "pullOutRequestDialogLabel");
					pullOutRequestDialogLabel.getActionMap().put("pullOutRequestDialogLabel",getPullOutRequestDialogLabelAbstractAction() );
				}
				{
					pullOutTabbedPane = new JTabbedPane();
					getContentPane().add(pullOutTabbedPane);
					pullOutTabbedPane.setBounds(12, 34, 729, 365);
					{
						pullOutListPanel = new JPanel();
						pullOutTabbedPane.addTab("Pull Out Request List", null, pullOutListPanel, null);
						pullOutListPanel.setLayout(null);
						pullOutListPanel.setBackground(new java.awt.Color(255,255,255));
						{
							pullOutRequestScrollPane = new JScrollPane();
							pullOutListPanel.add(pullOutRequestScrollPane);
							pullOutRequestScrollPane.setBounds(12, 34, 702, 127);
							pullOutRequestScrollPane.setViewportView(getPullOutRequestTable());
						}
						{
							pullOutRequestLabel = new JLabel();
							pullOutListPanel.add(pullOutRequestLabel);
							pullOutRequestLabel.setText("Pull Out Requests");
							pullOutRequestLabel.setBounds(12, 12, 102, 16);
						}
						{
							pullOutRequestItemScrollPane = new JScrollPane();
							pullOutListPanel.add(pullOutRequestItemScrollPane);
							pullOutRequestItemScrollPane.setBounds(12, 188, 700, 137);
							pullOutRequestItemScrollPane.setViewportView(getPullOutRequestItemTable());
						}
						{
							pullOutRequestItemLabel = new JLabel();
							pullOutListPanel.add(pullOutRequestItemLabel);
							pullOutRequestItemLabel.setText("Pull Out Request Items");
							pullOutRequestItemLabel.setBounds(12, 167, 120, 16);
						}
					}
					{
						newPullOutPanel = new JPanel();
						pullOutTabbedPane.addTab("New Pull Out Request", null, newPullOutPanel, null);
						newPullOutPanel.setBackground(new java.awt.Color(255,255,255));
						newPullOutPanel.setLayout(null);
						{
							newPullOutRequestItemScrollPane = new JScrollPane();
							newPullOutPanel.add(newPullOutRequestItemScrollPane);
							newPullOutRequestItemScrollPane.setBounds(12, 30, 700, 134);
							newPullOutRequestItemScrollPane.setViewportView(getNewPullOutRequestItemTable());
						}
						{
							newPullOutRequestLabel = new JLabel();
							newPullOutPanel.add(newPullOutRequestLabel);
							newPullOutRequestLabel.setText("Pull Out Request Items");
							newPullOutRequestLabel.setBounds(12, 8, 133, 16);
						}
						{
							addButton = new JButton();
							newPullOutPanel.add(addButton);
							addButton.setText("Add");
							addButton.setBounds(233, 170, 77, 22);
							addButton.setAction(getAddItemAbstractAction());
						}
						{
							editButton = new JButton();
							newPullOutPanel.add(editButton);
							editButton.setText("Edit");
							editButton.setBounds(320, 170, 74, 22);
							editButton.setAction(getEditAbstractAction());
						}
						{
							deleteButton = new JButton();
							newPullOutPanel.add(deleteButton);
							deleteButton.setText("Delete");
							deleteButton.setBounds(404, 170, 95, 22);
							deleteButton.setAction(getDeleteAbstractAction());
							deleteButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "deleteButton");
							deleteButton.getActionMap().put("deleteButton",getDeleteAbstractAction() );
						}
						{
							ComboBoxModel pullOutReasonComboBoxModel = 
								new DefaultComboBoxModel( PullOutReasonService.fetchAllPullOutReasons() );
							pullOutReasonComboBox = new JComboBox();
							newPullOutPanel.add(pullOutReasonComboBox);
							pullOutReasonComboBox.setModel(pullOutReasonComboBoxModel);
							pullOutReasonComboBox.setBounds(102, 221, 138, 22);
						}
						{
							pullOutReasonLabel = new JLabel();
							newPullOutPanel.add(pullOutReasonLabel);
							pullOutReasonLabel.setText("Pull Out Reason");
							pullOutReasonLabel.setBounds(10, 224, 84, 16);
						}
						{
							createButton = new JButton();
							newPullOutPanel.add(createButton);
							createButton.setText("Create New Pull Out Request");
							createButton.setBounds(263, 273, 184, 26);
							createButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									createPullOutRequest();
								}
							});
						}
					}
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(331, 420, 72, 27);
					closeButton.setAction(getCloseAbstractAction());
					
					closeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							pullOutRequestDialog.setVisible(false);
						}
					});
				}
			}
			this.setSize(766, 505);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private AbstractAction getCloseAbstractAction() {
		if(closeAbstractAction == null) {
			closeAbstractAction = new AbstractAction("Close", null) {
				public void actionPerformed(ActionEvent evt) {
					PullOutRequestDialog.this.dispose();
				}
			};
		}
		return closeAbstractAction;
	}
	
	private JTable getPullOutRequestTable() {
		if(pullOutRequestTable == null) {
			TableModel pullOutRequestTableModel = 
				new DefaultTableModel(
						null,
						new String[] { "Request No", "Date","Clerk Issued","Reason" });
			pullOutRequestTable = new JTable() {
				@Override
				public boolean isCellEditable(
					int row,
					int column) {
					return false;
				}
			};
			pullOutRequestTable.addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e){
					 if(pullOutRequestTable.getSelectedRow() != -1) {
						//refresh
						 refreshPullOutRequestItemsTable((Long) pullOutRequestTable.getValueAt(pullOutRequestTable.getSelectedRow(), 0));
					 }
				 }
			});
			pullOutRequestTable.setModel(pullOutRequestTableModel);
		}
		return pullOutRequestTable;
	}
	
	private JTable getPullOutRequestItemTable() {
		if(pullOutRequestItemTable == null) {
			TableModel pullOutRequestItemTableModel = 
				new DefaultTableModel(
						null,
						new String[] { "Product Code", "Product Name","Quantity" });
			pullOutRequestItemTable = new JTable() {
				@Override
				public boolean isCellEditable(
					int row,
					int column) {
					return false;
				}
			};
			pullOutRequestItemTable.setModel(pullOutRequestItemTableModel);
		}
		return pullOutRequestItemTable;
	}
	
	private JTable getNewPullOutRequestItemTable() {
		TableModel newPullOutRequestItemTableModel = 
			new DefaultTableModel(
					null,
					new String[] { "Product Code", "Product Name", "Quantity" });
		newPullOutRequestItemTable = new JTable() {
			@Override
			public boolean isCellEditable(
				int row,
				int column) {
				return false;
			}
		};
		newPullOutRequestItemTable.setModel(newPullOutRequestItemTableModel);
		return newPullOutRequestItemTable;
	}
	
	private AbstractAction getAddItemAbstractAction() {
		if(addItemAbstractAction == null) {
			addItemAbstractAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),PullOutRequestDialog.this,"add");
					dialog.clearProductInformation();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return addItemAbstractAction;
	}
	
	private AbstractAction getEditAbstractAction() {
		if(editAbstractAction == null) {
			editAbstractAction = new AbstractAction("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png"))) {
				public void actionPerformed(ActionEvent evt) {
					try{
						String productCode = newPullOutRequestItemTable.getValueAt(newPullOutRequestItemTable.getSelectedRow(), 0).toString();
						String quantity = newPullOutRequestItemTable.getValueAt(newPullOutRequestItemTable.getSelectedRow(), 2).toString();
						
						ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),PullOutRequestDialog.this,productCode);
						dialog.setProductCode(productCode);
						dialog.setQuantity(quantity);
						
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
					catch(ArrayIndexOutOfBoundsException e){
						JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
					}
				}
			};
		}
		return editAbstractAction;
	}
	
	private AbstractAction getDeleteAbstractAction() {
		if(deleteAbstractAction == null) {
			deleteAbstractAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these selected items?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
					if(confirm == 0){
						DefaultTableModel model = (DefaultTableModel) newPullOutRequestItemTable.getModel();
						int[] selectedRows = newPullOutRequestItemTable.getSelectedRows();
						String[] productCodes = new String[selectedRows.length];
						for(int i=0; i<selectedRows.length; i++){
							productCodes[i] = newPullOutRequestItemTable.getValueAt(selectedRows[i], 0).toString();
						}
						for(String s: productCodes){
							int index = getInvoiceItemRow(s);
							model.removeRow(index);
						}
						
					}
				}
			};
		}
		return deleteAbstractAction;
	}
	
	public Integer getInvoiceItemRow(String prodCode){
		DefaultTableModel model = (DefaultTableModel) newPullOutRequestItemTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			if(model.getValueAt(i, 0).toString().equals(prodCode)){
				return i;
			}
		}
		return null;
	}
	
	public void addInvoiceItem(InvoiceItem invoiceItem){
		DefaultTableModel model = (DefaultTableModel) newPullOutRequestItemTable.getModel();
//		"Product Code", "Product Name","Quantity" 
		Product product = ProductService.getProductById(invoiceItem.getProductCode());
		model.addRow(new String[]{invoiceItem.getProductCode(),
								  product.getName(),
								  invoiceItem.getQuantity().toString()});
	}
	
	public void editInvoiceItem(InvoiceItem invoiceItem, String productCode){
		Product product = ProductService.getProductById(invoiceItem.getProductCode());
		int index = getInvoiceItemRow(productCode);
		DefaultTableModel model = (DefaultTableModel) newPullOutRequestItemTable.getModel();
		model.setValueAt(invoiceItem.getProductCode(), index, 0);
		model.setValueAt(product.getName(), index, 1);
		model.setValueAt(invoiceItem.getQuantity(), index, 2);
	}
	
	public void removeInvoiceItem(){
		DefaultTableModel model = (DefaultTableModel) newPullOutRequestItemTable.getModel();
		model.removeRow(newPullOutRequestItemTable.getSelectedRow());
		
	}

	public PullOutRequestDialog getPullOutRequestDialog() {
		return pullOutRequestDialog;
	}

	public void setPullOutRequestDialog(PullOutRequestDialog pullOutRequestDialog) {
		this.pullOutRequestDialog = pullOutRequestDialog;
	}

	public void refreshPullOutRequestTable() {
		ResultSet rs = PullOutRequestService.fetchAllPullOutRequests();
		TableUtility.fillTable(pullOutRequestTable, rs, new String[]{"Request No", "Date","Clerk Issued","Reason"});
	}
	
	public void refreshPullOutRequestItemsTable(Long pullOutRequestId) {
		ResultSet rs =	PullOutRequestItemService.fetchPullOutRequestItems(pullOutRequestId); 
		TableUtility.fillTable(pullOutRequestItemTable, rs, new String[]{"Product Code", "Product Name","Quantity"});
	}
	
	public void clearNewPullOutRequestItemsTable() {
		newPullOutRequestItemScrollPane.setViewportView(getNewPullOutRequestItemTable());
	}
	
	public void createPullOutRequest() {
		PullOutRequestService.createPullOutRequest(pullOutReasonComboBox.getSelectedItem().toString());
		Long latestPullOutRequestId = PullOutRequestService.getLatestPullOutRequest();  
		for(int i = 0; i < newPullOutRequestItemTable.getRowCount(); i++) {
			PullOutRequestItemService.createPullOutRequestItem(latestPullOutRequestId, newPullOutRequestItemTable.getValueAt(i, 0).toString(), newPullOutRequestItemTable.getValueAt(i, 2).toString());
		}
		JOptionPane.showMessageDialog(null, "Pull Out Request: " + latestPullOutRequestId + " has been created.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
		refreshPullOutRequestTable();
		clearNewPullOutRequestItemsTable();
	}
	
	private AbstractAction getPullOutRequestDialogLabelAbstractAction() {
		AbstractAction pullOutLabelAction = new AbstractAction("Pull Outs", null) {
			
			public void actionPerformed(ActionEvent evt) {
				PullOutRequestDialog.this.dispose();
			}
		};
		return pullOutLabelAction;
	}
	
}

package com.dagitab.pos.forms.lookup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.dagitab.pos.bus.DiscountService;
import com.dagitab.pos.bus.ProductService;
import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.domain.Product;
import com.dagitab.pos.forms.pullouts.PullOutRequestDialog;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.TableUtility;
import com.dagitab.pos.util.Validator;
import com.dagtiab.pos.forms.invoice.InvoicePanel;
import com.dagtiab.pos.forms.returned.ReturnedPanel;



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
public class ProductDialog extends javax.swing.JDialog {
	private JLabel productDialogLabel;
	private JScrollPane productScrollPane;
	private JLabel productNameLabel;
	private JTextField productNameTextField;
	private JCheckBox deferredChk;
	private JButton jButton1;
	private JTable productTable;
	private JButton jButton2;
	private JLabel jLabel3;
	private JTextField quantityTxt;
	private JLabel jLabel2;
	private JComboBox discountCB;
	private JLabel jLabel1;
	private JTextField txtProduct;
	private static Object invoker;
	private static ProductDialog productDialog;
	private static String action;
	private static Logger logger = Logger.getLogger(ProductDialog.class);

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				JFrame frame = new JFrame();
//				ProductDialog inst = new ProductDialog(frame,null);
//				inst.setVisible(true);
//			}
//		});
	}
	
	@SuppressWarnings("static-access")
	private ProductDialog(JFrame frame, Object invoker, String action) {
		super(frame);
		this.invoker = invoker;
		this.action = action;
		initGUI();
		init();
	}
	public static ProductDialog getProductDialog(JFrame frame, Object invoker, String action){
		if(productDialog == null){
			productDialog = new ProductDialog(frame, invoker,action); 
		}
		else{
			if(!invoker.equals(getInvoker()) || !action.equals(getAction())){
				productDialog = null;
				productDialog = new ProductDialog(frame, invoker,action);
			}
			
		}
		return productDialog;
	}
	
	//special cases onRender
	private void init(){
		if(invoker instanceof PullOutRequestDialog) {
			deferredChk.setEnabled(false);
			discountCB.setEnabled(false);
		}
		
	}
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setModal(true);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					productDialogLabel = new JLabel();
					getContentPane().add(productDialogLabel);
					productDialogLabel.setText("Search Product");
					productDialogLabel.setBounds(12, 12, 164, 21);
					productDialogLabel.setFont(new java.awt.Font("Tahoma",0,18));
					productDialogLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "productDialogLabel");
					productDialogLabel.getActionMap().put("productDialogLabel",getproductDialogLabelAbstractAction() );
				}
				{
					txtProduct = new JTextField();
					getContentPane().add(txtProduct);
					txtProduct.setBounds(14, 52, 525, 22);
					txtProduct.addKeyListener(new KeyAdapter() {
						public void keyTyped(KeyEvent evt) {
							ResultSet rs = ProductService.filterProductById(txtProduct.getText());
							TableUtility.fillTable(productTable,rs, new String[]{ "Product Code","Product Name","Product Price"});
						}
					});
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Product Code ");
					jLabel1.setBounds(14, 33, 116, 16);
				}
				{
					productScrollPane = new JScrollPane();
					getContentPane().add(productScrollPane);
					productScrollPane.setBounds(15, 132, 523, 211);
					{
						TableModel jTable1Model = new DefaultTableModel(
								new String[][] { },
								new String[] { "Product Code", "Product Name","Product Price" });
						productTable = new JTable(){
							@Override
							public boolean isCellEditable(int row,int column) {
								return false;
							}
						};
						productScrollPane.setViewportView(productTable);
						productTable.setModel(jTable1Model);
						productTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0), "productTable");
						productTable.getActionMap().put("productTable",getTabProductScrollPaneAction() );
						
						
					}
				}
				{
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(DiscountService.getAllDiscounts());
					discountCB = new JComboBox();
					getContentPane().add(discountCB);
					discountCB.setModel(jComboBox1Model);
					discountCB.setBounds(12, 371, 249, 22);
					
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Discount");
					jLabel2.setBounds(12, 349, 47, 16);
				}
				{
					quantityTxt = new JTextField();
					getContentPane().add(quantityTxt);
					quantityTxt.setBounds(290, 371, 249, 22);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Quantity");
					jLabel3.setBounds(290, 349, 46, 16);
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("OK");
					jButton1.setBounds(215, 445, 49, 25);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
							String selected = "";
							try{
								selected = productTable.getValueAt(productTable.getSelectedRow(),0).toString();
							}
							catch(ArrayIndexOutOfBoundsException e){
								JOptionPane.showMessageDialog(ProductDialog.this,"Please select product from the list","Prompt",JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							if(!Validator.isEmpty("Product Item", selected) && 
							   !Validator.isEmpty("Quantity", quantityTxt.getText()) && 
							    Validator.isNumeric(quantityTxt.getText()) )
							{
							
							
								Product product = ProductService.getProductById(selected);
								String discountCode = discountCB.getSelectedItem().toString().split("-")[0].trim();
								Double discRate = DiscountService.getDiscRate(Integer.parseInt(discountCode.trim()));
								Double sellingPrice = product.getSellPrice() - (product.getSellPrice()*discRate);
									
								InvoiceItem invoiceItem = new InvoiceItem();
								invoiceItem.setCost(product.getCost());
								invoiceItem.setDiscountCode(Integer.parseInt(discountCode));
								invoiceItem.setIsDeferred(deferredChk.isSelected()?1:0);
								invoiceItem.setProductCode(selected);
								invoiceItem.setQuantity(Integer.parseInt(quantityTxt.getText()));
								invoiceItem.setSellPrice(sellingPrice);
								
								if(Integer.parseInt(quantityTxt.getText())>0){
									if(invoker instanceof InvoicePanel){
										logger.info("invoking...");
										InvoicePanel invoicePanel = (InvoicePanel)invoker;
										if(action.equals("add")){
											if(invoicePanel.getInvoiceItemRow(invoiceItem.getProductCode()) == null){
												invoicePanel.addInvoiceItem(invoiceItem);
												productDialog.setVisible(false);
											}
											else{
												JOptionPane.showMessageDialog(ProductDialog.this,"Product already exists  in the table.","Prompt",JOptionPane.ERROR_MESSAGE);
											}
										}
										else { //edit
											invoicePanel.editInvoiceItem(invoiceItem, action);
											productDialog.setVisible(false);
										}
									}
								
									else if(invoker instanceof ReturnedPanel) {
										ReturnedPanel returnedPanel = (ReturnedPanel)invoker;
										if(action.equals("add")){
											if(returnedPanel.getInvoiceItemRow(invoiceItem.getProductCode()) == null){
												returnedPanel.addInvoiceItem(invoiceItem);
												productDialog.setVisible(false);
											}
											else{
												JOptionPane.showMessageDialog(ProductDialog.this,"Product already exists  in the table.","Prompt",JOptionPane.ERROR_MESSAGE);
											}
										}
										else { //edit
											returnedPanel.editInvoiceItem(invoiceItem, action);
											productDialog.setVisible(false);
										}
									}
									
									else if(invoker instanceof PullOutRequestDialog) {
										PullOutRequestDialog pullOutRequestDialog = (PullOutRequestDialog)invoker;
										if(action.equals("add")){
											if(pullOutRequestDialog.getInvoiceItemRow(invoiceItem.getProductCode()) == null){
												pullOutRequestDialog.addInvoiceItem(invoiceItem);
												productDialog.setVisible(false);
											}
											else{
												JOptionPane.showMessageDialog(ProductDialog.this,"Product already exists  in the table.","Prompt",JOptionPane.ERROR_MESSAGE);
											}
										}
										else { //edit
											pullOutRequestDialog.editInvoiceItem(invoiceItem, action);
											productDialog.setVisible(false);
										}
									}
								}
								else{
									JOptionPane.showMessageDialog(ProductDialog.this,"Product Quantity not valid.","Prompt",JOptionPane.ERROR_MESSAGE);
								}
								
							}
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Cancel");
					jButton2.setBounds(280, 445, 66, 24);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							productDialog.setVisible(false);
						}
					});
				}
				{
					deferredChk = new JCheckBox();
					getContentPane().add(deferredChk);
					getContentPane().add(getProductNameTextField());
					getContentPane().add(getProductNameLabel());
					deferredChk.setText("Deferred");
					deferredChk.setBounds(12, 403, 85, 20);
					deferredChk.setBackground(new java.awt.Color(255,255,255));
				}

				TableUtility.fillTable(productTable, ProductService.getAllProducts(), new String[]{ "Product Code","Product Name","Product Price"});
			}
			this.setSize(567, 517);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	public static Object getInvoker() {
		return invoker;
	}

	public static String getAction() {
		return action;
	}
	
	public void setProductCode(String prodCode){
		txtProduct.setText(prodCode);
		ResultSet rs = ProductService.getByProductId(txtProduct.getText());
		TableUtility.fillTable(productTable,rs, new String[]{ "Product Code","Product Name","Product Price"});
		productTable.selectAll();
		txtProduct.setEditable(false);
		productNameTextField.setEditable(false);
	}
	
	public void setDeferredValue(String value){
		if(value.equals("Yes")){
			deferredChk.setSelected(true);
		}
		else{
			deferredChk.setSelected(false);
		}
	}
	
	public void setQuantity(String qty){
		quantityTxt.setText(qty);
	}
	
	public void setQuantityEditable(boolean editable){
		quantityTxt.setEditable(editable);
	}
	
	public void setDiscount(int discCode){
		DefaultComboBoxModel model = (DefaultComboBoxModel) discountCB.getModel();
		int index = 0;
		for(int i = 0; i<model.getSize(); i++){
			if(Integer.parseInt(model.getElementAt(i).toString().split("-")[0].trim())== discCode){
				index  = i;
				break;
			}
		}
		
		discountCB.setSelectedIndex(index);
	}
	
	public void clearProductInformation(){
		txtProduct.setText("");
		TableUtility.fillTable(productTable, ProductService.getAllProducts(), new String[]{ "Product Code","Product Name","Product Price"});
		discountCB.setSelectedIndex(0);
		quantityTxt.setText("");
		deferredChk.setSelected(false);
	}
	
	private AbstractAction getproductDialogLabelAbstractAction() {
		AbstractAction productDialogLabelAction = new AbstractAction("Search Product", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ProductDialog.this.dispose();
			}
		};
		return productDialogLabelAction;
	}
	
	private AbstractAction getTabProductScrollPaneAction(){
		AbstractAction tabProductScrollPaneAction = new AbstractAction("Search Product", null) {
			
			public void actionPerformed(ActionEvent evt) {
				discountCB.requestFocusInWindow();
			}
		};
		return tabProductScrollPaneAction;
	}
	
	private JTextField getProductNameTextField() {
		if(productNameTextField == null) {
			productNameTextField = new JTextField();
			productNameTextField.setBounds(14, 98, 525, 22);
			productNameTextField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent evt) {
					ResultSet rs = ProductService.filterProductByName(productNameTextField.getText());
					TableUtility.fillTable(productTable,rs, new String[]{ "Product Code","Product Name","Product Price"});
				}
			});
		}
		return productNameTextField;
	}
	
	private JLabel getProductNameLabel() {
		if(productNameLabel == null) {
			productNameLabel = new JLabel();
			productNameLabel.setText("Product Name");
			productNameLabel.setBounds(15, 77, 77, 16);
		}
		return productNameLabel;
	}

}

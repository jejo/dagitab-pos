package forms.lookup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.TableUtility;
import util.Validator;
import bus.DiscountService;
import bus.ProductService;
import domain.InvoiceItem;
import domain.Product;
import forms.invoice.InvoicePanel;
import forms.returned.ReturnedPanel;


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
	private JLabel lblProduct;
	private JScrollPane jScrollPane1;
	private JCheckBox deferredChk;
	private JButton jButton1;
	private JTable jTable1;
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
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setModal(true);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					lblProduct = new JLabel();
					getContentPane().add(lblProduct);
					lblProduct.setText("Search Product");
					lblProduct.setBounds(12, 12, 164, 21);
					lblProduct.setFont(new java.awt.Font("Tahoma",0,18));
				}
				{
					txtProduct = new JTextField();
					getContentPane().add(txtProduct);
					txtProduct.setBounds(14, 52, 525, 22);
					txtProduct.addKeyListener(new KeyAdapter() {
						public void keyTyped(KeyEvent evt) {
							ResultSet rs = ProductService.filterProducts(txtProduct.getText());
							TableUtility.fillTable(jTable1,rs, new String[]{ "Product Code","Product Name","Product Price"});
						}
					});
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Product Code / Name");
					jLabel1.setBounds(14, 33, 116, 16);
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1);
					jScrollPane1.setBounds(15, 80, 523, 235);
					{
						TableModel jTable1Model = new DefaultTableModel(
								new String[][] { },
								new String[] { "Product Code", "Product Name","Product Price" });
						jTable1 = new JTable();
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
					}
				}
				{
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(DiscountService.getAllDiscounts());
					discountCB = new JComboBox();
					getContentPane().add(discountCB);
					discountCB.setModel(jComboBox1Model);
					discountCB.setBounds(15, 340, 249, 22);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Discount");
					jLabel2.setBounds(15, 324, 47, 16);
				}
				{
					quantityTxt = new JTextField();
					getContentPane().add(quantityTxt);
					quantityTxt.setBounds(289, 340, 249, 22);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Quantity");
					jLabel3.setBounds(289, 324, 46, 16);
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("OK");
					jButton1.setBounds(215, 428, 49, 25);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
							String selected = "";
							try{
								selected = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
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
								String discountCode = discountCB.getSelectedItem().toString().split("-")[0];
								Double discRate = DiscountService.getDiscRate(Integer.parseInt(discountCode));
								Double sellingPrice = product.getSellPrice() - (product.getSellPrice()*discRate);
									
								InvoiceItem invoiceItem = new InvoiceItem();
								invoiceItem.setCost(product.getCost());
								invoiceItem.setDiscountCode(Integer.parseInt(discountCode));
								invoiceItem.setIsDeferred(deferredChk.isSelected()?1:0);
								invoiceItem.setProductCode(selected);
								invoiceItem.setQuantity(Integer.parseInt(quantityTxt.getText()));
								invoiceItem.setSellPrice(sellingPrice);
								
								if(invoker instanceof InvoicePanel){
									System.out.println("invoking...");
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
								
								if(invoker instanceof ReturnedPanel) {
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
							}
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Cancel");
					jButton2.setBounds(283, 428, 66, 24);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							productDialog.setVisible(false);
						}
					});
				}
				{
					deferredChk = new JCheckBox();
					getContentPane().add(deferredChk);
					deferredChk.setText("Deferred");
					deferredChk.setBounds(15, 381, 85, 20);
					deferredChk.setBackground(new java.awt.Color(255,255,255));
				}

				TableUtility.fillTable(jTable1, ProductService.getAllProducts(), new String[]{ "Product Code","Product Name","Product Price"});
			}
			this.setSize(567, 517);
		} catch (Exception e) {
			e.printStackTrace();
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
		ResultSet rs = ProductService.filterProducts(txtProduct.getText());
		TableUtility.fillTable(jTable1,rs, new String[]{ "Product Code","Product Name","Product Price"});
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
	
	public void setDiscount(int discCode){
		DefaultComboBoxModel model = (DefaultComboBoxModel) discountCB.getModel();
		int index = 0;
		for(int i = 0; i<model.getSize(); i++){
			if(Integer.parseInt(model.getElementAt(i).toString().split("-")[0])== discCode){
				index  = i;
				break;
			}
		}
		
		discountCB.setSelectedIndex(index);
	}
	
	public void clearProductInformation(){
		txtProduct.setText("");
		TableUtility.fillTable(jTable1, ProductService.getAllProducts(), new String[]{ "Product Code","Product Name","Product Price"});
		discountCB.setSelectedIndex(0);
		quantityTxt.setText("");
		deferredChk.setSelected(false);
	}

}

package forms.lookup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
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

import main.Main;
import util.StringUtility;
import bus.ProductService;
import domain.InvoiceItem;
import domain.Product;
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
public class FastAddition extends javax.swing.JDialog {
	private JButton okButton;
	private JButton deleteButton;
	private JTable itemListTable;
	private JScrollPane itemListScrollPane;
	private JLabel itemListLabel;
	private JButton insertButton;
	private JLabel fastAdditionLabel;
	private JLabel productCodeLabel;
	private JTextField productCodeTextField;
	private Object invoker;
	
	
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		FastAddition inst = new FastAddition(frame);
		inst.setVisible(true);
	}
	
	public FastAddition(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	
	
	private void initGUI() {
		try {
			{
				this.setTitle("Fast Addition");
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setModal(true);
				this.addKeyListener(new KeyAdapter() {
					
				});
				{
					okButton = new JButton();
					getContentPane().add(okButton);
					okButton.setText("OK");
					okButton.setBounds(343, 490, 63, 28);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							InvoicePanel invoicePanel = (InvoicePanel)invoker; //invoker should be set
							for(int i = 0; i<itemListTable.getRowCount(); i++){
								InvoiceItem invoiceItem = new InvoiceItem();
								Product product = ProductService.getProductById(itemListTable.getValueAt(i, 0).toString());
								invoiceItem.setCost(product.getCost());
								invoiceItem.setDiscountCode(Integer.parseInt(itemListTable.getValueAt(i, 6).toString()));
								invoiceItem.setIsDeferred(0);
								invoiceItem.setProductCode(itemListTable.getValueAt(i, 0).toString());
								invoiceItem.setQuantity(Integer.parseInt(itemListTable.getValueAt(i, 2).toString()));
								invoiceItem.setSellPrice(Double.parseDouble(itemListTable.getValueAt(i, 4).toString()));
								invoiceItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
								if(invoicePanel.getInvoiceItemRow(itemListTable.getValueAt(i, 0).toString()) == null){
									invoicePanel.addInvoiceItem(invoiceItem);
								}
								else{
									JOptionPane.showMessageDialog(null, "The product "+product.getProdCode()+" is already in the invoice item list. The entry will be replaced with fast addition's entry.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
									invoicePanel.editInvoiceItem(invoiceItem,itemListTable.getValueAt(i, 0).toString() );
								}
							}
							
							FastAddition.this.dispose();

						}
					});
				}
				{
					productCodeTextField = new JTextField();
					getContentPane().add(productCodeTextField);
					productCodeTextField.setBounds(245, 56, 224, 28);
					productCodeTextField.addKeyListener(new KeyAdapter() {
						 @Override
						public void keyPressed(KeyEvent e) {
							 int key = e.getKeyCode();
						     if (key == KeyEvent.VK_ENTER){
						    	 insertIntoItemList();
						    	 productCodeTextField.setText("");
						     }
						}
					});
				}
				{
					productCodeLabel = new JLabel();
					getContentPane().add(productCodeLabel);
					productCodeLabel.setText("Product Code");
					productCodeLabel.setBounds(161, 56, 80, 28);
				}
				{
					fastAdditionLabel = new JLabel();
					getContentPane().add(fastAdditionLabel);
					fastAdditionLabel.setText("Fast Addition");
					fastAdditionLabel.setBounds(14, 14, 182, 28);
					fastAdditionLabel.setFont(new java.awt.Font("Tahoma",0,18));
					fastAdditionLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "fastAdditionLabel");
					fastAdditionLabel.getActionMap().put("fastAdditionLabel",getFastAdditionLabelAbstractAction() );
				}
				{
					insertButton = new JButton();
					getContentPane().add(insertButton);
					insertButton.setText("Insert");
					insertButton.setBounds(476, 56, 63, 28);
					insertButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							insertIntoItemList();
						}
					});
				}
				{
					itemListLabel = new JLabel();
					getContentPane().add(itemListLabel);
					itemListLabel.setText("Item List");
					itemListLabel.setBounds(14, 98, 63, 28);
					itemListLabel.setFont(new java.awt.Font("Tahoma",0,14));
				}
				{
					itemListScrollPane = new JScrollPane();
					getContentPane().add(itemListScrollPane);
					itemListScrollPane.setBounds(14, 126, 728, 310);
					{
						TableModel itemListTableModel = new DefaultTableModel(
							null,new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
						itemListTable = new JTable();
						itemListScrollPane.setViewportView(itemListTable);
						itemListTable.setModel(itemListTableModel);
					}
				}
				{
					deleteButton = new JButton();
					getContentPane().add(deleteButton);
					deleteButton.setText("Delete Product");
					deleteButton.setBounds(616, 448, 126, 28);
					deleteButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
						}
					});
				}
			}
			this.setSize(764, 566);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertIntoItemList(){
		Product product = ProductService.loadProductOfStore(productCodeTextField.getText());
		if(product != null){
			DefaultTableModel itemListTableModel = (DefaultTableModel) itemListTable.getModel();
			Integer itemListIndex =getItemListIndex(product.getProdCode()); 
			if(itemListIndex == null){ //new entry
				String[] row = new String[8];
				row[0] = product.getProdCode(); //product code
				row[1] = product.getName(); //product name
				row[2] = "1"; // every entry has 1 item
				row[3] = product.getSellPrice().toString(); //current price
				row[4] = product.getSellPrice().toString(); //selling price = current price
				row[5] = "No";
				row[6] = StringUtility.zeroFill("1",10);
				row[7] = product.getSellPrice().toString();
				itemListTableModel.addRow(row);
			}
			else{ //just update quantity and extension of the entry
				Integer quantity = Integer.parseInt(itemListTableModel.getValueAt(itemListIndex, 2).toString());
				itemListTableModel.setValueAt(quantity+1, itemListIndex, 2);
				Double sellingPrice = Double.parseDouble(itemListTableModel.getValueAt(itemListIndex, 4).toString());
				Double extension = sellingPrice * (quantity+1);
				itemListTableModel.setValueAt(String.format("%.2f", extension), itemListIndex, 7);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Product does not exist in store's inventory. Please contact administrator and synchronize inventory list.", "Prompt", JOptionPane.ERROR_MESSAGE);
		}
	}
	private Integer getItemListIndex(String productCode){
		DefaultTableModel itemListTableModel = (DefaultTableModel) itemListTable.getModel();
		for(int i = 0; i< itemListTableModel.getRowCount(); i++){
			if(itemListTableModel.getValueAt(i, 0).toString().equals(productCode)){
				return i;
			}
		}
		return null;
	}

	public void setInvoker(Object invoker) {
		this.invoker = invoker;
	}
	private AbstractAction getFastAdditionLabelAbstractAction() {
		AbstractAction fastAdditionLabelAction = new AbstractAction("Fast Addition", null) {
			
			public void actionPerformed(ActionEvent evt) {
				FastAddition.this.dispose();
			}
		};
		return fastAdditionLabelAction;
	}
	
	

}

package forms.lookup;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bus.DiscountService;
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
public class ProductDiscountDialog extends javax.swing.JDialog {
	private JLabel titleLabel;
	private AbstractAction okAbstractAction;
	private AbstractAction abstractAction1;
	private JComboBox discountComboBox;
	private JButton cancelButton;
	private JButton okButton;
	private JLabel discountLabel;
	private JLabel productCodeLabel;
	private JTextField productCodeTextField;
	private Object invoker;
	private int[] indices;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ProductDiscountDialog inst = new ProductDiscountDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public ProductDiscountDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public ProductDiscountDialog(JFrame frame, Object invoker) {
		super(frame);
		this.invoker = invoker;
		initGUI();
		
	}
	
	public void setIndices(int[] indices){
		this.indices = indices;
	}
	
	public void setProductCode(String prodCode){
		productCodeTextField.setText(prodCode);
	}
	
	public void setDiscount(int discCode){
		DefaultComboBoxModel model = (DefaultComboBoxModel) discountComboBox.getModel();
		int index = 0;
		for(int i = 0; i<model.getSize(); i++){
			if(Integer.parseInt(model.getElementAt(i).toString().split("-")[0])== discCode){
				index  = i;
				break;
			}
		}
		
		discountComboBox.setSelectedIndex(index);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				getContentPane().setLayout(null);
				this.setModal(true);
				{
					titleLabel = new JLabel();
					getContentPane().add(titleLabel);
					titleLabel.setText("Product");
					titleLabel.setBounds(12, 12, 122, 17);
					titleLabel.setFont(new java.awt.Font("Tahoma",0,18));
				}
				{
					productCodeTextField = new JTextField();
					getContentPane().add(productCodeTextField);
					productCodeTextField.setBounds(12, 52, 440, 22);
					productCodeTextField.setEditable(false);
				}
				{
					productCodeLabel = new JLabel();
					getContentPane().add(productCodeLabel);
					productCodeLabel.setText("Product Codes");
					productCodeLabel.setBounds(12, 35, 154, 16);
				}
				{
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(DiscountService.getAllDiscounts());
					discountComboBox = new JComboBox();
					getContentPane().add(discountComboBox);
					discountComboBox.setModel(jComboBox1Model);
					discountComboBox.setBounds(12, 108, 440, 22);
				}
				{
					discountLabel = new JLabel();
					getContentPane().add(discountLabel);
					discountLabel.setText("Discount");
					discountLabel.setBounds(12, 92, 47, 16);
				}
				{
					okButton = new JButton();
					getContentPane().add(okButton);
					okButton.setText("OK");
					okButton.setBounds(159, 182, 64, 23);
					okButton.setAction(getOkAbstractAction());
				}
				{
					cancelButton = new JButton();
					getContentPane().add(cancelButton);
					cancelButton.setText("Cancel");
					cancelButton.setBounds(234, 182, 93, 23);
					cancelButton.setAction(getAbstractAction1());
				}
			}
			this.setSize(480, 252);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAction getOkAbstractAction() {
		if(okAbstractAction == null) {
			okAbstractAction = new AbstractAction("OK", null) {
				public void actionPerformed(ActionEvent evt) {
					if(invoker instanceof InvoicePanel){
						
						InvoicePanel invoicePanel = (InvoicePanel)invoker;
						String discountCode = discountComboBox.getSelectedItem().toString().split("-")[0];
						invoicePanel.editInvoiceItems(discountCode, indices);
						
						ProductDiscountDialog.this.dispose();
					}
					else if(invoker instanceof ReturnedPanel){
						
						ReturnedPanel returnedPanel = (ReturnedPanel)invoker;
						String discountCode = discountComboBox.getSelectedItem().toString().split("-")[0];
						returnedPanel.editInvoiceItems(discountCode, indices);
						
						ProductDiscountDialog.this.dispose();
					}
				}
			};
		}
		return okAbstractAction;
	}
	
	private AbstractAction getAbstractAction1() {
		if(abstractAction1 == null) {
			abstractAction1 = new AbstractAction("Cancel", null) {
				public void actionPerformed(ActionEvent evt) {
					ProductDiscountDialog.this.dispose();
				}
			};
		}
		return abstractAction1;
	}

}

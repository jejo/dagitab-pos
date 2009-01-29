package com.dagitab.pos.forms.receipts;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.print.PrintException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.ReceiptPrinter;
import com.dagitab.pos.util.ReceiptUtilities;


/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
public class ValidateReceipt extends javax.swing.JDialog {
	private JScrollPane receiptPanelScrollPane;
	private JScrollPane headerPanelScrollPane;
	private JScrollPane footerPanelScrollPane;
	private JButton printButton;
	private JLabel receiptLabel;
	private ReceiptPanel receiptPanel;
	
	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	public ValidateReceipt(Window frame, ReceiptPanel receiptPanel) {
		super(frame);
		this.receiptPanel = receiptPanel;
		initGUI();
	}
	
	
	
	
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255, 255, 255));
			this.setTitle("Validate Receipt");
			this.setModal(true);
			{
				printButton = new JButton();
				getContentPane().add(
						printButton,
						new AnchorConstraint(907, 660, 960, 318,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				printButton.setText("Print");
				printButton.setPreferredSize(new java.awt.Dimension(98, 28));
				printButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						
						List<String[]> items = ReceiptUtilities.getReceiptUtilities().getItems(receiptPanel.getInvoiceItems());
						List<String[]> payments = ReceiptUtilities.getReceiptUtilities().getPayments(receiptPanel.getPaymentItems(), receiptPanel.getGCItems());
						String[] details = ReceiptUtilities.getReceiptUtilities().getReceiptDetails(receiptPanel.getInvoice(), receiptPanel.getInvoiceItems(), receiptPanel.getChangeAmount());
						
						ReceiptPrinter receiptPrinter = new ReceiptPrinter(items, payments, details);
						try {
							receiptPrinter.printReceipt();
						} catch (PrintException e) {
							JOptionPane.showMessageDialog(null, "Error occured. Please restart printing.","Error",JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
			}
			{
				receiptLabel = new JLabel();
				getContentPane().add(
						receiptLabel,
						new AnchorConstraint(27, 806, 80, 99,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				receiptLabel.setText("Validate Receipt");
				receiptLabel.setPreferredSize(new java.awt.Dimension(203, 28));
				receiptLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
				receiptLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "receiptLabel");
				receiptLabel.getActionMap().put("receiptLabel",getReceiptLabelAbstractAction() );
			}
			{
				receiptPanelScrollPane = new JScrollPane();
				getContentPane().add(
						receiptPanelScrollPane,
						new AnchorConstraint(80, 879, 867, 99,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				receiptPanelScrollPane.setPreferredSize(new java.awt.Dimension(
						212, 413));
				receiptPanelScrollPane.setViewportView(receiptPanel);
			}
			
			this.setSize(296, 559);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private AbstractAction getReceiptLabelAbstractAction() {
		AbstractAction receiptLabelAction = new AbstractAction("Validate Receipt", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ValidateReceipt.this.dispose();
			}
		};
		return receiptLabelAction;
	}

	

}

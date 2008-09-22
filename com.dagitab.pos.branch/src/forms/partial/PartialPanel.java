package forms.partial;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import util.LoggerUtility;
import util.TableUtility;
import bus.InvoiceService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

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
@SuppressWarnings("serial")
public class PartialPanel extends javax.swing.JPanel {


	private JButton processButton;
	private JButton refreshButton;
	private JScrollPane partialScrollPane;
	private JTable partialTable;
	private JLabel jLabel19;
	private MainWindow mainWindow;
	private PartialDialog partialDialog;

	public PartialPanel(MainWindow mainWindow) {
		super();
		setMainWindow(mainWindow); 
		initGUI();
		refreshPartialTable();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			AnchorLayout jPanel2Layout = new AnchorLayout();
			this.setLayout(jPanel2Layout);
			this.setFont(new java.awt.Font("Dialog", 1, 14));
			this.setBackground(new java.awt.Color(255, 255, 255));
			this.setPreferredSize(new java.awt.Dimension(862,458));
			this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			{
				processButton = new JButton();
				this.add(processButton, new AnchorConstraint(808,966,865,747,AnchorConstraint.ANCHOR_REL,AnchorConstraint.ANCHOR_REL,AnchorConstraint.ANCHOR_REL,AnchorConstraint.ANCHOR_REL));
				processButton.setText("Process Partial Transaction");
				processButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				processButton.setPreferredSize(new java.awt.Dimension(189,28));
				processButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(partialTable.getSelectedRowCount()>0){
							Invoice invoice = InvoiceService.getInvoiceByOr(partialTable.getValueAt(partialTable.getSelectedRow(), 0).toString());
							partialDialog = new PartialDialog(Main.getInst(), invoice);
							partialDialog.setInvoker(PartialPanel.this);
							partialDialog.setLocationRelativeTo(null);
							partialDialog.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
			{
				refreshButton = new JButton();
				this.add(refreshButton, new AnchorConstraint(808, 130, 865, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				refreshButton.setText("Refresh");
				refreshButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/search2.png")));
				refreshButton.setPreferredSize(new java.awt.Dimension(98, 26));
				refreshButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						refreshPartialTable();
					}
				});
			}
			{
				partialScrollPane = new JScrollPane();
				this.add(partialScrollPane, new AnchorConstraint(114, 965, 793, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				partialScrollPane.setPreferredSize(new java.awt.Dimension(818, 311));
				partialScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel jTable4Model = new DefaultTableModel(new String[][] {},new String[] { "OR Number","Transaction Date & Time" });
					partialTable = new JTable() {
						@Override
						public boolean isCellEditable(int row,int column) {
							return false;
						}
					};
					partialScrollPane.setViewportView(partialTable);
					partialTable.setModel(jTable4Model);
				}
			}
			{
				jLabel19 = new JLabel();
				this.add(jLabel19, new AnchorConstraint(15,317,114,16,
														AnchorConstraint.ANCHOR_REL,
														AnchorConstraint.ANCHOR_REL,
														AnchorConstraint.ANCHOR_REL,
														AnchorConstraint.ANCHOR_REL));
				jLabel19.setText("Manage Partial Transactions");
				jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18));
				jLabel19.setPreferredSize(new java.awt.Dimension(259,49));
			}

		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	public void refreshPartialTable() {
		ResultSet rs = InvoiceService.fetchAllPartialInvoices();
		TableUtility.fillTable(partialTable, rs, new String[]{"OR Number","Transaction Date & Time", "Clerk No"});
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public JButton getProcessButton() {
		return processButton;
	}
}

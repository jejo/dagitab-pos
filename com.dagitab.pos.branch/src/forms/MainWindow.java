package forms;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Transaction;
import forms.deferred.DeferredPanel;
import forms.invoice.InvoicePanel;
import forms.partial.PartialPanel;
import forms.pending.PendingPanel;
import forms.returned.ReturnedPanel;

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
public class MainWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public JLabel jLabel3;
	private JLabel jLabel1;
	private static JPanel returnedPanel;
	private static JPanel deferredPanel;
	private JPanel statusBarPanel;
	private static JPanel partialPanel;
	private static JPanel invoicePanel;
	private JTabbedPane jTabbedPane1;
	private static JPanel pendingPanel;
	private ArrayList<Transaction> pendingTransactions;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {

	}

	public MainWindow() {
		super();
		initGUI();
		pendingTransactions = new ArrayList<Transaction>();
	}

	private void initGUI() {
		try {
			{
				this.setTitle("Babyland POS Application");
				getContentPane().setBackground(
						new java.awt.Color(255, 255, 255));
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setExtendedState(Frame.MAXIMIZED_BOTH);
				this
						.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				this.setIconImage(new ImageIcon(getClass().getClassLoader()
						.getResource("images/payments.png")).getImage());
				this.setFocusTraversalKeysEnabled(false);
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						int n = JOptionPane
								.showConfirmDialog(
										MainWindow.this,
										"Are you sure you want to exit this application?",
										"Confirmation",
										JOptionPane.YES_NO_OPTION);
						if (n == 0) {
							System.exit(0);
						}

					}
				});

				{
					jLabel1 = new JLabel();
					getContentPane().add(
							jLabel1,
							new AnchorConstraint(13, 984, 63, 849,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					jLabel1.setPreferredSize(new java.awt.Dimension(119, 28));
					jLabel1.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("images/logo.jpg")));
				}
				{
					statusBarPanel = new JPanel();
					AnchorLayout jPanel3Layout = new AnchorLayout();
					statusBarPanel.setLayout(jPanel3Layout);
					getContentPane().add(
							statusBarPanel,
							new AnchorConstraint(970, 1008, 1008, 0,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					statusBarPanel.setPreferredSize(new java.awt.Dimension(889, 21));
					statusBarPanel.setEnabled(false);
					statusBarPanel.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0), 1, false));
					{
						jLabel3 = new JLabel();
						statusBarPanel.add(jLabel3, new AnchorConstraint(23, 95, 690,
								8, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jLabel3.setText("Not Connected");
						jLabel3
								.setPreferredSize(new java.awt.Dimension(77, 14));
					}
				}
				{
					// tab panels
					jTabbedPane1 = new JTabbedPane();
					getContentPane().add(
							jTabbedPane1,
							new AnchorConstraint(25, 992, 963, 8,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					jTabbedPane1.setPreferredSize(new java.awt.Dimension(868,
							525));
					jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14));
					jTabbedPane1
							.setBackground(new java.awt.Color(255, 255, 255));
					jTabbedPane1.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {

							if (jTabbedPane1.getSelectedIndex() == 0) { // regular
								InvoicePanel invoicePanel = (InvoicePanel)MainWindow.this.getInvoicePanel();
								invoicePanel.resetORNumber();
							} else if (jTabbedPane1.getSelectedIndex() == 1) { // partial
								((PartialPanel) partialPanel).refreshPartialTable();
							} else if (jTabbedPane1.getSelectedIndex() == 2) { // deferred
								((DeferredPanel) deferredPanel).refreshDeferredTable();
							} else if (jTabbedPane1.getSelectedIndex() == 3) { // returned
								ReturnedPanel returnedPanel = (ReturnedPanel) MainWindow.this.getReturnedPanel();
								returnedPanel.resetORNumber();
							} else if (jTabbedPane1.getSelectedIndex() == 4) { // pending
								
								((PendingPanel) pendingPanel).refreshPendingTable();
							}
						}
					});
					{
						invoicePanel = new InvoicePanel(this);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/add.png"));
						jTabbedPane1.addTab("Invoice", icon, invoicePanel, null);

					}
					{
						partialPanel = new PartialPanel(this);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/partial.png"));
						jTabbedPane1.addTab("Partial", icon, partialPanel, null);

					}
					{
						deferredPanel = new DeferredPanel(this);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/deferred.png"));
						jTabbedPane1.addTab("Deferred", icon, deferredPanel,null);
					}
					{
						returnedPanel = new ReturnedPanel();
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/Files-text.png"));
						jTabbedPane1.addTab("Returned Items", icon,returnedPanel, null);
					}
					{
						pendingPanel = new PendingPanel(this, (InvoicePanel) invoicePanel);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/pausepic.png"));
						 jTabbedPane1.addTab("Pending Transactions",icon,pendingPanel,null);

					}
				}
			}
			this.setSize(890, 615);
			{
				setJMenuBar(MenuHelper.getMenuBar());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getInvoicePanel() {
		return invoicePanel;
	}
	public JPanel getReturnedPanel(){
		return returnedPanel;
	}
	
	public ArrayList<Transaction> getPendingTransactions() {
		return pendingTransactions;
	}

	public void setPendingTransactions(ArrayList<Transaction> pendingTransactions) {
		this.pendingTransactions = pendingTransactions;
	}

	public JTabbedPane getJTabbedPane1() {
		return jTabbedPane1;
	}

	public void setJTabbedPane1(JTabbedPane tabbedPane1) {
		jTabbedPane1 = tabbedPane1;
	}
	
	
}

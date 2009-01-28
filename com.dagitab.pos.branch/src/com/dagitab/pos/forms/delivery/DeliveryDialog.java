package com.dagitab.pos.forms.delivery;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.dagitab.pos.bus.DeliveryItemService;
import com.dagitab.pos.bus.DeliveryService;
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
public class DeliveryDialog extends javax.swing.JDialog {
	private JLabel deliveryTitleLabel;
	private JPanel pendingTab;
	private JPanel completedTab;
	private JScrollPane jScrollPane2;
	private JLabel jLabel4;
	private JTable closedDeliveryItemTable;
	private JScrollPane closedScrollPane;
	private JTable closedDeliveryTable;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JTable completedDeliveryItemTable;
	private JScrollPane jScrollPane1;
	private JLabel jLabel1;
	private JTable completedDeliveryTable;
	private JScrollPane completedScrollPane;
	private JButton closeButton;
	private JTable pendingDeliveryItemsTable;
	private JTable pendingDeliveryTable;
	private JButton processDeliveryButton;
	private JLabel pendingDeliveryItemslabel;
	private JScrollPane pendingDeliveryItemsScrollPane;
	private JLabel pendingDescriptionLabel;
	private JScrollPane pendingScrollPane;
	private JPanel closedTab;
	private JTabbedPane deliveryTabbedPane;

	private DeliveryDialog deliveryDialog; 
	private DeliveryItemsConfirmationDialog deliveryItemsConfirmationDialog;
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				DeliveryDialog inst = new DeliveryDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public DeliveryDialog(JFrame frame) {
		super(frame);
		initGUI();
		deliveryDialog = this;
		//refresh tables
		refreshPendingDeliveriesTable();
		refreshCompletedDeliveriesTable();
		refreshClosedDeliveriesTable();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					deliveryTitleLabel = new JLabel();
					getContentPane().add(deliveryTitleLabel);
					deliveryTitleLabel.setText("Delivery");
					deliveryTitleLabel.setBounds(12, 12, 366, 16);
					deliveryTitleLabel.setFont(new java.awt.Font("Tahoma",0,18));
					deliveryTitleLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "deliveryTitleLabel");
					deliveryTitleLabel.getActionMap().put("deliveryTitleLabel",getDeliveryTitleLabelAbstractAction() );
				}
				{
					deliveryTabbedPane = new JTabbedPane();
					getContentPane().add(deliveryTabbedPane);
					deliveryTabbedPane.setBounds(12, 40, 696, 426);
					{
						pendingTab = new JPanel();
						deliveryTabbedPane.addTab("Pending Deliveries", null, pendingTab, null);
						
						pendingTab.setPreferredSize(new java.awt.Dimension(691, 387));
						pendingTab.setBackground(new java.awt.Color(255,255,255));
						pendingTab.setLayout(null);
						{
							pendingScrollPane = new JScrollPane();
							pendingTab.add(pendingScrollPane);
							pendingScrollPane.setBounds(10, 30, 671, 138);
							{
								TableModel pendingDeliveryTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Delivery No","Delivery Receipt No","Date Issued","Store From","Clerk Issued" });
								pendingDeliveryTable = new JTable() {
									@Override
									public boolean isCellEditable(
										int row,
										int column) {
										return false;
									}
								};
								
								pendingDeliveryTable.addMouseListener(new MouseAdapter(){
									 public void mouseClicked(MouseEvent e){
										 if(pendingDeliveryTable.getSelectedRow() != -1) {
											//refresh
											 refreshPendingDeliveryItemsTable((Long) pendingDeliveryTable.getValueAt(pendingDeliveryTable.getSelectedRow(), 0));
										 }
									 }
								});
								pendingScrollPane.setViewportView(pendingDeliveryTable);
								pendingDeliveryTable.setModel(pendingDeliveryTableModel);
							}
						}
						{
							pendingDescriptionLabel = new JLabel();
							pendingTab.add(pendingDescriptionLabel);
							pendingDescriptionLabel.setText("List of pending deliveries");
							pendingDescriptionLabel.setBounds(10, 11, 118, 14);
						}
						{
							pendingDeliveryItemsScrollPane = new JScrollPane();
							pendingTab.add(pendingDeliveryItemsScrollPane);
							pendingDeliveryItemsScrollPane.setBounds(10, 202, 671, 156);
							{
								TableModel pendingDeliveryItemsTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Delivery Item No", "Product Code","Quantity","Accepted","Missing","Damaged","Process Status" });
								pendingDeliveryItemsTable = new JTable() {
									@Override
									public boolean isCellEditable(
										int row,
										int column) {
										return false;
									}
								};
								pendingDeliveryItemsTable.addMouseListener(new MouseAdapter(){
									 public void mouseClicked(MouseEvent e){
										 if(e.getClickCount() == 2) {
											 processPendingDeliveryItem();
										 }
									 }
								});
								
								pendingDeliveryItemsScrollPane.setViewportView(pendingDeliveryItemsTable);
								pendingDeliveryItemsTable.setModel(pendingDeliveryItemsTableModel);
							}
						}
						{
							pendingDeliveryItemslabel = new JLabel();
							pendingTab.add(pendingDeliveryItemslabel);
							pendingDeliveryItemslabel.setText("Delivery Item List");
							pendingDeliveryItemslabel.setBounds(10, 183, 83, 14);
						}
						{
							processDeliveryButton = new JButton();
							pendingTab.add(processDeliveryButton);
							processDeliveryButton.setText("Process Item");
							processDeliveryButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
							processDeliveryButton.setBounds(546, 365, 135, 22);
							processDeliveryButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									processPendingDeliveryItem();
								}
							});
						}
					}
					{
						completedTab = new JPanel();
						deliveryTabbedPane.addTab("Completed Deliveries", null, completedTab, null);
						completedTab.setLayout(null);
						completedTab.setBackground(new java.awt.Color(255,255,255));
						{
							completedScrollPane = new JScrollPane();
							completedTab.add(completedScrollPane);
							completedScrollPane.setBounds(9, 30, 675, 138);
							{
								TableModel completeDeliveryTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Delivery No","Delivery Receipt No","Date Issued","Store From","Clerk Issued" });
								completedDeliveryTable = new JTable() {
									@Override
									public boolean isCellEditable(
										int row,
										int column) {
										return false;
									}
								};
								completedDeliveryTable.addMouseListener(new MouseAdapter(){
									 public void mouseClicked(MouseEvent e){
										 if(completedDeliveryTable.getSelectedRow() != -1) {
											//refresh
											 refreshCompletedDeliveryItemsTable((Long) completedDeliveryTable.getValueAt(completedDeliveryTable.getSelectedRow(), 0));
										 }
									 }
								});
								completedScrollPane.setViewportView(completedDeliveryTable);
								completedDeliveryTable.setModel(completeDeliveryTableModel);
							}
						}
						{
							jLabel1 = new JLabel();
							completedTab.add(jLabel1);
							jLabel1.setText("List of completed deliveries");
							jLabel1.setBounds(10, 12, 149, 13);
						}
						{
							jScrollPane1 = new JScrollPane();
							completedTab.add(jScrollPane1);
							jScrollPane1.setBounds(9, 202, 673, 156);
							{
								TableModel jTable1Model = 
									new DefaultTableModel(
											null,
											new String[] { "Delivery Item No", "Product Code","Quantity","Accepted","Missing","Damaged","Process Status" });
								completedDeliveryItemTable = new JTable() {
									@Override
									public boolean isCellEditable(
										int row,
										int column) {
										return false;
									}
								};
								jScrollPane1.setViewportView(completedDeliveryItemTable);
								completedDeliveryItemTable.setModel(jTable1Model);
							}
						}
						{
							jLabel2 = new JLabel();
							completedTab.add(jLabel2);
							jLabel2.setText("Delivery Item List");
							jLabel2.setBounds(10, 182, 88, 16);
						}
					}
					{
						closedTab = new JPanel();
						deliveryTabbedPane.addTab("Closed Deliveries", null, closedTab, null);
						closedTab.setLayout(null);
						closedTab.setBackground(new java.awt.Color(255,255,255));
						{
							jLabel3 = new JLabel();
							closedTab.add(jLabel3);
							jLabel3.setText("List of closed deliveries");
							jLabel3.setBounds(10, 11, 153, 14);
						}
						{
							closedScrollPane = new JScrollPane();
							closedTab.add(closedScrollPane);
							closedScrollPane.setBounds(10, 31, 674, 134);
							{
								TableModel closedDeliveryTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Delivery No","Delivery Receipt No","Date Issued","Store From","Clerk Issued" });
								closedDeliveryTable = new JTable() {
									@Override
									public boolean isCellEditable(
										int row,
										int column) {
										return false;
									}
								};
								closedDeliveryTable.addMouseListener(new MouseAdapter(){
									 public void mouseClicked(MouseEvent e){
										 if(closedDeliveryTable.getSelectedRow() != -1) {
											//refresh
											 refreshClosedDeliveryItemsTable((Long) closedDeliveryTable.getValueAt(closedDeliveryTable.getSelectedRow(), 0));
										 }
									 }
								});
								closedScrollPane.setViewportView(closedDeliveryTable);
								closedDeliveryTable.setModel(closedDeliveryTableModel);
							}
						}
						{
							jScrollPane2 = new JScrollPane();
							closedTab.add(jScrollPane2);
							jScrollPane2.setBounds(10, 203, 673, 154);
							{
								TableModel jTable2Model = 
									new DefaultTableModel(
											null,
											new String[] { "Delivery Item No", "Product Code","Quantity","Accepted","Missing","Damaged","Process Status" });
								closedDeliveryItemTable = new JTable() {
									@Override
									public boolean isCellEditable(
										int row,
										int column) {
										return false;
									}
								};
								jScrollPane2.setViewportView(closedDeliveryItemTable);
								closedDeliveryItemTable.setModel(jTable2Model);
							}
						}
						{
							jLabel4 = new JLabel();
							closedTab.add(jLabel4);
							jLabel4.setText("Delivery Item List");
							jLabel4.setBounds(10, 183, 89, 14);
						}
					}
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(317, 502, 67, 23);
					
					closeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							deliveryDialog.setVisible(false);
						}
					});
				}
			}
			this.setSize(736, 569);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	public void refreshPendingDeliveriesTable() {
		ResultSet rs = DeliveryService.fetchAllDeliveriesByStatus(DeliveryService.PENDING);
		TableUtility.fillTable(pendingDeliveryTable, rs, new String[]{"Delivery No","Delivery Receipt No","Date Issued","Store From","Clerk Issued"});
	}
	
	public void refreshCompletedDeliveriesTable() {
		ResultSet rs = DeliveryService.fetchAllDeliveriesByStatus(DeliveryService.COMPLETE);
		TableUtility.fillTable(completedDeliveryTable, rs, new String[]{"Delivery No","Delivery Receipt No","Date Issued","Store From","Clerk Issued"});
	}
	
	public void refreshClosedDeliveriesTable() {
		ResultSet rs = DeliveryService.fetchAllDeliveriesByStatus(DeliveryService.CLOSED);
		TableUtility.fillTable(closedDeliveryTable, rs, new String[]{"Delivery No","Delivery Receipt No","Date Issued","Store From","Clerk Issued"});
	}
	
	public void refreshPendingDeliveryItemsTable(Long deliveryId) {
		ResultSet rs =	DeliveryItemService.fetchAllDeliveriesItems(deliveryId); 
		TableUtility.fillTable(pendingDeliveryItemsTable, rs, new String[]{"Delivery Item No", "Product Code","Quantity","Accepted","Missing","Damaged","Process Status"});
	}
	
	public void refreshCompletedDeliveryItemsTable(Long deliveryId) {
		ResultSet rs =	DeliveryItemService.fetchAllDeliveriesItems(deliveryId); 
		TableUtility.fillTable(completedDeliveryItemTable, rs, new String[]{"Delivery Item No", "Product Code","Quantity","Accepted","Missing","Damaged","Process Status"});
	}
	
	public void refreshClosedDeliveryItemsTable(Long deliveryId) {
		ResultSet rs =	DeliveryItemService.fetchAllDeliveriesItems(deliveryId); 
		TableUtility.fillTable(closedDeliveryItemTable, rs, new String[]{"Delivery Item No", "Product Code","Quantity","Accepted","Missing","Damaged","Process Status"});
	}

	public void processPendingDeliveryItem() {
		if(pendingDeliveryItemsTable.getSelectedRow() != -1) {
			if(((Integer) pendingDeliveryItemsTable.getValueAt(pendingDeliveryItemsTable.getSelectedRow(), 6)).equals(0) ) {
				if(deliveryItemsConfirmationDialog == null) {
					deliveryItemsConfirmationDialog = new DeliveryItemsConfirmationDialog(Main.getInst(), this, (Long) pendingDeliveryItemsTable.getValueAt(pendingDeliveryItemsTable.getSelectedRow(), 0));
					deliveryItemsConfirmationDialog.setLocationRelativeTo(null);
					deliveryItemsConfirmationDialog.setVisible(true);
				}
				else {
					deliveryItemsConfirmationDialog.setDeliveryItemId((Long) pendingDeliveryItemsTable.getValueAt(pendingDeliveryItemsTable.getSelectedRow(), 0));
					deliveryItemsConfirmationDialog.setVisible(true);
					deliveryItemsConfirmationDialog.getAcceptedQuantityTextField().setText("0");
					deliveryItemsConfirmationDialog.getMissingQuantityTextField().setText("0");
					deliveryItemsConfirmationDialog.getDamagedQuantityTextField().setText("0");
				}
			}
		}
	}

	public JTable getPendingDeliveryTable() {
		return pendingDeliveryTable;
	}

	public void setPendingDeliveryTable(JTable pendingDeliveryTable) {
		this.pendingDeliveryTable = pendingDeliveryTable;
	}
	
	public void updateSelectedPendingDelivery() {
		Long deliveryId = (Long) pendingDeliveryTable.getValueAt(pendingDeliveryTable.getSelectedRow(), 0);		
		if(!DeliveryItemService.hasPendingDeliveryItemsToCheck(deliveryId)) {
			DeliveryService.changeDeliveryStatus(DeliveryService.COMPLETE, deliveryId);
			JOptionPane.showMessageDialog(null, "Delivery No. " + deliveryId + " is now complete.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
			refreshCompletedDeliveriesTable();
			refreshPendingDeliveriesTable();
			TableUtility.clearTable(pendingDeliveryItemsTable);
			TableUtility.clearTable(completedDeliveryItemTable);
		}
	}
	private AbstractAction getDeliveryTitleLabelAbstractAction() {
		AbstractAction deliveryTitleLabelAction = new AbstractAction("Delivery", null) {
			
			public void actionPerformed(ActionEvent evt) {
				DeliveryDialog.this.dispose();
			}
		};
		return deliveryTitleLabelAction;
	}
}

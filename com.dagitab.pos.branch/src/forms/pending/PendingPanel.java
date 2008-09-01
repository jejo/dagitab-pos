package forms.pending;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.LoggerUtility;
import util.TableUtility;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Transaction;
import forms.MainWindow;
import forms.invoice.InvoicePanel;

@SuppressWarnings("serial")
public class PendingPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PendingPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JLabel jLabel39;
	private JButton jButton33;
	private JButton jButton32;
	private JScrollPane jScrollPane8;
	private JTable pendingTable;
	private JLabel jLabel38;
	private MainWindow mainWindow;
	private InvoicePanel invoicePanel;
	
	public PendingPanel() {
		super();
		initGUI();
	}
	
	public PendingPanel(MainWindow mainWindow, InvoicePanel invoicePanel) {
		super();
		initGUI();
		this.mainWindow = mainWindow;
		this.invoicePanel = invoicePanel;
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			AnchorLayout jPanel12Layout = new AnchorLayout();
			this.setLayout(jPanel12Layout);
			this.setBackground(new java.awt.Color(255,255,255));
			{
				jLabel39 = new JLabel();
				this.add(jLabel39, new AnchorConstraint(142, 958, 709, 731, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel39.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/giraffe.gif")));
				jLabel39.setPreferredSize(new java.awt.Dimension(196, 280));
			}
			{
				jButton33 = new JButton();
				this.add(jButton33, new AnchorConstraint(780, 983, 837, 731, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton33.setText("Delete Selected Transaction");
				jButton33.setPreferredSize(new java.awt.Dimension(217, 28));
				jButton33.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/delete1.png")));
				jButton33.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(pendingTable.getSelectedRow() != -1) {
							mainWindow.getPendingTransactions().remove(pendingTable.getSelectedRow());
							refreshPendingTable();
						}
//						pausedData.remove(jTable8.getSelectedRow());
//						Object[][] data = new Object[pausedData.size()][7];
//						for(int i = 0; i< pausedData.size(); i++)
//						{
//							data[i][0] = i+1;
//							PendingTransactionData pauseTransactionRow = pausedData.get(i);
//							data[i][1] = pauseTransactionRow.getDate();
//							data[i][2] = pauseTransactionRow.getTime();
//							
//						}
//						
//						TableModel jTable1Model = new DefaultTableModel(
//								data,
//								new String[] { "Transaction No", "Date","Time"  });
//							jTable8 = new JTable(){
//								public boolean isCellEditable(int row, int column)
//								{
//									return false;
//								}
//							};
//							jScrollPane8.setViewportView(jTable8);
//							jTable8.setModel(jTable1Model);
					}
				});
			}
			{
				jButton32 = new JButton();
				this.add(jButton32, new AnchorConstraint(879, 983, 936, 731, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton32.setText("Process Selected Transaction");
				jButton32.setPreferredSize(new java.awt.Dimension(217, 28));
				jButton32.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				jButton32.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(pendingTable.getSelectedRow() != -1) {
							resumePendingTransaction(pendingTable.getSelectedRow());
							refreshPendingTable();
							mainWindow.getJTabbedPane1().setSelectedIndex(0);
						}
//						if(jTable8.getSelectedRow() > -1){
//							pauseDeleteFlag = true;
//							pauseSelectedIndex = jTable8.getSelectedRow();
//							
//							try {
//								updateAll();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								LoggerUtility.getInstance().logStackTrace(e);
//							}
//							
//							PendingTransactionData ptd = pausedData.get(pauseSelectedIndex);
//							productItems = ptd.getProdItems();
//							setProductTable(null);
//							
////							
//							updateAmounts();
//							jTabbedPane1.setSelectedIndex(0);
//					
//						}
						
					}
				});
			}
			{
				jScrollPane8 = new JScrollPane();
				this.add(jScrollPane8, new AnchorConstraint(156, 715, 936, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane8.setPreferredSize(new java.awt.Dimension(602, 385));
				{
					TableModel jTable8Model = new DefaultTableModel(
						null,
						new String[] { "Transaction No", "Date","Time" });
					pendingTable = new JTable() {
						@Override
						public boolean isCellEditable(
							int row,
							int column) {
							return false;
						}
					};
					jScrollPane8.setViewportView(pendingTable);
					pendingTable.setModel(jTable8Model);
				}
			}
			{
				jLabel38 = new JLabel();
				this.add(jLabel38, new AnchorConstraint(43, 471, 156, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel38.setText("List of Pending Transactions");
				jLabel38.setPreferredSize(new java.awt.Dimension(392, 56));
				jLabel38.setFont(new java.awt.Font("Tahoma",1,18));
				jLabel38.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/transactions.PNG")));
			}
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	
	public void refreshPendingTable() {
		Object[][] pendingTableData = new Object[mainWindow.getPendingTransactions().size()][3];
		//ResultSet rs = InvoiceItemService.fetchAllDeferredInvoiceItems();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		
		for(int i = 0; i < mainWindow.getPendingTransactions().size(); i++) {
			
			pendingTableData[i][0] = i + 1;
			pendingTableData[i][1] = dateFormat.format(mainWindow.getPendingTransactions().get(i).getDate());
			pendingTableData[i][2] = timeFormat.format(mainWindow.getPendingTransactions().get(i).getDate());
		}
		TableUtility.fillTable(pendingTable, pendingTableData, new String[]{"Transaction No", "Date", "Time"});
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void resumePendingTransaction(int index) {
		Transaction transaction = mainWindow.getPendingTransactions().get(index);
		invoicePanel.resumeTransaction(transaction);
		mainWindow.getPendingTransactions().remove(index);
	}
}

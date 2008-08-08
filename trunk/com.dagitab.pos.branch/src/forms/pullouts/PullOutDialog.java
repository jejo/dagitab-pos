package forms.pullouts;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
public class PullOutDialog extends javax.swing.JDialog {
	private JLabel PullOutLabel;
	private JPanel pendingPullOutsPanel;
	private JLabel pullOutItemLabel;
	private AbstractAction closeAbstractAction;
	private JTable closedPullOutItemTable;
	private JTable closedPullOutTable;
	private JTable completedPullOutItemTable;
	private JTable completedPullOutTable;
	private JTable pendingPullOutItemTable;
	private JTable pendingPullOutTable;
	private JScrollPane closedPullOutItemScrollPane;
	private JLabel closedPullOutItemLabel;
	private JScrollPane closedPullOutScrollPane;
	private JLabel closedPullOutLabel;
	private JScrollPane completedPullOutItemScrollPane;
	private JLabel completedPullOutItemList;
	private JScrollPane completedPullOutScrollPane;
	private JLabel completedPullOutLabel;
	private JButton processPullOutItemButton;
	private JScrollPane pullOutItemScrollPane;
	private JLabel pullOutLabel;
	private JScrollPane pendingScrollPane;
	private JButton closeButton;
	private JPanel closedPullOutsPanel;
	private JPanel completedPullOutsPanel;
	private JTabbedPane pullOutTabbedPane;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				PullOutDialog inst = new PullOutDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public PullOutDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Pull Outs");
				this.setModal(true);
				{
					PullOutLabel = new JLabel();
					getContentPane().add(PullOutLabel);
					PullOutLabel.setText("Pull Outs");
					PullOutLabel.setBounds(10, 11, 105, 14);
					PullOutLabel.setFont(new java.awt.Font("Tahoma",0,18));
				}
				{
					pullOutTabbedPane = new JTabbedPane();
					getContentPane().add(pullOutTabbedPane);
					pullOutTabbedPane.setBounds(10, 36, 760, 424);
					{
						pendingPullOutsPanel = new JPanel();
						pullOutTabbedPane.addTab("Pending", null, pendingPullOutsPanel, null);
						
						pendingPullOutsPanel.setBackground(new java.awt.Color(255,255,255));
						pendingPullOutsPanel.setLayout(null);
						{
							pendingScrollPane = new JScrollPane();
							pendingPullOutsPanel.add(pendingScrollPane);
							pendingScrollPane.setBounds(10, 25, 735, 145);
							{
								TableModel pendingPullOutTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
								pendingPullOutTable = new JTable();
								pendingScrollPane.setViewportView(pendingPullOutTable);
								pendingPullOutTable.setModel(pendingPullOutTableModel);
							}
						}
						{
							pullOutLabel = new JLabel();
							pendingPullOutsPanel.add(pullOutLabel);
							pullOutLabel.setText("Pull Out List");
							pullOutLabel.setBounds(10, 11, 56, 14);
						}
						{
							pullOutItemScrollPane = new JScrollPane();
							pendingPullOutsPanel.add(pullOutItemScrollPane);
							pullOutItemScrollPane.setBounds(10, 197, 736, 150);
							{
								TableModel pendingPullOutItemTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Pull Out Item No", "Product Code","Product Name","Quantity","Processed Status" });
								pendingPullOutItemTable = new JTable();
								pullOutItemScrollPane.setViewportView(pendingPullOutItemTable);
								pendingPullOutItemTable.setModel(pendingPullOutItemTableModel);
							}
						}
						{
							pullOutItemLabel = new JLabel();
							pendingPullOutsPanel.add(pullOutItemLabel);
							pullOutItemLabel.setText("Pull Out Items");
							pullOutItemLabel.setBounds(10, 183, 67, 14);
						}
						{
							processPullOutItemButton = new JButton();
							pendingPullOutsPanel.add(processPullOutItemButton);
							processPullOutItemButton.setText("Process Pull Out Item");
							processPullOutItemButton.setBounds(610, 358, 135, 23);
						}
					}
					{
						completedPullOutsPanel = new JPanel();
						pullOutTabbedPane.addTab("Completed", null, completedPullOutsPanel, null);
						
						completedPullOutsPanel.setBackground(new java.awt.Color(255,255,255));
						completedPullOutsPanel.setLayout(null);
						{
							completedPullOutLabel = new JLabel();
							completedPullOutsPanel.add(completedPullOutLabel);
							completedPullOutLabel.setText("Completed Pull Out List");
							completedPullOutLabel.setBounds(10, 11, 113, 16);
						}
						{
							completedPullOutScrollPane = new JScrollPane();
							completedPullOutsPanel.add(completedPullOutScrollPane);
							completedPullOutScrollPane.setBounds(10, 25, 735, 155);
							{
								TableModel completedPullOutTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
								completedPullOutTable = new JTable();
								completedPullOutScrollPane.setViewportView(completedPullOutTable);
								completedPullOutTable.setModel(completedPullOutTableModel);
							}
						}
						{
							completedPullOutItemList = new JLabel();
							completedPullOutsPanel.add(completedPullOutItemList);
							completedPullOutItemList.setText("Completed Pull Out Item List");
							completedPullOutItemList.setBounds(10, 191, 151, 16);
						}
						{
							completedPullOutItemScrollPane = new JScrollPane();
							completedPullOutsPanel.add(completedPullOutItemScrollPane);
							completedPullOutItemScrollPane.setBounds(10, 211, 735, 169);
							{
								TableModel completePullOutItemTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Pull Out Item No", "Product Code","Product Name","Quantity","Processed Status" });
								completedPullOutItemTable = new JTable();
								completedPullOutItemScrollPane.setViewportView(completedPullOutItemTable);
								completedPullOutItemTable.setModel(completePullOutItemTableModel);
							}
						}
					}
					{
						closedPullOutsPanel = new JPanel();
						pullOutTabbedPane.addTab("Closed", null, closedPullOutsPanel, null);
						closedPullOutsPanel.setBackground(new java.awt.Color(255,255,255));
						closedPullOutsPanel.setLayout(null);
						{
							closedPullOutLabel = new JLabel();
							closedPullOutsPanel.add(closedPullOutLabel);
							closedPullOutLabel.setText("Closed Pull Out List");
							closedPullOutLabel.setBounds(10, 11, 91, 15);
						}
						{
							closedPullOutScrollPane = new JScrollPane();
							closedPullOutsPanel.add(closedPullOutScrollPane);
							closedPullOutScrollPane.setBounds(10, 27, 735, 166);
							{
								TableModel closedPullOutTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
								closedPullOutTable = new JTable();
								closedPullOutScrollPane.setViewportView(closedPullOutTable);
								closedPullOutTable.setModel(closedPullOutTableModel);
							}
						}
						{
							closedPullOutItemLabel = new JLabel();
							closedPullOutsPanel.add(closedPullOutItemLabel);
							closedPullOutItemLabel.setText("Closed Pull Out Item List");
							closedPullOutItemLabel.setBounds(10, 209, 116, 14);
						}
						{
							closedPullOutItemScrollPane = new JScrollPane();
							closedPullOutsPanel.add(closedPullOutItemScrollPane);
							closedPullOutItemScrollPane.setBounds(10, 229, 735, 150);
							{
								TableModel closedPullOutItemTableModel = 
									new DefaultTableModel(
											null,
											new String[] { "Pull Out Item No", "Product Code","Product Name","Quantity","Processed Status" });
								closedPullOutItemTable = new JTable();
								closedPullOutItemScrollPane.setViewportView(closedPullOutItemTable);
								closedPullOutItemTable.setModel(closedPullOutItemTableModel);
							}
						}
					}
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(370, 478, 59, 23);
					closeButton.setAction(getCloseAbstractAction());
				}
			}
			this.setSize(796, 553);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAction getCloseAbstractAction() {
		if(closeAbstractAction == null) {
			closeAbstractAction = new AbstractAction("Close", null) {
				public void actionPerformed(ActionEvent evt) {
					PullOutDialog.this.dispose();
				}
			};
		}
		return closeAbstractAction;
	}

}

package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.DBManager;

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
public class InventoryViewer extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton jButton1;
	private JLabel jLabel3;
	private JTable jTable1;
	private JButton jButton3;
	private JButton jButton2;
	private JScrollPane jScrollPane1;
	private JButton jButton4;
	private JTextField jTextField2;
	private JTextField jTextField1;
	@SuppressWarnings("unused")
	private MainWindow form;
	private DBManager db;
	@SuppressWarnings("unused")
	private String storeCode;
	private Vector<Vector<String>> inventoryTable;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		InventoryViewer inst = new InventoryViewer(frame);
//		inst.setVisible(true);
	}
	
	public InventoryViewer(MainWindow frame, DBManager db, String storeCode) {
		super(frame);
		this.form = frame;
		this.db = db;
		this.storeCode = storeCode;
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle("Inventory Viewer");
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setModal(true);
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3, new AnchorConstraint(905, 550, 969, 451, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("Close");
					jButton3.setPreferredSize(new java.awt.Dimension(77, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							InventoryViewer.this.dispose();
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(810, 197, 874, 29, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Export to Excel");
					jButton2.setPreferredSize(new java.awt.Dimension(161, 28));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							  JFileChooser chooser = new JFileChooser();
							  chooser.setDialogType(JFileChooser.SAVE_DIALOG);
							    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
							    // under the demo/jfc directory in the JDK.
//							  	
							  	FileFilter filter = createFileFilter("Excel Files Only",true,new String[]{"xls"});
							  	chooser.setFileFilter(filter);

							  
							  	int returnVal = chooser.showSaveDialog(InventoryViewer.this);
//							    int returnVal = chooser.showOpenDialog(InventoryViewer.this);
							    if(returnVal == JFileChooser.APPROVE_OPTION) {
							       System.out.println("You chose to open this file: " +
							            
							            chooser.getSelectedFile().getAbsolutePath()+".xls");
							       
							       
							       
							       boolean success = 
							    	   reports.CurrentInventory.generate(chooser.getSelectedFile().getAbsolutePath()+".xls", 
							    			   								inventoryTable,db,storeCode);
							       
							       if(success){
							    	   JOptionPane.showMessageDialog(null, 
												"The report is saved.", 
												"Saved",JOptionPane.INFORMATION_MESSAGE);
							       }
							       else{
							    	   JOptionPane.showMessageDialog(null, 
												"Cannot save file", 
												"Error",JOptionPane.ERROR_MESSAGE);
							       }
							       
							       		
							    }

						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(191, 978, 794, 29, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(910, 266));
					{
						TableModel jTable1Model = new DefaultTableModel(
							new String[][] { },
							new String[] { "Product Code", "Product Name", "Product Description" ,"Selling Price" , "Store Code","Store Name", "Available Quantity","Deferred Quantity" });
						
						jTable1 = new JTable();
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
					}
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(112, 453, 175, 328, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Search");
					jButton1.setPreferredSize(new java.awt.Dimension(119, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String query = "SELECT p.prod_code, " +
												  "p.name, " +
												  "p.description, " +
												  "p.sell_price, " +
												  "s.store_code, " +
												  "s.name, " +
												  "i.quantity, " +
												  "i.deferred_qty " +
										    "FROM products_lu p, " +
										    		"store_lu s, " +
										    		"inventory_lu i " +
										    "WHERE p.prod_code = i.prod_code " +
										    "AND s.store_code = i.store_code " +
										    "AND p.prod_code LIKE '%"+jTextField1.getText()+"%' ";
										    
							ResultSet rs = db.executeQuery(query);
							System.out.println(query);
							
							try {
								//inventoryTable.removeAllElements();
								inventoryTable = new Vector<Vector<String>>();
								
								while(rs.next()){
									Vector<String> values = new Vector<String>();
									values.add(rs.getString(1));
									values.add(rs.getString(2));
//									System.out.println(rs.getString(2));
									values.add(rs.getString(3));
									values.add(rs.getString(4));
									values.add(rs.getString(5));
									values.add(rs.getString(6));
									values.add(rs.getString(7));
									values.add(rs.getString(8));
									inventoryTable.add(values);
								}
								
								Object[][] data = new Object[inventoryTable.size()][8];
								for(int i = 0; i< inventoryTable.size(); i++)
								{
									for(int j=0; j<8; j++){
										data[i][j] = inventoryTable.get(i).get(j);
									
									}
								}
								
								TableModel jTable1Model = new DefaultTableModel(
										data,
										new String[] { "Product Code", "Product Name", "Product Description" ,"Selling Price" , "Store Code","Store Name", "Available Quantity","Deferred Quantity"  });
									jTable1 = new JTable(){
										@Override
										public boolean isCellEditable(int row, int column)
										{
											return false;
										}
									};
									jScrollPane1.setViewportView(jTable1);
									jTable1.setModel(jTable1Model);
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
				{
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1, new AnchorConstraint(112, 321, 175, 102, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField1.setPreferredSize(new java.awt.Dimension(210, 28));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(112, 171, 175, 27, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Product Code");
					jLabel2.setPreferredSize(new java.awt.Dimension(112, 28));
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new AnchorConstraint(18, 361, 102, 18, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("View Current Inventory");
					jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
					jLabel1.setPreferredSize(new java.awt.Dimension(266, 35));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(112, 679, 175, 533, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Product Name");
					jLabel3.setPreferredSize(new java.awt.Dimension(140, 28));
				}
				{
					jTextField2 = new JTextField();
					getContentPane().add(jTextField2, new AnchorConstraint(112, 832, 175, 613, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField2.setPreferredSize(new java.awt.Dimension(210, 28));
				}
				{
					jButton4 = new JButton();
					getContentPane().add(jButton4, new AnchorConstraint(112, 964, 175, 839, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton4.setText("Search");
					jButton4.setPreferredSize(new java.awt.Dimension(119, 28));
					jButton4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String query = "SELECT p.prod_code, "
								+ "p.name, "
								+ "p.description, "
								+ "p.sell_price, "
								+ "s.store_code, "
								+ "s.name, "
								+ "i.quantity, "
								+ "i.deferred_qty "
								+ "FROM products_lu p, "
								+ "store_lu s, "
								+ "inventory_lu i "
								+ "WHERE p.prod_code = i.prod_code "
								+ "AND s.store_code = i.store_code "
								+ "AND p.name LIKE '%"
								+ jTextField2.getText()
								+ "%' ";

							ResultSet rs = db.executeQuery(query);

							try {
								//inventoryTable.removeAllElements();
								inventoryTable = new Vector<Vector<String>>();

								while (rs.next()) {
									Vector<String> values = new Vector<String>();
									values.add(rs.getString(1));
									values.add(rs.getString(2));
									//									System.out.println(rs.getString(2));
									values.add(rs.getString(3));
									values.add(rs.getString(4));
									values.add(rs.getString(5));
									values.add(rs.getString(6));
									values.add(rs.getString(7));
									values.add(rs.getString(8));
									inventoryTable.add(values);
								}

								Object[][] data = new Object[inventoryTable
									.size()][8];
								for (int i = 0; i < inventoryTable.size(); i++) {
									for (int j = 0; j < 8; j++) {
										data[i][j] = inventoryTable.get(i).get(
											j);

									}
								}

								TableModel jTable1Model = new DefaultTableModel(
									data,
									new String[] { "Product Code",
											"Product Name",
											"Product Description",
											"Selling Price", "Store Code",
											"Store Name", "Available Quantity",
											"Deferred Quantity" });
								jTable1 = new JTable(){
									@Override
									public boolean isCellEditable(int row, int column)
									{
										return false;
									}
								};
								jScrollPane1.setViewportView(jTable1);
								jTable1.setModel(jTable1Model);

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
			this.setSize(967, 475);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private FileFilter createFileFilter(String description,
            boolean showExtensionInDescription, String...extensions) {
        if (showExtensionInDescription) {
            description = createFileNameFilterDescriptionFromExtensions(
                    description, extensions);
        }
        return new FileNameExtensionFilter(description, extensions);
    }
	
	private String createFileNameFilterDescriptionFromExtensions(
            String description, String[] extensions) {
        String fullDescription = (description == null) ?
                "(" : description + " (";
        // build the description from the extension list
        fullDescription += "." + extensions[0];
        for (int i = 1; i < extensions.length; i++) {
            fullDescription += ", .";
            fullDescription += extensions[i];
        }
        fullDescription += ")";
        return fullDescription;
    }
	

}

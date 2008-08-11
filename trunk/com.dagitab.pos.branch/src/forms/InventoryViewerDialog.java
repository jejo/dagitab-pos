package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
public class InventoryViewerDialog extends javax.swing.JDialog {
	private JLabel inventoryViewerLabel;
	private JLabel storeLabel;
	private JComboBox storeComboBox;
	private JLabel jLabel2;
	private JTable productTable;
	private JButton jButton3;
	private JButton jButton2;
	private JScrollPane productScrollPane;
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
	
	public InventoryViewerDialog(MainWindow frame, DBManager db, String storeCode) {
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
					getContentPane().add(getStoreLabel(), new AnchorConstraint(105, 848, 142, 805, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					getContentPane().add(getStoreComboBox(), new AnchorConstraint(99, 985, 149, 865, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					getContentPane().add(jButton3, new AnchorConstraint(912, 571, 976, 471, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("Close");
					jButton3.setPreferredSize(new java.awt.Dimension(95, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							InventoryViewerDialog.this.dispose();
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(839, 175, 896, 13, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Export to Excel");
					jButton2.setPreferredSize(new java.awt.Dimension(154, 25));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							  JFileChooser chooser = new JFileChooser();
							  chooser.setDialogType(JFileChooser.SAVE_DIALOG);
							    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
							    // under the demo/jfc directory in the JDK.
//							  	
							  	FileFilter filter = createFileFilter("Excel Files Only",true,new String[]{"xls"});
							  	chooser.setFileFilter(filter);

							  
							  	int returnVal = chooser.showSaveDialog(InventoryViewerDialog.this);
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
					productScrollPane = new JScrollPane();
					getContentPane().add(productScrollPane, new AnchorConstraint(187, 987, 825, 13, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					productScrollPane.setPreferredSize(new java.awt.Dimension(927, 280));
					{
						TableModel productTableModel = new DefaultTableModel(
							new String[][] { },
							new String[] { "Product Code", "Product Name", "Selling Price" , "Available Quantity","Deferred Quantity" });
						productTable = new JTable();
						productScrollPane.setViewportView(productTable);
						productTable.setModel(productTableModel);
					}
				}
				{
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1, new AnchorConstraint(99, 502, 149, 147, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField1.setPreferredSize(new java.awt.Dimension(337, 22));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(94, 156, 156, 12, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Product Code/Name");
					jLabel2.setPreferredSize(new java.awt.Dimension(137, 27));
				}
				{
					inventoryViewerLabel = new JLabel();
					getContentPane().add(inventoryViewerLabel, new AnchorConstraint(14, 351, 80, 13, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					inventoryViewerLabel.setText("View Current Inventory");
					inventoryViewerLabel.setFont(new java.awt.Font("Tahoma",0,18));
					inventoryViewerLabel.setPreferredSize(new java.awt.Dimension(322, 29));
					inventoryViewerLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "inventoryViewerLabel");
					inventoryViewerLabel.getActionMap().put("inventoryViewerLabel",getInventoryViewerLabelAbstractAction() );
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
	private AbstractAction getInventoryViewerLabelAbstractAction() {
		AbstractAction inventoryViewerLabelAction = new AbstractAction("View Current Inventory", null) {
			
			public void actionPerformed(ActionEvent evt) {
				InventoryViewerDialog.this.dispose();
			}
		};
		return inventoryViewerLabelAction;
	}
	
	private JComboBox getStoreComboBox() {
		if(storeComboBox == null) {
			ComboBoxModel storeComboBoxModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
			storeComboBox = new JComboBox();
			storeComboBox.setModel(storeComboBoxModel);
			storeComboBox.setPreferredSize(new java.awt.Dimension(114, 22));
		}
		return storeComboBox;
	}
	
	private JLabel getStoreLabel() {
		if(storeLabel == null) {
			storeLabel = new JLabel();
			storeLabel.setText("Store");
			storeLabel.setPreferredSize(new java.awt.Dimension(40, 16));
		}
		return storeLabel;
	}

}

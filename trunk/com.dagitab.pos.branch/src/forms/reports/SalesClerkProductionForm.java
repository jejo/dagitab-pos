package forms.reports;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import main.ResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import reports.SalesCerkProduction;

import com.cloudgarden.resource.SWTResourceManager;

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
public class SalesClerkProductionForm extends javax.swing.JDialog {
	private JLabel salesClerkLabel;
	private JTextField txtClerk;
	private JLabel jLabel2;
	private JTable jTable1;
	private JButton jButton3;
	private JButton jButton2;
	private JScrollPane jScrollPane1;
	private String storeCode;
	private DBManager db;
	private JButton btnSearch;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private Shell shell1;
	private Canvas canvas1;
	private String startDate; 
	private String endDate;
	private DateTime calendar1;
	private DateTime calendar2;
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SalesClerkProductionForm inst = new SalesClerkProductionForm(frame);
		inst.setVisible(true);
	}
	
	public SalesClerkProductionForm(JFrame frame) {
		super(frame);
		initSwtAwtGUI();
	}
	
	public SalesClerkProductionForm(DBManager db, String storeCode, JFrame frame) {
		this(frame);
		this.db = db;
		this.storeCode = storeCode;
	}
	
	private void initGUI() {
		try {{
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				{
					jLabel4 = new JLabel();
					getContentPane().add(
						jLabel4,
						new AnchorConstraint(
							519,
							568,
							568,
							457,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("End Date");
					jLabel4.setPreferredSize(new java.awt.Dimension(63, 28));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(
						jLabel3,
						new AnchorConstraint(
							519,
							161,
							568,
							50,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Start Date");
					jLabel3.setPreferredSize(new java.awt.Dimension(63, 28));
				}
				{
					salesClerkLabel = new JLabel();
					getContentPane().add(salesClerkLabel, new AnchorConstraint(37, 420, 87, 37, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					salesClerkLabel.setText("Sales Clerk Production");
					salesClerkLabel.setFont(new java.awt.Font("Tahoma",1,14));
					salesClerkLabel.setPreferredSize(new java.awt.Dimension(217, 28));
					salesClerkLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "salesClerkLabel");
					salesClerkLabel.getActionMap().put("salesClerkLabel",getSalesClerkLabelAbstractAction() );
				}
				
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(186, 951, 494, 37, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(518, 175));
					{
						TableModel jTable1Model = new DefaultTableModel(
							new String[][]{},
							new String[] { "Clerk Code", "Name" });
						jTable1 = new JTable();
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
					}
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(926, 210, 976, 37, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Export");
					jButton2.setPreferredSize(new java.awt.Dimension(98, 28));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int[] selectedRows = jTable1.getSelectedRows();
							String [] clerk_codes = new String[selectedRows.length];
							for(int i = 0; i<selectedRows.length; i++){
								clerk_codes[i] = jTable1.getValueAt(selectedRows[i],0).toString();
							}
							
							if(startDate == null){
								startDate = getCurrentDate();
							}
							if(endDate == null){
								endDate = getCurrentDate();
							}
							
							JFileChooser fc = new JFileChooser();
							fc.setDialogType(JFileChooser.SAVE_DIALOG);
							FileFilter filter = createFileFilter("Excel Files Only",true,new String[]{"xls"});
						  	fc.setFileFilter(filter);
						  	
						  	int returnVal = fc.showSaveDialog(SalesClerkProductionForm.this);
						    if(returnVal == JFileChooser.APPROVE_OPTION) {
						    	String fileName = fc.getSelectedFile().getAbsolutePath()+".xls";
						    	boolean success = SalesCerkProduction.generatePerClerk(fileName, db, storeCode, startDate, endDate, clerk_codes);
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
					jButton3 = new JButton();
					getContentPane().add(jButton3, new AnchorConstraint(926, 939, 976, 766, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("Close");
					jButton3.setPreferredSize(new java.awt.Dimension(98, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							SalesClerkProductionForm.this.dispose();
						}
					});
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(111, 198, 161, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Clerk First Name");
					jLabel2.setPreferredSize(new java.awt.Dimension(84, 28));
				}
				{
					txtClerk = new JTextField();
					getContentPane().add(txtClerk, new AnchorConstraint(111, 655, 161, 210, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					txtClerk.setPreferredSize(new java.awt.Dimension(252, 28));
				}
				{
					btnSearch = new JButton();
					getContentPane().add(btnSearch, new AnchorConstraint(111, 852, 161, 679, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					btnSearch.setText("Search");
					btnSearch.setPreferredSize(new java.awt.Dimension(98, 28));
					btnSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String query = "SELECT * FROM clerk_lu WHERE FIRST_NAME LIKE \""+txtClerk.getText()+"%\"";
							ResultSet rs = db.executeQuery(query);
							try {
								FillClerkTable(rs);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
				{
					canvas1 = new Canvas();
					getContentPane().add(canvas1, new AnchorConstraint(556, 951, 889, 37, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					canvas1.setPreferredSize(new java.awt.Dimension(518, 189));
				}
				{
					shell1 = SWT_AWT.new_Shell(Display.getDefault(), canvas1);
					FormLayout shell1Layout1 = new FormLayout();
					shell1.setLayout(shell1Layout1);
				}
				
				{
					calendar1 = new DateTime (shell1, SWT.CALENDAR);
					FormData calendar1LData = new FormData();
					calendar1LData.width = 176;
					calendar1LData.height = 145;
					calendar1LData.left =  new FormAttachment(15, 1000, 0);
					calendar1LData.right =  new FormAttachment(393, 1000, 0);
					calendar1LData.top =  new FormAttachment(42, 1000, 0);
					calendar1LData.bottom =  new FormAttachment(962, 1000, 0);
					calendar1.setLayoutData(calendar1LData);
					calendar1.setBackground(SWTResourceManager.getColor(255, 255, 255));
					calendar1.addSelectionListener (new SelectionAdapter () {
						@Override
						public void widgetSelected (SelectionEvent e) {
							String m = ResourceManager.pad(calendar1.getMonth()+1, 2);
							String d = ResourceManager.pad(calendar1.getDay(), 2);
							startDate = calendar1.getYear()+"-"+m+"-"+d;
						}
						
					});
					
				}
				{
					calendar2 = new DateTime (shell1, SWT.CALENDAR);
					FormData calendar1LData1 = new FormData();
					calendar1LData1.width = 176;
					calendar1LData1.height = 145;
					calendar1LData1.left =  new FormAttachment(465, 1000, 0);
					calendar1LData1.right =  new FormAttachment(843, 1000, 0);
					calendar1LData1.top =  new FormAttachment(42, 1000, 0);
					calendar1LData1.bottom =  new FormAttachment(962, 1000, 0);
					calendar2.setLayoutData(calendar1LData1);
					calendar2.setBackground(SWTResourceManager.getColor(255, 255, 255));
					calendar2.addSelectionListener (new SelectionAdapter () {
						@Override
						public void widgetSelected (SelectionEvent e) {
							String m = ResourceManager.pad(calendar2.getMonth()+1, 2);
							String d = ResourceManager.pad(calendar2.getDay(), 2);
							endDate = calendar2.getYear()+"-"+m+"-"+d;
						}
						
						
					});
				}

			}
			this.setSize(575, 594);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void FillClerkTable(ResultSet rs) throws SQLException{
		Vector<Vector<String>> data = new Vector<Vector<String>>(); 
		Vector<String> colHeader = new Vector<String>();
		colHeader.add("Clerk Code");
		colHeader.add("First Name");
		
		while(rs.next()){
			Vector<String> row = new Vector<String>();
			row.add(rs.getString("CLERK_CODE"));
			row.add(rs.getString("FIRST_NAME"));
			data.add(row);
		}
		
		
		TableModel jTable1Model = new DefaultTableModel(data,colHeader);
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
	}
	private String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
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
	
	private AbstractAction getSalesClerkLabelAbstractAction() {
		AbstractAction salesClerkLabelAction = new AbstractAction("Sales Clerk Production", null) {
			
			public void actionPerformed(ActionEvent evt) {
				SalesClerkProductionForm.this.dispose();
			}
		};
		return salesClerkLabelAction;
	}

	//$protect>>$
	//===== start of SWT_AWT special handler code =============

	/**
	 * This method should be called instead of initGUI to initialize
	 * and make visible this GUI, since it handles all the threading
	 * and other "quirks" of embedding SWT objects inside AWT ones.
	 */
	public void initSwtAwtGUI() {
		new DisplayThread().start();
	}
		
	/**
	 * This class makes sure that the SWT controls will be created
	 * and behave correctly
	 */
	private class DisplayThread extends Thread {

		public void run() {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					//make sure the GUI is created inside the SWT display thread
					//otherwise you will get invalid-thread-access errors, and
					//make sure it is visible before calling the SWT_AWT.new_Shell
					//method, otherwise a "No handles" error will be thrown.
					setVisible(true);
					initGUI();
				}
			});
			
			//"wiggling" the size is one way to make sure that the
			//SWT controls are displayed correctly
			java.awt.Dimension sz = getSize();
			int w = sz.width;
			int h = sz.height;
			setSize(w+1, h);
			validate();
			setSize(w, h);
			validate();
			
			swtEventLoop();
		}

		/**
		 * Listen for and dispatch SWT events
		 */
		private void swtEventLoop() {
			Display display = Display.getDefault();
			while (true) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}

	}

	//===== end of SWT_AWT special handler code =============
	//$protect<<$
	



}

package com.dagitab.pos.forms.reports;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import com.dagitab.pos.main.OLEViewer;
import com.dagitab.pos.reports.EastwoodSalesReport;
import com.dagitab.pos.util.DateUtility;
import com.dagitab.pos.util.FileChooserUtility;
import com.dagitab.pos.util.LoggerUtility;



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
public class EastwoodReportDialog extends javax.swing.JDialog {
	private JLabel monthLabel;
	private JComboBox jComboBox1;
	private JTextField yearText;
	private JButton exportButton;
	private AbstractAction saveAbstractAction;
	private AbstractAction exportAbstractAction;
	private AbstractAction closeAbstractAction;
	private JButton closeButton;
	private JButton saveButton;
	private JLabel yearLabel;

	
	
	public EastwoodReportDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Eastwood Sales Report");
				this.setModal(true);
				{
					monthLabel = new JLabel();
					getContentPane().add(monthLabel);
					monthLabel.setText("Month");
					monthLabel.setBounds(41, 25, 36, 16);
				}
				{
					
					ComboBoxModel jComboBox1Model = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getAllMonths());
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1);
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setBounds(104, 22, 250, 22);
					jComboBox1.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
				}
				{
					yearLabel = new JLabel();
					getContentPane().add(yearLabel);
					yearLabel.setText("Year");
					yearLabel.setBounds(41, 66, 23, 16);
				}
				{
					yearText = new JTextField();
					getContentPane().add(yearText);
					yearText.setBounds(104, 63, 128, 22);
					yearText.setText(Calendar.getInstance().get(Calendar.YEAR)+"");
				}
				{
					exportButton = new JButton();
					getContentPane().add(exportButton);
					exportButton.setText("Export");
					exportButton.setBounds(45, 108, 66, 22);
					exportButton.setAction(getExportAbstractAction());
				}
				{
					saveButton = new JButton();
					getContentPane().add(saveButton);
					saveButton.setText("Save Report");
					saveButton.setBounds(122, 108, 97, 22);
					saveButton.setAction(getSaveAbstractAction());
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(302, 108, 62, 22);
					closeButton.setAction(getCloseAbstractAction());
				}
			}
			this.setSize(400, 184);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAction getCloseAbstractAction() {
		if(closeAbstractAction == null) {
			closeAbstractAction = new AbstractAction("Close", null) {
				public void actionPerformed(ActionEvent evt) {
					EastwoodReportDialog.this.dispose();
				}
			};
		}
		return closeAbstractAction;
	}
	
	private AbstractAction getExportAbstractAction() {
		if(exportAbstractAction == null) {
			exportAbstractAction = new AbstractAction("Export", null) {
				public void actionPerformed(ActionEvent evt) {
					String fileName = "temp.xls";
					
					boolean success = false;
					try {
						success = (new EastwoodSalesReport()).generate(fileName,jComboBox1.getSelectedIndex(), Integer.parseInt(yearText.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"Number Format Error","Error",JOptionPane.ERROR_MESSAGE);
						LoggerUtility.getInstance().logStackTrace(e1);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						LoggerUtility.getInstance().logStackTrace(e1);
					}
					if(success){
						try {
							OLEViewer.open2(fileName);
						} catch (IOException e) {
							LoggerUtility.getInstance().logStackTrace(e);
						}
//						JOptionPane.showMessageDialog(null,"Successfully saved daily sales report.","Saved",JOptionPane.INFORMATION_MESSAGE);
			        }
			        else{
			    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
			        }
				}
			};
		}
		return exportAbstractAction;
	}
	
	private AbstractAction getSaveAbstractAction() {
		if(saveAbstractAction == null) {
			saveAbstractAction = new AbstractAction("Save Report", null) {
				public void actionPerformed(ActionEvent evt) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
					FileFilter fileFilter = FileChooserUtility.getFileChooserUtility().createFileFilter("Excel Files Only",true,new String[]{"xls"});
				  	fileChooser.setFileFilter(fileFilter);
				  	int returnValue = fileChooser.showSaveDialog(EastwoodReportDialog.this);
				    if(returnValue == JFileChooser.APPROVE_OPTION) {
				    	String fileName = fileChooser.getSelectedFile().getAbsolutePath()+".xls";
				    	boolean success = false;
						try {
							success = (new EastwoodSalesReport()).generate(fileName,jComboBox1.getSelectedIndex(), Integer.parseInt(yearText.getText()));
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"Number Format Error","Error",JOptionPane.ERROR_MESSAGE);
							LoggerUtility.getInstance().logStackTrace(e);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
							LoggerUtility.getInstance().logStackTrace(e);
						}
						if(success){
							JOptionPane.showMessageDialog(null,"Successfully saved daily sales report.","Saved",JOptionPane.INFORMATION_MESSAGE);
				        }
				        else{
				    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
				        }
				    }
				    
				}
			};
		}
		return saveAbstractAction;
	}

}

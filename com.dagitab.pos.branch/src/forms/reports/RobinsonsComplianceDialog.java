package forms.reports;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import util.ComplianceFileReader;
import util.DateUtility;
import util.TableUtility;
import bus.RobinsonsComplianceService;

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
public class RobinsonsComplianceDialog extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JTextArea fileTextArea;
	private JScrollPane unsentItemScrollPane;
	private JComboBox filterMonthComboBox;
	private AbstractAction sendMonthAction;
	private JComboBox viewLastComboBox;
	private JLabel viewLast;
	private AbstractAction closeButtonAction;
	private JButton sendByDateButton;
	private AbstractAction sendUnsentAction;
	private AbstractAction sendAllUnsentAction;
	private AbstractAction viewLastDaysAction;
	private JLabel dayLabel;
	private AbstractAction viewFileAction;
	private JButton viewFileButton;
	private AbstractAction resendDateAction;
	private AbstractAction sendByDateAction;
	private AbstractAction filterYearAction;
	private AbstractAction filterMonthAction;
	private AbstractAction sendYearAction;
	private JTable unsentItemTable;
	private JTable sentItemTable;
	private JComboBox sendYearComboBox;
	private JComboBox sendDayComboBox;
	private JComboBox sendMonthComboBox;
	private JLabel sendByDateLabel;
	private JButton closeButton;
	private JButton sendAllButton;
	private JButton sendButton;
	private JButton reSendButton;
	private JLabel sentDatesLabel;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JComboBox filterYearComboBox;
	private JLabel filterLabel;

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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				RobinsonsComplianceDialog inst = new RobinsonsComplianceDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public RobinsonsComplianceDialog(JFrame frame) {
		super(frame);
		initGUI();
		init();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Robinsons Compliance");
				getContentPane().setLayout(null);
				this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				this.setModal(true);
				{
					titleLabel = new JLabel();
					getContentPane().add(titleLabel);
					titleLabel.setText("Robinsons Compliance Form");
					titleLabel.setBounds(10, 11, 236, 23);
					titleLabel.setFont(new java.awt.Font("Tahoma",0,18));
				}
				{
					fileTextArea = new JTextArea();
					getContentPane().add(fileTextArea);
					fileTextArea.setBounds(448, 45, 422, 509);
					fileTextArea.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					fileTextArea.setEditable(false);
				}
				{
					unsentItemScrollPane = new JScrollPane();
					getContentPane().add(unsentItemScrollPane);
					unsentItemScrollPane.setBounds(10, 379, 412, 146);
					unsentItemScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						final TableModel sentItemTableModel = 
							new DefaultTableModel(
									new String[][] { { "2008-01-02", "file1.txt" }, { "2008-01-03", "file2.txt" } },
									new String[] { "Date", "File Name" });
						
						sentItemTable = new JTable(){
							@Override
							public boolean isCellEditable(int row,int column) {
								return false;
							}
						};
						sentItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						sentItemTable.getSelectionModel().addListSelectionListener(
							new ListSelectionListener(){

								@Override
								public void valueChanged(ListSelectionEvent e) {
//									 ListSelectionModel lsm = (ListSelectionModel)e.getSource();
//									 int selected = lsm.getMaxSelectionIndex();
									 
						        }
							}
						);
						
						unsentItemScrollPane.setViewportView(sentItemTable);
						sentItemTable.setModel(sentItemTableModel);
						
						
					}
				}
				{
					filterLabel = new JLabel();
					getContentPane().add(filterLabel);
					filterLabel.setText("Filter Month/Year");
					filterLabel.setBounds(10, 356, 93, 14);
				}
				{
					ComboBoxModel filterMonthComboBoxModel = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getAllMonths());
					filterMonthComboBox = new JComboBox();
					getContentPane().add(filterMonthComboBox);
					filterMonthComboBox.setModel(filterMonthComboBoxModel);
					filterMonthComboBox.setBounds(122, 351, 80, 22);
					filterMonthComboBox.setAction(getFilterMonthAction());
				}
				{
					ComboBoxModel filterYearComboBoxModel = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getYears());
					filterYearComboBox = new JComboBox();
					getContentPane().add(filterYearComboBox);
					filterYearComboBox.setModel(filterYearComboBoxModel);
					filterYearComboBox.setBounds(212, 351, 65, 22);
					filterYearComboBox.setAction(getFilterYearAction());
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1);
					jScrollPane1.setBounds(10, 162, 413, 117);
					jScrollPane1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						TableModel unsentItemTableModel = 
							new DefaultTableModel(
									new String[][] { {  }},
									new String[] { "Date" });
						unsentItemTable = new JTable();
						jScrollPane1.setViewportView(unsentItemTable);
						unsentItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						unsentItemTable.setModel(unsentItemTableModel);
					}
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Unsent Dates");
					jLabel1.setBounds(10, 146, 84, 14);
					jLabel1.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					sentDatesLabel = new JLabel();
					getContentPane().add(sentDatesLabel);
					sentDatesLabel.setText("Sent Dates");
					sentDatesLabel.setBounds(10, 331, 70, 14);
					sentDatesLabel.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					reSendButton = new JButton();
					getContentPane().add(reSendButton);
					reSendButton.setText("Resend");
					reSendButton.setBounds(10, 531, 75, 23);
					reSendButton.setAction(getResendDateAction());
				}
				{
					sendButton = new JButton();
					getContentPane().add(sendButton);
					sendButton.setText("Send");
					sendButton.setBounds(363, 285, 59, 23);
					sendButton.setAction(getSendUnsentAction());
				}
				{
					sendAllButton = new JButton();
					getContentPane().add(sendAllButton);
					sendAllButton.setText("Send All");
					sendAllButton.setBounds(10, 285, 73, 23);
					sendAllButton.setAction(getSendAllUnsentAction());
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(809, 583, 61, 23);
					closeButton.setAction(getCloseButtonAction());
				}
				{
					sendByDateLabel = new JLabel();
					getContentPane().add(sendByDateLabel);
					sendByDateLabel.setText("Send By Date");
					sendByDateLabel.setBounds(10, 45, 93, 14);
					sendByDateLabel.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					ComboBoxModel sendMonthComboBoxModel =new DefaultComboBoxModel(DateUtility.getDateUtility().getAllMonths());
					sendMonthComboBox = new JComboBox();
					getContentPane().add(sendMonthComboBox);
					sendMonthComboBox.setModel(sendMonthComboBoxModel);
					sendMonthComboBox.setBounds(10, 65, 73, 22);
					sendMonthComboBox.setAction(getSendMonthAction());
				}
				
				{
					ComboBoxModel sendYearComboBoxModel = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getYears());
					sendYearComboBox = new JComboBox();
					getContentPane().add(sendYearComboBox);
					sendYearComboBox.setModel(sendYearComboBoxModel);
					sendYearComboBox.setBounds(156, 65, 65, 22);
					sendYearComboBox.setAction(getSendYearAction());
				}
				{
					ComboBoxModel sendDayComboBoxModel = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getDaysOfMonth(sendMonthComboBox.getSelectedIndex(), 
																Integer.parseInt(sendYearComboBox.getSelectedItem().toString())));
					sendDayComboBox = new JComboBox();
					getContentPane().add(sendDayComboBox);
					sendDayComboBox.setModel(sendDayComboBoxModel);
					sendDayComboBox.setBounds(93, 65, 53, 22);
				}
				{
					sendByDateButton = new JButton();
					getContentPane().add(sendByDateButton);
					sendByDateButton.setText("Send");
					sendByDateButton.setBounds(227, 65, 59, 23);
					sendByDateButton.setAction(getSendByDateAction());
				}
				{
					viewLast = new JLabel();
					getContentPane().add(viewLast);
					viewLast.setText("View Last");
					viewLast.setBounds(10, 121, 45, 14);
				}
				{
					ComboBoxModel viewLastComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					viewLastComboBox = new JComboBox();
					getContentPane().add(viewLastComboBox);
					getContentPane().add(getViewFileButton());
					getContentPane().add(getDayLabel());
					viewLastComboBox.setModel(viewLastComboBoxModel);
					viewLastComboBox.setBounds(70, 117, 76, 22);
					viewLastComboBox.setAction(getViewLastDaysAction());
				}
			}
			this.setSize(896, 653);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getCloseButtonAction() {
		if(closeButtonAction == null) {
			closeButtonAction = new AbstractAction("Close", null) {
				public void actionPerformed(ActionEvent evt) {
					RobinsonsComplianceDialog.this.dispose();
				}
			};
		}
		return closeButtonAction;
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getSendMonthAction() {
		if(sendMonthAction == null) {
			sendMonthAction = new AbstractAction("", null) {
				public void actionPerformed(ActionEvent evt) {
					ComboBoxModel comboBoxModel = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getDaysOfMonth(sendMonthComboBox.getSelectedIndex(), 
																Integer.parseInt(sendYearComboBox.getSelectedItem().toString())));
					sendDayComboBox.setModel(comboBoxModel);
				}
			};
		}
		return sendMonthAction;
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getSendYearAction() {
		if(sendYearAction == null) {
			sendYearAction = new AbstractAction("", null) {
				public void actionPerformed(ActionEvent evt) {
					ComboBoxModel comboBoxModel = 
						new DefaultComboBoxModel(DateUtility.getDateUtility().getDaysOfMonth(sendMonthComboBox.getSelectedIndex(), 
																Integer.parseInt(sendYearComboBox.getSelectedItem().toString())));
					sendDayComboBox.setModel(comboBoxModel);
				}
			};
		}
		return sendYearAction;
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getFilterMonthAction() {
		if(filterMonthAction == null) {
			filterMonthAction = new AbstractAction("", null) {
				public void actionPerformed(ActionEvent evt) {
					int year = Integer.parseInt(filterYearComboBox.getSelectedItem().toString());
					int month = filterMonthComboBox.getSelectedIndex()+1;
					filterSentItemTableByDate(year,month , 0);
				}
			};
		}
		return filterMonthAction;
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getFilterYearAction() {
		if(filterYearAction == null) {
			filterYearAction = new AbstractAction("", null) {
				public void actionPerformed(ActionEvent evt) {
					int year = Integer.parseInt(filterYearComboBox.getSelectedItem().toString());
					int month = filterMonthComboBox.getSelectedIndex()+1;
					filterSentItemTableByDate(year,month , 0);
				}
			};
		}
		return filterYearAction;
	}
	
	private AbstractAction getSendByDateAction() {
		if(sendByDateAction == null) {
			sendByDateAction = new AbstractAction("Send", null) {
				public void actionPerformed(ActionEvent evt) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, Integer.parseInt(sendYearComboBox.getSelectedItem().toString()));
					calendar.set(Calendar.MONTH, sendMonthComboBox.getSelectedIndex());
					calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sendDayComboBox.getSelectedItem().toString()));
					System.out.println("*****generating robinsons compliance report for date: "+calendar.getTime());
					try {
						RobinsonsComplianceService.getInstance().generateAndSendComplianceReport(calendar.getTime());
						JOptionPane.showMessageDialog(null, "Successfully generated compliance report.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
						init();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Compliance report was not successfully generated. Please try again.", "Prompt", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			};
		}
		return sendByDateAction;
	}
	
	//initialize form after initGUI
	private void init(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT REPORT_DATE, FILENAME FROM robinsons_compliance ORDER BY REPORT_DATE DESC LIMIT 30");
		TableUtility.fillTable(sentItemTable, rs, new String[]{"Date","File Name"});
		
		ComboBoxModel viewLastComboBoxModel =new DefaultComboBoxModel(new String[] { "7", "15","30" });
		viewLastComboBox.setModel(viewLastComboBoxModel);

		//Default view last 7 days unsent reports
		List<Date> listDates = RobinsonsComplianceService.getInstance().getUnsentComplianceReports(7);
		System.out.println("date list: "+listDates.size());
		String[][] data = new String[listDates.size()][1];
		for(int i = 0; i<listDates.size(); i++){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(listDates.get(i));
			int month = calendar.get(Calendar.MONTH)+1;
			int year = calendar.get(Calendar.YEAR);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			data[i][0] = year+"-"+month+"-"+day;
			System.out.println("ALEX: "+year+"-"+month+"-"+day);
		}
		DefaultTableModel model = new DefaultTableModel(data,new String[]{"Dates"});
		unsentItemTable.setModel(model);
		
		
	}
	
	private void filterSentItemTableByDate(int year, int month, int day){
		String query = "SELECT REPORT_DATE, FILENAME FROM robinsons_compliance";
		String yearClause = " YEAR(REPORT_DATE) = '"+year+"' ";
		String monthClause = " MONTH(REPORT_DATE) = '"+month+"' ";
		String dayClause = " DAY (REPORT_DATE) = '"+day+"'";
		String orderClause = " ORDER BY REPORT_DATE DESC LIMIT 30";
		
		int whereCounter = 0;
		if(year == 0 && month ==0 && day==0){
			ResultSet rs = Main.getDBManager().executeQuery("SELECT REPORT_DATE, FILENAME FROM robinsons_compliance ORDER BY REPORT_DATE DESC LIMIT 30");
			TableUtility.fillTable(sentItemTable, rs, new String[]{"Date","File Name"});
		}
		else{
			query += " WHERE ";
			if(year > 0){
				if(whereCounter>0){
					query += " AND ";
				}
				query += yearClause;
				whereCounter++;
			}
			if(month > 0){
				if(whereCounter>0){
					query += " AND ";
				}
				query += monthClause;
				whereCounter++;
			}
			if(day > 0){
				if(whereCounter>0){
					query += " AND ";
				}
				query += dayClause;
				whereCounter++;
			}
			System.out.println(query+orderClause);
			ResultSet rs = Main.getDBManager().executeQuery(query+orderClause);
			TableUtility.fillTable(sentItemTable, rs, new String[]{"Date","File Name"});
		}
		
		
		
		
		
	}
	
	private AbstractAction getResendDateAction() {
		if(resendDateAction == null) {
			resendDateAction = new AbstractAction("Resend", null) {
				public void actionPerformed(ActionEvent evt) {
					int row = sentItemTable.getSelectedRow();
					String dateString = sentItemTable.getValueAt(row, 0).toString();
					System.out.println(dateString);
					String[] dateSplit = dateString.split("-");
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]));
					calendar.set(Calendar.MONTH, Integer.parseInt(dateSplit[1])-1);
					calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]));
					System.out.println("generating robinsons compliace report for date: "+calendar.getTime());
					try {
						
						RobinsonsComplianceService.getInstance().generateAndSendComplianceReport(calendar.getTime());
						
						JOptionPane.showMessageDialog(null, "Successfully generated compliance report.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
						init();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Compliance report was not successfully generated. Please try again.", "Prompt", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
				}
			};
		}
		return resendDateAction;
	}
	
	private JButton getViewFileButton() {
		if(viewFileButton == null) {
			viewFileButton = new JButton();
			viewFileButton.setText("View File");
			viewFileButton.setBounds(347, 531, 75, 23);
			viewFileButton.setAction(getViewFileAction());
		}
		return viewFileButton;
	}
	
	private AbstractAction getViewFileAction() {
		if(viewFileAction == null) {
			viewFileAction = new AbstractAction("View File", null) {
				public void actionPerformed(ActionEvent evt) {
					int selected = sentItemTable.getSelectedRow();
					String filename = sentItemTable.getValueAt(selected, 1).toString();
					File file = new File("compliance/"+filename);
					fileTextArea.setText(ComplianceFileReader.getComplianceFileReader().getFileContents(file));
				}
			};
		}
		return viewFileAction;
	}
	
	private JLabel getDayLabel() {
		if(dayLabel == null) {
			dayLabel = new JLabel();
			dayLabel.setText("Days");
			dayLabel.setBounds(156, 121, 24, 14);
		}
		return dayLabel;
	}
	
	private AbstractAction getViewLastDaysAction() {
		if(viewLastDaysAction == null) {
			viewLastDaysAction = new AbstractAction("", null) {
				public void actionPerformed(ActionEvent evt) {
					String viewDay = viewLastComboBox.getSelectedItem().toString();
					List<Date> listDates = RobinsonsComplianceService.getInstance().getUnsentComplianceReports(Integer.parseInt(viewDay));
					System.out.println("date list: "+listDates.size());
					String[][] data = new String[listDates.size()][1];
					for(int i = 0; i<listDates.size(); i++){
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis((listDates.get(i).getTime()));
						int month = calendar.get(Calendar.MONTH)+1;
						int year = calendar.get(Calendar.YEAR);
						int day = calendar.get(Calendar.DAY_OF_MONTH);
						
						data[i][0] = year+"-"+month+"-"+day;
						System.out.println("ALEX: "+year+"-"+month+"-"+day);
					}
					DefaultTableModel model = new DefaultTableModel(data,new String[]{"Dates"});
					unsentItemTable.setModel(model);
				}
			};
		}
		return viewLastDaysAction;
	}
	
	private AbstractAction getSendAllUnsentAction() {
		if(sendAllUnsentAction == null) {
			sendAllUnsentAction = new AbstractAction("Send All", null) {
				public void actionPerformed(ActionEvent evt) {
					DefaultTableModel model = (DefaultTableModel) unsentItemTable.getModel();
					Calendar calendar = Calendar.getInstance();
					ArrayList<String> sentDatesList = new ArrayList<String>();
					ArrayList<String> unsentDateList = new ArrayList<String>();
					for(int i = 0; i<model.getRowCount(); i++){
						String dateString = model.getValueAt(i, 0).toString();
						String[] dateSplit = dateString.split("-");
						calendar.set(Calendar.MONTH, Integer.parseInt(dateSplit[1])-1);
						calendar.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]));
						calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]));
						try {
							RobinsonsComplianceService.getInstance().generateAndSendComplianceReport(calendar.getTime());
							sentDatesList.add(dateString);
							
						} catch (FileNotFoundException e) {
//							JOptionPane.showMessageDialog(null, "Compliance report was not successfully generated. Please try again.", "Prompt", JOptionPane.ERROR_MESSAGE);
							unsentDateList.add(dateString);
						}
					}
					
					if(sentDatesList.size() == model.getRowCount()){
						JOptionPane.showMessageDialog(null, "Successfully generated compliance report for all dates.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
						init();
					}
					else{
						String unsentDates = "";
						for(String s: unsentDateList){
							unsentDates += s;
						}
						
						JOptionPane.showMessageDialog(null, "There were dates that weren't sent. These are: "+unsentDates, "Prompt", JOptionPane.ERROR_MESSAGE);
					}
				}
			};
		}
		return sendAllUnsentAction;
	}
	
	private AbstractAction getSendUnsentAction() {
		if(sendUnsentAction == null) {
			sendUnsentAction = new AbstractAction("Send", null) {
				public void actionPerformed(ActionEvent evt) {
					DefaultTableModel model = (DefaultTableModel) unsentItemTable.getModel();
					Calendar calendar = Calendar.getInstance();
					String dateString = model.getValueAt(unsentItemTable.getSelectedRow(), 0).toString();
					String[] dateSplit = dateString.split("-");
					calendar.set(Calendar.MONTH, Integer.parseInt(dateSplit[1])-1);
					calendar.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]));
					calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]));
					try {
						RobinsonsComplianceService.getInstance().generateAndSendComplianceReport(calendar.getTime());
						JOptionPane.showMessageDialog(null, "Successfully generated compliance report.", "Prompt", JOptionPane.INFORMATION_MESSAGE);
						init();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Compliance report was not successfully generated. Please try again.", "Prompt", JOptionPane.ERROR_MESSAGE);
					}
				}
			};
		}
		return sendUnsentAction;
	}

}

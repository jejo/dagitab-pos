package forms;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import util.LoggerUtility;

import com.babyland.debugger.Messenger;


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
public class DebuggerDialog extends javax.swing.JDialog {
	private JLabel headLabel;
	private JLabel headLabel2;
	private JTextArea taskTextArea;
	private JLabel taskLabel;
	private JLabel logLabel;
	private JComboBox logComboBox;
	private AbstractAction closeAbstractAction;
	private AbstractAction taskAbstractAction;
	private AbstractAction logAbstractAction;
	private JButton logButton;
	private JButton sendButton;
	private JButton closeButton;
	private JScrollPane taskScrollPane;
	private static Logger logger = Logger.getLogger(DebuggerDialog.class);
	private static LoggerUtility loggerUtility = LoggerUtility.getInstance();

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				DebuggerDialog inst = new DebuggerDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public DebuggerDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Babyland Debugger");
				getContentPane().setLayout(null);
				this.setModal(true);
				{
					headLabel = new JLabel();
					getContentPane().add(headLabel);
					headLabel.setText("This module will send email to dagitab administrators based ");
					headLabel.setBounds(12, 9, 353, 24);
				}
				{
					headLabel2 = new JLabel();
					getContentPane().add(headLabel2);
					headLabel2.setText("from the task string that we will provide.");
					headLabel2.setBounds(12, 30, 293, 16);
				}
				{
					taskScrollPane = new JScrollPane();
					getContentPane().add(taskScrollPane);
					taskScrollPane.setBounds(12, 70, 360, 89);
					{
						taskTextArea = new JTextArea();
						taskScrollPane.setViewportView(taskTextArea);
						taskTextArea.setPreferredSize(new java.awt.Dimension(357, 68));
					}
				}
				{
					taskLabel = new JLabel();
					getContentPane().add(taskLabel);
					taskLabel.setText("Task String");
					taskLabel.setBounds(12, 52, 58, 16);
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(156, 282, 87, 22);
					closeButton.setAction(getCloseAbstractAction());
				}
				{
					sendButton = new JButton();
					getContentPane().add(sendButton);
					getContentPane().add(getLogComboBox());
					getContentPane().add(getLogLabel());
					getContentPane().add(getLogButton());
					sendButton.setText("Send SQL");
					sendButton.setBounds(269, 165, 104, 22);
					sendButton.setAction(getTaskAbstractAction());
				}
			}
			this.setSize(400, 351);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getTaskAbstractAction() {
		if(taskAbstractAction == null) {
			taskAbstractAction = new AbstractAction("Send", null) {
				public void actionPerformed(ActionEvent evt) {
					Messenger messenger = new Messenger();
					try {
						if(taskTextArea.getText().trim().length() > 0){
							messenger.send(taskTextArea.getText());
							JOptionPane.showMessageDialog(null, "Successfully sent the debug information", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null, "Please insert a valid task string", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "File Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "IO Error", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					} catch (SQLException e) {
						loggerUtility.logStackTrace(e);
						JOptionPane.showMessageDialog(null, "SQL Error", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (EmailException e) {
						JOptionPane.showMessageDialog(null, "Cannot send Email", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "An error has occured.", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					}
					
					
					
				}
			};
		}
		return taskAbstractAction;
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getCloseAbstractAction() {
		if(closeAbstractAction == null) {
			closeAbstractAction = new AbstractAction("Close", null) {
				public void actionPerformed(ActionEvent evt) {
					DebuggerDialog.this.dispose();
				}
			};
		}
		return closeAbstractAction;
	}
	
	private JComboBox getLogComboBox() {
		if(logComboBox == null) {
			ComboBoxModel logComboBoxModel = 
				new DefaultComboBoxModel(
						new String[] { "error.log", "out.log" });
			logComboBox = new JComboBox();
			logComboBox.setModel(logComboBoxModel);
			logComboBox.setBounds(12, 209, 360, 22);
		}
		return logComboBox;
	}
	
	private JLabel getLogLabel() {
		if(logLabel == null) {
			logLabel = new JLabel();
			logLabel.setText("Logs");
			logLabel.setBounds(12, 188, 25, 16);
		}
		return logLabel;
	}
	
	private JButton getLogButton() {
		if(logButton == null) {
			logButton = new JButton();
			logButton.setText("Send Logs");
			logButton.setBounds(269, 243, 102, 23);
			logButton.setAction(getLogAbstractAction());
		}
		return logButton;
	}
	
	private AbstractAction getLogAbstractAction() {
		if(logAbstractAction == null) {
			logAbstractAction = new AbstractAction("Send Log", null) {
				public void actionPerformed(ActionEvent evt) {
					String logDirectory = "logs/";
					String logFile = logDirectory+logComboBox.getSelectedItem().toString();
					
					Messenger messenger = new Messenger();
					try {
						messenger.sendLog(logFile);
						JOptionPane.showMessageDialog(null, "Successfully sent the debug information", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "File Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "IO Error", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					} catch (EmailException e) {
						JOptionPane.showMessageDialog(null, "Cannot send Email", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "An error has occured.", "Error", JOptionPane.ERROR_MESSAGE);
						loggerUtility.logStackTrace(e);
					}
				}
			};
		}
		return logAbstractAction;
	}

}

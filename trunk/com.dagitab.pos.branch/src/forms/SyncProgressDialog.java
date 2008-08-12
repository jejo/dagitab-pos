package forms;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import main.Main;

import org.apache.log4j.Logger;


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
public class SyncProgressDialog extends javax.swing.JDialog implements PropertyChangeListener {
	private JProgressBar jProgressBar1;
	
	private JLabel textLabel;

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
				SyncProgressDialog inst = new SyncProgressDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public SyncProgressDialog(JFrame frame) {
		super(frame);
		initGUI();
		startSync();
		
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Synchronization Progress");
				this.setModal(true);
				{
					jProgressBar1 = new JProgressBar(0,100);
					getContentPane().add(jProgressBar1);
					jProgressBar1.setBounds(10, 16, 509, 16);
				}
				{
					textLabel = new JLabel();
					getContentPane().add(textLabel);
					textLabel.setText("Please wait while your data is being synchronized.");
					textLabel.setBounds(10, 38, 280, 14);
				}
				{
					percentageLabel = new JLabel();
					getContentPane().add(percentageLabel);
					percentageLabel.setText("0% completed");
					percentageLabel.setBounds(426, 37, 92, 16);
					percentageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
			}
			this.setSize(544, 94);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startSync(){
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Task task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			jProgressBar1.setValue(progress);
		} 
		
	}
	
	int prevPercentage = 0;
	private JLabel percentageLabel;
	class Task extends SwingWorker<Void, Void> {
		/*
		* Main task. Executed in background thread.
		*/
		@Override
		public Void doInBackground() {
			Logger.getLogger(SyncProgressDialog.class).info("running in background");
			new Thread() {
				public void run() {
					Logger.getLogger(SyncProgressDialog.class).info("running in thread");
					Main.getSyncManager().sync();
				}
			}.start();
			
			setProgress(0);
			while (Main.getPercentage() < 100) {
				try {
					
					TimeUnit.MILLISECONDS.sleep(10);
					
				} catch (InterruptedException ignore) {
					
				}
				if (prevPercentage != Main.getPercentage()) {
					setProgress(Main.getPercentage());
					percentageLabel.setText(Main.getPercentage()+"% completed");
					prevPercentage = Main.getPercentage();
				}
					
			}
			//to display 100%
			jProgressBar1.setValue(100);
			percentageLabel.setText("100% completed");
			
			return null;
		}
		
		/*
		* Executed in event dispatching thread
		*/
		@Override
		public void done() {
			
			
			Toolkit.getDefaultToolkit().beep();
			setCursor(null); //turn off the wait cursor
			SyncProgressDialog.this.dispose();
			JOptionPane.showMessageDialog(null, "Synchronization complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			
			
		}

		
		
	}

}

package forms.pullouts;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


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
public class PullOutRequestDialog extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JTabbedPane pullOutTabbedPane;
	private JPanel newPullOutPanel;
	private JPanel pullOutListPanel;
	private JButton closeButton;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				PullOutRequestDialog inst = new PullOutRequestDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public PullOutRequestDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Pull Out Request");
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Pull Outs");
					jLabel1.setBounds(12, 12, 125, 16);
					jLabel1.setFont(new java.awt.Font("Tahoma",0,18));
				}
				{
					pullOutTabbedPane = new JTabbedPane();
					getContentPane().add(pullOutTabbedPane);
					pullOutTabbedPane.setBounds(12, 34, 729, 365);
					{
						pullOutListPanel = new JPanel();
						pullOutTabbedPane.addTab("Pull Out Request List", null, pullOutListPanel, null);
						pullOutListPanel.setLayout(null);
						pullOutListPanel.setBackground(new java.awt.Color(255,255,255));
					}
					{
						newPullOutPanel = new JPanel();
						pullOutTabbedPane.addTab("New Pull Out Request", null, newPullOutPanel, null);
						newPullOutPanel.setBackground(new java.awt.Color(255,255,255));
					}
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton);
					closeButton.setText("Close");
					closeButton.setBounds(358, 417, 57, 27);
				}
			}
			this.setSize(766, 505);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

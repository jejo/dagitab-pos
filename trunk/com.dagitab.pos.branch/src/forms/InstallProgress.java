package forms;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import util.LoggerUtility;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

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
public class InstallProgress extends javax.swing.JDialog {
	private JProgressBar jProgressBar1;
	private JLabel jLabel1;
	public JLabel jLabel2;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		InstallProgress inst = new InstallProgress(frame);
		//inst.jLabel2.setText("fasdfasd");
		inst.setVisible(true);
		
	}
	
	public InstallProgress(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Installing Initial Data");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			{
				
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(639, 922, 835, 86, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setPreferredSize(new java.awt.Dimension(413, 28));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(150, 950, 346, 86, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Please wait until your data is completely transferred");
				jLabel1.setPreferredSize(new java.awt.Dimension(427, 28));
			}
			{
				jProgressBar1 = new JProgressBar();
				getContentPane().add(jProgressBar1, new AnchorConstraint(381, 922, 576, 86, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jProgressBar1.setPreferredSize(new java.awt.Dimension(413, 28));
				jProgressBar1.setIndeterminate(true);
			}
			this.setSize(502, 177);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	public void stop(){
		jProgressBar1.setIndeterminate(false);
	}
	public void setLabel(String name){
		jLabel2.setText(name);
	}

}

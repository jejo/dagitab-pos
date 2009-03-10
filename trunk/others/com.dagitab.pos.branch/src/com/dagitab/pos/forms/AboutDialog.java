package com.dagitab.pos.forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;


import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
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

@SuppressWarnings("serial")
public class AboutDialog extends javax.swing.JDialog {
	private JLabel aboutLabel;
	private JLabel jLabel2;
	private JButton jButton1;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JLabel jLabel3;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		AboutDialog inst = new AboutDialog(frame);
		inst.setVisible(true);
	}
	
	public AboutDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("About");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setModal(true);
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5, new AnchorConstraint(463, 834, 565, 250, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel5.setText("All Rights Reserved 2009");
				jLabel5.setPreferredSize(new java.awt.Dimension(343, 28));
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4, new AnchorConstraint(232, 965, 335, 250, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel4.setText("For questions or to report a bug, contact us at 438-4421");
				jLabel4.setPreferredSize(new java.awt.Dimension(420, 28));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new AnchorConstraint(155, 953, 258, 250, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setText("Developed by Dagitab Information Technology. ");
				jLabel3.setPreferredSize(new java.awt.Dimension(413, 28));
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(822, 596, 924, 441, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Close");
				jButton1.setPreferredSize(new java.awt.Dimension(91, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						AboutDialog.this.dispose();
					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(1, 262, 843, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/giraffe.gif")));
				jLabel2.setPreferredSize(new java.awt.Dimension(154, 224));
			}
			{
				aboutLabel = new JLabel();
				getContentPane().add(aboutLabel, new AnchorConstraint(53, 798, 155, 250, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				aboutLabel.setText("About Babyland POS Application 2.0");
				aboutLabel.setPreferredSize(new java.awt.Dimension(322, 28));
				aboutLabel.setFont(new java.awt.Font("Tahoma",1,18));
				aboutLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "aboutLabel");
				aboutLabel.getActionMap().put("aboutLabel",getAboutLabelAbstractAction() );
			}
			this.setSize(596, 307);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private AbstractAction getAboutLabelAbstractAction() {
		AbstractAction aboutLabelAction = new AbstractAction("About Babyland POS Application 2.0", null) {
			
			public void actionPerformed(ActionEvent evt) {
				AboutDialog.this.dispose();
			}
		};
		return aboutLabelAction;
	}

}

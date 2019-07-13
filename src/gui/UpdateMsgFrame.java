package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class UpdateMsgFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateMsgFrame() {
		
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(new JLabel("Updating database, please wait..."));
		this.pack();	
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MsgFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MsgFrame(String msg) {
		
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		JLabel msgLbl = new JLabel(msg);
		JButton btn = new JButton("Ok");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		
		this.add(msgLbl, BorderLayout.NORTH);
		this.add(btn, BorderLayout.CENTER);
		
		this.pack();
		
	}
	
	private void exit() {
		this.dispose();
	}
	
}

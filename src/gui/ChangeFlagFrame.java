package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChangeFlagFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private Vector<ImageIcon> flagImages;
	
	private JPanel mainPanel;
	private GridBagConstraints mainGC;
	
	private JComboBox<ImageIcon> flagSelectionCBox;
	
	private JButton finishBtn;
	
	private String flag;
	
	// Constructor
	public ChangeFlagFrame() {
		this.setTitle("Change Flag Menu");
		this.setSize(200, 200);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.flag = "None";
		this.flagImages = new Vector<ImageIcon>();
		
		for (int i = 1; i < 8; i++) {
			ImageIcon image = new ImageIcon(new ImageIcon("src/DefaultImages/country" + i +  ".jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			flagImages.addElement(image);
		}
		
		createView();
	}

	private void createView() {
		/*
		 * This method initiates all components and arrange them in the main panel.
		 */
		
		mainPanel = new JPanel(new GridBagLayout());
		mainGC = new GridBagConstraints();
		
		flagSelectionCBox = new JComboBox<ImageIcon>(flagImages);
		
		finishBtn = new JButton("Finish");
		finishBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				finish();
			}
		});
	
		mainGC.insets = new Insets(10, 10, 10, 10);
		mainGC.gridx = 0;
		mainGC.gridy = 0;
		mainPanel.add(flagSelectionCBox, mainGC);
		
		mainGC.gridx = 0;
		mainGC.gridy = 1;
		mainPanel.add(finishBtn, mainGC);
		
		
		this.add(mainPanel);
	}
	
	private void finish() {
		/*
		 * This method changes the flag variable to the matching flag chosen by the user
		 * and shuts down this frame. 
		 */
		
		switch (flagSelectionCBox.getSelectedIndex()) {
			case 0:
				flag = "Israel";
				break;
			case 1:
				flag = "USA";
				break;
			case 2:
				flag = "Germany";
				break;
			case 3:
				flag = "Italy";
				break;
			case 4:
				flag = "Greece";
				break;
			case 5:
				flag = "Somalia";
				break;
			case 6:
				flag = "Pirates";
				break;
		}
		this.dispose();	
	}
	
	public String getFlag() {
		return this.flag;
	}
}

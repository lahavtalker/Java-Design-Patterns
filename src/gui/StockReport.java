package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import decorators.ColorDecorator;
import decorators.StatusDecorator;
import interfaces.VehicleInterface;

public class StockReport extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Vector<VehicleInterface> vehicleList;
	private Vector<ImageIcon> vehicleImages;
	
	private JPanel mainPanel, innerVehicleImagesPanel;
	
	private GridBagConstraints mainGC;
	
	private JTextArea vehicleDetails;
	
	private Vector<JLabel> vehicleImagesDisplayLbl;
	
	public StockReport(Vector<VehicleInterface> vehicleList, Vector<ImageIcon> vehicleImages) {
		
		this.setTitle("Vehicle Stock");
		this.setSize(1000, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.vehicleList = vehicleList;
		this.vehicleImages = vehicleImages;
		
		createView();
	}

	private void createView() {

		mainPanel = new JPanel(new GridBagLayout());
		mainGC = new GridBagConstraints();
		
		vehicleDetails = new JTextArea();
		vehicleDetails.setEditable(false);
		
		innerVehicleImagesPanel = new JPanel(new FlowLayout());
		JScrollPane scrollPane1 = new JScrollPane(innerVehicleImagesPanel);
		
		vehicleImagesDisplayLbl = new Vector<JLabel>();
		
		drawVehiclePanel();
		
		mainGC.weightx = 1;
		mainGC.weighty = 1;
		mainGC.fill = GridBagConstraints.BOTH;
		mainGC.anchor = GridBagConstraints.NORTH;
		mainGC.gridx = 0;
		mainGC.gridy = 0;
		mainPanel.add(scrollPane1, mainGC);
		mainGC.gridx = 0;
		mainGC.gridy  = 1;
		mainPanel.add(vehicleDetails, mainGC);
		
		this.getContentPane().add(mainPanel);
	}
	
	public void drawVehiclePanel() {
		vehicleImagesDisplayLbl.removeAllElements();
		innerVehicleImagesPanel.removeAll();
		for (int i = 0; i < vehicleImages.size(); i++) {
			JLabel label = new JLabel();
			label.setIcon(vehicleImages.get(i));
			ColorDecorator cd = ((ColorDecorator)((StatusDecorator)vehicleList.elementAt(i)).removeLayer());
			label.setBorder(BorderFactory.createLineBorder(cd.getColor()));
			label.setToolTipText(((StatusDecorator)vehicleList.elementAt(i)).toString());
			label.addMouseListener(new MouseListener() {	
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vehicleDetails.setText("");
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					int selectedIndex;
					selectedIndex = vehicleImagesDisplayLbl.indexOf(label);
					vehicleDetails.setText(vehicleList.elementAt(selectedIndex).toString());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {}
			});
			vehicleImagesDisplayLbl.addElement(label);
			innerVehicleImagesPanel.add(label);
		}
		vehicleDetails.setText("");
		innerVehicleImagesPanel.revalidate();
		innerVehicleImagesPanel.repaint();
	}
}

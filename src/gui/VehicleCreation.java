package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import decorators.ColorDecorator;
import decorators.StatusDecorator;
import factories.AircraftFactory;
import factories.LandVehicleFactory;
import factories.WatercraftFactory;
import interfaces.VehicleInterface;
import interfaces.WatercraftInterface;

import javax.swing.JFileChooser;

import vehicles.AmphibiousVehicle;
import vehicles.CruiseShip;
import vehicles.Frigate;
import vehicles.HybridAirplane;

public class VehicleCreation extends JFrame {

	private static final long serialVersionUID = 1L;

	private AgencyFrame agency; 
	
	private final String[] types = {"Amphibious Vehicle", "Bicycle", "Cruise Ship",
									"Electric Bicycle", "Frigate", "Hybrid Airplane",
									"Game Glider", "Jeep", "Spying Glider"};
	
	private final String[] colors = {"BLACK", "BLUE", "RED", "GREEN", "YELLOW",
									 "WHITE", "ORANGE", "PINK", "GRAY",
									 "CYAN"};
	
	private Vector<VehicleInterface> vehicleList;
	private Vector<WatercraftInterface> watercrafts;
	private Vector<ImageIcon> vehicleImages;
	
	private ImageIcon selectedImage;
	
	private JPanel mainPanel, typeSubPanel, attrsSubPanel, imageSubPanel;

	private GridBagConstraints mainGC, typeGC, attrsGC, imageGC;

	private JLabel mainTitle, typeSubTitle, attrsSubTitle;
	private JLabel modelLbl, passengersLbl, speedLbl, wheelsLbl, saillingLbl;
	private JLabel flagLbl, avgFuelLbl, avgEngineLbl, ESourceLbl;
	private JLabel selectDefaultLbl, selectedImageLbl, selectedImageDisplayLbl;
	private JLabel colorLbl, errorLbl;
	
	private JTextField modelTextF, passengersTextF, speedTextF, wheelsTextF;
	private JTextField usageTextF, flagTextF, avgFuelTextF, avgEngineTextF;
	private JTextField ESourceTextF;
	
	private JCheckBox saillingWithWindCheckBox;
	
	private JButton createVehicleBtn, uploadImageBtn, finishBtn;
	
	private JRadioButton terrainOption1, terrainOption2;
	private ButtonGroup terrainSelect;

	private JComboBox<String> typeList;
	private JComboBox<ImageIcon> defaultVehicleImagesList;
	private JComboBox<String> colorList;
	
	// Constructor: 
	public VehicleCreation(Vector<VehicleInterface> vehicleList, Vector<WatercraftInterface> watercrafts, Vector<ImageIcon> vehicleImages, AgencyFrame agency) {	
		this.vehicleList = vehicleList;
		this.watercrafts = watercrafts;
		this.vehicleImages = vehicleImages;
		this.setTitle("Create Vehicle Menu");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.agency = agency;
		
		createView();
	}
	 
	private void createView() {
		/*
		 * Initiation of all components and arrangement of the main panel.
		 */
		
		// ** Panels **********************************************************
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mainGC = new GridBagConstraints();
		
		typeSubPanel = new JPanel(new GridBagLayout());
		typeSubPanel.setBorder(BorderFactory.createTitledBorder("Type"));
		typeGC = new GridBagConstraints();
		
		attrsSubPanel = new JPanel(new GridBagLayout());
		attrsSubPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));
		attrsGC = new GridBagConstraints();
		
		imageSubPanel = new JPanel(new GridBagLayout());
		imageSubPanel.setBorder(BorderFactory.createTitledBorder("Vehicle Image"));
		imageGC = new GridBagConstraints();
		// ********************************************************************
		
		// ** labels **********************************************************
		mainTitle = new JLabel("Create Vehicle Menu");
		mainTitle.setFont(new Font("Comic Sans MS", 3, 32));
		
		typeSubTitle = new JLabel("Choose Vehicle Type:");
		typeSubTitle.setFont(new Font("Comic Sans MS", 3, 24));	
		
		attrsSubTitle = new JLabel("Edit the attributes:");
		attrsSubTitle.setFont(new Font("Comic Sans MS", 3, 24));
		
		selectDefaultLbl = new JLabel("default images:");
		selectDefaultLbl.setFont(new Font("Comic Sans MS", 3, 16));
		
		selectedImageLbl = new JLabel("The image you selected:");
		selectedImageLbl.setFont(new Font("Comic Sans MS", 3, 16));
		selectedImageDisplayLbl = new JLabel();
		
		errorLbl = new JLabel("");
		errorLbl.setFont(new Font("Comic Sans MS", 3, 16));
		errorLbl.setForeground(Color.RED);
		
		modelLbl = new JLabel("Model: ");
		passengersLbl = new JLabel("Passengers: ");
		speedLbl = new JLabel("Speed: ");
		wheelsLbl = new JLabel("Wheels: ");
		saillingLbl = new JLabel("Sailing with the wind: ");
		flagLbl = new JLabel("Flag: ");
		avgFuelLbl = new JLabel("Average Fuel Consumption: ");
		avgEngineLbl = new JLabel("Average Egine Life Span: ");
		ESourceLbl = new JLabel("Energy Source: ");
		colorLbl = new JLabel("Color: ");
		// ********************************************************************
		
		// ** Text fields *****************************************************
		modelTextF = new JTextField(20);
		passengersTextF = new JTextField(20);
		speedTextF = new JTextField(20);
		wheelsTextF = new JTextField(20);
		flagTextF = new JTextField(20);
		usageTextF = new JTextField(20);
		avgFuelTextF = new JTextField(20);
		avgEngineTextF = new JTextField(20);
		ESourceTextF = new JTextField(20);
		// ********************************************************************
		
		// ** Type List *******************************************************
		typeList = new JComboBox<String>(types);
		typeList.setSelectedItem(null);
		typeList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String type = (String) typeList.getSelectedItem();
				createInputElements(type);
			}
		});
		
		colorList = new JComboBox<String>(colors);
		colorList.setSelectedItem("Black");
		// ********************************************************************
		
		// ** Buttons *********************************************************
		uploadImageBtn = new JButton("Upload image");
		uploadImageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uploadImageDialog();
			}
		});
		
		createVehicleBtn = new JButton("Create vehicle");
		createVehicleBtn.setFont(new Font("Comic Sans MS", 3, 30));
		createVehicleBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				createVehicle((String) typeList.getSelectedItem());
			}
		});
		
		finishBtn = new JButton("Finish");
		finishBtn.setFont(new Font("Comic Sans MS", 3, 30));
		finishBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agency();
			}
		});
		// ********************************************************************
		
		// ** CheckBoxes ******************************************************
		saillingWithWindCheckBox = new JCheckBox("");
		// ********************************************************************
		
		// ** RadioBoxMenu ****************************************************
		terrainOption1 = new JRadioButton("Dirt");
		terrainOption2 = new JRadioButton("Paved");
		terrainSelect = new ButtonGroup();
		terrainSelect.add(terrainOption1);
		terrainSelect.add(terrainOption2);
		// ********************************************************************
		
		// ** Default Images ComboBox *****************************************
		defaultVehicleImagesList = new JComboBox<ImageIcon>();
		defaultVehicleImagesList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedImage = (ImageIcon) defaultVehicleImagesList.getSelectedItem();
				selectedImageDisplayLbl.setIcon(selectedImage);
			}
		});
		// ********************************************************************
		
		// ** Arrangement of the sub panels ************************************
		arrangeComponents();
		// ********************************************************************
	
		// ** Main Panel arrangement using GridBagLayout and GridBagConstraints
		mainGC.weightx = 0.5;
		mainGC.weighty = 0.5;
		mainGC.anchor = GridBagConstraints.NORTH;
		mainGC.fill = GridBagConstraints.BOTH;
		mainGC.insets = new Insets(15, 5, 5, 5);
		mainGC.gridx = 0;
		mainGC.gridy = 0;
		mainPanel.add(typeSubPanel, mainGC);
		mainGC.gridx = 1;
		mainGC.gridy = 0;
		mainPanel.add(attrsSubPanel, mainGC);
		mainGC.gridx = 2;
		mainGC.gridy = 0;
		mainPanel.add(imageSubPanel, mainGC);
		mainGC.gridx = 0;
		mainGC.gridy = 1;
		mainGC.fill = GridBagConstraints.NONE;
		mainPanel.add(finishBtn, mainGC);
		mainGC.gridx = 1;
		mainGC.gridy = 1;
		mainPanel.add(createVehicleBtn, mainGC);
		mainGC.gridx = 2;
		mainGC.gridy = 1;
		mainPanel.add(errorLbl, mainGC);
		// *********************************************************************

		this.add(mainPanel);
	}
	
	private void createInputElements(String type) {
		/*
		 * The method empties all the content of the attrs sub panel and rearrange it
		 * according the vehicle type selected in the Type list.
		 * The appropriate default images of that vehicle type are presented to the user
		 * to choose from.
		 * The arrangement is done using GridBagLayout and GridBagConstraints.
		 * In addition to that, all the input text fields are reseted.
		 */
		
		attrsSubPanel.revalidate();
		attrsSubPanel.removeAll();
		defaultVehicleImagesList.removeAllItems();
		attrsGC.gridx = 0;
		attrsGC.gridy = 0;
		attrsGC.gridwidth = 2;
		attrsGC.anchor = GridBagConstraints.CENTER;
		attrsSubPanel.add(attrsSubTitle,attrsGC);
		attrsGC.gridwidth = 1;
		switch (type) {
			case "Amphibious Vehicle":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END;
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(wheelsLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(saillingLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(flagLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(wheelsTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(saillingWithWindCheckBox, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(flagTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("av");
				break;
			case "Bicycle":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END;
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(terrainOption1, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(terrainOption2, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("b");
				break;
			case "Cruise Ship":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END;
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(flagLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(flagTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("cp");
				break;
			case "Electric Bicycle":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END;
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(terrainOption1, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(terrainOption2, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("eb");
				break;
			case "Frigate":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END;
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(saillingLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(saillingWithWindCheckBox, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("f");
				break;
			case "Game Glider":
				loadDefaultImages("gg");
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(colorList, attrsGC);
				break;
			case "Hybrid Airplane":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END;
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(wheelsLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(saillingLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(flagLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(passengersTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(wheelsTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(saillingWithWindCheckBox, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(flagTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("ha");
				break;
			case "Jeep":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END; 
				attrsSubPanel.add(modelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(modelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(speedTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgFuelTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(avgEngineTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("j");
				break;
			case "Spying Glider":
				attrsGC.gridy++;
				attrsGC.anchor = GridBagConstraints.LINE_END; 
				attrsSubPanel.add(ESourceLbl, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorLbl, attrsGC);
				
				attrsGC.gridx = 1;
				attrsGC.gridy = 1;
				attrsGC.anchor = GridBagConstraints.LINE_START;
				attrsSubPanel.add(ESourceTextF, attrsGC);
				attrsGC.gridy++;
				attrsSubPanel.add(colorList, attrsGC);
				loadDefaultImages("sg");
				break;
		}
		resetInput();
		
		attrsSubPanel.repaint();
	}
	
	private void arrangeComponents() {
		/*
		 * This method arranges the other sub panels using 
		 * GridBagLayout and GridBagConstraints.
		 */
		
		typeGC.anchor = GridBagConstraints.CENTER;
		typeGC.insets = new Insets(10, 0, 0, 0);
		typeGC.gridx = 0;
		typeGC.gridy = 0;
		typeSubPanel.add(typeSubTitle, typeGC);
		typeGC.gridy = 1;
		typeSubPanel.add(typeList, typeGC);
		
		attrsGC.anchor = GridBagConstraints.CENTER;
		attrsGC.fill = GridBagConstraints.BOTH;
		attrsGC.gridx = 0;
		attrsGC.gridy = 0;
		attrsGC.gridwidth = 2;
		attrsSubPanel.add(attrsSubTitle,attrsGC);
		
		imageGC.insets = new Insets(2, 2, 2, 2);
		imageGC.gridx = 0;
		imageGC.gridy = 0;
		imageSubPanel.add(selectDefaultLbl, imageGC);
		imageGC.gridy = 1;
		imageSubPanel.add(defaultVehicleImagesList, imageGC);
		imageGC.gridy = 2;
		imageSubPanel.add(uploadImageBtn, imageGC);
		imageGC.gridy = 3;
		imageSubPanel.add(selectedImageLbl, imageGC);
		imageGC.gridy = 4;
		imageSubPanel.add(selectedImageDisplayLbl, imageGC);
		imageGC.gridy = 5;
		imageSubPanel.add(selectedImageDisplayLbl, imageGC);
	}
	
	private void createVehicle(String type) {	
		/*
		 * This method creates a new vehicle according the type and the matching 
		 * input and adds it to the vehicle list.
		 * If it is a watercraft it is also added to the watercraft list.
		 * The thread responsible for adding the new vehicle will sleep for 3 to  8 seconds
		 * In case of invalid input, a error message will be displayed.
		 */
		if(type != null) {
			switch (type) {
			case "Amphibious Vehicle":
				if(modelTextF.getText().equals("") || passengersTextF.getText().equals("") ||
					speedTextF.getText().equals("") || wheelsTextF.getText().equals("") ||
					flagTextF.getText().equals("") || avgFuelTextF.getText().equals("") ||
					avgEngineTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}
				else {
					try {
						VehicleInterface av = WatercraftFactory.getVehicle(type, this);
						WatercraftInterface av2 = (AmphibiousVehicle)av;
						ColorDecorator CD = new ColorDecorator(av, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						watercrafts.addElement(av2);		
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}
				}
				break;
			case "Bicycle":
				if(modelTextF.getText().equals("") || passengersTextF.getText().equals("") ||
					speedTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}
				else {
					try {
						VehicleInterface b = LandVehicleFactory.getVehicle(type, this);
						ColorDecorator CD = new ColorDecorator(b, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}
				}
				break;
			case "Cruise Ship":
				if(modelTextF.getText().equals("") || passengersTextF.getText().equals("") ||
					speedTextF.getText().equals("") || flagTextF.getText().equals("") ||
					avgFuelTextF.getText().equals("") || avgEngineTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}
				else {
					try {
						VehicleInterface cp = WatercraftFactory.getVehicle(type, this);
						WatercraftInterface cp2 = (CruiseShip)cp;
						ColorDecorator CD = new ColorDecorator(cp, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						watercrafts.addElement(cp2);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}
				}
				break;
			case "Electric Bicycle":
				if(modelTextF.getText().equals("") || passengersTextF.getText().equals("") ||
						speedTextF.getText().equals("") || avgEngineTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
					}
				else {
					try {
						VehicleInterface eb = LandVehicleFactory.getVehicle(type, this);
						ColorDecorator CD = new ColorDecorator(eb, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}	
				}
				break;
			case "Frigate":
				if(modelTextF.getText().equals("") || passengersTextF.getText().equals("") ||
				speedTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}	
				else {
					try {
						VehicleInterface f = WatercraftFactory.getVehicle(type, this);
						WatercraftInterface f2 = (Frigate)f;
						ColorDecorator CD = new ColorDecorator(f, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						watercrafts.addElement(f2);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}
				}
				break;
			case "Game Glider":
				try {
					VehicleInterface gg = AircraftFactory.getVehicle(type, this);
					ColorDecorator CD = new ColorDecorator(gg, getColor(colorList.getSelectedItem()));
					StatusDecorator SD = new StatusDecorator(CD);
					vehicleList.addElement(SD);
					vehicleImages.addElement(selectedImage);
					displayUpdatingMSG();
				} catch (Exception e) {
					errorLbl.setText("1 or more inputs is invalid!");
				}
				break;
			case "Hybrid Airplane":
				if(modelTextF.getText().equals("") || passengersTextF.getText().equals("") ||
						speedTextF.getText().equals("") || wheelsTextF.getText().equals("") ||
						flagTextF.getText().equals("") || avgFuelTextF.getText().equals("") ||
						avgEngineTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}
				else {
					try {
						VehicleInterface ha = WatercraftFactory.getVehicle(type, this);
						WatercraftInterface ha2 = (HybridAirplane)ha;
						ColorDecorator CD = new ColorDecorator(ha, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						watercrafts.addElement(ha2);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}
				}
				break;
			case "Jeep":
				if(modelTextF.getText().equals("") ||speedTextF.getText().equals("") ||
					avgFuelTextF.getText().equals("") || avgEngineTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}
				else {
					try {
						VehicleInterface j = LandVehicleFactory.getVehicle(type, this);
						ColorDecorator CD = new ColorDecorator(j, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}
				}
				break;
			case "Spying Glider":
				if(ESourceTextF.getText().equals("")) {
					errorLbl.setText("1 or more inputs is empty!");
				}
				else {
					try {
						VehicleInterface sp = AircraftFactory.getVehicle(type, this);
						ColorDecorator CD = new ColorDecorator(sp, getColor(colorList.getSelectedItem()));
						StatusDecorator SD = new StatusDecorator(CD);
						vehicleList.addElement(SD);
						vehicleImages.addElement(selectedImage);
						displayUpdatingMSG();
					} catch (Exception e) {
						errorLbl.setText("1 or more inputs is invalid!");
					}	
				}
				break;
			}
		}
		else {
			errorLbl.setText("No type selected!");
		}
	}
	
	private void displayUpdatingMSG() {
		SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>(){
			@Override
			protected Object doInBackground() throws Exception {
				Random rand = new Random();
				UpdateMsgFrame frame = new UpdateMsgFrame();
				frame.setVisible(true);
				Thread.sleep((rand.nextInt(5) + 3) * 1000);
				if(agency != null) {
					agency.createVehiclePanel();
				}
				frame.dispose();
				return null;
			}
		};
		
		sw.execute();	
	}
	
	private void resetInput() {
		/*
		 * This method resets all input text fields
		 */
		
		modelTextF.setText("");
		passengersTextF.setText("");
		speedTextF.setText("");
		wheelsTextF.setText("");
		flagTextF.setText("");
		usageTextF.setText("");
		avgEngineTextF.setText("");
		avgFuelTextF.setText("");
		ESourceTextF.setText("");
		terrainOption1.setSelected(true);
		terrainOption2.setSelected(false);
		errorLbl.setText("");
	}
	
	private void uploadImageDialog() {	
		/*
		 * This method handles the user custom image upload
		 * dialog and displays it on the screen.
		 */
		
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
		fc.setFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
		fc.setFileFilter(new FileNameExtensionFilter("JPEG", "jpeg"));
		fc.setFileFilter(new FileNameExtensionFilter("GIF", "gif"));
		fc.showOpenDialog(null);
		if (fc.getSelectedFile() != null) {
			selectedImageDisplayLbl.setIcon(new ImageIcon(new ImageIcon(fc.getSelectedFile().getPath()).getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
			selectedImage = (ImageIcon) selectedImageDisplayLbl.getIcon();
		}
	}
	
	private void loadDefaultImages(String type) {
		/*
		 * This method displays the default images the user can select from
		 * according the type of vehicle selected.
		 */
		
		for (int i = 1; i < 4; i++) {
			ImageIcon image = new ImageIcon(new ImageIcon("src/DefaultImages/" + type + i +  ".jpg").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
			defaultVehicleImagesList.addItem(image);
		}
	}
	
	private void agency() {
		/*
		 * This method shuts down this frame down and opens up a new AgencyFrame frame.
		 */

		this.dispose();
		agency.transferFocusUpCycle();
	}
	
	private Color getColor(Object object) {
		String name = (String)object;
		Color color;
		switch (name) {
			case "BLACK":
				color = Color.BLACK;
				break;
			case "BLUE":
				color = Color.BLUE;
				break;
			case "RED":
				color = Color.RED;
				break;
			case "GREEN":
				color = Color.GREEN;
				break;
			case "WHITE":
				color = Color.WHITE;
				break;
			case "YELLOW":
				color = Color.YELLOW;
				break;
			case "CYAN":
				color = Color.CYAN;
				break;
			case "GRAY":
				color = Color.GRAY;
				break;
			case "PINK":
				color = Color.PINK;
				break;
			case "ORANGE":
				color = Color.ORANGE;
				break;
			default:
				color = Color.BLACK;
				break;
		}
		return color;
	}
	
	public String getTxtInput(String source) {
		switch (source) {
		case "model":
			return modelTextF.getText();
		case "passengers":
			return passengersTextF.getText();
		case "speed":
			return speedTextF.getText();
		case "wheels":
			return wheelsTextF.getText();
		case "flag":
			return flagTextF.getText();
		case "usage":
			return usageTextF.getText();
		case "avg fuel":
			return avgFuelTextF.getText();
		case "avg engine":
			return avgEngineTextF.getText();
		case "source":
			return ESourceTextF.getText();
		case "terrain":
			if (terrainOption1.isSelected())
				return "Dirt";
			else
				return "Paved";
		default:
			return "";
		}
	}
	
	public boolean getTrueFalseInput() {
		if(saillingWithWindCheckBox.isSelected())
			return true;
		else
			return false;
	}
}


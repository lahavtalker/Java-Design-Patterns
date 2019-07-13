package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import decorators.ColorDecorator;
import decorators.StatusDecorator;
import interfaces.VehicleInterface;
import interfaces.WatercraftInterface;
import memento.Caretaker;
import memento.Memento;
import memento.Originator;
import memento.VehiclesState;
import observer.TestDriveObservable;

public class AgencyFrame extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;

	private volatile static AgencyFrame instance = null;

	private VehicleCreation creation;

	private StockReport stock;

	private Vector<VehicleInterface> vehicleList;
	private Vector<WatercraftInterface> watercrafts;
	private Vector<ImageIcon> vehicleImages;

	private VehicleInterface selectedVehicle;

	private JPanel mainPanel, innerVehicleImagesPanel;
	private GridBagConstraints mainGC;

	private ImageIcon noneImage = new ImageIcon(
			new ImageIcon("src/DefaultImages/noImage.jpg").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));

	private JLabel mainTitleLbl, selectedVehicleImageLbl, selectedVehicleLbl;
	private JLabel totalDistanceLbl;

	private JTextArea vehicleToStringTextA;

	private Vector<JLabel> vehicleImagesDisplayLbl;

	private JButton addMoreBtn, resetDistancesBtn, testDriveBtn, buyVehicleBtn, changeFlagBtn;
	private JButton exitBtn, stockReportBtn, saveStateBtn, loadStateBtn;

	private int selectedIndex = -1;
	private double totalDistance = 0;
	private String distance = "0";

	private Originator originator;
	private Caretaker caretaker;
	
	private ExecutorService threadPool;

	// ** Singeleton ********************************* //
	public static AgencyFrame getInstance() {
		if (instance == null)
			synchronized (AgencyFrame.class) {
				if (instance == null) {
					instance = new AgencyFrame();
				}
			}
		return instance;
	}
	// *********************************************** //

	// ** Constructor ******************************** //
	private AgencyFrame() {
		this.vehicleList = new Vector<VehicleInterface>();
		this.watercrafts = new Vector<WatercraftInterface>();
		this.vehicleImages = new Vector<ImageIcon>();
		this.setTitle("Vehicle Agency");
		this.setSize(1300, 600);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.originator = new Originator();
		this.caretaker = new Caretaker();

		this.threadPool = Executors.newFixedThreadPool(7);
		
		createView();
	}
	// ************************************************ //

	private void createView() {
		/*
		 * This method initiates all components and arrange the main panel
		 */

		// ** Panels ********************************************
		mainPanel = new JPanel(new GridBagLayout());
		mainGC = new GridBagConstraints();

		innerVehicleImagesPanel = new JPanel(new FlowLayout());
		JScrollPane scrollPane1 = new JScrollPane(innerVehicleImagesPanel);
		// ******************************************************

		// ** Labels ********************************************
		mainTitleLbl = new JLabel("Welcome to the agency! ");
		mainTitleLbl.setFont(new Font("Comic Sans MS", 3, 48));

		selectedVehicleLbl = new JLabel("The vehicle you selected:");
		selectedVehicleLbl.setFont(new Font("Comic Sans MS", 3, 13));

		selectedVehicleImageLbl = new JLabel();
		selectedVehicleImageLbl.setIcon(noneImage);

		totalDistanceLbl = new JLabel("Total distance: " + totalDistance);
		totalDistanceLbl.setFont(new Font("Comic Sans MS", 3, 13));
		// ******************************************************

		// ** Text Area *****************************************
		vehicleToStringTextA = new JTextArea();
		vehicleToStringTextA.setEditable(false);
		// ******************************************************

		JScrollPane scrollPane2 = new JScrollPane(vehicleToStringTextA);

		// ** Vehicle images ************************************
		vehicleImagesDisplayLbl = new Vector<JLabel>();
		createVehiclePanel();
		// ******************************************************

		// ** Buttons *******************************************
		testDriveBtn = new JButton("Test drive");
		testDriveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testDrive();
			}
		});

		resetDistancesBtn = new JButton("Reset all total Distances");
		resetDistancesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetDistances();
			}
		});

		addMoreBtn = new JButton("Add more vehicles");
		addMoreBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMoreVehicles();
			}
		});

		buyVehicleBtn = new JButton("Buy vehicle");
		buyVehicleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyVehicle();
			}
		});

		changeFlagBtn = new JButton("Change flag");
		changeFlagBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeFlagMenu();
			}
		});

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitProgram();
			}
		});

		stockReportBtn = new JButton("Stock report");
		stockReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stockReport();
			}
		});

		saveStateBtn = new JButton("Save State");
		saveStateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveState();
			}
		});

		loadStateBtn = new JButton("Load Previous State");
		loadStateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadState();
			}
		});
		// ******************************************************

		for (int i = 0; i < vehicleImagesDisplayLbl.size(); i++) {
			innerVehicleImagesPanel.add(vehicleImagesDisplayLbl.elementAt(i));
		}

		mainGC.insets = new Insets(10, 10, 10, 10);
		mainGC.gridwidth = 9;
		mainGC.weighty = 0.1;
		mainGC.gridx = 0;
		mainGC.gridy = 0;
		mainPanel.add(mainTitleLbl, mainGC);

		mainGC.weighty = 0.5;
		mainGC.fill = GridBagConstraints.BOTH;
		mainGC.gridx = 0;
		mainGC.gridy = 1;
		mainPanel.add(scrollPane1, mainGC);

		mainGC.weighty = 0;
		mainGC.fill = GridBagConstraints.NONE;
		mainGC.anchor = GridBagConstraints.NORTH;
		mainGC.gridwidth = 1;
		mainGC.gridx = 0;
		mainGC.gridy = 2;
		mainPanel.add(selectedVehicleLbl, mainGC);
		mainGC.gridx = 1;
		mainGC.gridy = 2;
		mainPanel.add(selectedVehicleImageLbl, mainGC);
		mainGC.fill = GridBagConstraints.BOTH;
		mainGC.gridwidth = 7;
		mainGC.gridx = 2;
		mainGC.gridy = 2;
		mainPanel.add(scrollPane2, mainGC);

		mainGC.gridwidth = 1;
		mainGC.weightx = 0.5;
		mainGC.anchor = GridBagConstraints.NORTH;
		mainGC.fill = GridBagConstraints.NONE;
		mainGC.weighty = 0;
		mainGC.fill = GridBagConstraints.HORIZONTAL;
		mainGC.gridx = 0;
		mainGC.gridy = 3;
		mainPanel.add(addMoreBtn, mainGC);
		mainGC.gridx = 1;
		mainGC.gridy = 3;
		mainPanel.add(buyVehicleBtn, mainGC);
		mainGC.gridx = 2;
		mainGC.gridy = 3;
		mainPanel.add(testDriveBtn, mainGC);
		mainGC.gridx = 3;
		mainGC.gridy = 3;
		mainPanel.add(resetDistancesBtn, mainGC);
		mainGC.gridx = 4;
		mainGC.gridy = 3;
		mainPanel.add(changeFlagBtn, mainGC);
		mainGC.gridx = 5;
		mainGC.gridy = 3;
		mainPanel.add(stockReportBtn, mainGC);
		mainGC.gridx = 6;
		mainGC.gridy = 3;
		mainPanel.add(exitBtn, mainGC);
		mainGC.gridx = 7;
		mainGC.gridy = 3;
		mainPanel.add(saveStateBtn, mainGC);
		mainGC.gridx = 8;
		mainGC.gridy = 3;
		mainPanel.add(loadStateBtn, mainGC);
		mainGC.gridx = 0;
		mainGC.gridy = 4;
		mainPanel.add(totalDistanceLbl, mainGC);

		this.getContentPane().add(mainPanel);
	}

	private void resetDistances() {
		/*
		 * This method resets all vehicles total distance to 0 The Thread responsible
		 * will sleep for 3 to 8 seconds
		 */

		SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				Random rand = new Random();
				UpdateMsgFrame frame = new UpdateMsgFrame();
				frame.setVisible(true);
				for (int i = 0; i < vehicleList.size(); i++) {
					vehicleList.elementAt(i).movement(vehicleList.elementAt(i).getTotalDistance() * -1);
					vehicleImagesDisplayLbl.elementAt(i).setToolTipText(vehicleList.elementAt(i).toString());
				}
				selectedVehicleImageLbl.setIcon(noneImage);
				selectedIndex = -1;
				vehicleToStringTextA.setText("");
				Thread.sleep((rand.nextInt(5) + 3) * 1000);
				frame.dispose();
				return null;
			}
		};

		sw.execute();
	}

	private void buyVehicle() {
		/*
		 * This method removes the selected vehicle from the vehicle list and also from
		 * the watercraft list if it a watercraft, and a message will be displayed. If
		 * no vehicle selected then an error will be displayed. The thread responsible
		 * will first sleep for 5 to 10 seconds and then ask the user to confirm the
		 * choice, if user buys the vehicle then the thread will sleep for another 3 to
		 * 8 seconds.
		 */

		if (selectedVehicle != null) {
			if (((StatusDecorator) selectedVehicle).getStatus().equals("Idle")) {
				((StatusDecorator) selectedVehicle).setStatus("Buy");
				SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						VehicleInterface temp = selectedVehicle;
						JLabel tempLbl = vehicleImagesDisplayLbl.elementAt(selectedIndex);
						ImageIcon tempImage = vehicleImages.elementAt(selectedIndex);
						Random rand = new Random();
						Thread.sleep((rand.nextInt(5) + 5) * 1000);
						int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm",
								JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
							UpdateMsgFrame frame = new UpdateMsgFrame();
							frame.setVisible(true);
							Thread.sleep((rand.nextInt(5) + 3) * 1000);
							innerVehicleImagesPanel.remove(tempLbl);
							innerVehicleImagesPanel.revalidate();
							innerVehicleImagesPanel.repaint();
							vehicleImagesDisplayLbl.remove(tempLbl);
							vehicleImages.remove(tempImage);
							vehicleList.removeElement(temp);
							watercrafts.removeElement(temp);
							selectedVehicleImageLbl.setIcon(noneImage);
							selectedIndex = -1;
							selectedVehicle = null;
							vehicleToStringTextA.setText("");
							if (stock != null) {
								stock.drawVehiclePanel();
							}
							frame.dispose();
						} else {
							((StatusDecorator) selectedVehicle).setStatus("Idle");
						}
						return null;
					}

				};
				sw.execute();
			} else {
				MsgFrame frame = new MsgFrame("Vehicle Unavailable!");
				frame.setVisible(true);
			}
		} else {
			MsgFrame frame = new MsgFrame("No vehicle selected!");
			frame.setVisible(true);
		}
	}

	private void testDrive() {
		/*
		 * This method creates an input dialog asking the user for the distance of the
		 * test drive, displays an appropriate the message/error if the test drive was
		 * successful, the input is invalid or no vehicle selected. If it is successful
		 * then the vehicle's total distance is updated. The thread responsible will
		 * sleep for (test drive length) * 100 milliseconds.
		 */
		TestDriveObservable t = new TestDriveObservable();
		t.addObserver(this);
		if (selectedVehicle != null) {
			if (((StatusDecorator) selectedVehicle).getStatus().equals("Idle")) {
				((StatusDecorator) selectedVehicle).setStatus("Test");
				SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						distance = JOptionPane.showInputDialog("Enter the distance: ");
						try {
							if (Double.parseDouble(distance) < 0) {
								MsgFrame frame = new MsgFrame("Invalid distance!");
								frame.setVisible(true);
								((StatusDecorator) selectedVehicle).setStatus("Idle");
							} else {
								vehicleList.elementAt(selectedIndex).movement(Double.parseDouble(distance));
								vehicleImagesDisplayLbl.elementAt(selectedIndex)
										.setToolTipText(vehicleList.elementAt(selectedIndex).toString());
								innerVehicleImagesPanel.removeAll();
								for (int i = 0; i < vehicleImagesDisplayLbl.size(); i++) {
									innerVehicleImagesPanel.add(vehicleImagesDisplayLbl.elementAt(i));
								}
								innerVehicleImagesPanel.repaint();
								innerVehicleImagesPanel.revalidate();
								((StatusDecorator) selectedVehicle).setStatus("Idle");
								t.updateData(Double.parseDouble(distance));
								System.out.println(t.getData());
							}
						} catch (Exception e) {
							MsgFrame frame = new MsgFrame("Invalid distance!");
							frame.setVisible(true);
							((StatusDecorator) selectedVehicle).setStatus("Idle");
						}
						return null;
					}
				};
				threadPool.execute(sw);
			} else {
				MsgFrame frame = new MsgFrame("Vehicle Unavailable!");
				frame.setVisible(true);
			}
		} else {
			MsgFrame frame = new MsgFrame("No vehicle selected!");
			frame.setVisible(true);
		}
	}

	private void changeFlagMenu() {
		/*
		 * This method creates a new change flag frame and once a flag is selected and
		 * user clicks finish in that frame a String with the matching flag will be
		 * returned.
		 */

		ChangeFlagFrame frame = new ChangeFlagFrame();
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				changeFlag(frame.getFlag());
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
	}

	private void changeFlag(String flag) {
		/*
		 * This method changes all the watercrafts'es flag to the flag provided by the
		 * change flag frame, this method will be used when changeFlagMenu is called.
		 * the thread responsible will sleep for 3 to 8 seconds.
		 */
		SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				UpdateMsgFrame frame = new UpdateMsgFrame();
				Random rand = new Random();
				frame.setVisible(true);
				Thread.sleep((rand.nextInt(5) + 3) * 1000);
				for (int i = 0; i < watercrafts.size(); i++) {
					watercrafts.elementAt(i).setCountry_flag(flag);
				}
				selectedVehicleImageLbl.setIcon(noneImage);
				selectedIndex = -1;
				vehicleToStringTextA.setText("");
				for (int i = 0; i < vehicleList.size(); i++) {
					vehicleImagesDisplayLbl.elementAt(i).setToolTipText(vehicleList.elementAt(i).toString());
				}
				frame.dispose();
				return null;
			}
		};

		sw.execute();
	}

	private void addMoreVehicles() {
		/*
		 * This method brings the vehicle creation menu to the front
		 */
		if (creation == null || !creation.isVisible()) {
			creation = new VehicleCreation(vehicleList, watercrafts, vehicleImages, instance);
			creation.setVisible(true);
		} else {
			creation.transferFocusUpCycle();
		}
	}

	public void createVehiclePanel() {
		/*
		 * this method update and refresh the vehicle display panel.
		 */
		vehicleImagesDisplayLbl.removeAllElements();
		innerVehicleImagesPanel.removeAll();
		for (int i = 0; i < vehicleImages.size(); i++) {
			JLabel label = new JLabel();
			label.setIcon(vehicleImages.get(i));
			ColorDecorator cd = ((ColorDecorator) ((StatusDecorator) vehicleList.elementAt(i)).removeLayer());
			label.setBorder(BorderFactory.createLineBorder(cd.getColor()));
			label.setToolTipText(((StatusDecorator) vehicleList.elementAt(i)).toString());
			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
					selectedIndex = vehicleImagesDisplayLbl.indexOf(label);
					selectedVehicleImageLbl.setIcon(vehicleImagesDisplayLbl.elementAt(selectedIndex).getIcon());
					selectedVehicle = vehicleList.elementAt(selectedIndex);
					vehicleToStringTextA.setText(vehicleList.elementAt(selectedIndex).toString());
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			vehicleImagesDisplayLbl.addElement(label);
			innerVehicleImagesPanel.add(label);
		}
		innerVehicleImagesPanel.revalidate();
		innerVehicleImagesPanel.repaint();

		if (stock != null) {
			stock.drawVehiclePanel();
		}
	}

	private void exitProgram() {
		/*
		 * this methods closes all programs, in case there is a buying or test drive
		 * still active the method will wait until they are finished to close the
		 * program.
		 */
		if (selectedVehicle == null) {
			if (creation != null) {
				creation.dispose();
			}
			if (stock != null) {
				stock.dispose();
			}
			this.dispose();
		} else {
			synchronized (selectedVehicle) {
				if (creation != null) {
					creation.dispose();
				}
				if (stock != null) {
					stock.dispose();
				}
				this.dispose();
			}
		}

	}

	private void stockReport() {
		/*
		 * this method creates a stock report frame and brings it to the front.
		 */
		if (stock == null || !stock.isVisible()) {
			stock = new StockReport(vehicleList, vehicleImages);
			stock.setVisible(true);
		} else {
			stock.transferFocusUpCycle();
		}
	}

	private void saveState() {

		boolean canSave = true;

		for (int i = 0; i < vehicleList.size(); i++) {
			if (!((StatusDecorator) vehicleList.elementAt(i)).getStatus().equals("Idle"))
				canSave = false;
		}

		if (canSave) {
			originator.setState(new VehiclesState(vehicleList, vehicleImages, watercrafts));
			caretaker.addMemento(originator.saveState());
		}
	}

	private void loadState() {

		originator.restore(caretaker.getMemento());
		Memento m = originator.saveState();
		VehiclesState v = m.getVehicleState();
		this.vehicleList = v.getVehicleList();
		this.watercrafts = v.getWatercraftList();
		this.vehicleImages = v.getVehicleImages();

		createVehiclePanel();

	}

	
	@Override
	public void update(Observable o, Object arg) {
		this.totalDistance += ((TestDriveObservable)o).getData();
		this.totalDistanceLbl.setText("Total distance: " + totalDistance);
		
	}
}
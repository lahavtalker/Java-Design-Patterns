package memento;

import java.util.Vector;

import javax.swing.ImageIcon;

import interfaces.VehicleInterface;
import interfaces.WatercraftInterface;

public class VehiclesState {

	// ** State ********************************** //
	private Vector<VehicleInterface> vehicleList;
	private Vector<ImageIcon> vehicleImages;
	private Vector<WatercraftInterface> watercraftList;
	// ******************************************* //
	
	// ** Constructor **************************** //
	public VehiclesState(Vector<VehicleInterface> vehicleList, Vector<ImageIcon> vehicleImages, Vector<WatercraftInterface> watercraftList) {
		
		this.vehicleList = new Vector<VehicleInterface>();
		this.vehicleImages = new Vector<ImageIcon>();
		this.watercraftList = new Vector<WatercraftInterface>();
		
		for (int i = 0; i < vehicleList.size(); i++) {
			this.vehicleList.addElement(vehicleList.elementAt(i).clone());
			this.vehicleImages.addElement(vehicleImages.elementAt(i));
			try {
				this.watercraftList.addElement((WatercraftInterface)vehicleList.elementAt(i).clone());
			} catch (Exception e) {}
		}
		
	}
	// ******************************************* //
	
	// ** Getters ******************************** //
	public Vector<VehicleInterface> getVehicleList() {
		return this.vehicleList;
	}
	
	public Vector<ImageIcon> getVehicleImages(){
		return this.vehicleImages;
	}
	
	public Vector<WatercraftInterface> getWatercraftList(){
		return this.watercraftList;
	}
	// ******************************************* //
}

package decorators;

import interfaces.VehicleInterface;

public class StatusDecorator extends GeneralDecorator {

	// ** State ********************************************* //
	private String status;
	// ****************************************************** //
	
	// ** Constructor *************************************** //
	public StatusDecorator(VehicleInterface vehicle) {
		super(vehicle);
		this.status = "Idle";
	}
	// ****************************************************** //

	// ** Getters & Setters ********************************* //
	public String getStatus() {
		return this.status;
	}
	
	public boolean setStatus(String status) {
		this.status = status;
		return true;
	}
	// ****************************************************** //
	
	// ** Remove Layer ************************************** //
	public VehicleInterface removeLayer() {
		return this.vehicle;
	}
	
	// ** toString ****************************************** //
	@Override
	public String toString() {
		return super.toString() + "Status: " + this.status;
	}
	// ****************************************************** //
	
	// ** Clone ********************************************* //
	public StatusDecorator clone() {
		return new StatusDecorator(vehicle.clone());
	}
	// ****************************************************** //
}

package decorators;

import interfaces.VehicleInterface;

public abstract class GeneralDecorator implements VehicleInterface, Cloneable {

	// ** State *********************************** //
	protected VehicleInterface vehicle;
	// ******************************************** //
	
	// ** Consructor ****************************** //
	public GeneralDecorator(VehicleInterface vehicle) {
		this.vehicle = vehicle;
	}
	// ******************************************** //
	
	// ** Inerface methods ************************ //
	@Override
	public boolean movement(double distance) {
		return this.vehicle.movement(distance);
	}
	
	@Override
	public double getTotalDistance() {
		return this.vehicle.getTotalDistance();
	}
	
	@Override 
	public void setTotalDistance(double distance) {
		this.vehicle.setTotalDistance(distance);
	}
	
	@Override
	public String getModelName() {
		return this.vehicle.getModelName();
	}
	
	@Override
	public int getMaximumPassengers() {
		return this.vehicle.getMaximumPassengers();
	}
	
	@Override
	public double getMaximumSpeed() {
		return this.vehicle.getMaximumSpeed();
	}
	// ******************************************** //
	
	// ** toString & equals *********************** //
	@Override
	public String toString() {
		return this.vehicle.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof GeneralDecorator)
				& this.vehicle.equals(((GeneralDecorator)other).vehicle);
	}
	// ******************************************** //
	
	public GeneralDecorator clone() {
		GeneralDecorator gd = new GeneralDecorator(vehicle.clone()) {
		};
		return gd;
	}
}

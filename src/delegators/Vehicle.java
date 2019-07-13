package delegators;

import interfaces.VehicleInterface;

public class Vehicle implements VehicleInterface {

	// ** State ********************************** //
	private double totalDistance;
	private final String modelName;
	private final int maximumPassengers;
	private final double maximumSpeed;
    // ******************************************* // 
	
	// ** Constructor **************************** //
	public Vehicle(String modelName, int maximumPassengers, double maximumSpeed) throws Exception {
		if(maximumPassengers < 0 || maximumSpeed < 0)
			throw new Exception();
		this.totalDistance = 0;
		this.modelName = modelName;
		this.maximumPassengers = maximumPassengers;
		this.maximumSpeed = maximumSpeed;
	}
	// ******************************************* // 

	// ** Behavior ******************************* // 
	public boolean movement(double distance) {
		try {
			Thread.sleep((long) (100 * distance));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.totalDistance += distance;
		return true;
	}
	// ******************************************* //

	// ** Getters & Setters ********************** //
	public double getTotalDistance() {
		return this.totalDistance;
	}
	
	public void setTotalDistance(double distance) {
		this.totalDistance = distance;
	}

	public String getModelName() {
		return this.modelName;
	}

	public int getMaximumPassengers() {
		return this.maximumPassengers;
	}

	public double getMaximumSpeed() {
		return this.maximumSpeed;
	}
	// ******************************************* //

	// ** toString & equals ********************** //
	@Override
	public String toString() {
		return "Model: " + this.modelName
				+ ", traveled: " + this.totalDistance
				+ "km, Max speed of " + this.maximumSpeed
				+ " Mph, " + "can carry max of " + this.maximumPassengers + " people.\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Vehicle)
				&& (this.modelName.equals(((Vehicle) other).modelName))
				&& (this.maximumPassengers == ((Vehicle) other).maximumPassengers)
				&& (this.maximumSpeed == ((Vehicle) other).maximumSpeed);
	}
	// ******************************************* //
	
	// ** Clone ********************************** //
	public VehicleInterface clone() {
		return null;
	}
	// ******************************************* //
}
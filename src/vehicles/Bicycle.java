package vehicles;

import delegators.LandVehicle;
import delegators.NonMotor;
import delegators.Vehicle;
import interfaces.LandVehicleInterface;
import interfaces.NonMotorized;
import interfaces.VehicleInterface;

public class Bicycle implements VehicleInterface, LandVehicleInterface, NonMotorized, Cloneable {

	// ** State ************************************* //
	private Vehicle vehiclePart;
	private LandVehicle landVehiclePart;
	private NonMotor nonMotor;
	// ********************************************** //
	
	// ** Constructor ******************************* //
	public Bicycle(String name, int passengers, double speed, String terrain) throws Exception {
		this.vehiclePart = new Vehicle(name, passengers, speed);
		this.landVehiclePart = new LandVehicle(2, terrain);
		this.nonMotor = new NonMotor("Manual", "A");
	}
	// ********************************************** //
	
	// ** Interfaces methods ************************ //
	// ** Vehicle methods *************************** //
	@Override
	public boolean movement(double distance) {
		return this.vehiclePart.movement(distance);
	}

	@Override
	public double getTotalDistance() {
		return this.vehiclePart.getTotalDistance();
	}
	
	@Override
	public void setTotalDistance(double distance) {
		this.vehiclePart.setTotalDistance(distance);
	}

	@Override
	public String getModelName() {
		return this.vehiclePart.getModelName();
	}

	@Override
	public int getMaximumPassengers() {
		return this.vehiclePart.getMaximumPassengers();
	}

	@Override
	public double getMaximumSpeed() {
		return this.vehiclePart.getMaximumSpeed();
	}
	// ********************************************** //
	
	// ** LandVehicle methods *********************** //
	@Override
	public int getWheels() {
		return this.landVehiclePart.getWheels();
	}

	@Override
	public String getTerrain() {
		return this.landVehiclePart.getTerrain();
	}
	// ********************************************** //
	
	// ** NonMotor methods ************************** //
	@Override
	public String getEnergySource() {
		return this.nonMotor.getEnergySource();
	}
	
	@Override
	public String getEnergyRating() {
		return this.nonMotor.getEnergyRating();
	}
	// ********************************************** //
	
	// ** toString & equals ************************* //
	@Override
	public String toString() {
		return "Bicycle: \n"
			+ this.vehiclePart.toString()
			+ this.landVehiclePart.toString()
			+ this.nonMotor.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof Bicycle)
				&& this.vehiclePart.equals(((Bicycle) other).vehiclePart)
				&& this.landVehiclePart.equals(((Bicycle) other).landVehiclePart)
				&& this.nonMotor.equals(((Bicycle) other).nonMotor);
	}
	// ********************************************** //
	
	// ** Clone ************************************* //
	@Override
	public Bicycle clone() {
		try {
			Bicycle b = new Bicycle(this.getModelName(), this.getMaximumPassengers(), this.getMaximumSpeed()
					, this.getTerrain());
			b.setTotalDistance(this.getTotalDistance());
			return b;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// ********************************************** //
}
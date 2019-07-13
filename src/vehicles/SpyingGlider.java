package vehicles;

import delegators.Aircraft;
import delegators.NonMotor;
import delegators.Vehicle;
import interfaces.AircraftInterface;
import interfaces.NonMotorized;
import interfaces.VehicleInterface;

public class SpyingGlider implements VehicleInterface, AircraftInterface, NonMotorized, Cloneable {

	// ** State ************************************** //
	private Vehicle vehiclePart;
	private Aircraft aircraftPart;
	private NonMotor nonMotor;
	// *********************************************** //

	// ** Constructor ******************************** //
	public SpyingGlider(String energy_source) throws Exception {
		this.vehiclePart = new Vehicle("Classified", 1, 50.0);
		this.aircraftPart = new Aircraft("Millitary");
		this.nonMotor = new NonMotor(energy_source, "C");
	}
	// *********************************************** //
	
	// ** Interfaces methods ************************* //
	// ** Vehicle methods **************************** //
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
	// *********************************************** //
	
	// ** AirCraft methods *************************** //
	@Override
	public String getUsage() {
		return this.aircraftPart.getUsage();
	}

	@Override
	public boolean setUsage(String usage) {
		return this.aircraftPart.setUsage(usage);
	}
	// *********************************************** //
	
	// ** NonMotor methods *************************** //
	@Override
	public String getEnergySource() {
		return this.nonMotor.getEnergySource();
	}
	
	@Override
	public String getEnergyRating() {
		return this.nonMotor.getEnergyRating();
	}
	// *********************************************** //

	// ** toString & equals ************************** //
	@Override
	public String toString() {
		return "Spying Gilder: \n"
				+ this.vehiclePart.toString()
				+ this.aircraftPart.toString()
				+ this.nonMotor.toString();
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof SpyingGlider)
				&& this.vehiclePart.equals(((SpyingGlider) other).vehiclePart)
				&& this.nonMotor.equals(((SpyingGlider) other).nonMotor)
				&& this.aircraftPart.equals(((SpyingGlider)other).aircraftPart);
	}
	// *********************************************** //

	// ** Clone ************************************** //
	@Override 
	public SpyingGlider clone() {
		try {
			SpyingGlider sg = new SpyingGlider(this.getEnergySource());
			sg.setTotalDistance(this.getTotalDistance());
			return sg;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// *********************************************** //
}

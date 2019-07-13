package vehicles;

import delegators.Aircraft;
import delegators.NonMotor;
import delegators.Vehicle;
import interfaces.AircraftInterface;
import interfaces.NonMotorized;
import interfaces.VehicleInterface;

public class GameGlider implements VehicleInterface, AircraftInterface, NonMotorized, Cloneable{

	// ** State ****************************************** //
	private Vehicle vehiclePart;
	private Aircraft aircraftPart;
	private NonMotor nonMotor;
	// *************************************************** //

	// ** Constructor ************************************ //
	public GameGlider() throws Exception {
		this.vehiclePart = new Vehicle("Toy", 0, 10.0);
		this.aircraftPart = new Aircraft("Civil");
		this.nonMotor = new NonMotor("Manual", "A");
	}
	// *************************************************** //
	
	// ** Interfaces methods ***************************** //
	// ** Vehicle methods ******************************** //
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
	// *************************************************** //

	// ** AirCraft methods ******************************* //
	@Override
	public String getUsage() {
		return this.aircraftPart.getUsage();
	}

	@Override
	public boolean setUsage(String usage) {
		return this.aircraftPart.setUsage(usage);
	}
	// *************************************************** //
	
	// ** NonMotor methods ******************************* //
	@Override
	public String getEnergySource() {
		return this.nonMotor.getEnergySource();
	}
	
	@Override
	public String getEnergyRating() {
		return this.nonMotor.getEnergyRating();
	}
	// *************************************************** //

	// ** toString & equals ****************************** //
	@Override
	public String toString() {
		return "Game Glider: \n"
				+ this.vehiclePart.toString()
				+ this.aircraftPart.toString()
				+ this.nonMotor.toString();
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof GameGlider)
				&& this.vehiclePart.equals(((GameGlider)other).vehiclePart)
				&& this.aircraftPart.equals(((GameGlider)other).aircraftPart)
				&& this.nonMotor.equals(((GameGlider) other).nonMotor);
	}
	// *************************************************** //

	// ** Clone ****************************************** //
	@Override
	public GameGlider clone() {
		try {
			GameGlider gg = new GameGlider();
			gg.setTotalDistance(this.getTotalDistance());
			return gg;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// *************************************************** //
}
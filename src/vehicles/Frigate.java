package vehicles;

import delegators.Motor;
import delegators.Vehicle;
import delegators.Watercraft;
import interfaces.Motorized;
import interfaces.VehicleInterface;
import interfaces.WatercraftInterface;

public class Frigate implements VehicleInterface, WatercraftInterface, Motorized, Cloneable {

	// ** State ************************************** //
	private Vehicle vehiclePart;
	private Watercraft watercraftPart;
	private Motor motor;
	// *********************************************** //

	// ** Constructor ******************************** //
	public Frigate(String name, int passengers, double speed, boolean sailingWithWind) throws Exception {
		this.vehiclePart = new Vehicle(name, passengers, speed);
		this.watercraftPart = new Watercraft(sailingWithWind, "Israel");
		this.motor = new Motor(500.0, 4.0);
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
	
	// ** WaterCraft methods ************************* //
	@Override
	public boolean getSailingWithWind() {
		return this.watercraftPart.getSailingWithWind();
	}

	@Override
	public boolean setSailingWithWind(boolean sailingWithWind) {
		return this.watercraftPart.setSailingWithWind(sailingWithWind);
	}

	@Override
	public String getCountry_flag() {
		return this.watercraftPart.getCountry_flag();
	}

	@Override
	public boolean setCountry_flag(String country_flag) {
		return this.watercraftPart.setCountry_flag(country_flag);
	}
	// *********************************************** //
	
	// ** Motor methods ****************************** //
	@Override
	public double getAvgFuelConsumption() {
		return this.motor.getAvgFuelConsumption();
	}

	@Override
	public boolean setAvgFuelConsumption(double avgFuelConsumption) {
		return this.motor.setAvgFuelConsumption(avgFuelConsumption);
	}

	@Override
	public double getAvgEngineLifeSpan() {
		return this.motor.getAvgEngineLifeSpan();
	}

	@Override
	public boolean setAvgEngineLifeSpan(double avgEngineLifeSpan) {
		return this.motor.setAvgEngineLifeSpan(avgEngineLifeSpan);
	}
	// *********************************************** //

	// ** toString & equals ************************** //
	@Override
	public String toString() {
		return "Frigate: \n"
				+ this.vehiclePart.toString()
				+ this.watercraftPart.toString()
				+ this.motor.toString();
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Frigate)
				&& this.vehiclePart.equals(((Frigate)other).vehiclePart)
				&& this.watercraftPart.equals(((Frigate)other).watercraftPart)
				&& this.motor.equals(((Frigate) other).motor);
	}
	// *********************************************** //
	
	// ** Clone ************************************** //
	@Override 
	public Frigate clone() {
		try {
			Frigate f = new Frigate(this.getModelName(), this.getMaximumPassengers(), this.getMaximumSpeed()
					, this.getSailingWithWind());
			f.setTotalDistance(this.getTotalDistance());
			return f;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// *********************************************** //
}
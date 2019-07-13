package vehicles;

import delegators.CommercialUse;
import delegators.LandVehicle;
import delegators.Motor;
import delegators.Vehicle;
import interfaces.CommercialUsage;
import interfaces.LandVehicleInterface;
import interfaces.Motorized;
import interfaces.VehicleInterface;

public class Jeep implements VehicleInterface ,LandVehicleInterface, Motorized, CommercialUsage, Cloneable{

	// ** State ***************************************** //
	private Vehicle vehiclePart;
	private LandVehicle landVehiclePart;
	private CommercialUse commercialUse;
	private Motor motor;
	// ************************************************** //

	// ** Constructor *********************************** //
	public Jeep(String name, double speed, double avgFuelConsumption, double avgEngineLifeSpan) throws Exception {
		this.vehiclePart = new Vehicle(name, 5, speed);
		this.landVehiclePart = new LandVehicle(4, "Dirt");
		this.commercialUse = new CommercialUse("MINI");
		this.motor = new Motor(avgFuelConsumption, avgEngineLifeSpan);
	}
	
	// ** Interfaces method ***************************** //
	// ** Vehicle methods ******************************* // 
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
	// ************************************************** //
	
	// ** LandVehicle methods *************************** //
	@Override
	public int getWheels() {
		return this.landVehiclePart.getWheels();
	}

	@Override
	public String getTerrain() {
		return this.landVehiclePart.getTerrain();
	}
	// ************************************************** //
	
	// ** CommercialUse methods ************************* //
	@Override
	public String getLicenceType() {
		return this.commercialUse.getLicenceType();
	}
	// ************************************************** //
	
	// ** Motor methods ********************************* //
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
	// ************************************************** //
	
	// ** toString & equals ***************************** //
	@Override
	public String toString() {
		return "Jeep: \n"
				+ this.vehiclePart.toString()
				+ this.landVehiclePart.toString()
				+ this.commercialUse.toString()
				+ this.motor.toString();
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Jeep)
				&& this.vehiclePart.equals(((Jeep)other).vehiclePart)
				&& this.landVehiclePart.equals(((Jeep)other).landVehiclePart)
				&& this.motor.equals(((Jeep) other).motor)
				&& this.commercialUse.equals(((Jeep)other).commercialUse);
	}
	// ************************************************** //

	// ** Clone ***************************************** //
	@Override
	public Jeep clone() {
		try {
			Jeep j = new Jeep(this.getModelName(), this.getMaximumSpeed()
					, this.getAvgFuelConsumption(), this.getAvgFuelConsumption());
			j.setTotalDistance(this.getTotalDistance());
			return j;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// ************************************************** //
}

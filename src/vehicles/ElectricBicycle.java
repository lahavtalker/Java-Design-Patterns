package vehicles;

import delegators.LandVehicle;
import delegators.Motor;
import delegators.Vehicle;
import interfaces.LandVehicleInterface;
import interfaces.Motorized;
import interfaces.VehicleInterface;

public class ElectricBicycle implements VehicleInterface, LandVehicleInterface, Motorized, Cloneable {

	// ** State ******************************************** //
	private Vehicle vehiclePart;
	private LandVehicle landVehiclePart;
	private Motor engine;
	// ***************************************************** //
	
	// ** Constructor ************************************** //
	public ElectricBicycle(String modelName, int maximumPassengers, double maximumSpeed,
			String terrain, double avgEngineLifeSpan) throws Exception {
		this.vehiclePart = new Vehicle(modelName, maximumPassengers, maximumSpeed);
		this.landVehiclePart = new LandVehicle(2, terrain);
		this.engine = new Motor(20, avgEngineLifeSpan);
	}
	// ***************************************************** //

	// ** Interface methods ******************************** //
	// ** Vehicle methods ********************************** //
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
	// ***************************************************** //
	
	// ** LandVehicle methods ****************************** //
	@Override
	public int getWheels() {
		return this.landVehiclePart.getWheels();
	}
	@Override
	public String getTerrain() {
		return this.landVehiclePart.getTerrain();
	}
	// ***************************************************** //
	
	// ** Motor methods ************************************ //
	@Override
	public double getAvgFuelConsumption() {
		return this.engine.getAvgFuelConsumption();
	}

	@Override
	public boolean setAvgFuelConsumption(double avgFuelConsumption) {
		return this.engine.setAvgFuelConsumption(avgFuelConsumption);
	}

	@Override
	public double getAvgEngineLifeSpan() {
		return this.engine.getAvgEngineLifeSpan();
	}

	@Override
	public boolean setAvgEngineLifeSpan(double avgEngineLifeSpan) {
		return this.engine.setAvgEngineLifeSpan(avgEngineLifeSpan);
	}
	// ***************************************************** //
	
	// ** toString & equals ******************************** //
	public String toString() {
		return "Electric Bicycle: \n"
				+ this.vehiclePart.toString()
				+ this.landVehiclePart.toString()
				+ this.engine.toString();
	}
	
	public boolean equals(Object other) {
		return (other instanceof ElectricBicycle)
				&& this.vehiclePart.equals(((ElectricBicycle)other).vehiclePart)
				&& this.landVehiclePart.equals(((ElectricBicycle)other).landVehiclePart)
				&& this.engine.equals(((ElectricBicycle)other).engine);
		
	}
	// ***************************************************** //

	// ** Clone ******************************************** //
	@Override 
	public ElectricBicycle clone() {
		try {
			ElectricBicycle eb = new ElectricBicycle(this.getModelName(), this.getMaximumPassengers(), this.getMaximumSpeed()
					, this.getTerrain()
					, this.getAvgEngineLifeSpan());
			eb.setTotalDistance(this.getTotalDistance());
			return eb;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}

package vehicles;

import delegators.LandVehicle;
import delegators.Motor;
import delegators.Vehicle;
import delegators.Watercraft;
import interfaces.LandVehicleInterface;
import interfaces.Motorized;
import interfaces.VehicleInterface;
import interfaces.WatercraftInterface;

public class AmphibiousVehicle implements VehicleInterface ,LandVehicleInterface, WatercraftInterface, Motorized, Cloneable{

	// ** State ************************************ // 
	private Vehicle vehiclePart;
	private LandVehicle landVehiclePart;
	private Watercraft watercraftPart;
	private Motor engine;
	// ********************************************* // 
	
	// ** Constructor ****************************** //
	public AmphibiousVehicle(String name, int passengers, double speed, int wheels,
			boolean sailingWithWind, String flag, double avgFuelConsumption, double avgEngineLifeSpan) throws Exception {
		this.vehiclePart = new Vehicle(name, passengers, speed);
		this.landVehiclePart = new LandVehicle(wheels, "Paved");
		this.watercraftPart = new Watercraft(sailingWithWind, flag);
		this.engine = new Motor(avgFuelConsumption, avgEngineLifeSpan);
	}
	// ********************************************* //

	// ** Interfaces methods *********************** //
	// ** Vehicle methods ************************** //
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
	// ********************************************* //
	
	// ** LandVehicle methods ********************** //
	@Override
	public int getWheels() {
		return this.landVehiclePart.getWheels();
	}

	@Override
	public String getTerrain() {
		return this.landVehiclePart.getTerrain();
	}
	// ********************************************* //
	
	// ** WaterCraft methods *********************** //
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
	// ********************************************* //
	
	// ** Motor methods **************************** //
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
	// ********************************************* //

	// ** toString & equals ************************ //
	@Override
	public String toString() {
		return "Amphibious Vehicle: \n" + toStringHelper();
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof AmphibiousVehicle)
				&& this.vehiclePart.equals(((AmphibiousVehicle)other).vehiclePart)
				&& this.landVehiclePart.equals(((AmphibiousVehicle)other).landVehiclePart)
				&& this.watercraftPart.equals(((AmphibiousVehicle)other).watercraftPart)
				&& this.engine.equals(((AmphibiousVehicle)other).engine);
	}
	// ********************************************* //
	
	// ** toString part for inheriting classes ***** //
	protected String toStringHelper() {
		return this.vehiclePart.toString() 
				+ this.landVehiclePart.toString()
				+ this.watercraftPart.toString()
				+ this.engine.toString();
	}
	// ********************************************* //
	
	// ** Clone method ***************************** //
	@Override
	public AmphibiousVehicle clone() {
		try {
			AmphibiousVehicle av = new AmphibiousVehicle(this.getModelName(), this.getMaximumPassengers(), this.getMaximumSpeed()
					, this.getWheels()
					, this.getSailingWithWind(), this.getCountry_flag()
					, this.getAvgFuelConsumption(), this.getAvgEngineLifeSpan());
			av.setTotalDistance(this.getTotalDistance());
			return av;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// ********************************************* //
}
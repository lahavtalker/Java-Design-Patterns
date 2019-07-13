package vehicles;

import delegators.CommercialUse;
import delegators.Motor;
import delegators.Vehicle;
import delegators.Watercraft;
import interfaces.CommercialUsage;
import interfaces.Motorized;
import interfaces.VehicleInterface;
import interfaces.WatercraftInterface;

public class CruiseShip implements VehicleInterface ,WatercraftInterface ,CommercialUsage, Motorized, Cloneable {

	// ** State ************************************ //
	private Vehicle vehiclePart;
	private Watercraft watercraftPart;
	private CommercialUse commercialUse;
	private Motor motor;
	// ********************************************* //
	
	
	// ** Constructor ****************************** //
	public CruiseShip(String name, int passengers, double speed, String flag,
			double avgFuelConsumption, double avgEngineLifeSpan) throws Exception {
		this.vehiclePart = new Vehicle(name, passengers, speed);
		this.watercraftPart = new Watercraft(true, flag);
		this.commercialUse = new CommercialUse("UNLIMIT");
		this.motor = new Motor(avgFuelConsumption, avgEngineLifeSpan);
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
		return vehiclePart.getTotalDistance();
	}

	@Override
	public void setTotalDistance(double distance) {
		this.vehiclePart.setTotalDistance(distance);
	}
	
	@Override
	public String getModelName() {
		return vehiclePart.getModelName();
	}

	@Override
	public int getMaximumPassengers() {
		return vehiclePart.getMaximumPassengers();
	}

	@Override
	public double getMaximumSpeed() {
		return vehiclePart.getMaximumSpeed();
	}
	// ********************************************** //
	
	// ** WaterCraft methods ************************* //
	@Override
	public boolean getSailingWithWind() {
		return this.watercraftPart.getSailingWithWind();
	}

	@Override
	public boolean setSailingWithWind(boolean sailingWithWind) {
		return this.setSailingWithWind(sailingWithWind);
	}

	@Override
	public String getCountry_flag() {
		return this.watercraftPart.getCountry_flag();
	}

	@Override
	public boolean setCountry_flag(String country_flag) {
		return this.watercraftPart.setCountry_flag(country_flag);
	}
	// ********************************************************** //
	
	// ** CommecrialUse methods ********************************* //
	@Override
	public String getLicenceType() {
		return this.commercialUse.getLicenceType();
	}
	// ********************************************************** //
	
	// ** Motor methods ***************************************** //
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
	// ********************************************************** //
	
	// ** toString & equals ************************************* //
	@Override
	public String toString() {
		return "CruiseShip: \n"
				+ vehiclePart.toString()
				+ this.watercraftPart.toString()
				+ this.commercialUse.toString()
				+ this.motor.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof CruiseShip)
				&& this.vehiclePart.equals(((CruiseShip)other).vehiclePart)
				&& this.watercraftPart.equals(((CruiseShip)other).watercraftPart)
				&& this.commercialUse.equals(((CruiseShip)other).commercialUse)
				&& this.motor.equals(((CruiseShip)other).motor);
	}
	// ********************************************************** //

	// ** Clone ************************************************* //
	@Override
	public CruiseShip clone() {
		try {
			CruiseShip cp = new CruiseShip(this.getModelName(), this.getMaximumPassengers(), this.getMaximumSpeed()
					, this.getCountry_flag()
					, this.getAvgFuelConsumption(), this.getAvgEngineLifeSpan());
			cp.setTotalDistance(this.getTotalDistance());
			return cp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// ********************************************************** //
}
package vehicles;

import delegators.Aircraft;
import interfaces.AircraftInterface;

public class HybridAirplane extends AmphibiousVehicle implements AircraftInterface, Cloneable{

	// ** State **************************************** //
	private Aircraft aircraftPart;
	// ************************************************* //
	
	// ** Constructor ********************************** //
	public HybridAirplane(String name, int passengers, double speed, int wheels, boolean sailingWithWind, String flag,
			double avgFuelConsumption, double avgEngineLifeSpan) throws Exception {
		super(name, passengers, speed, wheels, sailingWithWind, flag, avgFuelConsumption, avgEngineLifeSpan);
		this.aircraftPart = new Aircraft("Millitary");
	}
	// ************************************************* //

	// Interface methods ******************************* //
	// ** AirCraft methods ***************************** //
	@Override
	public String getUsage() {
		return this.aircraftPart.getUsage();
	}

	@Override
	public boolean setUsage(String usage) {
		return this.aircraftPart.setUsage(usage);
	}
	// ************************************************* //
	
	// ** toString & equals **************************** //
	@Override
	public String toString() {
		return "Hybrid Airplane: "
				+ this.toStringHelper()
				+ this.aircraftPart.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof HybridAirplane)
				&& super.equals(other)
				&& this.aircraftPart.equals(((HybridAirplane)other).aircraftPart);
	}
	// ************************************************* //
	
	// ** Clone **************************************** //
	@Override
	public HybridAirplane clone() {
		try {
			HybridAirplane ha = new HybridAirplane(this.getModelName(), this.getMaximumPassengers(), this.getMaximumSpeed()
					, this.getWheels()
					, this.getSailingWithWind(), this.getCountry_flag()
					, this.getAvgFuelConsumption(), this.getAvgEngineLifeSpan());
			ha.setTotalDistance(this.getTotalDistance());
			return ha;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}

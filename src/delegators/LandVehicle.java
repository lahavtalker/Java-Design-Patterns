package delegators;

public class LandVehicle{

	// ** State ************************************** //
	private final int wheels;
	private final String terrain;
	// *********************************************** //

	// ** Constructor ******************************** //
	public LandVehicle(int wheels, String terrain) throws Exception {
		if(terrain != "Paved" && terrain != "Dirt")
			throw new Exception();
		this.wheels = wheels;
		this.terrain = terrain;
	}
	// *********************************************** //

	// ** Getters ************************************ //
	public int getWheels() {
		return wheels;
	}

	public String getTerrain() {
		return terrain;
	}
	// *********************************************** //

	// ** toString & equals ************************** //
	@Override
	public String toString() {
		return "Has " + this.wheels + " wheels, drives on " + this.terrain + " terrain.\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof LandVehicle)
				&& (this.wheels == ((LandVehicle) other).wheels)
				&& (this.terrain.equals(((LandVehicle) other).terrain));
	}
	// *********************************************** //
}
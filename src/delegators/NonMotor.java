package delegators;

import interfaces.NonMotorized;

public class NonMotor implements NonMotorized {

	// ** State ********************************** //
	private final String energySource;
	private final String energyRating;
	// ******************************************* //

	// ** Constructor **************************** //
	public NonMotor(String energySource, String energyRating) throws Exception {
		if(energyRating != "A" && energyRating != "B" && energyRating != "C")
			throw new Exception();
		this.energySource = energySource;
		this.energyRating = energyRating;
	}
	// ******************************************* //

	// ** Getters ******************************** //
	@Override
	public String getEnergySource() {
		return this.energySource;
	}

	@Override
	public String getEnergyRating() {
		return this.energyRating;
	}
	// ******************************************* //

	// ** toString & equals ********************** //
	@Override
	public String toString() {
		return "Energy source: " + this.energySource
				+ ", energy rating: " + this.energyRating + ".\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof NonMotor)
				&& (this.energyRating.equals(((NonMotor) other).energyRating))
				&& (this.energySource.equals(((NonMotor) other).energySource));

	}
	// ******************************************* //
}

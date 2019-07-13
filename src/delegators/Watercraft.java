package delegators;

public class Watercraft{

	// ** State ********************************** //
	private boolean sailingWithWind;
	private String country_flag;
	// ******************************************* //

	// ** Constructor **************************** //
	public Watercraft(boolean sailingWithWind, String flag) {
		this.sailingWithWind = sailingWithWind;
		this.country_flag = flag;
	}
	// ******************************************* //

	// ** Getters & Setters ********************** //
	public boolean getSailingWithWind() {
		return sailingWithWind;
	}

	public boolean setSailingWithWind(boolean sailingWithWind) {
		this.sailingWithWind = sailingWithWind;
		return true;
	}

	public String getCountry_flag() {
		return country_flag;
	}

	public boolean setCountry_flag(String country_flag) {
		this.country_flag = country_flag;
		return true;
	}
	// ******************************************* //

	// ** toString & equals ********************** //
	@Override
	public String toString() {
		return "Under " + this.country_flag + " flag, "
				+ "sails with the wind: " + this.sailingWithWind + ".\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Watercraft)
				&& (this.sailingWithWind == ((Watercraft) other).sailingWithWind)
				&& (this.country_flag.equals(((Watercraft) other).country_flag));
	}
	// ******************************************* //
}	
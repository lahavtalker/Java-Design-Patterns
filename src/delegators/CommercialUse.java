package delegators;

import interfaces.CommercialUsage;

public class CommercialUse implements CommercialUsage {

	// ** State **************************************** //
	private final String licence_type;
	// ************************************************* //
	
	// ** Constructor ********************************** //
	public CommercialUse(String licence_type) throws Exception {
		if(licence_type != "MINI" && licence_type != "LIMIT" && licence_type != "UNLIMIT")
			throw new Exception();
		this.licence_type = licence_type;
	}
	// ************************************************* //

	// ** Getters ************************************** //
	@Override
	public String getLicenceType() {
		return this.licence_type;
	}
	// ************************************************* //

	// ** toString & equals **************************** //
	@Override
	public String toString() {
		return "Licence type: " + this.licence_type + ".\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof CommercialUse)
				&& this.licence_type.equals(((CommercialUse) other).licence_type);
	}
	// ************************************************* //
}

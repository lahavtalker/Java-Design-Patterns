package delegators;

public class Aircraft{

	// ** State *************************************** // 
	private String usage;
	// ************************************************ //
	
	// ** Constructor ********************************* //
	public Aircraft(String usage) throws Exception {
		if(usage != "Civil" && usage != "Millitary")
			throw new Exception();
		this.usage = usage;
	}
	// ************************************************ //

	// ** Getters & Setters *************************** //
	public String getUsage() {
		return usage;
	}
	
	public boolean setUsage(String usage) {
		this.usage = usage;
		return true;
	}
	// ************************************************ //

	// ** toString & equals *************************** //
	@Override
	public String toString() {
		return "For " + this.usage + " use.\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Aircraft) && (this.usage.equals(((Aircraft) other).usage));
	}
	// ************************************************ //
}
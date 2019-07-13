package delegators;

import interfaces.Motorized;

public class Motor implements Motorized {

	// ** State ***************************************** //
	private double avgFuelConsumption;
	private double avgEngineLifeSpan;
	// ************************************************** //

	// Constructor:
	public Motor(double avgFuelConsumption, double avgEngineLifeSpan) throws Exception {
		if(avgFuelConsumption < 0 || avgEngineLifeSpan <= 0)
			throw new Exception();
		this.avgFuelConsumption = avgFuelConsumption;
		this.avgEngineLifeSpan = avgEngineLifeSpan;
	}
	// ************************************************** //

	// ** Getters & Setters ***************************** //
	@Override
	public double getAvgFuelConsumption() {
		return this.avgFuelConsumption;
	}

	@Override
	public boolean setAvgFuelConsumption(double avgFuelConsumption) {
		this.avgFuelConsumption = avgFuelConsumption;
		return true;
	}

	@Override
	public double getAvgEngineLifeSpan() {
		return this.avgEngineLifeSpan;
	}

	@Override
	public boolean setAvgEngineLifeSpan(double avgEngineLifeSpan) {
		this.avgEngineLifeSpan = avgEngineLifeSpan;
		return true;
	}
	// ************************************************** //

	// ** toString & equals ***************************** //
	@Override
	public String toString() {
		return "Engine: " + this.avgEngineLifeSpan
				+ " L, life of " + this.avgEngineLifeSpan + " years.\n";
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Motor)
				&& (this.avgEngineLifeSpan == ((Motor) other).avgEngineLifeSpan)
				&& (this.avgFuelConsumption == ((Motor) other).avgFuelConsumption);
	}
	// ************************************************** //
}

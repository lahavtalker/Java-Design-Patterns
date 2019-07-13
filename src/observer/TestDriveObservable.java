package observer;

import java.util.Observable;

public class TestDriveObservable extends Observable {

	// ** State ************************************ //
	private double data;
	// ********************************************* //
	
	// ** Constructor ****************************** //
	public TestDriveObservable() {
		this.data = 0;
	}
	// ********************************************* //
	
	// ** Update data and notify ******************* //
	public void updateData(double data) {
		this.data = data;
		setChanged();
		notifyObservers(this);
	}
	// ********************************************* //
	
	// ** Getter *********************************** //
	public double getData() {
		return this.data;
	}
	// ********************************************* //
}

package memento;

public class Memento {

	// ** State ******************************* //
	private VehiclesState state;
	// **************************************** //
	
	// ** Constructor ************************* //
	public Memento(VehiclesState state) {
		this.state = state;
	}
	// **************************************** //

	// ** Getter ****************************** //
	public VehiclesState getVehicleState() {
		return this.state;
	}
	// **************************************** //
}

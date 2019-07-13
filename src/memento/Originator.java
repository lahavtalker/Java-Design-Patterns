package memento;

public class Originator {

	// ** State *************************************** //
	private VehiclesState state;
	// ************************************************ //
	
	// ** Setter ************************************** //
	public void setState(VehiclesState state) {
		this.state = state;
	}
	// ************************************************ //
	
	// ** Save state ********************************** //
	public Memento saveState() {
		return new Memento(state);
	}
	// ************************************************ //
	
	// ** Restore state ******************************* //
    public void restore(Memento m) {
        state = m.getVehicleState();
    }
	// ************************************************ //
	
}

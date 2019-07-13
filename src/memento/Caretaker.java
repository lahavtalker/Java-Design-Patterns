package memento;

import java.util.Vector;

public class Caretaker {

	// ** State ********************************* //
	private Vector<Memento> states;
	// ****************************************** //
	
	// ** Constructor *************************** //
	public Caretaker() {
		this.states = new Vector<Memento>();
	}
	// ****************************************** //
	
	// ** Add State ***************************** //
	public void addMemento(Memento m) {
		
		if(states.size() < 3)
			states.addElement(m);
		else {
			states.insertElementAt(m, 0);
			states.setSize(states.size() - 1);
		}
	}
	// ****************************************** //
	
	// ** Get State ***************************** //
	public Memento getMemento() {
		Memento m = states.lastElement();
		states.removeElementAt(states.size() - 1);
		return m;
	}
	// ****************************************** //
}

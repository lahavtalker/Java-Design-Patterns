package decorators;

import java.awt.Color;

import interfaces.VehicleInterface;

public class ColorDecorator extends GeneralDecorator {

	// ** State *********************************** //
	private Color color;
	// ******************************************** //
	
	// ** Constructor ***************************** //
	public ColorDecorator(VehicleInterface vehicle, Color color) {
		super(vehicle);
		this.color = color;
	}
	// ******************************************** //

	// ** Getter ********************************** //
	public Color getColor() {
		return this.color;
	}
	// ******************************************** //
	
	// ** Clone *********************************** //
	public ColorDecorator clone() {
		return new ColorDecorator(super.vehicle.clone(), this.color);
	}
	// ******************************************** //
}

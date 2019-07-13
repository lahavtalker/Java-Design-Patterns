package factories;

import gui.VehicleCreation;
import interfaces.VehicleInterface;
import vehicles.GameGlider;
import vehicles.SpyingGlider;

public class AircraftFactory {

	public static VehicleInterface getVehicle(String type, VehicleCreation frame) throws Exception {
		
		String source;
		
		switch (type) {
		case "Game Glider":
			return new GameGlider();
		case "Spying Glider":
			source = frame.getTxtInput("source");
			return new SpyingGlider(source);
		default:
			return null;
		}
	}
}

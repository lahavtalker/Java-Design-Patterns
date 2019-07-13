package factories;

import gui.VehicleCreation;
import interfaces.VehicleInterface;
import vehicles.Bicycle;
import vehicles.ElectricBicycle;
import vehicles.Jeep;

public class LandVehicleFactory {

	public static VehicleInterface getVehicle(String type, VehicleCreation frame) throws Exception {
		
		String model, terrain;
		int passengers;
		double speed, avg_engine, avg_fuel;
		
		switch (type) {
		case "Bicycle":
			model = frame.getTxtInput("model");
			passengers = Integer.parseInt(frame.getTxtInput("passengers"));
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			terrain = frame.getTxtInput("terrain");
			Bicycle b = new Bicycle(model, passengers, speed, terrain);
			return b;
		case "Electric Bicycle":
			model = frame.getTxtInput("model");
			passengers = Integer.parseInt(frame.getTxtInput("passengers"));
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			terrain = frame.getTxtInput("terrain");
			avg_engine = Double.parseDouble(frame.getTxtInput("avg engine"));
			ElectricBicycle eb = new ElectricBicycle(model, passengers, speed, terrain, avg_engine);
			return eb;
		case "Jeep":
			model = frame.getTxtInput("model");
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			avg_fuel = Double.parseDouble(frame.getTxtInput("avg fuel"));
			avg_engine = Double.parseDouble(frame.getTxtInput("avg engine"));
			Jeep j = new Jeep(model, speed, avg_fuel, avg_engine);
			return j;
		default:
			return null;
		}
	}
}

package factories;

import gui.VehicleCreation;
import interfaces.VehicleInterface;
import vehicles.AmphibiousVehicle;
import vehicles.CruiseShip;
import vehicles.Frigate;
import vehicles.HybridAirplane;

public class WatercraftFactory {

	public static VehicleInterface getVehicle(String type, VehicleCreation frame) throws Exception {
		
		String model, flag;
		int passengers, wheels;
		double speed, avg_fuel, avg_engine;
		boolean withWind;
		
		switch (type) {
		case "Amphibious Vehicle":
			model = frame.getTxtInput("model");
			passengers = Integer.parseInt(frame.getTxtInput("passengers"));
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			wheels = Integer.parseInt(frame.getTxtInput("wheels"));
			flag = frame.getTxtInput("flag");
			avg_fuel = Double.parseDouble(frame.getTxtInput("avg fuel"));
			avg_engine = Double.parseDouble(frame.getTxtInput("avg engine"));
			withWind = frame.getTrueFalseInput();
			AmphibiousVehicle av = new AmphibiousVehicle(model, passengers, speed, wheels, withWind, flag, avg_fuel, avg_engine);
			return av;
		case "Cruise Ship":
			model = frame.getTxtInput("model");
			passengers = Integer.parseInt(frame.getTxtInput("passengers"));
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			flag = frame.getTxtInput("flag");
			avg_fuel = Double.parseDouble(frame.getTxtInput("avg fuel"));
			avg_engine = Double.parseDouble(frame.getTxtInput("avg engine"));
			CruiseShip cs = new CruiseShip(model, passengers, speed, flag, avg_fuel, avg_engine);
			return cs;
		case "Frigate":
			model = frame.getTxtInput("model");
			passengers = Integer.parseInt(frame.getTxtInput("passengers"));
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			withWind = frame.getTrueFalseInput();
			Frigate f = new Frigate(model, passengers, speed, withWind);
			return f;
		case "Hybrid Airplane":
			model = frame.getTxtInput("model");
			passengers = Integer.parseInt(frame.getTxtInput("passengers"));
			speed = Double.parseDouble(frame.getTxtInput("speed"));
			wheels = Integer.parseInt(frame.getTxtInput("wheels"));
			flag = frame.getTxtInput("flag");
			avg_fuel = Double.parseDouble(frame.getTxtInput("avg fuel"));
			avg_engine = Double.parseDouble(frame.getTxtInput("avg engine"));
			withWind = frame.getTrueFalseInput();
			HybridAirplane ha = new HybridAirplane(model, passengers, speed, wheels, withWind, flag, avg_fuel, avg_engine);
			return ha;
		default:
			return null;
		}
	}
}

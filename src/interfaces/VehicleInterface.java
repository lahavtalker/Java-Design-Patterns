package interfaces;

public interface VehicleInterface {

	public boolean movement(double distance);
	public double getTotalDistance();
	public String getModelName();
	public int getMaximumPassengers();
	public double getMaximumSpeed();
	public void setTotalDistance(double distance);
	public VehicleInterface clone();
	
}

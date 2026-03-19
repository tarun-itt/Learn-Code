public class Motorcycle extends FuelVehicle {

	private final boolean hasSidecar;

	public Motorcycle(VehicleInfo info, double price, double fuelLevel, boolean hasSidecar) {
		super(info, price, fuelLevel);
		this.hasSidecar = hasSidecar;
	}

	@Override
	public String getInfo() {
		return "Motorcycle: " + getYear() + " " + getMake() + " " + getModel()
			+ ", Sidecar: " + hasSidecar + ", Price: $" + getPrice();
	}

	public boolean hasSidecar() {
		return hasSidecar;
	}
}

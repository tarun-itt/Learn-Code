public class Car extends FuelVehicle {

	public Car(VehicleInfo info, double price, double fuelLevel) {
		super(info, price, fuelLevel);
	}

	@Override
	public String getInfo() {
		return "Car: " + getYear() + " " + getMake() + " " + getModel()
			+ ", Price: $" + getPrice();
	}
}

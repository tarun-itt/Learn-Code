public class ElectricCar extends Vehicle {

	private static final double MAX_BATTERY_LEVEL = 100.0;

	private double batteryLevel;

	public ElectricCar(VehicleInfo info, double price, double batteryLevel) {
		super(info, price);
		if (batteryLevel < 0 || batteryLevel > MAX_BATTERY_LEVEL) {
			throw new IllegalArgumentException(
				"Initial battery level must be between 0 and " + MAX_BATTERY_LEVEL + "%, got: " + batteryLevel);
		}
		this.batteryLevel = batteryLevel;
	}

	@Override
	public void energize(double chargeAmount) {
		if (chargeAmount <= 0) {
			throw new IllegalArgumentException("Charge amount must be positive, got: " + chargeAmount);
		}
		if (batteryLevel + chargeAmount > MAX_BATTERY_LEVEL) {
			throw new IllegalArgumentException(
				"Charge amount exceeds battery capacity. Current: " + batteryLevel + "%, max: " + MAX_BATTERY_LEVEL + "%");
		}
		batteryLevel += chargeAmount;
	}

	@Override
	public String getInfo() {
		return "Electric Car: " + getYear() + " " + getMake() + " " + getModel()
			+ ", Price: $" + getPrice();
	}

	@Override
	protected boolean hasEnergy() {
		return batteryLevel > 0;
	}

	public double getBatteryLevel() {
		return batteryLevel;
	}

}

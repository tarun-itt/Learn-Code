public abstract class FuelVehicle extends Vehicle {

	private static final double MAX_FUEL_LEVEL = 100.0;

	private double fuelLevel;

	protected FuelVehicle(VehicleInfo info, double price, double fuelLevel) {
		super(info, price);
		if (fuelLevel < 0 || fuelLevel > MAX_FUEL_LEVEL) {
			throw new IllegalArgumentException(
				"Initial fuel level must be between 0 and " + MAX_FUEL_LEVEL + "%, got: " + fuelLevel);
		}
		this.fuelLevel = fuelLevel;
	}

	@Override
	public void energize(double refuelAmount) {
		if (refuelAmount <= 0) {
			throw new IllegalArgumentException("Refuel amount must be positive, got: " + refuelAmount);
		}
		if (fuelLevel + refuelAmount > MAX_FUEL_LEVEL) {
			throw new IllegalArgumentException(
				"Refuel amount exceeds tank capacity. Current: " + fuelLevel + "%, max: " + MAX_FUEL_LEVEL + "%");
		}
		fuelLevel += refuelAmount;
	}

	@Override
	protected boolean hasEnergy() {
		return fuelLevel > 0;
	}

	public double getFuelLevel() {
		return fuelLevel;
	}

}

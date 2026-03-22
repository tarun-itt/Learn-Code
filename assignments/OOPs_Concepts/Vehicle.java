public abstract class Vehicle {

	private final VehicleInfo info;
	private double price;
	private boolean running;

	protected Vehicle(VehicleInfo info, double price) {
		if (info == null) {
			throw new IllegalArgumentException("VehicleInfo cannot be null");
		}
		this.info = info;
		this.running = false;
		setPrice(price);
	}

	public void start() {
		if (hasEnergy()) {
			running = true;
		} else {
			throw new IllegalStateException("Cannot start - no energy!");
		}
	}

	public void stop() {
		running = false;
	}

	public void setPrice(double price) {
		if (price <= 0) {
			throw new IllegalArgumentException("Invalid price: must be greater than $0");
		}
		this.price = price;
	}

	public abstract void energize(double amount);

	public abstract String getInfo();

	protected abstract boolean hasEnergy();

	public String getMake() {
		return info.make();
	}

	public String getModel() {
		return info.model();
	}

	public int getYear() {
		return info.year();
	}

	public VehicleInfo getVehicleInfo() {
		return info;
	}

	public double getPrice() {
		return price;
	}

	public boolean isRunning() {
		return running;
	}
}

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {

	private final List<Vehicle> vehicles = new ArrayList<>();

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		System.out.println(vehicle.getClass().getSimpleName() + " added.");
	}

	public void displayAll() {
		System.out.println("\n=== Vehicles ===");
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle.getInfo());
		}
	}

	public double calculateTotalValue() {
		double totalValue = 0;
		for (Vehicle vehicle : vehicles) {
			totalValue += vehicle.getPrice();
		}
		return totalValue;
	}

	public void startAllVehicles() {
		for (Vehicle vehicle : vehicles) {
			try {
				vehicle.start();
				System.out.println(vehicle.getMake() + " " + vehicle.getModel() + " started.");
			} catch (IllegalStateException exception) {
				System.out.println(exception.getMessage());
			}
		}
	}
}

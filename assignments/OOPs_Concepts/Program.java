public class Program {

	public static void main(String[] args) {
		System.out.println("=== Vehicle Management Demo ===\n");

		Car car = new Car(new VehicleInfo("Honda", "Accord", 2023), 28000, 100);
		Motorcycle motorcycle = new Motorcycle(new VehicleInfo("Harley-Davidson", "Street 750", 2022), 7500, 80, false);
		ElectricCar electricCar = new ElectricCar(new VehicleInfo("Tesla", "Model 3", 2023), 42000, 100);

		demonstrateIndividualVehicles(car, motorcycle, electricCar);
		demonstrateVehicleManager(car, motorcycle, electricCar);
		demonstrateEncapsulation(car);

		System.out.println("\n=== Demo Complete ===");
	}

	private static void demonstrateIndividualVehicles(Car car, Motorcycle motorcycle,
			ElectricCar electricCar) {
		System.out.println("Testing Vehicles:");

		try { car.start(); System.out.println(car.getMake() + " " + car.getModel() + " started."); } catch (IllegalStateException exception) { System.out.println(exception.getMessage()); }
		System.out.println(car.getInfo());
		car.stop();
		System.out.println();

		try { motorcycle.start(); System.out.println(motorcycle.getMake() + " " + motorcycle.getModel() + " started."); } catch (IllegalStateException exception) { System.out.println(exception.getMessage()); }
		System.out.println(motorcycle.getInfo());
		System.out.println();

		try { electricCar.start(); System.out.println(electricCar.getMake() + " " + electricCar.getModel() + " started."); } catch (IllegalStateException exception) { System.out.println(exception.getMessage()); }
		System.out.println(electricCar.getInfo());
	}

	private static void demonstrateVehicleManager(Vehicle car, Vehicle motorcycle,
			Vehicle electricCar) {
		VehicleManager vehicleManager = new VehicleManager();
		vehicleManager.addVehicle(car);
		vehicleManager.addVehicle(motorcycle);
		vehicleManager.addVehicle(electricCar);

		vehicleManager.displayAll();
		System.out.println("\nTotal Value: $" + vehicleManager.calculateTotalValue());

		System.out.println("\nStarting all vehicles:");
		vehicleManager.startAllVehicles();
	}

	private static void demonstrateEncapsulation(Car car) {
		System.out.println("\n=== Encapsulation Demo ===");

		try {
			car.setPrice(-1000);
		} catch (IllegalArgumentException exception) {
			System.out.println("Caught exception: " + exception.getMessage());
		}
		System.out.println("Car price after invalid set: $" + car.getPrice());

		try {
			car.energize(500);
			System.out.println("Refueled. Fuel level: " + car.getFuelLevel() + "%");
		} catch (IllegalArgumentException exception) {
			System.out.println("Caught exception: " + exception.getMessage());
		}

		try {
			car.energize(-10);
			System.out.println("Refueled. Fuel level: " + car.getFuelLevel() + "%");
		} catch (IllegalArgumentException exception) {
			System.out.println("Caught exception: " + exception.getMessage());
		}
	}
}

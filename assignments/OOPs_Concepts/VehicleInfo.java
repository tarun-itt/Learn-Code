public record VehicleInfo(String make, String model, int year) {
    public VehicleInfo {
        if (make == null || make.isBlank()) {
            throw new IllegalArgumentException("Make cannot be null or empty.");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be greater than 0.");
        }
    }
}

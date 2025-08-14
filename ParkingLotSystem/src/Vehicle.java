public class Vehicle {
    private String licensePlate;
    private String type;

    public Vehicle(String licensePlate, String type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + licensePlate;
    }
}

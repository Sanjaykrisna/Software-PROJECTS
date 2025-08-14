import java.time.LocalDateTime;
import java.time.Duration;

public class Ticket {
    private int slotNumber;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(int slotNumber, Vehicle vehicle) {
        this.slotNumber = slotNumber;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
    
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public long getParkedMinutes() {
        if (exitTime == null) return 0;
        Duration duration = Duration.between(entryTime, exitTime);
        return duration.toMinutes();
    }

    public double calculateFee() {
        long minutes = getParkedMinutes();
        if (minutes <= 15) return 0.0;

        double hours = Math.ceil(minutes / 60.0);

        double ratePerHour = switch (vehicle.getType().toLowerCase()) {
            case "car" -> 30.0;
            case "bike" -> 15.0;
            default -> 20.0; // Default rate for unknown vehicle types
        };

        return hours * ratePerHour;
    }


    @Override
    public String toString() {
        return "Ticket{Slot: " + slotNumber + ", Vehicle: " + vehicle +
               ", Entry: " + entryTime +
               (exitTime != null ? ", Exit: " + exitTime : "") + "}";
    }
}

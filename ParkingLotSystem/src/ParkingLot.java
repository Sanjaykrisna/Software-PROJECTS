import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private final int capacity;
    private final Map<Integer, Ticket> occupiedSlots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.occupiedSlots = new HashMap<>();
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if (occupiedSlots.size() >= capacity) {
            System.out.println("Parking lot is full.");
            return null;
        }

        for (int i = 1; i <= capacity; i++) {
            if (!occupiedSlots.containsKey(i)) {
                Ticket ticket = new Ticket( i, vehicle);
                occupiedSlots.put(i, ticket);
                return ticket;
            }
        }

        return null;
    }

    public Ticket unparkVehicle(int slotNumber) {
        Ticket ticket = occupiedSlots.remove(slotNumber);
        if (ticket != null) {
            ticket.setExitTime(LocalDateTime.now());
        }
        return ticket;
    }


    public void showStatus() {
        if (occupiedSlots.isEmpty()) {
            System.out.println("Parking lot is empty.");
        } else {
            System.out.println("Currently Parked Vehicles:");
            for (Map.Entry<Integer, Ticket> entry : occupiedSlots.entrySet()) {
                System.out.println("Slot " + entry.getKey() + ": " + entry.getValue().getVehicle());
            }
        }
    }

    public int getAvailableSlots() {
        return capacity - occupiedSlots.size();
    }
}

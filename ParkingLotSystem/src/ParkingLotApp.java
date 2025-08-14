import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ParkingLotApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // capacity: 5

        while (true) {
            System.out.println("\n== Parking Lot Menu ==");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. Show Status");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle type (Car/Bike): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter license plate: ");
                    String plate = scanner.nextLine();
                    Vehicle vehicle = new Vehicle(plate, type);
                    Ticket ticket = parkingLot.parkVehicle(vehicle);
                    if (ticket != null) {
                        System.out.println("Parked successfully! Ticket info:");
                        System.out.println(ticket);
                    }
                    break;
                case 2:
                    System.out.print("Enter slot number to unpark: ");
                    int slot = scanner.nextInt();
                    Ticket removed = parkingLot.unparkVehicle(slot);
                    if (removed != null) {
                        System.out.println("Unparked vehicle: " + removed.getVehicle());
                        System.out.println("Entry time: " + removed.getEntryTime());
                        System.out.println("Exit time: " + LocalDateTime.now());
                        System.out.println("Parked duration (mins): " + removed.getParkedMinutes());
                        System.out.println("Total fee: ₹" + removed.calculateFee());
                        
                        //To Save receipt
                        try (PrintWriter writer = new PrintWriter("receipt_" + removed.getVehicle().getLicensePlate() + ".txt")) {
                            writer.println("---- Parking Receipt ----");
                            writer.println("Vehicle: " + removed.getVehicle());
                            writer.println("Slot No: " + removed.getSlotNumber());
                            writer.println("Entry: " + removed.getEntryTime());
                            writer.println("Exit: " + removed.getExitTime());
                            writer.println("Duration: " + removed.getParkedMinutes() + " mins");
                            writer.println("Amount Paid: ₹" + removed.calculateFee());
                            writer.println("-------------------------");
                            System.out.println("Receipt saved to file.");
                        } catch (IOException e) {
                            System.out.println("Error writing receipt: " + e.getMessage());
                        }
                    } else {
                        System.out.println("No vehicle found at slot " + slot);
                    }
                    break;

                case 3:
                    System.out.print("Enter admin password to view status: ");
                    String adminPass = scanner.nextLine();

                    if (!adminPass.equals("admin123")) {
                        System.out.println("Incorrect password. Access denied.");
                    } else {
                        parkingLot.showStatus();
                        System.out.println("Available slots: " + parkingLot.getAvailableSlots());
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

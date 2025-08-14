import model.*;
import service.*;
import util.SummaryReport;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        //Load existing data
        manager.getAllTransactions().addAll(FileHandler.loadFromFile());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Transaction\n2. View All\n3. Summary\n4. Save & Exit\n5. View Charts (Swing)\n6. Import Transactions from File");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                	//Add transaction logic
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    
                    System.out.print("Amount: ");
                    double amt = scanner.nextDouble();
                    
                    System.out.print("Is it income? (true/false): ");
                    boolean isIncome = scanner.nextBoolean();
                    
                    scanner.nextLine(); // consume newline
                    System.out.print("Category (FOOD, TRANSPORT, etc.): ");
                    
                    Category cat = Category.valueOf(scanner.nextLine().toUpperCase());
                    
                    Transaction t = new Transaction(desc, amt, cat, LocalDate.now(), isIncome);
                    manager.addTransaction(t);
                    System.out.println("✅ Added!");
                    break;
                case 2:
                	//View transactions
                    manager.getAllTransactions().forEach(System.out::println);
                    break;
                case 3:
                	//Summary logic
                    System.out.printf("Total Income: $%.2f\n", manager.getTotalIncome());
                    System.out.printf("Total Expense: $%.2f\n", manager.getTotalExpense());
                    //call summary reports
                    SummaryReport.printCategorySummary(manager.getAllTransactions());
                    SummaryReport.printMonthlySummary(manager.getAllTransactions());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    //to save and load transactions to/from a CSV file
                    FileHandler.saveToFile(manager.getAllTransactions());
                    return;
                case 5:
                    new gui.ChartViewSwing("Expense Charts", manager);
                    break;
                case 6:
                    List<Transaction> imported = FileHandler.loadFromFile();
                    manager.getAllTransactions().addAll(imported);
                    System.out.println(imported.size() + " transactions added from file.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

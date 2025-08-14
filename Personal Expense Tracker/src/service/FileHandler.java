package service;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "transactions.csv";

    public static void saveToFile(List<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.printf("%s,%f,%s,%s,%b%n",
                        t.getDescription(),
                        t.getAmount(),
                        t.getCategory(),
                        t.getDate(),
                        t.isIncome()
                );
            }
            System.out.println("✅ Data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("❌ Error writing file: " + e.getMessage());
        }
    }

    public static List<Transaction> loadFromFile() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
        	System.out.println("⚠️ No file found: " + FILE_NAME);
            return transactions; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length != 5) continue; // Skip invalid lines

                String description = parts[0];
                double amount = Double.parseDouble(parts[1]);
                Category category = Category.valueOf(parts[2].toUpperCase());
                LocalDate date = LocalDate.parse(parts[3]);
                boolean isIncome = Boolean.parseBoolean(parts[4]);

                Transaction t = new Transaction(description, amount, category, date, isIncome);
                transactions.add(t);
            }
            System.out.println("✅ Transactions imported successfully.");
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("❌ Failed to load data: " + e.getMessage());
        }

        return transactions;
    }
}

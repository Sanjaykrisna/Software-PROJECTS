package util;

import model.Transaction;
import model.Category;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class SummaryReport {

    // Group total amount by category
    public static void printCategorySummary(List<Transaction> transactions) {
        Map<Category, Double> categoryTotals = new HashMap<>();

        for (Transaction t : transactions) {
            categoryTotals.merge(
                t.getCategory(),
                t.getAmount() * (t.isIncome() ? 1 : -1),
                Double::sum
            );
        }

        System.out.println("\n💼 Category Summary:");
        for (Map.Entry<Category, Double> entry : categoryTotals.entrySet()) {
            System.out.printf("%-15s : ₹%.2f\n", entry.getKey(), entry.getValue());
        }
    }

    // Group by month and calculate net change
    public static void printMonthlySummary(List<Transaction> transactions) {
        Map<Month, Double> monthlyTotals = new TreeMap<>();

        for (Transaction t : transactions) {
            Month month = t.getDate().getMonth();
            double signedAmount = t.isIncome() ? t.getAmount() : -t.getAmount();
            monthlyTotals.merge(month, signedAmount, Double::sum);
        }

        System.out.println("\n📅 Monthly Summary:");
        for (Map.Entry<Month, Double> entry : monthlyTotals.entrySet()) {
            System.out.printf("%-10s : ₹%.2f\n", entry.getKey(), entry.getValue());
        }
    }
}

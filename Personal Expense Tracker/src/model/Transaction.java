package model;

import java.time.LocalDate;

public class Transaction {
    private String description;
    private double amount;
    private Category category;
    private LocalDate date;
    private boolean isIncome;

    public Transaction(String description, double amount, Category category, LocalDate date, boolean isIncome) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.isIncome = isIncome;
    }

    // Getters
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public boolean isIncome() { return isIncome; }

    @Override
    public String toString() {
        return date + " | " + (isIncome ? "Income" : "Expense") + " | " +
               category + " | " + description + " | ₹" + amount;
    }
}

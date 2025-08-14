package gui;

import model.Transaction;
import javax.swing.JFrame;
import service.TransactionManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartViewSwing extends JFrame {

    public ChartViewSwing(String title, TransactionManager manager) {
        super(title);

        // Create charts
        JFreeChart pieChart = createCategoryPieChart(manager.getAllTransactions());
        JFreeChart barChart = createMonthlyBarChart(manager.getAllTransactions());

        // Layout
        java.awt.GridLayout layout = new java.awt.GridLayout(2, 1);
        setLayout(layout);

        ChartPanel piePanel = new ChartPanel(pieChart);
        ChartPanel barPanel = new ChartPanel(barChart);

        add(piePanel);
        add(barPanel);

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JFreeChart createCategoryPieChart(List<Transaction> transactions) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<String, Double> categoryMap = new HashMap<>();
        for (Transaction t : transactions) {
            if (!t.isIncome()) {
                categoryMap.merge(t.getCategory().toString(), t.getAmount(), Double::sum);
            }
        }

        for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        return ChartFactory.createPieChart(
                "Expenses by Category",
                dataset,
                true, true, false
        );
    }

    private JFreeChart createMonthlyBarChart(List<Transaction> transactions) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Month, Double> incomeMap = new HashMap<>();
        Map<Month, Double> expenseMap = new HashMap<>();

        for (Transaction t : transactions) {
            Month month = t.getDate().getMonth();
            if (t.isIncome()) {
                incomeMap.merge(month, t.getAmount(), Double::sum);
            } else {
                expenseMap.merge(month, t.getAmount(), Double::sum);
            }
        }

        for (Month month : Month.values()) {
            dataset.addValue(incomeMap.getOrDefault(month, 0.0), "Income", month.name());
            dataset.addValue(expenseMap.getOrDefault(month, 0.0), "Expense", month.name());
        }

        return ChartFactory.createBarChart(
                "Monthly Income vs Expense",
                "Month",
                "Amount",
                dataset
        );
    }
}

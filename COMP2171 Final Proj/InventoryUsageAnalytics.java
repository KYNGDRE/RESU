import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class InventoryUsageAnalytics {

    private StockInventory stockInventory;

    public InventoryUsageAnalytics(StockInventory stockInventory) {
        this.stockInventory = stockInventory;
    }

    // Method to generate report
    public String generateReport(String timeRange) {
        Map<String, Integer> usageData = new HashMap<>();
        // Placeholder logic for report generation based on time range
        // In a real scenario, we would filter based on actual usage data and time ranges

        // Sample data for illustration
        usageData.put("Paper A", 50);
        usageData.put("Ink Red", 30);
        usageData.put("Paper B", 80);
        usageData.put("Ink Blue", 20);
        usageData.put("Paper C", 100);

        StringBuilder report = new StringBuilder();
        report.append("Inventory Usage Report (").append(timeRange).append(")\n\n");
        report.append("Item Name\t\tUsage Quantity\n");

        // Display total usage of each item
        for (Map.Entry<String, Integer> entry : usageData.entrySet()) {
            report.append(entry.getKey()).append("\t\t").append(entry.getValue()).append("\n");
        }

        // Get top 5 most frequently used items
        report.append("\nTop 5 Most Frequently Used Items:\n");
        usageData.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .limit(5)
                .forEach(entry -> report.append(entry.getKey()).append("\t\t").append(entry.getValue()).append("\n"));

        return report.toString();
    }

    // Method to allow export of the report as PDF or CSV
    public boolean exportReport(String reportContent, String format) {
        try {
            if ("pdf".equalsIgnoreCase(format)) {
                // PDF export logic (for simplicity, placeholder)
                System.out.println("Exporting report as PDF...");
                // Add actual PDF generation logic here
            } else if ("csv".equalsIgnoreCase(format)) {
                // CSV export logic
                System.out.println("Exporting report as CSV...");
                // Add actual CSV export logic here
            } else {
                throw new IllegalArgumentException("Invalid export format: " + format);
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting report: " + e.getMessage());
            return false;
        }
    }

    // Method to show report in real-time within the web-based dashboard (Swing demo)
    public void showReportInDashboard(String reportContent) {
        JFrame dashboardFrame = new JFrame("Inventory Usage Report");
        JTextArea reportArea = new JTextArea(reportContent);
        reportArea.setEditable(false);
        dashboardFrame.add(new JScrollPane(reportArea));
        dashboardFrame.setSize(600, 400);
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Testing the InventoryUsageAnalytics class
        StockInventory stockInventory = new StockInventory(); // You can add some sample data here
        InventoryUsageAnalytics analytics = new InventoryUsageAnalytics(stockInventory);

        // Generate a sample report
        String report = analytics.generateReport("Weekly");
        System.out.println(report);

        // Show the report in the dashboard
        analytics.showReportInDashboard(report);

        // Export the report as CSV
        analytics.exportReport(report, "csv");
    }
}

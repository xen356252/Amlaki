import java.util.Scanner;

public class Sell {
    private final AdManager adManager;
    private final Scanner scanner;

    public Sell(AdManager adManager, Scanner scanner) {
        this.adManager = adManager;
        this.scanner = scanner;
    }

    public void addProperty() {
        System.out.println("\nDo you want to add Land or Built Property?");
        System.out.println("1. Land (Zamin)");
        System.out.println("2. Built Property");
        int choice = safeIntInput("Enter choice (1/2): ");

        int id = safeIntInput("Enter property ID: ");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Category (eghamati/tejari): ");
        String category = scanner.nextLine();

        double price = safeDoubleInput("Price: ");

        System.out.print("Contact info: ");
        String contact = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        int area = safeIntInput("Area (m²): ");

        Property property;
        if (choice == 1) {
            property = new Land(title, category, price, contact, address, area);
        } else {
            property = new BuiltProperty(title, category, price, contact, address, area);
        }

        property.setId(id);
        adManager.addAd(property);
        System.out.println("✅ Property added successfully!");
    }

    private int safeIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number.");
            }
        }
    }

    private double safeDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number.");
            }
        }
    }
}

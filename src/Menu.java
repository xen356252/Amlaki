import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdManager adManager = new AdManager();
        Sell sell = new Sell(adManager, scanner);
        Buy buy = new Buy(adManager, scanner);

        int choice;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Sell / Add Property");
            System.out.println("2. Buy Property");
            System.out.println("3. Delete Property");
            System.out.println("4. Show All Properties");
            System.out.println("5. Undercut Suggestion");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {
                case 1 -> sell.addProperty();
                case 2 -> buy.searchAndBuy();
                case 3 -> {
                    System.out.print("Enter property ID to delete: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        adManager.removeAd(id);
                        System.out.println("✅ Deleted if existed.");
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Invalid ID input. Please enter a number.");
                    }
                }
                case 4 -> {
                    System.out.println("\nAll Properties:");
                    for (Property p : adManager.getAllProperties()) {
                        System.out.println(p);
                    }
                }
                case 5 -> {
                    System.out.print("Enter property ID for undercut: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        adManager.suggestUndercut(id);
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Invalid ID input. Please enter a number.");
                    }
                }
                case 6 -> System.out.println("👋 Goodbye!");
                default -> System.out.println("⚠️ Invalid choice.");
            }

        } while (choice != 6);
    }
}

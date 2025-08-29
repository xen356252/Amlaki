import java.util.List;
import java.util.Scanner;

public class Buy {
    private final AdManager adManager;
    private final Scanner scanner;

    public Buy(AdManager adManager, Scanner scanner) {
        this.adManager = adManager;
        this.scanner = scanner;
    }

    public void searchAndBuy() {
        System.out.println("\nChoose property type:");
        System.out.println("1. Eghamati");
        System.out.println("2. Tejari");
        System.out.println("3. All");

        int typeChoice = Integer.parseInt(scanner.nextLine().trim());

        String category;
        switch (typeChoice) {
            case 1 -> category = "eghamati";
            case 2 -> category = "tejari";
            case 3 -> category = "all";
            default -> {
                System.out.println("⚠️ Invalid choice.");
                return;
            }
        }

        List<Property> results = adManager.searchAdsByCategory(category);

        if (results.isEmpty()) {
            System.out.println("❌ No ads found.");
            return;
        }

        System.out.println("\nAvailable properties:");
        for (Property ad : results) {
            System.out.println(ad);
        }

        System.out.print("\nEnter ID to buy: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Property selected = adManager.getAdById(id);
            if (selected != null) {
                System.out.println("✅ You bought: " + selected.getTitle());
                adManager.removeAd(id);
            } else {
                System.out.println("⚠️ Invalid ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Wrong input.");
        }
    }
}

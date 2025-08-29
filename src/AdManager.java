import java.io.*;
import java.util.*;

public class AdManager {
    private List<Property> properties = new ArrayList<>();
    private static final String FILE_NAME = "properties.txt";
    private int nextId = 1;

    public AdManager() {
        loadFromFile();
    }

    public void addAd(Property property) {
        // Ensure no duplicate IDs
        if (getAdById(property.getId()) != null) {
            System.out.println("⚠️ A property with this ID already exists.");
            return;
        }
        properties.add(property);
        saveToFile();
    }


    public void removeAd(int id) {
        properties.removeIf(p -> p.getId() == id);
        saveToFile();
    }

    public Property getAdById(int id) {
        for (Property p : properties) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public List<Property> searchAdsByCategory(String category) {
        List<Property> results = new ArrayList<>();
        for (Property p : properties) {
            if ("all".equalsIgnoreCase(category) || p.getCategory().equalsIgnoreCase(category)) {
                results.add(p);
            }
        }
        return results;
    }

    public List<Property> getAllProperties() {
        return new ArrayList<>(properties);
    }
    
    private void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Property p : properties) {
                out.println(p.toFileString());
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Property p = Property.fromFileString(line);
                properties.add(p);
                nextId = Math.max(nextId, p.getId() + 1);
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading file: " + e.getMessage());
        }
    }

    public void suggestUndercut(int id) {
        Property target = getAdById(id);
        if (target == null) {
            System.out.println("⚠️ Property with ID " + id + " not found.");
            return;
        }

        System.out.println("📊 Current property: " + target);

        List<Property> similar = new ArrayList<>();
        for (Property p : properties) {
            if (p.getId() != id && Math.abs(p.getPrice() - target.getPrice()) <= target.getPrice() * 0.2) {
                similar.add(p);
            }
        }

        if (similar.isEmpty()) {
            System.out.println("ℹ️ No similar properties found.");
            return;
        }

        System.out.println("🔎 Similar properties:");
        for (Property s : similar) {
            System.out.println("   " + s);
        }

        double avgPrice = similar.stream().mapToDouble(Property::getPrice).average().orElse(target.getPrice());
        double suggested = avgPrice - (avgPrice * 0.05);

        System.out.println("💡 Suggested new price to undercut competition: " + suggested);
        target.setPrice(suggested);
        saveToFile();
    }
}

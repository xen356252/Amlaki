public class Land extends Property {
    public Land(String title, String category, double price, String contact, String address, int area) {
        super(title, category, price, contact, address, area);
    }

    @Override
    public String getType() {
        return "Land";
    }
}

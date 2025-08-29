public abstract class Property {
    protected int id;
    protected String title;
    protected String category;
    protected double price;
    protected String contact;
    protected String address;
    protected int area;

    public Property(String title, String category, double price, String contact, String address, int area) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.contact = contact;
        this.address = address;
        this.area = area;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getContact() { return contact; }
    public String getAddress() { return address; }
    public int getArea() { return area; }

    public abstract String getType();

    public String toFileString() {
        return id + "|" + title + "|" + category + "|" + price + "|" + contact + "|" + address + "|" + area + "|" + getType();
    }

    public static Property fromFileString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length < 8) {
            throw new IllegalArgumentException("Invalid property data: " + data);
        }

        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        String category = parts[2];
        double price = Double.parseDouble(parts[3]);
        String contact = parts[4];
        String address = parts[5];
        int area = Integer.parseInt(parts[6]);
        String type = parts[7];

        Property property;
        if ("Land".equalsIgnoreCase(type)) {
            property = new Land(title, category, price, contact, address, area);
        } else {
            property = new BuiltProperty(title, category, price, contact, address, area);
        }

        property.setId(id);
        return property;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " (" + getType() + ") - " + price + " toman, " + area + " m², Address: " + address + ", Contact: " + contact;
    }
}

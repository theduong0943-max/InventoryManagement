package model;

public abstract class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public abstract String getCategory();

    @Override
    public String toString() {
        return String.format("ID: %-5s | Tên: %-20s | Giá: %,10.2f VNĐ | SL: %-3d | Loại: %s", 
            id, name, price, quantity, getCategory());
    }
}
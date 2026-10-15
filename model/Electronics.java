package model;

public class Electronics extends Product {
    private int warrantyMonths;

    public Electronics(String id, String name, double price, int quantity, int warrantyMonths) {
        super(id, name, price, quantity);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getCategory() {
        return "Đồ điện tử (BH: " + warrantyMonths + " tháng)";
    }
}
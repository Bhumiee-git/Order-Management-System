public class Order {
    private String productId;
    private int quantity;
    private double pricePerUnit;

    public Order(String productId, int quantity, double pricePerUnit) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    // Getters
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return quantity * pricePerUnit; }
}
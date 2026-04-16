import java.util.HashMap;
import java.util.Map;

public class OrderProcessor {
    // A simple Map to simulate a Database (Key: ProductID, Value: Stock Count)
    private Map<String, Integer> inventory = new HashMap<>();

    public OrderProcessor() {
        // Pre-loading some stock for testing
        inventory.put("LAPTOP01", 10);
        inventory.put("PHONE02", 5);
    }

    /**
     * The main logic for processing an order.
     * @param order The order details
     * @param paymentAuthorized Simulated payment status
     * @return String message indicating result
     */
    public String processOrder(Order order, boolean paymentAuthorized) {
        String pid = order.getProductId();
        
        // Step 1: Inventory Validation
        if (!inventory.containsKey(pid)) {
            return "FAILED: Product not found.";
        }

        int currentStock = inventory.get(pid);
        if (currentStock < order.getQuantity()) {
            return "FAILED: Insufficient stock. Available: " + currentStock;
        }

        // Step 2: Payment Handling
        if (!paymentAuthorized) {
            return "FAILED: Payment declined.";
        }

        // Step 3: Update Inventory (Deduct stock)
        inventory.put(pid, currentStock - order.getQuantity());

        return "SUCCESS: Order processed. Total charged: $" + order.getTotalPrice();
    }

    // Method to check stock (needed for JUnit testing later)
    public int getStockCount(String productId) {
        return inventory.getOrDefault(productId, 0);
    }
}
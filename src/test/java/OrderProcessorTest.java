import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorTest {
    private OrderProcessor processor;

    @BeforeEach
    void init() {
        // This ensures every test starts with a fresh 10-item inventory
        processor = new OrderProcessor();
    }

    @Test
    @DisplayName("Test 1: Successful Order Processing")
    void testSuccess() {
        Order order = new Order("LAPTOP01", 2, 1000.0);
        String response = processor.processOrder(order, true);
        
        // Check if message is correct
        assertEquals("SUCCESS: Order processed. Total charged: $2000.0", response);
        // Check if stock actually decreased (10 - 2 = 8)
        assertEquals(8, processor.getStockCount("LAPTOP01"));
    }

    @Test
    @DisplayName("Test 2: Failure due to Out of Stock")
    void testStockFailure() {
        Order order = new Order("LAPTOP01", 50, 1000.0); // 50 is more than stock
        String response = processor.processOrder(order, true);
        
        assertTrue(response.contains("FAILED"), "Should indicate failure");
        assertTrue(response.contains("Insufficient stock"), "Should give stock error message");
        // Ensure stock did NOT change
        assertEquals(10, processor.getStockCount("LAPTOP01"));
    }

    @Test
    @DisplayName("Test 3: Failure due to Payment Declined")
    void testPaymentFailure() {
        Order order = new Order("LAPTOP01", 1, 1000.0);
        // Pass 'false' for payment authorization
        String response = processor.processOrder(order, false);
        
        assertEquals("FAILED: Payment declined.", response);
        // Ensure stock did NOT change
        assertEquals(10, processor.getStockCount("LAPTOP01"));
    }
}
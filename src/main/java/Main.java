public class Main {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        
        // Create an order for 2 Laptops
        Order myOrder = new Order("LAPTOP01", 2, 1200.00);
        
        // Process it with a "True" payment
        String result = processor.processOrder(myOrder, true);
        
        System.out.println(result); 
        // Should print: SUCCESS: Order processed. Total charged: $2400.0
    }
}
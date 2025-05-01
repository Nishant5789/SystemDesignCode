package org.motadata.DesignPattern.SagaPattern.OrchestratorwithBlockingWay;

public class OrchestrationDemo {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        orderService.createOrder(1, 2, 100); // Success
        orderService.createOrder(2, 3, 101); // Inventory fail
        orderService.createOrder(3, 4, 102); // Payment fail
    }
}

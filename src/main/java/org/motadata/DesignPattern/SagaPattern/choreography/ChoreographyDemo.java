package org.motadata.DesignPattern.SagaPattern.choreography;


public class ChoreographyDemo
{
    public static void main(String[] args)
    {
        new OrderService();
        new InventoryService();
        new PaymentService();

        OrderService orderService = new OrderService();
        orderService.createOrder(1, 2, 100); // Successful
        orderService.createOrder(2, 3, 101); // Inventory fail
        orderService.createOrder(3, 4, 102); // Payment fail
    }
}


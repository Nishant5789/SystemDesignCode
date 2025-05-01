package org.motadata.DesignPattern.SagaPattern.choreography;

class OrderService
{
    public void createOrder(int orderId, int productId, int userId)
    {
        System.out.println("Order created: " + orderId);
        EventBus.publish(new OrderCreatedEvent(orderId, productId, userId));
    }

    public OrderService()
    {
        EventBus.subscribe(PaymentProcessedEvent.class, event ->
        {
            if (event.success)
            {
                System.out.println("Order " + event.orderId + " confirmed.");
            }
            else
            {
                System.out.println("Payment failed for order " + event.orderId + ". Order cancelled.");
            }
        });
    }
}


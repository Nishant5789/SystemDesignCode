package org.motadata.DesignPattern.SagaPattern.choreography;

class PaymentService
{
    public PaymentService()
    {
        EventBus.subscribe(InventoryReservedEvent.class, event ->
        {
            if (!event.success)
            {
                System.out.println("Inventory reservation failed for order " + event.orderId + ". Aborting.");
                return;
            }

            boolean paid = processPayment(event.orderId);
            System.out.println("Payment status for order " + event.orderId + ": " + (paid ? "Success":"Failure"));
            EventBus.publish(new PaymentProcessedEvent(event.orderId, paid));
        });
    }

    private boolean processPayment(int orderId)
    {
        return orderId % 3!=0; // simulate random failure
    }
}


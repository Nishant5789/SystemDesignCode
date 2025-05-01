package org.motadata.DesignPattern.SagaPattern.choreography;

class InventoryService
{
    public InventoryService()
    {
        EventBus.subscribe(OrderCreatedEvent.class, event ->
        {
            boolean available = checkInventory(event.productId);
            System.out.println("Inventory check for order " + event.orderId + ": " + (available ? "Reserved":"Out of stock"));
            EventBus.publish(new InventoryReservedEvent(event.orderId, available));
        });
    }

    private boolean checkInventory(int productId)
    {
        return productId % 2==0; // simulate availability
    }
}


package org.motadata.DesignPattern.SagaPattern.choreography;

public class Events
{

}

// Event classes
class OrderCreatedEvent {
    public int orderId;
    public int productId;
    public int userId;

    public OrderCreatedEvent(int orderId, int productId, int userId) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
    }
}

class InventoryReservedEvent {
    public int orderId;
    public boolean success;

    public InventoryReservedEvent(int orderId, boolean success) {
        this.orderId = orderId;
        this.success = success;
    }
}

class PaymentProcessedEvent {
    public int orderId;
    public boolean success;

    public PaymentProcessedEvent(int orderId, boolean success) {
        this.orderId = orderId;
        this.success = success;
    }
}


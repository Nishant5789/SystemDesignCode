package org.motadata.DesignPattern.SagaPattern.OrchestratorwithBlockingWay;

class InventoryService {
    public boolean reserveInventory(int productId) {
        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            return false;
        }
        boolean available = productId % 2 == 0;
        System.out.println("Inventory: Product " + productId + " " + (available ? "reserved" : "unavailable"));
        return available;
    }
}

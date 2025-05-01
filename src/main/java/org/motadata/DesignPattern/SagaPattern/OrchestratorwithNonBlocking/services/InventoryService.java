package org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking.services;

public class InventoryService {
    public boolean reserve(String productId) {
        System.out.println("📦 Reserving inventory for: " + productId);
        sleep(500);  // Simulate processing time
        return true; // Simulate success
    }

    public void release(String productId) {
        System.out.println("↩️ Releasing inventory for: " + productId);
    }

    private void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}


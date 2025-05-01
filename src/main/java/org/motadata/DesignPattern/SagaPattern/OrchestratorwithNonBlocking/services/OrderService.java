package org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking.services;

public class OrderService {
    public boolean confirm(String orderId) {
        System.out.println("📝 Confirming order: " + orderId);
        sleep(500);
        return true; // Simulate success
    }

    private void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}


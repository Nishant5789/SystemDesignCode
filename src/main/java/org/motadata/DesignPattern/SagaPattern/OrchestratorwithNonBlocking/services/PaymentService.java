package org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking.services;

public class PaymentService {
    public boolean process(String orderId) {
        System.out.println("💰 Processing payment for: " + orderId);
        sleep(500);
        return true; // Simulate success
    }

    public void refund(String orderId) {
        System.out.println("💸 Refunding payment for: " + orderId);
    }

    private void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}


package org.motadata.DesignPattern.SagaPattern.OrchestratorwithBlockingWay;

class PaymentService {
    public boolean processPayment(int orderId) {
        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            return false;
        }
        boolean paid = orderId % 3 != 0;
        System.out.println("Payment: Order " + orderId + " " + (paid ? "successful" : "failed"));
        return paid;
    }
}


package org.motadata.DesignPattern.SagaPattern.OrchestratorwithBlockingWay;

class OrderService {
    OrchestratorService orchestrator = new OrchestratorService();

    public void createOrder(int orderId, int productId, int userId) {
        System.out.println("OrderService: Created order " + orderId);
        orchestrator.processOrder(orderId, productId, userId);
    }
}

package org.motadata.DesignPattern.SagaPattern.OrchestratorwithBlockingWay;

import java.util.concurrent.*;

class OrchestratorService
{
    InventoryService inventoryService = new InventoryService();
    PaymentService paymentService = new PaymentService();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void processOrder(int orderId, int productId, int userId)
    {
        System.out.println("Orchestrator: Starting transaction for Order " + orderId);

        Future<Boolean> inventoryFuture = scheduler.submit(() -> inventoryService.reserveInventory(productId));
        boolean inventorySuccess = waitWithTimeout(inventoryFuture, 3, orderId, "Inventory");

        if (!inventorySuccess)
        {
            System.out.println("Orchestrator: Inventory step failed or timed out. Order cancelled.");
            return;
        }

        Future<Boolean> paymentFuture = scheduler.submit(() -> paymentService.processPayment(orderId));
        boolean paymentSuccess = waitWithTimeout(paymentFuture, 3, orderId, "Payment");

        if (!paymentSuccess)
        {
            System.out.println("Orchestrator: Payment step failed or timed out. Order cancelled.");
            return;
        }

        System.out.println("Orchestrator: Order " + orderId + " completed successfully!");
    }

    private boolean waitWithTimeout(Future<Boolean> future, int timeoutSeconds, int orderId, String stage)
    {
        try
        {
            return future.get(timeoutSeconds, TimeUnit.SECONDS);
        }
        catch (TimeoutException e)
        {
            System.out.println(stage + ": Timeout for Order " + orderId);
            return false;
        }
        catch (Exception e)
        {
            System.out.println(stage + ": Exception for Order " + orderId);
            return false;
        }
    }
}


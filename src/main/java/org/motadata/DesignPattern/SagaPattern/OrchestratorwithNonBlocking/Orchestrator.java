package org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking;

import org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking.services.InventoryService;
import org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking.services.OrderService;
import org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking.services.PaymentService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Orchestrator
{
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();
    private final OrderService orderService = new OrderService();

    public void placeOrder(String productId, String orderId)
    {
        System.out.println("Starting Saga Orchestration...");

        CompletableFuture.supplyAsync(() -> inventoryService.reserve(productId))
        .orTimeout(3, TimeUnit.SECONDS)
        .thenCompose(inventorySuccess ->
        {
            if (!inventorySuccess)
            {
                throw new RuntimeException("Inventory reservation failed");
            }
            return CompletableFuture.supplyAsync(() -> paymentService.process(orderId))
            .orTimeout(3, TimeUnit.SECONDS);
        })
        .thenCompose(paymentSuccess ->
        {
            if (!paymentSuccess)
            {
                throw new RuntimeException("Payment failed");
            }
            return CompletableFuture.supplyAsync(() -> orderService.confirm(orderId));
        })
        .thenAccept(orderConfirmed ->
        {
            if (orderConfirmed)
            {
                System.out.println("✅ Order successfully completed.");
            }
            else
            {
                System.out.println("❌ Order confirmation failed.");
                paymentService.refund(orderId);
                inventoryService.release(productId);
            }
        })
        .exceptionally(ex ->
        {
            System.out.println("❌ Saga failed: " + ex.getMessage());
            System.out.println("🔁 Initiating compensation...");
            paymentService.refund(orderId);
            inventoryService.release(productId);
            return null;
        });
    }
}


package org.motadata.DesignPattern.SagaPattern.OrchestratorwithNonBlocking;

public class Main {
    public static void main(String[] args) {
        Orchestrator orchestrator = new Orchestrator();
        orchestrator.placeOrder("product123", "order456");
    }
}


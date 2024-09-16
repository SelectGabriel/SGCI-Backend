package br.tads.ufpr.sgci_backend.esb.orchestrator;

public interface Orchestrator {
    void orchestrate(Record input) throws Exception;
}

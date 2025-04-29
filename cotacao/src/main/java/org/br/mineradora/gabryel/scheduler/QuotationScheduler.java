package org.br.mineradora.gabryel.scheduler;


import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.gabryel.service.QuotationService;

@ApplicationScoped
public class QuotationScheduler {

    @Inject
    QuotationService quotationService;

    /**
     * This method is scheduled to run every 35 seconds.
     * It calls the {@link QuotationService#getCurrencyPrice()} method to get the current price of BRL-USD and
     * send an event to the Kafka topic if the price has changed.
     */
    @Transactional
    @Scheduled(every = "35s", identity = "task-job")
    void schedule() {
        quotationService.getCurrencyPrice();
    }
}

package org.br.mineradora.gabryel.message;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.dto.QuotationDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("quotation-channel")
    Emitter<QuotationDTO> quotationRequestEmitter;


    public void sendNewKafkaEvent(QuotationDTO quotationDTO) {

        LOG.info("--- Sending quotation {} ---", quotationDTO);
        quotationRequestEmitter.send(quotationDTO).toCompletableFuture().join();
    }

}

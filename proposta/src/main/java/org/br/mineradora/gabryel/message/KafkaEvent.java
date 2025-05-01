package org.br.mineradora.gabryel.message;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.dto.ProposalDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvent {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvent.class);

    @Channel("proposal")
    Emitter<ProposalDTO> proposalEmitter;

    public void sendProposal(ProposalDTO proposal) {
        LOG.info("Sending proposal: {}", proposal);
        proposalEmitter.send(proposal).toCompletableFuture().join();
    }

}

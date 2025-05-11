package org.br.mineradora.gabryel.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.gabryel.client.ProposalRestClient;
import org.br.mineradora.gabryel.dto.ProposalDetailsDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {

    @Inject
    @RestClient
    ProposalRestClient proposalRestClient;

    @Override
    public ProposalDetailsDTO getProposalDetails(long proposalId) {
        return proposalRestClient.getProposalDetailsById(proposalId);
    }

    @Override
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        return proposalRestClient.createProposal(proposalDetailsDTO);
    }

    @Override
    public Response removeProposal(long proposalId) {
        return proposalRestClient.removeProposal(proposalId);
    }

}

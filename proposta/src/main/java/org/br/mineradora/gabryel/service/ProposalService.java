package org.br.mineradora.gabryel.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.dto.ProposalDetailsDTO;

@ApplicationScoped
public interface ProposalService {

    /**
     * Finds a proposal by the given id and returns its details.
     *
     * @param id the id of the proposal
     * @return a ProposalDetailsDTO object containing the proposal details if found, null otherwise
     */
    ProposalDetailsDTO findProposalDetails(Long id);

    /**
     * Creates a new proposal.
     *
     * @param proposalDetailsDTO the proposal details
     */
    void createProposal(ProposalDetailsDTO proposalDetailsDTO);

    /**
     * Deletes a proposal by the given id.
     *
     * @param id the id of the proposal to be deleted
     */
    void deleteProposal(Long id);

}

package org.br.mineradora.gabryel.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.dto.OpportunityDto;
import org.br.mineradora.gabryel.dto.ProposalDTO;
import org.br.mineradora.gabryel.dto.QuotationDTO;

import java.util.List;

@ApplicationScoped
public interface OpportunityService {

    void buildOpportunity(ProposalDTO proposal);

    void saveQuotation(QuotationDTO quotation);

    List<OpportunityDto> generateOpportunityData();

}

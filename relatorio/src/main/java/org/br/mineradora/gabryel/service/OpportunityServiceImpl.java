package org.br.mineradora.gabryel.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.gabryel.dto.OpportunityDto;
import org.br.mineradora.gabryel.dto.ProposalDTO;
import org.br.mineradora.gabryel.dto.QuotationDTO;
import org.br.mineradora.gabryel.entity.OpportunityEntity;
import org.br.mineradora.gabryel.entity.QuotationEntity;
import org.br.mineradora.gabryel.message.KafkaEvent;
import org.br.mineradora.gabryel.repository.OpportunityRepository;
import org.br.mineradora.gabryel.repository.QuotationRepository;
import org.br.mineradora.gabryel.utils.CSVHelper;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    @Inject
    KafkaEvent kafkaEvent;

    @Inject
    OpportunityRepository opportunityRepository;

    @Inject
    QuotationRepository quotationRepository;


    @Override
    public void buildOpportunity(ProposalDTO proposal) {
        QuotationEntity lastQuotation = quotationRepository.findLastQuotation();

        OpportunityEntity opportunityEntity = new OpportunityEntity();
        opportunityEntity.setDate(LocalDateTime.now());
        opportunityEntity.setProposalId(proposal.getId());
        opportunityEntity.setCustomer(proposal.getCustomer());
        opportunityEntity.setPair(lastQuotation.getPair());
        opportunityEntity.setPriceTonne(proposal.getPriceTonne());
        opportunityEntity.setLastQuotation(lastQuotation.getCurrencyPrice());

        opportunityRepository.persist(opportunityEntity);
    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {

        QuotationEntity quotationEntity = new QuotationEntity();
        quotationEntity.setDate(quotation.getDate());
        quotationEntity.setCurrencyPrice(quotation.getCurrencyPrice());
        quotationEntity.setPair(quotation.getPair());

        quotationRepository.persist(quotationEntity);
    }

    @Override
    public List<OpportunityDto> generateOpportunityData() {
        return List.of();
    }

    @Override
    public ByteArrayInputStream generateCsvOpportunityReport() {
        List<OpportunityDto> opportunities = opportunityRepository.listAll().stream()
                .map(item -> OpportunityDto.builder()
                        .proposalId(item.getProposalId())
                        .customer(item.getCustomer())
                        .priceTonne(item.getPriceTonne())
                        .lastQuotation(item.getLastQuotation())
                        .pair(item.getPair())
                        .build())
                .toList();

        return CSVHelper.opportunityReportToCSV(opportunities);
    }

}

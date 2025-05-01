package org.br.mineradora.gabryel.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.gabryel.dto.ProposalDTO;
import org.br.mineradora.gabryel.dto.ProposalDetailsDTO;
import org.br.mineradora.gabryel.entity.ProposalEntity;
import org.br.mineradora.gabryel.message.KafkaEvent;
import org.br.mineradora.gabryel.repository.ProposalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class ProposalServiceImpl implements ProposalService {

    private final Logger LOG = LoggerFactory.getLogger(ProposalServiceImpl.class);

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaEvent;


    @Override
    public ProposalDetailsDTO findProposalDetails(Long id) {
        ProposalEntity proposalEntity = proposalRepository.findById(id);

        return ProposalDetailsDTO.builder()
                .id(proposalEntity.getId())
                .customer(proposalEntity.getCustomer())
                .country(proposalEntity.getCountry())
                .priceTonne(proposalEntity.getPriceTonne())
                .proposalValidityInDays(proposalEntity.getProposalValidityInDays())
                .tonnes(proposalEntity.getTonnes())
                .build();
    }

    @Override
    @Transactional
    public void createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        ProposalDTO proposalDTO = buildAndSaveProposal(proposalDetailsDTO);
        kafkaEvent.sendProposal(proposalDTO);
    }

    @Override
    @Transactional
    public void deleteProposal(Long id) {
        proposalRepository.deleteById(id);
    }

    @Transactional
    private ProposalDTO buildAndSaveProposal(ProposalDetailsDTO proposalDetailsDTO) {
        try {

            ProposalEntity entity = new ProposalEntity();
            entity.setCustomer(proposalDetailsDTO.getCustomer());
            entity.setCountry(proposalDetailsDTO.getCountry());
            entity.setPriceTonne(proposalDetailsDTO.getPriceTonne());
            entity.setProposalValidityInDays(proposalDetailsDTO.getProposalValidityInDays());
            entity.setTonnes(proposalDetailsDTO.getTonnes());
            entity.setCreated(LocalDate.now());

            proposalRepository.persist(entity);
            return ProposalDTO.builder()
                    .id(entity.getId())
                    .customer(entity.getCustomer())
                    .priceTonne(entity.getPriceTonne())
                    .build();

        } catch (Exception e) {
            LOG.error("Error creating proposal", e);
            throw new RuntimeException(e);
        }
    }

}

package org.br.mineradora.gabryel.service;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.gabryel.client.CurrencyPriceClient;
import org.br.mineradora.gabryel.dto.CurrencyPriceDTO;
import org.br.mineradora.gabryel.dto.QuotationDTO;
import org.br.mineradora.gabryel.entity.QuotationEntity;
import org.br.mineradora.gabryel.message.KafkaEvents;
import org.br.mineradora.gabryel.repository.QuotationRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class QuotationService {

    private final Logger LOG = LoggerFactory.getLogger(QuotationService.class);

    @Inject
    @RestClient
    CurrencyPriceClient currencyPriceClient;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;

    @Inject
    private Gson gson;

    /**
     * This method gets the current price of BRL-USD and sends an event to the Kafka topic if the price has changed.
     */
    @Transactional
    public void getCurrencyPrice() {
        CurrencyPriceDTO currencyPriceDTO = gson.fromJson(currencyPriceClient.getPriceByPair("USD-BRL"), CurrencyPriceDTO.class);

        if (updateCurrencyInfoPrice(currencyPriceDTO)) {
            kafkaEvents.sendNewKafkaEvent(QuotationDTO
                    .builder()
                    .currencyPrice(new BigDecimal(currencyPriceDTO.getUSDBRL().getBid()))
                    .pair("USD-BRL")
                    .date(LocalDate.now())
                    .build());
        }
    }

    /**
     * This method checks if the current price of BRL-USD has changed in comparison to the last one stored in the database.
     * If it has changed, it updates the price in the database and sends an event to the Kafka topic.
     * <p>
     * If the database is empty, it saves the current price as the first record.
     * <p>
     * The return value of this method indicates if the price has changed or not.
     * @param currencyPriceDTO the current price of BRL-USD.
     * @return true if the price has changed, false otherwise.
     */
    private boolean updateCurrencyInfoPrice(CurrencyPriceDTO currencyPriceDTO) {
        BigDecimal currentPrice = new BigDecimal(currencyPriceDTO.getUSDBRL().getBid());
        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();

        if (quotationEntities.isEmpty()) {
            saveQuotation(currencyPriceDTO);
            return true;
        }

        QuotationEntity lastQuotation = quotationEntities.getLast();
        if (currentPrice.compareTo(lastQuotation.getCurrencyPrice()) > 0) {
            saveQuotation(currencyPriceDTO);
            return true;
        }

        return false;
    }

    /**
     * This method saves a quotation entity to the database.
     * It takes a CurrencyPriceDTO, creates a QuotationEntity from it and persists it.
     * The pair of the quotation entity is always "USD-BRL".
     * @param currencyPriceDTO the currency price DTO to be saved.
     */
    private void saveQuotation(CurrencyPriceDTO currencyPriceDTO) {

        QuotationEntity quotationEntity = new QuotationEntity();
        quotationEntity.setCurrencyPrice(new BigDecimal(currencyPriceDTO.getUSDBRL().getBid()));
        quotationEntity.setPctChange(currencyPriceDTO.getUSDBRL().getPctChange());
        quotationEntity.setDate(LocalDateTime.now());
        quotationEntity.setPair("USD-BRL");

        quotationRepository.persist(quotationEntity);
    }

}

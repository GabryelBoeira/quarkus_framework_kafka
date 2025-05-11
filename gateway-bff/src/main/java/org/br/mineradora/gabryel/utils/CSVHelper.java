package org.br.mineradora.gabryel.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.br.mineradora.gabryel.dto.OpportunityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class CSVHelper {

    private static final Logger LOG = LoggerFactory.getLogger(CSVHelper.class);

    public static ByteArrayInputStream opportunityReportToCSV(List<OpportunityDTO> Opportunities) {
        final CSVFormat format = CSVFormat.DEFAULT.builder().setHeader("ID Proposta", "Cliente", "Preco da Tonelada", "Melhor cotacao", "Moeda").get();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

            Opportunities.forEach(opportunity -> {
                try {
                    csvPrinter.printRecord(List.of(String.valueOf(opportunity.getProposalId()), opportunity.getCustomer(), String.valueOf(opportunity.getPriceTonne()), String.valueOf(opportunity.getLastQuotation()), opportunity.getPair()));
                } catch (IOException e) {
                    LOG.error("fail to write data to csv file: {}", e.getMessage());
                }
            });

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            LOG.error("fail to import data to csv file: {}", e.getMessage());
            throw new RuntimeException("fail to import data to csv file: " + e.getMessage());
        }
    }

}

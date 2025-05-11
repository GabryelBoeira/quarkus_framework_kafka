package org.br.mineradora.gabryel.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.dto.OpportunityDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface ReportService {

    ByteArrayInputStream generateReportCSV();

    List<OpportunityDTO> getOpportunitiesData();

}

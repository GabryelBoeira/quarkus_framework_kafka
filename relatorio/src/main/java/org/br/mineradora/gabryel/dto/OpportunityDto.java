package org.br.mineradora.gabryel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityDto {

    private Long proposalId;

    private String customer;

    private BigDecimal priceTonne;

    private BigDecimal lastQuotation;

    private String pair;

}

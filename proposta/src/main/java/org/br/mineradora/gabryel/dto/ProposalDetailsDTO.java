package org.br.mineradora.gabryel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class ProposalDetailsDTO {

    private Long proposalId;

    private String customer;

    private BigDecimal priceTonne;

    private Integer tonnes;

    private Integer country;

    private Integer proposalValidityInDays;

}

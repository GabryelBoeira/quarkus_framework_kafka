package org.br.mineradora.gabryel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class ProposalDetailsDTO {

    private Long id;

    private String customer;

    private BigDecimal priceTonne;

    private Integer tonnes;

    private Integer country;

    private Integer proposalValidityInDays;

}

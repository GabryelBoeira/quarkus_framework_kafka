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
public class ProposalDTO {

    private Long id;

    private String customer;

    private BigDecimal priceTonne;

}

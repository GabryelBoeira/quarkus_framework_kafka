package org.br.mineradora.gabryel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class QuotationDTO {

    private LocalDate date;

    private BigDecimal currencyPrice;

    private String pair;

}

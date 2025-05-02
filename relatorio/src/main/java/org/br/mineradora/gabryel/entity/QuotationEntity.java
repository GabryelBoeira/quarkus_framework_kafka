package org.br.mineradora.gabryel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "quotation")
@Data
@NoArgsConstructor
public class QuotationEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    private String pair;

    @Column(name = "currency_price")
    private BigDecimal currencyPrice;

}

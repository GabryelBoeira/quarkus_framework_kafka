package org.br.mineradora.gabryel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quotation")
public class QuotationEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    @Column(name = "currency_price")
    private BigDecimal currencyPrice;

    @Column(name = "pct_change")
    private String pctChange;

    private String pair;

}

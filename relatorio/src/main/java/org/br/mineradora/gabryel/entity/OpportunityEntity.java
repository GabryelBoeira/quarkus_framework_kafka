package org.br.mineradora.gabryel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "opportunity")
@Data
@NoArgsConstructor
public class OpportunityEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    private Long proposalId;

    private String customer;

    private String pair;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;

    @Column(name = "last_quotation")
    private BigDecimal lastQuotation;

}

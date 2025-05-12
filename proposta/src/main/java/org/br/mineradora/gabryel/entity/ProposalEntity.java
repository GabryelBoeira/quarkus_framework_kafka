package org.br.mineradora.gabryel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "proposal")
@Data
@NoArgsConstructor
public class ProposalEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String customer;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;

    private Integer tonnes;

    private Integer country;

    private Integer proposalValidityInDays;

    private LocalDateTime created;

}

package org.br.mineradora.gabryel.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.entity.QuotationEntity;

@ApplicationScoped
public class QuotationRepository implements PanacheRepository<QuotationEntity> {

    public QuotationEntity findByPair(String pair) {
        return find("pair", pair).firstResult();
    }

}

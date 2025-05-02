package org.br.mineradora.gabryel.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.entity.OpportunityEntity;

@ApplicationScoped
public class OpportunityRepository implements PanacheRepository<OpportunityEntity> {


}

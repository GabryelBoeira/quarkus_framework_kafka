package org.br.mineradora.gabryel.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.gabryel.entity.ProposalEntity;

import java.util.Optional;

@ApplicationScoped
public class ProposalRepository implements PanacheRepository<ProposalEntity> {

    /**
     * Find a proposal by the customer.
     *
     * @param customer the customer
     * @return an optional containing the proposal if found, empty otherwise
     */
    public Optional<ProposalEntity> findByCustomer(String customer) {
        return find("customer", customer).firstResultOptional();
    }

}

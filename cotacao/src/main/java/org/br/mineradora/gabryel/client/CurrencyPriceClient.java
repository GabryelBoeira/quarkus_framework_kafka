package org.br.mineradora.gabryel.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/currency-price")
@RegisterRestClient
@ApplicationScoped
public interface CurrencyPriceClient {
}

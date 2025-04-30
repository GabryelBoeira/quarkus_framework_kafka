package org.br.mineradora.gabryel.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://economia.awesomeapi.com.br")
public interface CurrencyPriceClient {

    @GET
    @Path("/json/last/{pair}")
    String getPriceByPair(@PathParam("pair") String pair);

}

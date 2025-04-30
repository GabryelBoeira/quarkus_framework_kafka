package org.br.mineradora.gabryel.configuration;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class GsonProducer {

    @Produces
    @ApplicationScoped
    public Gson gson() {
        return new Gson();
    }
}
package org.br.mineradora.gabryel.configuration;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
public class LoggingFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        if (responseContext.getStatusInfo().getFamily() == Response.Status.Family.CLIENT_ERROR ||
                responseContext.getStatusInfo().getFamily() == Response.Status.Family.SERVER_ERROR) {

            String entity = "";
            if (responseContext.hasEntity()) {
                entity = new String(responseContext.getEntityStream().readAllBytes(), StandardCharsets.UTF_8);
            }
            System.out.println("Corpo da Resposta: " + entity);
        }
    }
}
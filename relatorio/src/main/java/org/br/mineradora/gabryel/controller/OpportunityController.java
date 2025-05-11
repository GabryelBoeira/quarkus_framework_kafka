package org.br.mineradora.gabryel.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.gabryel.service.OpportunityService;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/opportunity")
@Authenticated
public class OpportunityController {

    @Inject
    OpportunityService opportunityService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/report")
    @RolesAllowed({"user", "manager"})
    public Response generateReport() {
        try {
            return Response.ok(opportunityService.generateOpportunityData()).build();
        } catch (ServerErrorException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}

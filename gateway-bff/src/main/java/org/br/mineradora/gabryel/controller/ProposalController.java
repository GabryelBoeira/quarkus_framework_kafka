package org.br.mineradora.gabryel.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.gabryel.dto.ProposalDetailsDTO;
import org.br.mineradora.gabryel.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/api/trade")
public class ProposalController {

    private static final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProposalDetails(@PathParam("id") long id) {
        try {
            return Response.ok().entity(proposalService.getProposalDetailsById(id)).build();
        } catch (ServerErrorException ex) {
            LOG.error("Error finding proposal", ex);
            return Response.serverError().build();
        }
    }

    @POST
    @RolesAllowed("proposal-customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        try (Response response = proposalService.createProposal(proposalDetailsDTO)) {
            int responseStatus = response.getStatus();

            if (isSuccessfulStatus(responseStatus)) {
                return Response.ok().build();
            }

            return Response.status(responseStatus).build();
        } catch (ServerErrorException ex) {

            LOG.error("Error creating proposal", ex);
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id) {
        try (Response response = proposalService.removeProposal(id)) {
            int responseStatus = response.getStatus();

            if (isSuccessfulStatus(responseStatus)) {
                return Response.ok().build();
            }

            return Response.status(responseStatus).build();
        } catch (ServerErrorException ex) {

            LOG.error("Error creating proposal", ex);
            return Response.serverError().build();
        }
    }

    private boolean isSuccessfulStatus(int status) {
        return status >= 200 && status <= 204;
    }
}

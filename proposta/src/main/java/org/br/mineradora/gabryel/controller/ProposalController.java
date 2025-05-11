package org.br.mineradora.gabryel.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.gabryel.dto.ProposalDetailsDTO;
import org.br.mineradora.gabryel.service.ProposalService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/proposal")
@Authenticated
public class ProposalController {

    private static final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    @Inject
    ProposalService proposalService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public Response findProposalDetails(@PathParam("id") long id) {
        try {
            return Response.ok().entity(proposalService.findProposalDetails(id)).build();
        } catch (Exception e) {
            LOG.error("Error finding proposal", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        try {
            proposalService.createProposal(proposalDetailsDTO);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Error creating proposal", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("manager")
    public Response deleteProposal(@PathParam("id") long id) {
        try {
            proposalService.deleteProposal(id);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Error deleting proposal", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}

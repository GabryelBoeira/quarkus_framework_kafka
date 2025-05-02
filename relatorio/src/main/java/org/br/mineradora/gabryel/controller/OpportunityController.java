package org.br.mineradora.gabryel.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.gabryel.service.OpportunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;

@Path("/api/opportunity")
public class OpportunityController {

    private static final Logger LOG = LoggerFactory.getLogger(OpportunityController.class);

    @Inject
    OpportunityService opportunityService;

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        try {
            return Response
                    .ok(opportunityService.generateCsvOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment; filename = " + LocalDate.now() + "_opportunities.csv")
                    .build();
        } catch (ServerErrorException e) {
            LOG.error("fail to generate report csv: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}

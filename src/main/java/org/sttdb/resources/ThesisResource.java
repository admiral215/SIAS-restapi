package org.sttdb.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sttdb.dto.ResponseDto;
import org.sttdb.dto.thesis.ThesisStatusRequestDto;
import org.sttdb.dto.thesis.ThesisChooseSupervisorRequestDto;
import org.sttdb.dto.thesis.ThesisUpsertRequestDto;
import org.sttdb.services.ThesisService;

@Path("api/theses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThesisResource {
    @Inject
    ThesisService service;

    @GET
    public Response getTheses(@QueryParam("pageNumber") Integer pageNumber,
                              @QueryParam("pageSize") Integer pageSize,
                              @QueryParam("status") String status) {
        return Response.ok(service.getTheses(pageNumber, pageSize, status)).build();
    }

    @POST
    public Response insertThesis(ThesisUpsertRequestDto dto) {
        service.insertThesis(dto);
        return Response.ok()
                .status(Response.Status.CREATED)
                .entity(ResponseDto.builder()
                        .message("Thesis inserted successfully")
                        .build())
                .build();
    }

    @PUT
    public Response updateThesis(ThesisUpsertRequestDto dto) {
        service.updateThesis(dto);
        return Response.ok()
                .entity(ResponseDto.builder()
                        .message("Thesis updated successfully")
                        .build())
                .build();
    }

    @PUT
    @Path("/supervisor")
    public Response chooseSupervisor(ThesisChooseSupervisorRequestDto dto){
        service.chooseSupervisor(dto);
        return Response.ok()
                .entity(ResponseDto.builder()
                        .message("Choose supervisor successfully")
                        .build())
                .build();
    }

    @PUT
    @Path("/status")
    public Response updateStatusThesis(ThesisStatusRequestDto dto){
        service.updateStatusThesis(dto);
        return Response.ok()
                .entity(ResponseDto.builder()
                        .message("thesis "+ dto.status() + " successfully")
                        .build())
                .build();
    }
}

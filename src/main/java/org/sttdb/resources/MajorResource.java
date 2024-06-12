package org.sttdb.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sttdb.dto.lecturer.LecturerResponseDto;
import org.sttdb.dto.major.MajorResponseDto;
import org.sttdb.dto.major.MajorUpsertRequestDto;
import org.sttdb.services.MajorService;

@Path("/api/majors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MajorResource {
    @Inject
    MajorService service;

    @GET
    public Response getMajors(@QueryParam("pageNumber") Integer pageNumber,
                              @QueryParam("pageSize") Integer pageSize,
                              @QueryParam("name") String name) {
        return Response.ok(service.getMajors(pageNumber,pageSize,name)).build();
    }

    @GET
    @Path("/{id}")
    public Response getMajor(@PathParam("id") Long id) {
        return Response.ok(service.getMajor(id)).build();
    }


    @POST
    public Response addMajor(@Valid MajorUpsertRequestDto dto) {
        service.addMajor(dto);
        return Response.accepted()
                .status(Response.Status.CREATED)
                .entity(MajorResponseDto.builder()
                        .message("Major added successfully")
                        .build())
                .build();
    }

    @PUT
    public Response updateMajor(@Valid MajorUpsertRequestDto dto) {
        service.updateMajor(dto);
        return Response.accepted()
                .status(Response.Status.OK)
                .entity(MajorResponseDto.builder()
                        .id(dto.id())
                        .message("Major updated successfully")
                        .build())
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMajor(@PathParam("id") Long id) {
        service.deleteMajor(id);
        return Response.accepted()
                .status(Response.Status.OK)
                .entity(MajorResponseDto.builder()
                        .id(id)
                        .message("Major deleted successfully")
                        .build())
                .build();
    }

    @PUT
    @Path("inactive/{id}")
    public Response inactiveMajor(@PathParam("id") Long id) {
        service.inactiveMajor(id);
        return Response.accepted()
                .status(Response.Status.OK)
                .entity(MajorResponseDto.builder()
                        .id(id)
                        .message("Major inactivated successfully")
                        .build())
                .build();
    }

}

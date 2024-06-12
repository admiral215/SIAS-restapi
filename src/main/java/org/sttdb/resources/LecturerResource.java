package org.sttdb.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sttdb.dto.lecturer.LecturerRegistrationRequestDto;
import org.sttdb.dto.lecturer.LecturerResponseDto;
import org.sttdb.dto.lecturer.LecturerUpdateRequestDto;
import org.sttdb.entities.Lecturer;
import org.sttdb.services.LecturerService;

@Path("api/lecturers")
public class LecturerResource {
    @Inject
    LecturerService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLecturers(@QueryParam("pageNumber") Integer pageNumber,
                                 @QueryParam("pageSize") Integer pageSize,
                                 @QueryParam("name") String name){
        return Response.ok(service.getAllActiveLecturers(pageNumber,pageSize, name)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLecturerById(@PathParam("id") String id){
        return Response.ok(service.getLecturerById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLecturer(@Valid LecturerRegistrationRequestDto dto){
        service.addLecturer(dto);

        return Response.accepted()
                .status(Response.Status.CREATED)
                .entity(LecturerResponseDto.builder()
                        .username(dto.userId())
                        .message("Lecturer added successfully")
                        .build())
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLecturer(LecturerUpdateRequestDto dto){
        service.updateLecturer(dto);
        return Response.accepted()
                .status(Response.Status.CREATED)
                .entity(LecturerResponseDto.builder()
                        .username(dto.userId())
                        .message("Lecturer updated successfully")
                        .build())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLecturer(@PathParam("id") String id){
        service.deleteLecturer(id);
        return Response.accepted()
                .status(Response.Status.CREATED)
                .entity(LecturerResponseDto.builder()
                        .username(id)
                        .message("Lecturer deleted successfully")
                        .build())
                .build();
    }

    @PUT
    @Path("inactive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inactiveLecturer(@PathParam("id") String id){
        service.inactiveLecturer(id);

        return Response.accepted()
                .status(Response.Status.CREATED)
                .entity(LecturerResponseDto.builder()
                        .username(id)
                        .message("Lecturer inactive successfully")
                        .build())
                .build();
    }
}

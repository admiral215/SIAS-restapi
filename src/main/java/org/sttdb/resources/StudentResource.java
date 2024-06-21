package org.sttdb.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.sttdb.dto.student.StudentRegistrationRequestDto;
import org.sttdb.dto.student.StudentResponseDto;
import org.sttdb.dto.student.StudentUpdateRequestDto;
import org.sttdb.services.StudentService;


@Path("api/students")
@RolesAllowed("student")
public class StudentResource {

    @Inject
    StudentService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents(@QueryParam("pageNumber") Integer pageIndex,
                                   @QueryParam("pageSize") Integer pageSize,
                                   @QueryParam("name") String name) {
        return Response.ok(service.getAllActiveStudents(pageIndex, pageSize, name)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") String id) {
        return Response.ok(service.getStudentById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(@Valid StudentRegistrationRequestDto dto) {
        service.addStudent(dto);
        return Response.accepted()
                .entity(
                        StudentResponseDto.builder()
                                .username(dto.userId())
                                .name(dto.getFullName())
                                .message("Student successfully added")
                                .build()
                ).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(@Valid StudentUpdateRequestDto dto, @PathParam("id") String id) {
        service.updateStudent(dto, id);
        return Response.accepted()
                .entity(
                        StudentResponseDto.builder()
                                .username(id)
                                .name(dto.getFullName())
                                .message("Student successfully updated")
                                .build()
                ).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") String id) {
        service.deleteStudent(id);
        var student = service.getStudentById(id);
        return Response.accepted()
                .entity(
                        StudentResponseDto.builder()
                                .username(id)
                                .name(student.getFullName())
                                .message("Student successfully deleted")
                                .build()
                ).build();
    }

    @PUT
    @Path("inactive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inactiveStudent(@PathParam("id") String id) {
        service.inactiveStudent(id);
        var student = service.getStudentById(id);
        return Response.accepted()
                .entity(
                        StudentResponseDto.builder()
                                .username(id)
                                .name(student.getFullName())
                                .message("Student successfully inactived")
                                .build()
                ).build();
    }
}

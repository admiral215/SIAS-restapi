package org.sttdb.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.sttdb.dto.user.UserChangePasswordRequestDto;
import org.sttdb.dto.user.UserRegistrationRequestDto;
import org.sttdb.dto.user.UserResponseDto;
import org.sttdb.services.UserService;

@Path("api/users")
public class UserResource {
    @Inject
    UserService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("role") String role) {
        var users = service.getUserByRole(role);
        if (users.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(service.getUserByRole(role)).build();
    }

    @POST
    @Path("registration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid UserRegistrationRequestDto dto) {
        service.addUser(dto);
        return Response.accepted()
                .entity(UserResponseDto.builder()
                        .username(dto.username())
                        .message("Registration Successful")
                        .build())
                .build();
    }

    @POST
    @Path("change-password")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePasswordUser(UserChangePasswordRequestDto dto) {
        service.changePassword(dto);
        return Response.accepted()
                .entity(UserResponseDto.builder()
                        .username(dto.username())
                        .message("Change Password Successful")
                        .build())
                .build();
    }
}

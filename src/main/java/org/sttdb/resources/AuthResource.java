package org.sttdb.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.sttdb.dto.auth.LoginRequestDto;
import org.sttdb.dto.auth.LoginResponseDto;
import org.sttdb.services.AuthService;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    @Inject
    AuthService service;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/login")
    public Response login (LoginRequestDto dto){
        LoginResponseDto resp = service.login(dto);
        return Response.ok(resp).build();
    }
}

package org.sttdb.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(Response.Status.NOT_FOUND.getStatusCode())
                .message(e.getMessage())
                .build();
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }
}

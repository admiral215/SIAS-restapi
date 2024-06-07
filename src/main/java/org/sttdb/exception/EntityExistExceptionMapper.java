package org.sttdb.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityExistExceptionMapper implements ExceptionMapper<EntityExistsException> {
    @Override
    public Response toResponse(EntityExistsException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(Response.Status.CONFLICT.getStatusCode())
                .message(e.getMessage())
                .build();

        return Response.status(Response.Status.CONFLICT.getStatusCode())
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}

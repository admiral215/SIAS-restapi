package org.sttdb.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .message(e.getMessage())
                .build();

        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}

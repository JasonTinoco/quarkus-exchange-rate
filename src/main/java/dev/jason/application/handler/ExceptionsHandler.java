package dev.jason.application.handler;

import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static dev.jason.application.util.ResponseStatus.BAD_REQUEST;

@Provider
public class ExceptionsHandler implements
        ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        String message = e.getConstraintViolations().iterator()
                .next().getMessage();

        return Response.status(BAD_REQUEST.getCode())
                .entity(ExchangeRateResponse.builder()
                        .code(BAD_REQUEST.getCode())
                        .response(BAD_REQUEST.getStatus())
                        .message(message)
                        .build())
                .build();
    }
}

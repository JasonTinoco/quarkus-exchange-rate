package dev.jason.infrastructure.adapters.in.rest;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import static dev.jason.application.util.Constants.DNI_NULL_MESSAGE;
import static dev.jason.application.util.Constants.DNI_REGEX;
import static dev.jason.application.util.Constants.DNI_REGEX_MESSAGE;

@Path("/exchange-rate")
@RequiredArgsConstructor
public class ExchangeApiAdapter {

    private final ExchangeRateUseCase exchangeRateUseCase;

    @GET
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExchangeRate(
            @NotNull(message = DNI_NULL_MESSAGE)
            @Pattern(regexp = DNI_REGEX, message = DNI_REGEX_MESSAGE)
            @PathParam("dni") String dni) {

        ExchangeRateResponse response =
                exchangeRateUseCase.getExchangeRateTodayByDocument(dni);

        return Response.status(response.getCode())
                .entity(response)
                .build();
    }
}

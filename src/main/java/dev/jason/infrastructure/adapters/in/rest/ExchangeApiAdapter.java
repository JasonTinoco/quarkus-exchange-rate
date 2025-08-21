package dev.jason.infrastructure.adapters.in.rest;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/exchange-rate")
@RequiredArgsConstructor
public class ExchangeApiAdapter {

    private final ExchangeRateUseCase exchangeRateUseCase;

    @GET
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExchangeRate(@PathParam("dni") String dni) {
        Log.info("Start endpoint getExchangeRate with param: " + dni);
        var response = exchangeRateUseCase.getExchangeRateTodayByDocument(dni);
        return Response.ok(response).build();
    }
}

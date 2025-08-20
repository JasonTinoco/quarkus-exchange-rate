package dev.jason.infrastructure.adapters.out.client.freeapi;

import dev.jason.infrastructure.adapters.out.client.freeapi.dto.FreeApiResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/tipo-cambio/today.json")
@RegisterRestClient(configKey = "free-api")
public interface FreeApi {

    @GET
    FreeApiResponse getExchangeRateToday();
}

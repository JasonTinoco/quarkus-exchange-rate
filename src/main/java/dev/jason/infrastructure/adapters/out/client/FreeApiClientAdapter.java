package dev.jason.infrastructure.adapters.out.client;

import dev.jason.application.ports.out.FreeApiClientOutPort;
import dev.jason.domain.model.ExchangeRate;
import dev.jason.infrastructure.adapters.out.client.freeapi.FreeApi;
import dev.jason.infrastructure.mappers.ExchangeRateMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class FreeApiClientAdapter implements FreeApiClientOutPort {

    private final FreeApi freeApi;
    private final ExchangeRateMapper mapper;

    public FreeApiClientAdapter(@RestClient FreeApi freeApi, ExchangeRateMapper mapper) {
        this.freeApi = freeApi;
        this.mapper = mapper;
    }

    @Override
    public ExchangeRate getExchangeToday() {
        return mapper.toDomain(freeApi.getExchangeRateToday());
    }
}

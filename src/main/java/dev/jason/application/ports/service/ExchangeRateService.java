package dev.jason.application.ports.service;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import dev.jason.application.ports.out.FreeApiClientOutPort;
import dev.jason.domain.model.ExchangeRate;
import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateUseCase {

    private final FreeApiClientOutPort exchangeRateOutPort;

    @Override
    public ExchangeRateResponse getExchangeRateTodayByDni(String dni) {
        ExchangeRate er = exchangeRateOutPort.getExchangeToday();

        return ExchangeRateResponse.builder()
                .code(200)
                .message("Success")
                .response("El tipo de cambio para el DNI: " + dni + " es: " + er.getSale())
                .build();
    }
}

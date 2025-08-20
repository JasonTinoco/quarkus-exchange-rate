package dev.jason.application.ports.in;

import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;

public interface ExchangeRateUseCase {

    ExchangeRateResponse getExchangeRateTodayByDni(String dni);
}

package dev.jason.application.ports.service;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import dev.jason.application.ports.out.FreeApiClientOutPort;
import dev.jason.application.ports.out.QuotesOutPort;
import dev.jason.domain.model.ExchangeRate;
import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateUseCase {

    private final FreeApiClientOutPort exchangeRateOutPort;
    private final QuotesOutPort quotesOutPort;

    @Override
    @Transactional
    public ExchangeRateResponse getExchangeRateTodayByDni(String dni) {

        quotesOutPort.saveQuotes(QuotesEntity.builder()
                .dni("62776640")
                .date(LocalDate.now())
                .quotes(2)
                .build());

        ExchangeRate er = exchangeRateOutPort.getExchangeToday();

        return ExchangeRateResponse.builder()
                .code(200)
                .message("Success")
                .response("El tipo de cambio para el DNI: " + dni + " es: " + er.getSale())
                .build();
    }
}

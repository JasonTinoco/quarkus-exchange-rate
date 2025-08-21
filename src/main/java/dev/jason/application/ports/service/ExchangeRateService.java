package dev.jason.application.ports.service;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import dev.jason.application.ports.out.FreeApiClientOutPort;
import dev.jason.application.ports.out.QuotesOutPort;
import dev.jason.domain.model.ExchangeRate;
import dev.jason.domain.model.Quotes;
import dev.jason.domain.service.ExchangeRateServiceDomain;
import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateUseCase {

    private final FreeApiClientOutPort exchangeRateOutPort;
    private final QuotesOutPort quotesOutPort;
    private final ExchangeRateServiceDomain serviceDomain;

    @Override
    @Transactional
    public ExchangeRateResponse getExchangeRateTodayByDocument(String document) {

        LocalDate today = LocalDate.now();

        Quotes quotesResp = quotesOutPort.getQuotesPerDayByDocument(document, today);
        Integer countQuotes = serviceDomain.getQuotes(quotesResp);
        Log.info("countQuotesPerDayByDocument: " + countQuotes);

        if (serviceDomain.isExceededQuotesLimit(countQuotes)) {
            return ExchangeRateResponse.builder()
                    .code(400)
                    .response("Warning")
                    .message("El cliente con DNI: " + document +
                            " Superó el límite de cotizaciones ")
                    .build();
        }

        ExchangeRate exchangeRate = exchangeRateOutPort.getExchangeToday();
        Quotes quotesReq = Quotes.builder()
                .document(document)
                .quotes(serviceDomain.addQuotes(countQuotes))
                .date(today)
                .exchangeRate(exchangeRate.getSunat())
                .buy(exchangeRate.getBuy())
                .sale(exchangeRate.getSale())
                .build();

        if (serviceDomain.isFirstQuotesOfDay(countQuotes)) {
            quotesOutPort.saveQuotes(quotesReq);
        } else {
            quotesOutPort.updateQuotes(quotesReq);
        }

        return ExchangeRateResponse.builder()
                .code(200)
                .response("Success")
                .message("El tipo de cambio para el cliente con DNI: " + document
                        + " es: " + exchangeRate.getSale())
                .build();
    }
}

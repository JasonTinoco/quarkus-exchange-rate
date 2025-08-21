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

        Quotes quotes = quotesOutPort.getQuotesPerDayByDocument(document, today);
        Integer countQuotes = serviceDomain.getQuotes(quotes);
        Log.info("countQuotesPerDayByDocument: " + countQuotes);

        if (serviceDomain.isExceededQuotesLimit(countQuotes)) {
            return ExchangeRateResponse.builder()
                    .code(401)
                    .response("Warning")
                    .message("El cliente con DNI: " + document +
                            " Superó el límite de cotizaciones ")
                    .build();
        }

        if (serviceDomain.isFirstQuotesOfDay(countQuotes)) {
            quotesOutPort.saveQuotes(Quotes.builder()
                    .document(document)
                    .quotes(serviceDomain.addQuotes(countQuotes))
                    .date(today).build());
        } else {
            quotesOutPort.updateQuotes(
                    serviceDomain.addQuotes(countQuotes), document, today);
        }

        ExchangeRate er = exchangeRateOutPort.getExchangeToday();

        return ExchangeRateResponse.builder()
                .code(200)
                .response("Success")
                .message("El tipo de cambio para el cliente con DNI: " + document
                        + " es: " + er.getSale())
                .build();
    }
}

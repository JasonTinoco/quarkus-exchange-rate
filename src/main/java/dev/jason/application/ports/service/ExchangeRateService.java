package dev.jason.application.ports.service;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import dev.jason.application.ports.out.FreeApiClientOutPort;
import dev.jason.application.ports.out.QuotesOutPort;
import dev.jason.domain.model.ExchangeRate;
import dev.jason.domain.model.Quotes;
import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateUseCase {

    private final FreeApiClientOutPort exchangeRateOutPort;
    private final QuotesOutPort quotesOutPort;

    @Override
    @Transactional
    public ExchangeRateResponse getExchangeRateTodayByDocument(String document) {

        LocalDate today = LocalDate.now();

        Quotes quotes = quotesOutPort.getQuotesPerDayByDocument(document, today);
        Integer countQuotes = Objects.isNull(quotes) ? 0 : quotes.getQuotes();
        Log.info("getQuotesPerDayByDocument: " + countQuotes);

        if (countQuotes == 0) {
            quotes = Quotes.builder()
                    .document(document)
                    .quotes(countQuotes+1)
                    .date(today).build();

            quotesOutPort.saveQuotes(quotes);
        } else {
            quotesOutPort.updateQuotes(countQuotes+1, document, today);
        }

        ExchangeRate er = exchangeRateOutPort.getExchangeToday();

        return ExchangeRateResponse.builder()
                .code(200)
                .message("Success")
                .response("El tipo de cambio para el DNI: " + document + " es: " + er.getSale())
                .build();
    }
}

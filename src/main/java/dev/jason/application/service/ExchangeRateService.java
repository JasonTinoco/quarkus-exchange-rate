package dev.jason.application.service;

import dev.jason.application.ports.in.ExchangeRateUseCase;
import dev.jason.application.ports.out.FreeApiClientOutPort;
import dev.jason.application.ports.out.QuotesOutPort;
import dev.jason.domain.model.ExchangeRate;
import dev.jason.domain.model.Quotes;
import dev.jason.domain.service.ExchangeRateServiceDomain;
import dev.jason.infrastructure.adapters.in.rest.dto.ExchangeRateResponse;
import dev.jason.infrastructure.mappers.ExchangeRateMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

import static dev.jason.application.util.ResponseStatus.ERROR;
import static dev.jason.application.util.ResponseStatus.SUCCESS;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateUseCase {

    private final FreeApiClientOutPort exchangeRateOutPort;
    private final QuotesOutPort quotesOutPort;
    private final ExchangeRateServiceDomain serviceDomain;
    private final ExchangeRateMapper mapper;

    @Override
    @Transactional
    public ExchangeRateResponse getExchangeRateTodayByDocument(String document) {
        LocalDate today = LocalDate.now();
        Integer countQuotes;

        if (serviceDomain.requiresSaveAllQuotes()) {
            countQuotes = quotesOutPort.countQuotesPerDayByDocument(document, today);
        } else {
            Quotes quotesResp = quotesOutPort.getQuotesPerDayByDocument(document, today);
            countQuotes = serviceDomain.getQuotes(quotesResp);
        }

        if (serviceDomain.isExceededQuotesLimit(countQuotes)) {
            return this.buildResponseWarning(document);
        }

        ExchangeRate exchangeRate = exchangeRateOutPort.getExchangeToday();
        Quotes quotesReq = this.buildQuote(document, countQuotes, today,
                exchangeRate);

        if (serviceDomain.requiresSaveAllQuotes()) {
            quotesOutPort.saveQuotes(quotesReq);
        } else {
            if (serviceDomain.isFirstQuotesOfDay(countQuotes)) {
                quotesOutPort.saveQuotes(quotesReq);
            } else {
                quotesOutPort.updateQuotes(quotesReq);
            }
        }

        return this.buildResponseSuccess(document, exchangeRate);
    }

    private ExchangeRateResponse buildResponseWarning(String document) {
        return ExchangeRateResponse.builder()
                .code(ERROR.getCode())
                .response(ERROR.getStatus())
                .message(ERROR.formatMessage(document))
                .build();
    }

    private ExchangeRateResponse buildResponseSuccess(String document,
                                                      ExchangeRate exchangeRate) {
        return ExchangeRateResponse.builder()
                .code(SUCCESS.getCode())
                .response(SUCCESS.getStatus())
                .message(SUCCESS.formatMessage(document, exchangeRate.getSunat()))
                .data(mapper.toData(exchangeRate))
                .build();
    }

    private Quotes buildQuote(String document, Integer quotes, LocalDate today,
                              ExchangeRate exchangeRate) {
        return Quotes.builder()
                .document(document)
                .quotes(serviceDomain.addQuotes(quotes))
                .date(today)
                .exchangeRate(exchangeRate.getSunat())
                .buy(exchangeRate.getBuy())
                .sale(exchangeRate.getSale())
                .build();
    }
}

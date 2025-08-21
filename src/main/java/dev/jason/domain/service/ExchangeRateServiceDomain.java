package dev.jason.domain.service;

import dev.jason.domain.model.Quotes;
import dev.jason.infrastructure.config.AppProperties;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateServiceDomain {

    private final AppProperties appProperties;

    public Integer getQuotes(Quotes quotes) {
        return Objects.isNull(quotes) ? 0 : quotes.getQuotes();
    }

    public Boolean isFirstQuotesOfDay(Integer quotes) {
        return quotes.equals(0);
    }

    public Integer addQuotes(Integer quotes) {
        return quotes + 1;
    }

    public Boolean isExceededQuotesLimit(Integer quotes) {
        return quotes >= appProperties.maxQuotesPerDay();
    }
}

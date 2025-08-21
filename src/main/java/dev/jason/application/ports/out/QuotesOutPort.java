package dev.jason.application.ports.out;

import dev.jason.domain.model.Quotes;

import java.time.LocalDate;

public interface QuotesOutPort {

    void saveQuotes(Quotes quotes);

    void updateQuotes(Integer quotes, String document, LocalDate date);

    Quotes getQuotesPerDayByDocument(String document, LocalDate date);
}

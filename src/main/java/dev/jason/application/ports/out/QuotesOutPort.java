package dev.jason.application.ports.out;

import dev.jason.domain.model.Quotes;

import java.time.LocalDate;

public interface QuotesOutPort {

    void saveQuotes(Quotes quotes);

    void updateQuotes(Quotes quotes);

    Quotes getQuotesPerDayByDocument(String document, LocalDate date);

    Integer countQuotesPerDayByDocument(String document, LocalDate date);
}

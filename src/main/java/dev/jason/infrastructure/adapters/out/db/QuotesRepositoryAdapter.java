package dev.jason.infrastructure.adapters.out.db;

import dev.jason.application.ports.out.QuotesOutPort;
import dev.jason.domain.model.Quotes;
import dev.jason.infrastructure.adapters.out.db.repository.QuotesRepository;
import dev.jason.infrastructure.mappers.QuotesMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
public class QuotesRepositoryAdapter implements QuotesOutPort {

    private final QuotesRepository repository;
    private final QuotesMapper mapper;

    @Override
    public void saveQuotes(Quotes quotes) {
        repository.save(mapper.toEntity(quotes));
    }

    @Override
    public void updateQuotes(Integer quotes, String document, LocalDate date) {
        repository.update(quotes, document, date);
    }

    @Override
    public Quotes getQuotesPerDayByDocument(String document, LocalDate date) {
        return mapper.toDomain(repository.quotesPerDayByDyDocument(document, date));
    }
}

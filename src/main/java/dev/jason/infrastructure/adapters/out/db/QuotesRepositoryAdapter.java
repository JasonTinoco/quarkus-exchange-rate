package dev.jason.infrastructure.adapters.out.db;

import dev.jason.application.ports.out.QuotesOutPort;
import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;
import dev.jason.infrastructure.adapters.out.db.repository.QuotesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class QuotesRepositoryAdapter implements QuotesOutPort {

    private final QuotesRepository repository;

    @Override
    public void saveQuotes(QuotesEntity quotes) {
        repository.save(quotes);
    }
}

package dev.jason.application.ports.out;

import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;

public interface QuotesOutPort {

    void saveQuotes(QuotesEntity quotes);
}

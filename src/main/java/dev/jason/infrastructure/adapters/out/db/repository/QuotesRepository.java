package dev.jason.infrastructure.adapters.out.db.repository;

import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;

@ApplicationScoped
public class QuotesRepository implements PanacheRepository<QuotesEntity> {

    public void save(QuotesEntity quotes) {
        persist(quotes);
    }

    public Integer update(Integer quotes, String document, LocalDate date) {
        return update("quotes = ?1 where document = ?2 and date =?3",
                quotes, document, date);
    }

    public Long numberQuotesPerDayByDyDocument(String document, LocalDate date) {
        return count("document = ?1 and date = ?2", document, date);
    }

    public QuotesEntity quotesPerDayByDyDocument(String document, LocalDate date) {
        return find("document = ?1 and date = ?2", document, date).firstResult();
    }

}

package dev.jason.infrastructure.adapters.out.db.repository;

import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalTime;

@ApplicationScoped
public class QuotesRepository implements PanacheRepository<QuotesEntity> {

    public void save(QuotesEntity quotes) {
        persist(quotes);
    }

    public void update(Integer quotes, Double exchangeRate, Double buy,
                       Double sale, String document, LocalDate date) {
        update("quotes = ?1, exchangeRate =?2, buy = ?3, sale = ?4, " +
                        "updateTime = ?5 where document = ?6 and date =?7",
                quotes, exchangeRate, buy, sale, LocalTime.now(), document, date);
    }

    public QuotesEntity quotesPerDayByDyDocument(String document, LocalDate date) {
        return find("document = ?1 and date = ?2", document, date).firstResult();
    }

}

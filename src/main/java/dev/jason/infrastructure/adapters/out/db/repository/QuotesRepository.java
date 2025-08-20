package dev.jason.infrastructure.adapters.out.db.repository;

import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotesRepository implements PanacheRepository<QuotesEntity> {

    public void save(QuotesEntity quotes) {
        persist(quotes);
    }

}

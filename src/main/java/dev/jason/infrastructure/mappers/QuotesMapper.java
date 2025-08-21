package dev.jason.infrastructure.mappers;

import dev.jason.domain.model.Quotes;
import dev.jason.infrastructure.adapters.out.db.entity.QuotesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface QuotesMapper {

    Quotes toDomain(QuotesEntity quotesEntity);
    QuotesEntity toEntity(Quotes quotes);
}

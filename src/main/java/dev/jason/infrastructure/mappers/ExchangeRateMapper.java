package dev.jason.infrastructure.mappers;


import dev.jason.domain.model.ExchangeRate;
import dev.jason.infrastructure.adapters.out.client.freeapi.dto.FreeApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ExchangeRateMapper {

    @Mapping(target = "date", source = "fecha")
    @Mapping(target = "buy", source = "compra")
    @Mapping(target = "sale", source = "venta")
    ExchangeRate toDomain(FreeApiResponse freeApiResponse);

}

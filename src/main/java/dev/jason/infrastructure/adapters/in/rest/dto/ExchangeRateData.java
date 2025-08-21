package dev.jason.infrastructure.adapters.in.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeRateData {

    private Double tipoCambio;
    private Double compra;
    private Double venta;
}

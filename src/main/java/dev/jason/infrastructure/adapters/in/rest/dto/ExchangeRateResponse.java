package dev.jason.infrastructure.adapters.in.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeRateResponse {
    private Integer code;
    private String response;
    private String message;
    private ExchangeRateData data;

}

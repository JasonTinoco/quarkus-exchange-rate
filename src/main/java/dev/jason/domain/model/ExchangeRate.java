package dev.jason.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExchangeRate {

    private String date;
    private Double sunat;
    private Double buy;
    private Double sale;
}

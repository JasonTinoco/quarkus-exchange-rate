package dev.jason.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Quotes {

    private Long id;
    private String document;
    private LocalDate date;
    private Integer quotes;
}

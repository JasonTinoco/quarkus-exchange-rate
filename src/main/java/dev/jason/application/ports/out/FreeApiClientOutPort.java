package dev.jason.application.ports.out;

import dev.jason.domain.model.ExchangeRate;

public interface FreeApiClientOutPort {

    ExchangeRate getExchangeToday();
}

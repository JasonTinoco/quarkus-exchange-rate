package dev.jason.infrastructure.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app")
public interface AppProperties {
    Integer maxQuotesPerDay();
}

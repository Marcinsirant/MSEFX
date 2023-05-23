package pl.sirant.price.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreatePriceRequest(
    int id,
    String instrumentName,
    BigDecimal bid,
    BigDecimal ask,
    Instant instant
) {
}

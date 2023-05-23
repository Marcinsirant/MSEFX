package pl.sirant.price.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record PriceRepresentation(
    int id,
    String instrumentName,
    BigDecimal bid,
    BigDecimal ask,
    Instant instant

) {
}

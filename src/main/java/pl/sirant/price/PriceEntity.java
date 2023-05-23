package pl.sirant.price;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
class PriceEntity {
  private int id;
  private String instrumentName; // W poleceniu nie ma sprecyzowanego formatu, więc zakładam, że może być dowolny
  private BigDecimal bid;
  private BigDecimal ask;
  private Instant instant;
}

package pl.sirant.message;

import lombok.RequiredArgsConstructor;
import pl.sirant.price.PriceApi;
import pl.sirant.price.dto.CreatePriceRequest;

import java.math.BigDecimal;
import java.time.Instant;

@RequiredArgsConstructor
class Subscriber {

  private final PriceApi prices;

  void onMessage(String message) {
    var attributes = message.split(",");
    if (attributes.length != 5) {
      throw new IllegalArgumentException("Invalid message format");
    }
    prices.savePrice(new CreatePriceRequest(Integer.parseInt(attributes[0]), attributes[1], new BigDecimal(attributes[2]), new BigDecimal(attributes[3]), Instant.parse(attributes[4])));
  }
}

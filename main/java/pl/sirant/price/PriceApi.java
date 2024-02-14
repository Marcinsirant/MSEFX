package pl.sirant.price;

import pl.sirant.price.dto.CreatePriceRequest;
import pl.sirant.price.dto.PriceRepresentation;

public interface PriceApi {


  /**
   * @param priceRepresentation
   * @return price including commission
   */
  PriceRepresentation savePrice(CreatePriceRequest priceRepresentation);

  PriceRepresentation getActualPrice(String instrumentName);
}

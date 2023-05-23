package pl.sirant.price;

import java.util.Optional;

interface PriceRepository {

  PriceEntity save(PriceEntity priceEntity);

  Optional<PriceEntity> findActualPriceByInstrumentName(String instrumentName);
}

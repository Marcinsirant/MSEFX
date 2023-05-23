package pl.sirant.price;

import lombok.RequiredArgsConstructor;
import pl.sirant.price.dto.CreatePriceRequest;
import pl.sirant.price.dto.PriceRepresentation;
import pl.sirant.price.exception.OldPriceException;
import pl.sirant.price.exception.PriceNotFoundException;

@RequiredArgsConstructor
class PriceFacade implements PriceApi {

  private final PriceRepository priceRepository;
  private final CommissionPolicy commissionPolicy;

  @Override
  public PriceRepresentation savePrice(CreatePriceRequest priceRepresentation) {
    priceRepository.findActualPriceByInstrumentName(priceRepresentation.instrumentName())
        .ifPresent(priceEntity -> {
          if (priceEntity.getInstant().isAfter(priceRepresentation.instant())) {
            throw new OldPriceException();
          }
        });

    PriceEntity priceEntity = new PriceEntity(
        priceRepresentation.id(),
        priceRepresentation.instrumentName(),
        commissionPolicy.increase(priceRepresentation.bid()),
        commissionPolicy.decrease(priceRepresentation.ask()),
        priceRepresentation.instant()
    );

    return toRepresentation(priceRepository.save(priceEntity));
  }

  @Override
  public PriceRepresentation getActualPrice(String instrumentName) {
    return priceRepository.findActualPriceByInstrumentName(instrumentName).map(this::toRepresentation).orElseThrow(() -> new PriceNotFoundException(instrumentName));
  }

  // Jeżeli byłoby więcej mapowań warto byłoby przenieść je do osobnej klasy
  private PriceRepresentation toRepresentation(PriceEntity priceEntity) {
    return new PriceRepresentation(priceEntity.getId(), priceEntity.getInstrumentName(), priceEntity.getBid(), priceEntity.getAsk(), priceEntity.getInstant());
  }
}

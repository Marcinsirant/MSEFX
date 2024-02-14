package pl.sirant.price;

import org.junit.jupiter.api.Test;
import pl.sirant.price.dto.CreatePriceRequest;
import pl.sirant.price.dto.PriceRepresentation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PriceFacadeTest {

  private final PriceFacade priceFacade = new PriceFacade(mockPriceRepository(), new CommissionPolicy(BigDecimal.valueOf(0.1d)));


  @Test
  void should_create_price() {
    // given
    Instant now = Instant.now();
    CreatePriceRequest createPriceRequest = new CreatePriceRequest(1, "EURUSD", BigDecimal.valueOf(100d), BigDecimal.valueOf(100d), now);

    // when
    PriceRepresentation priceRepresentation = priceFacade.savePrice(createPriceRequest);

    // then
    assertThat(priceRepresentation.bid().doubleValue()).isEqualTo(110d);
    assertThat(priceRepresentation.ask().doubleValue()).isEqualTo(90d);
  }


  private static PriceRepository mockPriceRepository() {
    return new PriceRepository() {
      @Override
      public PriceEntity save(PriceEntity priceEntity) {
        return priceEntity;
      }

      @Override
      public Optional<PriceEntity> findActualPriceByInstrumentName(String instrumentName) {
        return Optional.empty();
      }
    };
  }
}
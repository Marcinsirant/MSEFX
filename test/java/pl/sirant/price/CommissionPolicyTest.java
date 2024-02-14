package pl.sirant.price;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CommissionPolicyTest {

  private final CommissionPolicy commissionPolicy = new CommissionPolicy(BigDecimal.valueOf(0.1));

  @Test
  void should_add_10_percent_commission_to_price() {
    BigDecimal price = BigDecimal.valueOf(100);
    BigDecimal expected = BigDecimal.valueOf(110);

    BigDecimal actual = commissionPolicy.increase(price);

    assertThat(actual.doubleValue()).isEqualTo(expected.doubleValue());
  }

  @Test
  void should_subtract_10_percent_commission_from_price() {
    BigDecimal price = BigDecimal.valueOf(100);
    BigDecimal expected = BigDecimal.valueOf(90);

    BigDecimal actual = commissionPolicy.decrease(price);

    assertThat(expected.doubleValue()).isEqualTo(actual.doubleValue());
  }


}
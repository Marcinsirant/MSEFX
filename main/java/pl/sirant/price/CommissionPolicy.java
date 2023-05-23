package pl.sirant.price;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
class CommissionPolicy {

  private final BigDecimal commission;

  BigDecimal increase(BigDecimal price) {
    BigDecimal value = price.add(price.multiply(commission));
    checkLessThenZero(value);
    return value;
  }


  BigDecimal decrease(BigDecimal price) {
    BigDecimal value = price.subtract(price.multiply(commission));
    checkLessThenZero(value);
    return value;
  }

  private static void checkLessThenZero(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Price must be greater than 0");
    }
  }

}

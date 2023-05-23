package pl.sirant.price.exception;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(String instrumentName) {
    super("Price for instrument " + instrumentName + " not found");
  }
}

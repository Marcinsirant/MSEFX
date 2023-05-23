package pl.sirant.price.exception;

public class OldPriceException extends RuntimeException {

  public OldPriceException() {
    super("Price is older than actual");
  }
}

package errors;

public class CannotBuyBookException extends Exception {
  public CannotBuyBookException(String message) {
    super(message);
  }
}

package book;

public class PaperBook extends Book {
  private int stock;
  
  public PaperBook() {
  }

  public PaperBook(String isbn, String title, double price, int publishedYear, int stock) {
    super(isbn, title, price, publishedYear);
    this.stock = stock;
  }

  public int getNumberInStock() {
    return stock;
  }

  public void decreaseStock(int value) {
    stock -= value;
  }

  public void sendToShippingService(String address) {
    System.out.println(String.format("\"%s\" shipped to \"%s\"", super.getTitle(), address));
  }
}

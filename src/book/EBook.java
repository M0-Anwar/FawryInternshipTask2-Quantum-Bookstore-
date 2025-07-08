package book;

public class EBook extends Book{
  private String type;

  public EBook() {
  }

  public EBook(String isbn, String title, double price, int publishedYear, String type) {
    super(isbn, title, price, publishedYear);
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void sendViaEmail(String email) {
    System.out.println(String.format("\"%s\" sent to \"%s\" successfully", super.getTitle(), email));
  }
}

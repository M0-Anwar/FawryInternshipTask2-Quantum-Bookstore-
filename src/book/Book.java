package book;

public class Book {
  private String isbn;
  private String title;
  private double price;
  private int publishedYear;
  
  public Book() {
  }
  
  public Book(String isbn, String title, double price, int publishedYear) {
    this.isbn = isbn;
    this.title = title;
    this.price = price;
    this.publishedYear = publishedYear;
  }

  public String getISBN() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public double getPrice() {
    return price;
  }

  public int getPublishedYear() {
    return publishedYear;
  }

  // ! remove the setter methods, since that once the book created cannot 
  // ! change its name or title ....

  // public void setIsbn(String isbn) {
  //   this.isbn = isbn;
  // }

  // public void setTitle(String title) {
  //   this.title = title;
  // }

  // public void setPrice(double price) {
  //   this.price = price;
  // }

  // public void setPublishedYear(int publishedYear) {
  //   this.publishedYear = publishedYear;
  // }

  
}

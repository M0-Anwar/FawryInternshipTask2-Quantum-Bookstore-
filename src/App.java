import java.util.List;

import book.Book;
import book.EBook;
import book.PaperBook;
import book.ShowCaseBook;
import errors.BookNotFoundException;
import errors.CannotBuyBookException;
import errors.OutOfStockException;

public class App {
  public static void main(String[] args) {
    BookStore bookStore = BookStore.getInstance();
    
    addToInverntory();

    System.out.println("==================================");
    try {
      // this is the isbn for the book1 and we will see that ther is no vaiolation for any constains
      bookStore.buyBook("9780136520238", 2, "moanwer@gmail.com", "Earth-616");
    } catch (CannotBuyBookException | BookNotFoundException | OutOfStockException e) {
      e.printStackTrace();
    } finally {
      System.out.println("==================================");
    }

    try {
      // this is the isbn for the book3 and we will see that ther is no vaiolation for any constains
      bookStore.buyBook("9780132774208", 2, "moanwer@gmail.com", "Earth-616");
    } catch (CannotBuyBookException | BookNotFoundException | OutOfStockException e) {
      e.printStackTrace();
    } finally {
      System.out.println("==================================");
    }

    try {
      // this is the isbn for the book2 and we will order a quantity more than the stock
      bookStore.buyBook("9781478627777", 5, "moanwer@gmail.com", "Earth-616");
    } catch (CannotBuyBookException | BookNotFoundException | OutOfStockException e) {
      e.printStackTrace();
    } finally {
      System.out.println("==================================");
    }

    try {
      // this is the isbn for the book5 witch is a ShowCaseBook, not for buy
      bookStore.buyBook("9780072322064", 1, "moanwer@gmail.com", "Earth-616");
    } catch (CannotBuyBookException | BookNotFoundException | OutOfStockException e) {
      e.printStackTrace();
    } finally {
      System.out.println("==================================");
    }

    try {
      // enter an isbn for a book that not in the inventory
      String wrongIsbn = "1111111111111";
      bookStore.buyBook(wrongIsbn, 1, "moanwer@gmail.com", "Earth-616");
    } catch (CannotBuyBookException | BookNotFoundException | OutOfStockException e) {
      e.printStackTrace();
    } finally {
      System.out.println("==================================");
    }

    // remove the book that published from more than 4 years
    // at this point inventory have 5 different books
    List<Book> removedBooks = bookStore.removeOutDatedBooks(4);
    // at this point inventory should have 3 different books

    System.out.println("The booked removed: ");
    System.out.println(removedBooks);
    System.out.println("==================================");


    // try to buy on of the outDatedBooks after remove it
    try {
      // enter an isbn for book1 that is removed
      String wrongIsbn = "9780136520238";
      bookStore.buyBook(wrongIsbn, 1, "moanwer@gmail.com", "Earth-616");
    } catch (CannotBuyBookException | BookNotFoundException | OutOfStockException e) {
      e.printStackTrace();
    } finally {
      System.out.println("==================================");
    }
  }

  private static void addToInverntory() {
    BookStore bookStore = BookStore.getInstance();

    Book book1 = new PaperBook("9780136520238",
     "Y. Daniel Liang - Introduction to Java Programming and Data Structures", 1200, 2021, 5);
    
    Book book2 = new PaperBook("9781478627777",
     "introduction to linear algebra with applications", 2000, 2020, 3);

    Book book3 = new EBook("9780132774208",
     "digital design - morris mano (5th)", 1500, 2024, "pdf");

    Book book4 = new EBook("9781284049190",
     "Foundations Of Algorithms (5th)", 1000, 2022, "pdf");

    Book book5 = new ShowCaseBook("9780072322064",
     "Database Management Systems second edition", 1000, 2025);

    bookStore.addToInventory(book1);
    bookStore.addToInventory(book2);
    bookStore.addToInventory(book3);
    bookStore.addToInventory(book4);
    bookStore.addToInventory(book5);
  }
}

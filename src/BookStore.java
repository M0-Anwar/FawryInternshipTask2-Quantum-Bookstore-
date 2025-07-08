import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import book.Book;
import book.EBook;
import book.PaperBook;
import book.ShowCaseBook;
import errors.BookNotFoundException;
import errors.CannotBuyBookException;
import errors.OutOfStockException;

public class BookStore {
  private static BookStore instance;
  
  private ArrayList<Book> inventory = new ArrayList<>();

  private BookStore() {}

  // apply the singletone designBattern
  public static BookStore getInstance() {
    if (instance == null) {
      instance = new BookStore();
    }

    return instance;
  }

  public void addToInventory(Book book) {
    inventory.add(book);
  }

  public List<Book> removeOutDatedBooks(int yearsPassed) {
    List<Book> outDatedBooks = new ArrayList<>();

    for (Book book: inventory) {
      if (Year.now().getValue() - book.getPublishedYear() >= yearsPassed) {
        outDatedBooks.add(book);
      }
    }

    outDatedBooks.forEach(book -> inventory.remove(book));

    return outDatedBooks;
  }

  public double buyBook(String isbn, int quantity, String email, String address)
       throws CannotBuyBookException, BookNotFoundException, OutOfStockException {
    
    Book theBook = getBookByISBN(isbn);

    if (theBook instanceof ShowCaseBook) {
      throw new CannotBuyBookException("Cannot buy a demo book");
    }

    if (theBook instanceof EBook) {
      
      EBook tempBook = (EBook)theBook;
      tempBook.sendViaEmail(email);

      return quantity * theBook.getPrice();
    }

    // now we are guarentee that the book is of type PaperBook
    PaperBook tempBook = (PaperBook)theBook;
    
    if (quantity > tempBook.getNumberInStock()) {
      throw new OutOfStockException("The quantity you order is out of the stock");
    }
    
    tempBook.sendToShippingService(address);
    tempBook.decreaseStock(quantity);

    return quantity * theBook.getPrice();
  }

  private Book getBookByISBN(String isbn) throws BookNotFoundException {
    Book theBook = null;

    for(Book book: inventory) {
      if (book.getISBN().equals(isbn)) {
        theBook = book;
        break;
      }
    }

    if (theBook == null) {
      throw new BookNotFoundException(String.format("Cannot found a book with isbn: %s", isbn));
    } else {
      return theBook;
    }
  }
}

package vod.service;

import vod.model.Bookstore;
import vod.model.Book;

import java.util.List;

public interface BookstoreService {
//api zwraca nam wszystkie kina
    Bookstore getBookstoresById(int id);

    List<Bookstore> getAllBookstores();

    List<Bookstore> getBookstoresByBook(Book b);

    List<Book> getBooksInBookstores(Bookstore b);

}

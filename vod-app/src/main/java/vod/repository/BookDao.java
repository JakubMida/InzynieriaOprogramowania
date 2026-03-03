package vod.repository;

import vod.model.Bookstore;
import vod.model.Author;
import vod.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(int id);

    List<Book> findByAuthor(Author a);

    List<Book> findByBookstore(Bookstore b);

    Book add(Book m);

}

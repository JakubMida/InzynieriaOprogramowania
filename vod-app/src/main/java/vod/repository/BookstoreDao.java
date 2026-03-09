package vod.repository;

import vod.model.Bookstore;
import vod.model.Book;

import java.util.List;

public interface BookstoreDao {

    List<Bookstore> findAll();

    Bookstore findById(int id);

    List<Bookstore> findByBook(Book b);

    Bookstore save(Bookstore bookstore);
}

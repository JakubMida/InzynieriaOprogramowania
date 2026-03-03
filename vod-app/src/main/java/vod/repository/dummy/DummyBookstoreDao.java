package vod.repository.dummy;

import org.springframework.stereotype.Component;
import vod.model.Book;
import vod.model.Bookstore;
import vod.repository.BookstoreDao;

import java.util.List;

@Component
public class DummyBookstoreDao implements BookstoreDao {
    @Override
    public List<Bookstore> findAll() {return List.of();}
    @Override
    public Bookstore findById(int id){return null;}
    @Override
    public List<Bookstore> findByBook(Book b){return List.of();}
}

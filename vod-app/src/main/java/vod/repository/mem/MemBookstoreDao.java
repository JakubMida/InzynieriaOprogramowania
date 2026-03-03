package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vod.repository.BookstoreDao;
import vod.model.Bookstore;
import vod.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MemBookstoreDao implements BookstoreDao {

    @Override
    public List<Bookstore> findAll() {
        return SampleData.bookstores;
    }

    @Override
    public Bookstore findById(int id) {
        return SampleData.bookstores.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Bookstore> findByBook(Book b) {
        return SampleData.bookstores.stream().filter(c -> c.getBooks().contains(b)).collect(Collectors.toList());
    }
}

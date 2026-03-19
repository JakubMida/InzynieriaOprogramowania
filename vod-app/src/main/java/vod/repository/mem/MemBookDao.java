package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.repository.BookDao;
import vod.model.Bookstore;
import vod.model.Author;
import vod.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Component("BookDao")
public class MemBookDao implements BookDao {
    @Override
    public List<Book> findAll() {
        return SampleData.books;
    }

    @Override
    public Book findById(int id) {
        return SampleData.books.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Book> findByAuthor(Author a) {
       return SampleData.books.stream().filter(m -> m.getAuthor() == a).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByBookstore(Bookstore b) {
        return SampleData.books.stream().filter(m -> m.getBookstores().contains(b)).collect(Collectors.toList());
    }

    @Override
    public Book add(Book b) {
        int max = SampleData.books.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        b.setId(++max);
        SampleData.books.add(b);
        return b;
    }
}

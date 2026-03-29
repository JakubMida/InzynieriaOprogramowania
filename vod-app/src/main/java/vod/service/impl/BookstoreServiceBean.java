package vod.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vod.model.Bookstore;
import vod.model.Book;
import vod.repository.BookstoreDao;
import vod.repository.BookDao;
import vod.service.BookstoreService;

import java.util.List;
import java.util.logging.Logger;

@Service
@Scope("prototype")
public class BookstoreServiceBean implements BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());

    private BookstoreDao bookstoreDao;
    private BookDao bookDao;

    public BookstoreServiceBean(@Qualifier("jpaBookstoreDao") BookstoreDao bookstoreDao, BookDao bookDao) {
        log.info("creating bookstore service bean");
        this.bookstoreDao = bookstoreDao;
        this.bookDao = bookDao;
    }

    @Override
    public Bookstore getBookstoresById(int id) {
        log.info("searching book by id " + id);
        return bookstoreDao.findById(id);
    }

    @Override
    public List<Book> getBooksInBookstores(Bookstore b) {
        log.info("searching books in bookstore " + b.getId());
        return bookDao.findByBookstore(b);
    }

    @Override
    public List<Bookstore> getAllBookstores() {
        log.info("searching all bookstores");
        return bookstoreDao.findAll();
    }

    @Override
    public List<Bookstore> getBookstoresByBook(Book b) {
        log.info("searching bookstores by books " + b.getId());
        return bookstoreDao.findByBook(b);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Bookstore addBookstore(Bookstore b) {
        log.info("adding bookstore " + b);
        return bookstoreDao.save(b);
    }
}

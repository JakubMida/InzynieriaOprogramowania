package vod.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vod.repository.BookstoreDao;
import vod.repository.AuthorDao;
import vod.repository.BookDao;
import vod.model.Bookstore;
import vod.model.Author;
import vod.model.Book;
import vod.service.BookService;

import java.util.List;
import java.util.logging.Logger;

@Component
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private AuthorDao authorDao;
    private BookstoreDao bookstoreDao;
    private BookDao bookDao;

    public BookServiceBean(AuthorDao authorDao, BookstoreDao bookstoreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookstoreDao = bookstoreDao;
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        log.info("searching all books...");
        return bookDao.findAll();
    }

    public List<Book> getBooksByAuthor(Author a) {
        log.info("serching books by author " + a.getId());
        return bookDao.findByAuthor(a);
    }

    public List<Book> getBooksInBookstores(Bookstore c) {
        log.info("searching books in bookstore " + c.getId());
        return bookDao.findByBookstore(c);
    }

    public Book getBookById(int id) {
        log.info("searching book by id " + id);
        return bookDao.findById(id);
    }

    public List<Bookstore> getAllBookstores() {
        log.info("searching all bookstores");
        return bookstoreDao.findAll();
    }

    public List<Bookstore> getCinemasByMovie(Book m) {
        log.info("searching bookstores by book " + m.getId());
        return bookstoreDao.findByBook(m);
    }

    public Bookstore getBookstoreById(int id) {
        log.info("searching bookstores by id " + id);
        return bookstoreDao.findById(id);
    }

    public List<Author> getAllAuthors() {
        log.info("searching all authors");
        return authorDao.findAll();
    }

    public Author getAuthorById(int id) {
        log.info("searching author by id " + id);
        return authorDao.findById(id);
    }

    @Override
    public Book addBook(Book b) {
        log.info("about to add book " + b);
        return bookDao.add(b);
    }

    @Override
    public Author addAuthor(Author a) {
        log.info("about to add author " + a);
        return authorDao.add(a);
    }

}

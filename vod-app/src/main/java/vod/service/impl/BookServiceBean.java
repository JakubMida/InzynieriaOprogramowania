package vod.service.impl;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vod.repository.BookstoreDao;
import vod.repository.AuthorDao;
import vod.repository.BookDao;
import vod.model.Bookstore;
import vod.model.Author;
import vod.model.Book;
import vod.service.BookService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private final AuthorDao authorDao;
    @Qualifier("jpaBookstoreDao")
    private final BookstoreDao bookstoreDao;
    private final BookDao bookDao;
    private final PlatformTransactionManager transactionManager;

    public BookServiceBean(
            AuthorDao authorDao,
            @Qualifier("jpaBookstoreDao") BookstoreDao bookstoreDao,
            BookDao bookDao,
            PlatformTransactionManager transactionManager
    ) {
        this.authorDao = authorDao;
        this.bookstoreDao = bookstoreDao;
        this.bookDao = bookDao;
        this.transactionManager = transactionManager;
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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Book addBook(Book b) {
        log.info("about to add book " + b);
        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            b = bookDao.add(b);
            if(b.getTitle().equals("Apocalypse Now")){
                throw new RuntimeException("not yet!");
            }
            transactionManager.commit(ts);
        }catch (RuntimeException e){
            transactionManager.rollback(ts);
            throw e;
        }

        return b;
    }

    @Override
    public Author addAuthor(Author a) {
        log.info("about to add author " + a);
        return authorDao.add(a);
    }

}

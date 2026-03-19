package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.model.Book;
import vod.model.Bookstore;
import vod.repository.BookDao;
import vod.repository.BookstoreDao;

import java.util.List;

@Repository
@Primary
public class JpaBookDao implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll(){
        return entityManager.createQuery(
                "select b from Book b").getResultList();
    }


    @Override
    public Book findById(int id) {return entityManager.find(Book.class, id);}

    @Override
    public List<Book> findByAuthor(Author a){
        return entityManager
                .createQuery("select b from Book b where b.author=:author")
                .setParameter("author", a)
                .getResultList();
    }

    @Override
    public List<Book> findByBookstore(Bookstore b){
        return entityManager.createQuery(
                "select b from Book b inner join b.bookstores bookstore where bookstore=:bookstore")
                .setParameter("bookstore", b)
                .getResultList();
    }

    @Override
    public Book add(Book b){
        entityManager.persist(b);
        return b;
    }
}

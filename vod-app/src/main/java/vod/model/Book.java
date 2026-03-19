package vod.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;//relacja do rezysera - kolejny obiekt danych w uproszczeniu założenie że jeden film ma 1 reżysera
    private float rating;//rating

    @ManyToMany
    @JoinTable(
            name = "book_bookstore",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bookstore_id", referencedColumnName = "id")
    )
    private List<Bookstore> bookstores = new ArrayList<>();

    private String poster;

    public Book(int id, String title, String poster, Author author, float rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public Book() {
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Bookstore> getBookstores() {
        return bookstores;
    }

    public void setBookstores(List<Bookstore> bookstores) {
        this.bookstores = bookstores;
    }

    public void addBookstore(Bookstore b) {
        this.bookstores.add(b);
    }


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.rating, rating) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return poster != null ? poster.equals(movie.poster) : movie.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", director=" + author +
                ", rating=" + rating +
                '}';
    }
}

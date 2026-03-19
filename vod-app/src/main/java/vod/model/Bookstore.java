package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.wildfly.common.annotation.NotNull;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bookstore")
public class Bookstore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max=20)
    private String name;

    @Column(name="logo")
    private String logo; //url logo w przypadku UI będzie zaciągany dynamicznie

    @ManyToMany(mappedBy = "bookstores")
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    public Bookstore(int id, String name, String logo) {//konsturktor
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Bookstore() {//bezparametrowy
    }
//settery, gettery i to String - później będziemy korzystać z wynalazku Lombok
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setMovies(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book b) {
        this.books.add(b);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}

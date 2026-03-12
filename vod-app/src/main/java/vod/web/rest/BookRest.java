package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Book;
import vod.model.Bookstore;
import vod.service.BookService;
import vod.service.BookstoreService;
import vod.web.rest.dto.BookDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BookRest {
    private final BookstoreService bookstoreService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;


    @GetMapping("/books")
    List<Book> getBooks(){
        log.info("about to receive books list");
        List<Book> books = bookService.getAllBooks();
        log.info("{} books found", books.size());
        return books;
    }

    @GetMapping("/books/{id}")
    ResponseEntity<Book> getBook(@PathVariable("id") int id){
        log.info("about to receive book {}", id);
        Book book = bookService.getBookById(id);
        log.info("book found: {}" ,book);
        if(book != null){
            return ResponseEntity.ok(book);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookstores/{bookstoreId}/books")
    ResponseEntity<List<Book>> getBooksInBookstore(@PathVariable("bookstoreId") int bookstoreId){
        log.info("about to retrieve books in bookstore {}", bookstoreId);
        Bookstore bookstore = bookstoreService.getBookstoresById(bookstoreId);
        if(bookstore == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            List<Book> books = bookstoreService.getBooksInBookstores(bookstore);
            log.info("there's {} books in bookstore", books.size(), bookstore.getName());
            return ResponseEntity.ok(books);
        }
    }

    @PostMapping("/books")
    ResponseEntity<?>addBook(@RequestBody BookDTO bookDTO){
        log.info("about to add new book {}", bookDTO);
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setRating(bookDTO.getRating());
        book.setAuthor(bookService.getAuthorById(bookDTO.getAuthorId()));

        book = bookService.addBook(book);
        log.info("book added: {}", book);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + book.getId())
                                .build()
                                .toUri())
                .body(book);


    }
}

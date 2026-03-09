package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vod.model.Book;
import vod.model.Bookstore;
import vod.service.BookService;
import vod.service.BookstoreService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BookstoreRest {
    private final BookstoreService bookstoreService;
    private final BookService bookService;

    @GetMapping("/bookstores")
    List<Bookstore> getBookstores(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @CookieValue(value = "some-cookie", required = false) String someCookie){
        log.info("about to receive bookstore list");
        log.info("phrase param: {}", phrase);
        log.info("custom-header param: {}", customHeader);
        log.info("some-cookie param: {}", someCookie);
        List<Bookstore> bookstores = bookstoreService.getAllBookstores();
        log.info("{} bookstores found", bookstores.size());
        return bookstores;
    }

    /*@GetMapping("/bookstores")
    List<Bookstore> getBookstores(){
        log.info("about to retrive boostores list");
        List<Bookstore> bookstores = bookstoreService.getAllBookstores();
        log.info("{} bookstores found", bookstores.size());
        return bookstores;
    }*/

    @GetMapping("/bookstores/{id}")
    ResponseEntity<Bookstore> getBoostore(@PathVariable("id") int id){
        log.info("about to retrieve bookstore {}", id);
        Bookstore bookstore = bookstoreService.getBookstoresById(id);
        log.info("bookstores found {}", bookstore);
        if(bookstore !=null){
            return ResponseEntity.status(200).body(bookstore);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bookstores/{bookId}/bookstores")
    ResponseEntity<List<Bookstore>> getBookstores(@PathVariable("bookId") int bookId){
        log.info("about to retrieve bookstore {}", bookId);
        Book book = bookService.getBookById(bookId);
        if(book ==null){
            return ResponseEntity.notFound().build();
        }else{
            List<Bookstore> bookstores = bookstoreService.getBookstoresByBook(book);
            log.info("{} bookstores found", bookstores.size(), book.getTitle());
            return ResponseEntity.ok(bookstores);
        }
    }

    @PostMapping("/bookstores")
    public ResponseEntity<Bookstore> addBookstore(@RequestBody Bookstore bookstore) {
        log.info("about to add bookstore {}", bookstore);

        bookstore = bookstoreService.addBookstore(bookstore);
        log.info("new bookstore added {}", bookstore);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookstore);
    }
}

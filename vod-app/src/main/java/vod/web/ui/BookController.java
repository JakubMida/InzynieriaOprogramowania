package vod.web.ui;


import org.springframework.ui.Model;
import vod.model.Author;
import vod.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Bookstore;
import vod.service.BookService;
import vod.service.BookstoreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookstoreService bookstoreService;
    private final BookService bookService;

    @GetMapping("/books")
    String getBooks(
            Model model,
            @RequestParam(value = "bookstoreId", required = false) Integer bookstoreId,
            @RequestParam(value="authorId", required = false) Integer authorId){
        log.info("about to display books list in bookstores {}", bookstoreId);
        if(bookstoreId != null){
            Bookstore bookstore = bookstoreService.getBookstoresById(bookstoreId);
            List<Book> books = bookstoreService.getBooksInBookstores(bookstore);
            model.addAttribute("books", books);
            model.addAttribute("tittle", "Books in bookstores " + bookstore.getName() + " ");
        }else if(authorId != null){
            Author author = bookService.getAuthorById(authorId);
            List<Book> books = bookService.getBooksByAuthor(author);
            model.addAttribute("books", books);
            model.addAttribute("tittle", "Books written by " + author.getLastName()+  " ");
        }
        else{
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
            model.addAttribute("tittle", "Books");
        }

        return "booksView";
    }

}

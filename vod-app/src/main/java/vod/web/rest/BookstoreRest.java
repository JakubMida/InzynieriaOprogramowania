package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vod.model.Bookstore;
import vod.service.BookstoreService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookstoreRest {
    private final BookstoreService bookstoreService;

    @GetMapping("/bookstores")
    List<Bookstore> getBookstores(){
        log.info("about to retrive boostores list");
        List<Bookstore> bookstores = bookstoreService.getAllBookstores();
        log.info("{} bookstores found", bookstores.size());
        return bookstores;
    }
}

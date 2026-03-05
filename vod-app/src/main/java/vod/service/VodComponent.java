package vod.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vod.model.Bookstore;

import java.util.List;

@Component
@Slf4j
public class VodComponent {

    private final BookstoreService bookstoreService;

    public VodComponent(BookstoreService bookstoreService){this.bookstoreService = bookstoreService;}

    @PostConstruct
    void init(){
        List<Bookstore> bookstores = bookstoreService.getAllBookstores();
        log.info("{} bookstores found", bookstores.size());
        bookstores.forEach(bookstore -> log.info("{}", bookstore));
    }
}


package vod.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import vod.service.BookService;

public class VodInfoContributor implements InfoContributor {
    private final BookService bookService;

    @Override
    public void contribute(Info.Builder builder){
        builder.withDetail("books", bookService.getAllBooks().size());
    }
}

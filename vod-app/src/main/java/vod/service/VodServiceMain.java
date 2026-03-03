package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.repository.BookstoreDao;
import vod.repository.BookDao;
import vod.repository.mem.MemBookstoreDao;
import vod.repository.mem.MemBookDao;
import vod.model.Bookstore;
import vod.service.impl.BookstoreServiceBean;

import java.util.List;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find bookstores!");

        ApplicationContext context = new AnnotationConfigApplicationContext("vod");
        BookstoreService service = context.getBean(BookstoreService.class);

        BookstoreService service2 = context.getBean(BookstoreService.class);

        List<Bookstore> bookstores = service.getAllBookstores();
        System.out.println(bookstores.size() + " bookstores found:");
        bookstores.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: "+ foo);
    }
}

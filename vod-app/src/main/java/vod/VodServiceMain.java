package vod;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import vod.service.BookstoreService;

@SpringBootApplication
public class VodServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(VodServiceMain.class, args);
    }

    @Bean
    public CommandLineRunner runner(ApplicationContext context, BookstoreService service) {
        return args -> {
            System.out.println("--- LOGIKA PO STARTE ---");

            var bookstores = service.getAllBookstores();
            System.out.println(bookstores.size() + " bookstores found: ");
            bookstores.forEach(System.out::println);


            try {
                String foo = context.getBean(String.class);
                System.out.println("foo string: " + foo);
            } catch (Exception e) {
                System.out.println("Nie znaleziono beana typu String");
            }
        };
    }
}
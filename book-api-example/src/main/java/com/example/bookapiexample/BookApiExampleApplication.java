package com.example.bookapiexample;

import com.example.bookapiexample.domain.book.Book;
import com.example.bookapiexample.domain.book.BookRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class BookApiExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApiExampleApplication.class, args);
    }

}

@Component
class ApiInitDataRunner implements ApplicationRunner {

    private final BookRepository bookRepository;

    public ApiInitDataRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            books.add(
                    Book.builder()
                            .writer(String.format("writer %d", i))
                            .price(BigDecimal.valueOf(i))
                            .publisher(String.format("publisher %d", i))
                            .title(String.format("title %d", i))
                            .build()
            );
        }

        bookRepository.saveAll(books);

    }
}


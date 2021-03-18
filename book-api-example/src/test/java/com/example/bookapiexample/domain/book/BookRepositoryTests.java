package com.example.bookapiexample.domain.book;

import com.example.bookapiexample.domain.SpringTestSupport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;


class BookRepositoryTests extends SpringTestSupport {

//    private BookRepository bookRepository;

//    public BookRepositoryTests(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @Test
    void create() {

        // given
        String title = "나무";
        String writer = "베르베르";
        String publisher = "한빛미디어";
        int price = 1000;

        Book book = Book.builder()
                .title(title)
                .writer(writer)
                .publisher(publisher)
                .price(BigDecimal.valueOf(price))
                .build();

        // when
//        Book savedBook = bookRepository.save(book);

        Book savedBook = save(book);

        // then
        then(savedBook.getPrice()).isEqualTo(BigDecimal.valueOf(1000));
        then(savedBook.getTitle()).isEqualTo("베르베르");

    }
}
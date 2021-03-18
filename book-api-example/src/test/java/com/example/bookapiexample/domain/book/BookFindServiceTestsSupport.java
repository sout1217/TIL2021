package com.example.bookapiexample.domain.book;

import com.example.bookapiexample.domain.SpringTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;


class BookFindServiceTestsSupport extends SpringTestSupport {

    //    private final BookRepository bookRepository;
    private final BookFindService bookFindService;

    BookFindServiceTestsSupport(BookFindService bookFindService) {
//        this.bookRepository = bookRepository;
        this.bookFindService = bookFindService;
    }


    @Test
    void notExisted() {

        Long id = 0L;

        thenThrownBy(() -> {
            bookFindService.findById(id);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void findTest() {

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            books.add(Book.builder()
                    .title(String.valueOf(i))
                    .publisher(String.valueOf(i))
                    .price(BigDecimal.valueOf(i))
                    .writer(String.valueOf(i))
                    .build());
        }

        saveAll(books);

        PageRequest pageRequest = PageRequest.of(0, 5);


        // when
        Page<Book> page = bookFindService.findPageable(pageRequest);

        then(page.getTotalElements()).isEqualTo(10);
        then(page.getContent()).hasSize(5);

    }

}
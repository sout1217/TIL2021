package com.example.bookapiexample.web.api;

import com.example.bookapiexample.domain.book.Book;
import com.example.bookapiexample.domain.book.BookFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BookApi {

    private final BookFindService bookFindService;

    @GetMapping("books")
    public ResponseEntity<?> getBooks(@PageableDefault(
            sort = {"id"}, direction = Sort.Direction.DESC
    ) Pageable pageable) {

        Page<Book> result = bookFindService.findPageable(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Book book = bookFindService.findById(id);
        return ResponseEntity.ok(book);
    }
}

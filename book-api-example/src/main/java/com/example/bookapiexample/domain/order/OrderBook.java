package com.example.bookapiexample.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class OrderBook {

    @Column(name = "bookId", nullable = false)
    private Long bookId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    private OrderBook(Long bookId, String title, BigDecimal price) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
    }

    public static OrderBook create(long bookId, String title, BigDecimal price) {
        return new OrderBook(bookId, title, price);
    }
}

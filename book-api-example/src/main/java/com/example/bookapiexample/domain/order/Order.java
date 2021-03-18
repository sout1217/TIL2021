package com.example.bookapiexample.domain.order;

import com.example.bookapiexample.domain.AuditingEntity;
import com.example.bookapiexample.domain.book.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order extends AuditingEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "orders_id", nullable = false, updatable = false)

    )
    @BatchSize(size = 2)
    private final List<OrderBook> orderBooks = new ArrayList<>();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)),
            @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false))
    })
    private Orderer orderer;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Builder
    private Order(Orderer orderer, List<Book> books) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Book book : books) {
            totalPrice = totalPrice.add(book.getPrice());
            this.orderBooks.add(OrderBook.create(1L, book.getTitle(), book.getPrice()));
        }

        this.orderer = orderer;
        this.price = totalPrice;
    }
}

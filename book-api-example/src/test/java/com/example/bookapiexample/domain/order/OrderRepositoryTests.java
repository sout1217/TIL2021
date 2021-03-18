package com.example.bookapiexample.domain.order;

import com.example.bookapiexample.domain.SpringTestSupport;
import com.example.bookapiexample.domain.book.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class OrderRepositoryTests extends SpringTestSupport {

    private final OrderRepository orderRepository;

    public OrderRepositoryTests(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Test
    void create() {


        Orderer orderer = Orderer.builder()
                .name("root")
                .email("root@gmail.com")
                .build();

        List<Book> books = Arrays.asList(
                Book.builder()
                        .title("나무")
                        .publisher("베르베르")
                        .price(BigDecimal.valueOf(10000))
                        .build(),
                Book.builder()
                        .title("자바")
                        .publisher("로버트")
                        .price(BigDecimal.valueOf(15000))
                        .build()
        );

        Order order = Order.builder()
                .orderer(orderer)
                .books(books)
                .build();

        save(order);

        Order savedOrder = orderRepository.save(order);


        then(savedOrder.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(25000));
        then(savedOrder.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(10));

        // BigDecimal 용 ComparingTo
//        then(order.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(25000));
//        then(order.getOrderBooks()).hasSizeGreaterThanOrEqualTo(1); // 1이상
//        then(order.getOrderBooks()).hasSize(2); // 2
//        then(order.getOrderBooks()).anySatisfy(orderBook -> then(orderBook.getPrice()).isGreaterThanOrEqualTo(BigDecimal.valueOf(10000)));
    }


}
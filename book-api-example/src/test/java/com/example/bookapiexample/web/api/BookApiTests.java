package com.example.bookapiexample.web.api;

import com.example.bookapiexample.domain.book.Book;
import com.example.bookapiexample.domain.book.QBook;
import com.example.bookapiexample.web.SpringWebTestSupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookApiTests extends SpringWebTestSupport {

    private final String URL = "/api/v1/books";


    @Test
    @DisplayName("페이징 조회")
    void pageFind() throws Exception {

        String title = "나무";
        String publisher = "한빛 미디어";
        BigDecimal price = BigDecimal.valueOf(10000.00);
        String writer = "베르베르";

        // given
        Book book = save(
                Book.builder()
                        .title(title)
                        .publisher(publisher)
                        .price(price)
                        .writer(writer)
                        .build()
        );

        // when
        ResultActions actions = mvc.perform(get(URL + "/" + book.getId()));

        // 코틀린에서는 DSL 2.* 부터 DSL 방식을 지원한다
        // 때문에 무슨 메소드가 있는 지 사용자는 . 을 찍어서 바로 확인이 가능하다

        // then
        actions
                .andDo(write)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.publisher").value(book.getPublisher()))
                .andExpect(jsonPath("$.price").value(book.getPrice()))
                .andExpect(jsonPath("$.writer").value(book.getWriter()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
        ;

        // DB 에 실제 들어가는 지 확인하는 것은 중요하지만
        // 이미 레이어에서 많은 테스트 확인을 했기 때문에 어느정도 검증이 되었다
        // 그래도 굳이 확인하기 위해서는 해당 Entity 에 Repository 를 주입받아 findById 를 날리면 된다

        // 하지만 현업에서는 여러 테이블을 조회 한 비지니스 로직용 result 가 나오기 때문에
        // 단순 findById 처럼 간단한 로직으로만 확인이 불가능하다

        // 예를들어 DB 에서 조회 후 자바단에서 name 부분만 stream 으로 추출해서 map 으로 만들고
        // 또 다른 곳에서 어떠한 특정한 부분만을 추출해서 새로운 result 를 만들어 낸다면
        // 해당 test를 검증하기 위한 repository 에 새로운 메소드를 추가해야 하는 비효율적인 일이 발생한다

        // 때문에 꼭 확인이 필요한 경우에는 queryDsl 이용하여 복잡한 쿼리문을 작성하는 것이 좋다


        // QueryDSL 은 Maven 에 dependency 와 Plugin 을 하고난 후
        // clean install 하고 나면
        // JPA 에 Entity 클래스를 기준으로 QEntity 하고 클래스가 만들어진다
        qdsl = new JPAQueryFactory(entityManager);

        Book qBook = qdsl.selectFrom(QBook.book)
                .where(QBook.book.id.eq(book.getId()))
                .fetchFirst();

        then(qBook.getTitle()).isEqualTo("나무");

    }

}
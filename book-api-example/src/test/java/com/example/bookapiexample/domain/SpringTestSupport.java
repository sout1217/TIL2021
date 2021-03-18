package com.example.bookapiexample.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional // rollback 처를 해줌
public class SpringTestSupport {

    protected JPAQueryFactory qdsl;


    @Autowired
    protected EntityManager entityManager;
    // 생성자로 주입하면 생성자에 계속 써야하는 중복문제가 발생하기 때문에
    // 필드 기반 @Autowired 를 사용한다


    private void flushAndClear() {
        entityManager.flush(); // DB 영속화
        entityManager.clear(); // context 데이터 초기화
    }

    protected <T> T save(T entity) {
        entityManager.persist(entity);
        flushAndClear();
        return entity;
    }

    protected <T> List<T> saveAll(List<T> entities) {

        for (T entity : entities) {
            entityManager.persist(entity);
        }
        flushAndClear();
        return entities;
    }

    // JPA 에서는 findBy는 영속성 컨텍스트 먼저 들러서 확인하지만 (PK 기반)
    // 그 외는 전부 DB 먼저 들른 후 영속성 컨텍스트에서 관리하게 된다

}

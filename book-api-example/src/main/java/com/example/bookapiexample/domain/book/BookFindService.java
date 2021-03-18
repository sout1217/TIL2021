package com.example.bookapiexample.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookFindService {

    private final BookRepository bookRepository;

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("error"));
    }

    public Page<Book> findPageable(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public List<Book> findByIds(List<Long> ids) {
        return bookRepository.findByIdIn(ids);
    }

    /*
     * Service 레이어로 나누는 이유
     * Repository 에서는 저장하는 역할만 담당하기 때문에
     * 어떠한 비지니스 서비스를 거치지 않고 바로 저장되기 때문에
     * 자칫하다간 비지니스 로직을 간과하는 경우가 생길 수 있다
     * 또한 비지니스 로직을 짜다보면 여러 테이블에 저장해야 되는 경우가 있는 데
     * 그 때마다 레파지토리를 계속 주입받아 작성하면 중복코드가 작성될 우려가 있다
     *
     * 서비스 레이어로 관리한다면 특정 여러 저장에 관한 로직을 한 틀로 묶어서 관리해서 사용 할 수 있으며,
     * 해당 서비스 레이어 1개만 주입받아 관리하여 재사용성과 가독성에 좋다
     *
     * */

}

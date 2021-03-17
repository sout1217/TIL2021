package com.example.jpa_relation.domain.dept;

import com.example.jpa_relation.domain.emp.Emp;
import com.example.jpa_relation.domain.emp.EmpRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DeptRepositoryTests {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private EmpRepository empRepository;

    @Test
    void findByAll() {
        deptRepository.findAll().forEach(System.out::println);
    }

    @Test
    void findById() {
        Dept dept = deptRepository.findById(10).get();
        System.out.println(dept);
    }

    @Test
    @DisplayName("부서번호가 10번인 사원")
    void emp() {
        Dept dept = Dept.builder()
                .deptNo(10)
                .build();

        List<Emp> emps = empRepository.findByDeptNo(dept);

        emps.forEach(System.out::println);
    }
}
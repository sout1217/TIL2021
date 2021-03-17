package com.example.jpa_relation.domain.emp;

import com.example.jpa_relation.domain.dept.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
    List<Emp> findByDeptNo(Dept dept);
}
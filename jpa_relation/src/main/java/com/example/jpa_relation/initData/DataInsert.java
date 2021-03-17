package com.example.jpa_relation.initData;

import com.example.jpa_relation.domain.dept.Dept;
import com.example.jpa_relation.domain.dept.DeptRepository;
import com.example.jpa_relation.domain.emp.Emp;
import com.example.jpa_relation.domain.emp.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInsert implements ApplicationRunner {

    private static final String PRESIDENT = "PRESIDENT";
    private static final String MANAGER = "MANAGER";
    private static final String SALESMAN = "SALESMAN";
    private static final String CLERK = "CLERK";
    private static final String ANALYST = "ANALYST";
    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Dept accounting = Dept.builder()
                .deptNo(10)
                .dName("ACCOUNTING")
                .loc("NEW YORK")
                .build();
        Dept research = Dept.builder()
                .deptNo(20)
                .dName("RESEARCH")
                .loc("DALLAS")
                .build();
        Dept sales = Dept.builder()
                .deptNo(30)
                .dName("SALES")
                .loc("CHICAGO")
                .build();
        Dept operations = Dept.builder()
                .deptNo(40)
                .dName("OPERATIONS")
                .loc("BOSTON")
                .build();

        deptRepository.save(accounting);
        deptRepository.save(research);
        deptRepository.save(sales);
        deptRepository.save(operations);


        Emp king = createEmp(7839, "KING", PRESIDENT, null, 81, 11, 17, 5000, null, accounting);
        Emp black = createEmp(7698, "BLACK", MANAGER, 7839, 81, 5, 1, 2850, null, sales);
        Emp clark = createEmp(7782, "CLARK", MANAGER, 7839, 81, 5, 9, 2450, null, accounting);
        Emp jones = createEmp(7566, "JONES", MANAGER, 7839, 81, 4, 1, 2975, null, research);
        Emp martin = createEmp(7654, "MARTIN", SALESMAN, 7698, 81, 9, 10, 1250, 1400, sales);
        Emp allen = createEmp(7499, "ALLEN", SALESMAN, 7698, 81, 2, 11, 1600, 300, sales);
        Emp turner = createEmp(7844, "TURNER", SALESMAN, 7698, 81, 8, 21, 1500, 0, sales);
        Emp james = createEmp(7900, "JAMES", CLERK, 7698, 81, 12, 11, 950, null, sales);
        Emp ward = createEmp(7521, "WARD", SALESMAN, 7698, 81, 2, 23, 1250, 500, sales);
        Emp ford = createEmp(7902, "FORD", ANALYST, 7566, 81, 12, 11, 3000, null, research);
        Emp smith = createEmp(7369, "SMITH", CLERK, 7902, 80, 12, 9, 800, null, research);
        Emp scott = createEmp(7788, "SCOTT", ANALYST, 7566, 82, 12, 22, 3000, null, research);
        Emp adams = createEmp(7876, "ADAMS", CLERK, 7788, 83, 1, 15, 1100, null, research);
        Emp miller = createEmp(7934, "MILLER", CLERK, 7782, 82, 1, 11, 1300, null, accounting);

        List<Emp> emps = Arrays.asList(king, black, clark, jones, martin, allen, turner, james, ward, ford, smith, scott, adams, miller);

        empRepository.saveAll(emps);

    }

    private Emp createEmp(int empNo, String eName, String job, Integer mgr, int year, int month, int day, Integer sal, Integer comm, Dept deptNo) {

        return Emp.builder()
                .empNo(empNo)
                .eName(eName)
                .job(job)
                .mgr(mgr)
                .hiredate(LocalDate.of(year, month, day))
                .sal(sal)
                .comm(comm)
                .deptNo(deptNo)
                .build();
    }
}

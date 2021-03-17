package com.example.jpa_relation.domain.emp;

import com.example.jpa_relation.domain.dept.Dept;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "emp")
@ToString
public class Emp {

    @Column(name = "e_name", length = 10)
    public String eName;
    @Column(name = "job", length = 9)
    public String job;
    @Column(name = "mgr", length = 4)
    public Integer mgr;
    @Column(name = "hiredate")
    public LocalDate hiredate;
    @Column(name = "sal", length = 7)
    public Integer sal;
    @Column(name = "comm", length = 7)
    public Integer comm;
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    public Dept deptNo;
    @Id
    @Column(name = "emp_no", nullable = false)
    private Integer empNo;

}

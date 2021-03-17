package com.example.jpa_relation.domain.dept;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dept")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Dept {

    @Id
    @Column(name = "dept_no")
    private Integer deptNo;

    @Column(name = "d_name", length = 14)
    private String dName;

    @Column(name = "loc", length = 13)
    private String loc;
}
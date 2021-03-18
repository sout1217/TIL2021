package com.example.bookapiexample.domain.book;

import com.example.bookapiexample.domain.AuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Builder
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book extends AuditingEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "writer", nullable = false)
    private String writer;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
}

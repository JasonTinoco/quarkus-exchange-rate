package dev.jason.infrastructure.adapters.out.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_quotes")
public class QuotesEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String document;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Integer quotes;
    private Double exchangeRate;
    private Double buy;
    private Double sale;
    @Column(nullable = false)
    private LocalTime recordTime;
    @Column(nullable = false)
    private LocalTime updateTime;

    @PrePersist
    public void prePersist() {
        recordTime = LocalTime.now();
        updateTime = LocalTime.now();
    }
}


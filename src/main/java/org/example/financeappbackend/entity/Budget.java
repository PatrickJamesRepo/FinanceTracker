package org.example.financeappbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Month;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "category")
@Entity
@Table(name = "budgets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"category_id", "month"})
})
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Month month;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}

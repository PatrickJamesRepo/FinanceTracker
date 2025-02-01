package org.example.financeappbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "category")
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate date;

    private String description;

    private Boolean isRecurring = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection
    @CollectionTable(name = "transaction_tags", joinColumns = @JoinColumn(name = "transaction_id"))
    @Column(name = "tag")
    private List<String> tags;
}

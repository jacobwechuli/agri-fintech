package com.sgrifintech.loan_management_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"loan"})
@EqualsAndHashCode(callSuper = true, of = {"id"})
@Table(name = "repayments")
public class Repayment extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(precision = 15, scale = 2)
    private BigDecimal principalComponent;

    @Column(precision = 15, scale = 2)
    private BigDecimal interestComponent;

    @Column(length = 100)
    private String paymentMethod;

    private String transactionReference;
}

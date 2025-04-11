package com.sgrifintech.loan_management_service.model.entity;

import com.sgrifintech.loan_management_service.model.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"borrower", "loanApplication", "repayments"})
@EqualsAndHashCode(callSuper = true, of = {"id"})
@Table(name = "loans")
public class Loan extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private User borrower;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal principalAmount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;
    @Column(nullable = false)
    private Integer termMonths;

    private LocalDate disbursementDate;
    private LocalDate firstPaymentDate;
    private LocalDate finalPaymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Column(precision = 15, scale = 2)
    private BigDecimal outstandingBalance;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Repayment> repayments = new ArrayList<>();

    public void addRepayment(Repayment repayment) {
        repayments.add(repayment);
        repayment.setLoan(this);
    }
}

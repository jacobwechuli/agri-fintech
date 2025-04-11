package com.sgrifintech.loan_management_service.model.entity;

import com.sgrifintech.loan_management_service.model.enums.LoanApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "loan"})
@EqualsAndHashCode(callSuper = true, of = {"id"})
@Table(name = "loan_applications")
public class LoanApplication extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 500)
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;

    @Column(precision = 15, scale = 2)
    private BigDecimal requestedAmount;

    private String adminRemarks;

    @OneToOne(mappedBy = "loanApplication",orphanRemoval = true ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Loan loan;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = LoanApplicationStatus.SUBMITTED;
        }
    }
}

package com.sgrifintech.loan_management_service.model.entity;

import com.sgrifintech.loan_management_service.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"loanApplications", "loans"})
@EqualsAndHashCode(callSuper = false, of = {"email"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 20)
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LoanApplication> loanApplications = new ArrayList<>();

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();

    public void addLoanApplication(LoanApplication application) {
        loanApplications.add(application);
        application.setUser(this);
    }
    public void removeLoanApplication(LoanApplication application) {
        loanApplications.remove(application);
        application.setUser(null);
    }
    public void addLoan(Loan loan) {
        loans.add(loan);
        loan.setBorrower(this);
    }
    public void removeLoan(Loan loan) {
        loans.remove(loan);
        loan.setBorrower(null);
    }


}

package com.sgrifintech.loan_management_service.repository;

import com.sgrifintech.loan_management_service.model.entity.LoanApplication;
import com.sgrifintech.loan_management_service.model.enums.LoanApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByUserId(Long userId);
    List<LoanApplication> findByStatus(LoanApplicationStatus status);
}

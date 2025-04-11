package com.sgrifintech.loan_management_service.dto;

import com.sgrifintech.loan_management_service.model.enums.LoanApplicationStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class LoanApplicationDto {
    private Long id;
    private Long userId;
    private String userFullName;
    private BigDecimal requestedAmount;
    private String purpose;
    private LoanApplicationStatus status;
    private String adminRemarks;
    private Instant createdAt;
    private Instant updatedAt;
    private Long resultingLoanId;
}

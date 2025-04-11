package com.sgrifintech.loan_management_service.service;

import com.sgrifintech.loan_management_service.dto.LoanApplicationDto;
import com.sgrifintech.loan_management_service.dto.LoanApplicationRequest;
import com.sgrifintech.loan_management_service.model.entity.LoanApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationService {
    LoanApplicationDto submitApplication(Long userId, LoanApplicationRequest request);
    Optional<LoanApplicationDto> getLoanApplicationById(Long applicationId);
    List<LoanApplicationDto> getApplicationsByUserId(Long userId);
}

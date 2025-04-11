package com.sgrifintech.loan_management_service.service;

import com.sgrifintech.loan_management_service.dto.LoanApplicationDto;
import com.sgrifintech.loan_management_service.dto.LoanApplicationRequest;
import com.sgrifintech.loan_management_service.model.entity.LoanApplication;
import com.sgrifintech.loan_management_service.model.entity.User;
import com.sgrifintech.loan_management_service.model.enums.LoanApplicationStatus;
import com.sgrifintech.loan_management_service.repository.LoanApplicationRepository;
import com.sgrifintech.loan_management_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanApplicationImpl implements LoanApplicationService {
    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public LoanApplicationDto submitApplication(Long userId, LoanApplicationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        LoanApplication application = new LoanApplication();
        application.setUser(user);
        application.setRequestedAmount(request.getRequestedAmount());
        application.setPurpose(request.getPurpose());
        application.setStatus(LoanApplicationStatus.SUBMITTED);

        LoanApplication savedApplication = loanApplicationRepository.save(application);
        return mapApplicationToDto(savedApplication);

    }
    @Override
    @Transactional(readOnly = true)
    public Optional<LoanApplicationDto> getLoanApplicationById(Long applicationId) {
        return loanApplicationRepository.findById(applicationId).map(this::mapApplicationToDto);
    }
    @Override
    @Transactional(readOnly = true)
    public List<LoanApplicationDto> getApplicationsByUserId(Long userId) {

        return loanApplicationRepository.findByUserId(userId).stream().map(this::mapApplicationToDto).collect(Collectors.toList());
    }
    private LoanApplicationDto mapApplicationToDto(LoanApplication app) {
        LoanApplicationDto dto = new LoanApplicationDto();
        dto.setId(app.getId());
        dto.setRequestedAmount(app.getRequestedAmount());
        dto.setPurpose(app.getPurpose());
        dto.setStatus(app.getStatus());
        dto.setAdminRemarks(app.getAdminRemarks());
        dto.setCreatedAt(app.getCreatedAt());
        dto.setUpdatedAt(app.getUpdatedAt());

        if (app.getUser() != null) {
            dto.setUserId(app.getUser().getId());
            dto.setUserFullName(app.getUser().getFirstName() + " " + app.getUser().getLastName());
        }
        if (app.getLoan() != null) {
            dto.setResultingLoanId(app.getLoan().getId());
        }
        return dto;
    }
}

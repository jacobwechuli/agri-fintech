package com.sgrifintech.loan_management_service.controller;

import com.sgrifintech.loan_management_service.dto.LoanApplicationDto;
import com.sgrifintech.loan_management_service.dto.LoanApplicationRequest;
import com.sgrifintech.loan_management_service.model.entity.User;
import com.sgrifintech.loan_management_service.repository.UserRepository;
import com.sgrifintech.loan_management_service.service.LoanApplicationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<LoanApplicationDto> submitApplication(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody LoanApplicationRequest request) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new EntityNotFoundException("Authenticated user not found in database"));
            LoanApplicationDto createdApplication = loanApplicationService.submitApplication(user.getId(), request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdApplication);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    @GetMapping("/{applicationId}")
    public ResponseEntity<LoanApplicationDto> getApplicationById(@PathVariable Long applicationId, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        return loanApplicationService.getLoanApplicationById(applicationId).map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found with id: " + applicationId));
    }
    @GetMapping("/my")
    public ResponseEntity<LoanApplicationDto> getMyApplications(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new EntityNotFoundException("Authenticated user not found in database"));

            List<LoanApplicationDto> applications = loanApplicationService.getApplicationsByUserId(user.getId());
            return ResponseEntity.ok(applications.get(0));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}

//package com.sgrifintech.loan_management_service.controller.subsidy;
//
//import com.sgrifintech.loan_management_service.dto.SubsidyEligibilityRequest;
//import com.sgrifintech.loan_management_service.dto.SubsidyEligibilityResponse;
//import com.sgrifintech.loan_management_service.service.subsidy.SubsidyService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//@RestController
//@RequestMapping("/api/v1/subsidies")
//@RequiredArgsConstructor
//public class SubsidyController {
//    private final SubsidyService subsidyService;
//
//    //Option 1: Pass details in request body
//    @PostMapping("/check-eligibility")
//    public ResponseEntity<SubsidyEligibilityResponse> checkSubsidyEligibility(
//            //@Valid @RequestBody SubsidyEligibilityRequest request
//            @AuthenticationPrincipal UserDetails userDetails,
//            @RequestParam(required = false) String cropType,
//            @RequestParam(required = false) String county) {
//        if (userDetails == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
//
//        }
//    }
//    )
//
//}

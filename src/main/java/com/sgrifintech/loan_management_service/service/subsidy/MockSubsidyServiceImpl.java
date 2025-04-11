//package com.sgrifintech.loan_management_service.service.subsidy;
//
//import com.sgrifintech.loan_management_service.dto.SubsidyEligibilityRequest;
//import com.sgrifintech.loan_management_service.dto.SubsidyEligibilityResponse;
//import com.sgrifintech.loan_management_service.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class MockSubsidyServiceImpl implements SubsidyService {
//    private final UserRepository userRepository;
//
//    @Override
//    public SubsidyEligibilityResponse checkEligibility(SubsidyEligibilityRequest request) {
//        log.info("Checking mock subsidy eligibility for user ID: {}, Crop: {}, County: {}", request.getUserId(), request.getCropType(), request.getCounty());
//
//        String farmerCounty = request.getCounty() != null ? request.getCounty() : "Unknown";
//
//        List<SubsidyEligibilityResponse.EligibleScheme> eligibleSchemes = new ArrayList<>();
//        if ("Maize".equalsIgnoreCase(request.getCropType()) || "Wheat".equalsIgnoreCase(request.getCropType())) {
//            eligibleSchemes.add(SubsidyEligibilityResponse.EligibleScheme.builder()
//                    .schemeName("National Fertilizer Subsidy Program (Mock)")
//                    .description("Eligible for subsidized fertilizer for staple crops")
//                    .detailsUrl("https://www.kilimo.go.ke/mock-fertilizer-subsidy")
//            .build());
//
//        }
//        if ("Kiambu".equalsIgnoreCase(farmerCounty)) {
//            eligibleSchemes.add(SubsidyEligibilityResponse.EligibleScheme.builder()
//                    .schemeName("Kiambu County Dairy Support (Mock)")
//                    .description("Potential support for dairy farmers in Kiambu.")
//                    .detailsUrl("https://kiambu.go.ke/mock-dairy-grant")
//                    .build());
//        }
//        boolean overallEligibility = !eligibleSchemes.isEmpty();
//        log.info("Eligibility overall result: {}", overallEligibility);
//
//        return SubsidyEligibilityResponse.builder()
//                .overallEligibility(overallEligibility)
//                .eligibleSchemes(eligibleSchemes)
//                .build();
//    }
//}

package com.sgrifintech.loan_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubsidyEligibilityResponse {
    private boolean overallEligibility;
    private List<EligibleScheme> eligibleSchemes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EligibleScheme {
        private String schemeName;
        private String description;
        private String detailsUrl;
    }
}

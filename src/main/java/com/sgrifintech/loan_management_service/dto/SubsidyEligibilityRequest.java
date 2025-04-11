package com.sgrifintech.loan_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubsidyEligibilityRequest {
    private Long userId;
    private String cropType;
    private String county;
}

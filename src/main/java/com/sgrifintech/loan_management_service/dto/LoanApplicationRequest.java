package com.sgrifintech.loan_management_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanApplicationRequest {
    @NotNull(message = "Requested amount cannot be null")
    @DecimalMin(value = "0.01", message = "Requested amount must be positive")
    private BigDecimal requestedAmount;

    @NotBlank(message = "Purpose cannot be blank")
    @Size(max = 500)
    private String purpose;
}

package com.sgrifintech.loan_management_service.dto;

import com.sgrifintech.loan_management_service.model.enums.UserRole;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private Instant createdAt;
    private Instant updatedAt;

}

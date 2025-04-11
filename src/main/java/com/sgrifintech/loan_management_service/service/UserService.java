package com.sgrifintech.loan_management_service.service;

import com.sgrifintech.loan_management_service.dto.UserDto;
import com.sgrifintech.loan_management_service.dto.UserRegistrationRequest;

import java.util.Optional;

public interface UserService {
    UserDto registerFarmer(UserRegistrationRequest registrationRequest);
    Optional<UserDto> findById(Long id);
    Optional<UserDto> findByEmail(String email);
}

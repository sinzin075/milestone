package com.calendar.milestone.model.service;

import org.springframework.stereotype.Service;
import com.calendar.milestone.controller.dto.request.user.LoginRequest;
import com.calendar.milestone.controller.dto.response.common.ApiResponse;
import com.calendar.milestone.controller.dto.response.common.ApiStatus;
import com.calendar.milestone.controller.dto.response.user.LoginResponse;
import com.calendar.milestone.controller.dto.response.user.UserApiStatusResponse;
import com.calendar.milestone.model.config.JwtSigningKeyConfig;
import com.calendar.milestone.model.repository.UserRepository;
import com.calendar.milestone.model.utility.JwtUtility;
import com.calendar.milestone.model.value.Email;
import com.calendar.milestone.model.value.JwtPayload;
import com.calendar.milestone.model.value.RawPassword;
import com.calendar.milestone.model.value.UserId;

@Service
public class AuthService {
    private UserRepository userRepository;
    private JwtSigningKeyConfig key;

    public AuthService(final JwtSigningKeyConfig key, final UserRepository userRepository) {
        this.userRepository = userRepository;
        this.key = key;
    }

    public ApiResponse issueLoginToken(LoginRequest loginRequest) {
        final Email loginEmail = Email.of(loginRequest.getEmail());
        final RawPassword loginRawPassword = RawPassword.of(loginRequest.getPassword());
        if (!loginRawPassword.passwordMatch(userRepository.findPassword(loginEmail))) {
            ApiStatus badApiStatus = ApiStatus.BAD_REQUEST;
            return new UserApiStatusResponse(badApiStatus);
        }
        ApiStatus apiStatus = ApiStatus.OK;
        final UserId userId = userRepository.selectLoginUserId(loginEmail);
        final JwtPayload jwtPayload = JwtPayload.of(userId);
        final JwtUtility jwtUtility = JwtUtility.of(key, jwtPayload);
        ApiResponse loginResponse = new LoginResponse(userId.getValue(), jwtUtility.getToken(),
                apiStatus.getStatus(), apiStatus.getMessage());
        return loginResponse;
    }
}

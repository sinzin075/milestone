package com.calendar.milestone.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calendar.milestone.controller.dto.request.user.LoginRequest;
import com.calendar.milestone.controller.dto.response.common.ApiResponse;
import com.calendar.milestone.model.service.AuthService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ApiResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.issueLoginToken(loginRequest);
    }


}

package com.calendar.milestone.model.service;

import org.springframework.stereotype.Service;
import com.calendar.milestone.controller.dto.request.user.LoginRequest;
import com.calendar.milestone.controller.dto.request.user.UserPostRequest;
import com.calendar.milestone.controller.dto.response.user.LoginResponse;
import com.calendar.milestone.model.config.JwtSigningKeyConfig;
import com.calendar.milestone.model.repository.UserRepository;
import com.calendar.milestone.model.value.Email;
import com.calendar.milestone.model.value.JwtPayload;
import com.calendar.milestone.model.value.RawPassword;
import com.calendar.milestone.model.value.UserId;
import com.calendar.milestone.security.token.JwtToken;
import com.calendar.milestone.security.token.JwtTokenGenerator;

@Service
public class AuthService {
    private UserRepository userRepository;
    private JwtSigningKeyConfig key;

    public AuthService(final JwtSigningKeyConfig key, final UserRepository userRepository) {
        this.userRepository = userRepository;
        this.key = key;
    }

    /**
     * ログイントークン認証。認可のちログイントークン
     * @param loginRequest
     * @return JWTトークン
    */
    public LoginResponse issueLoginToken(LoginRequest loginRequest) throws IllegalArgumentException {
        final Email loginEmail = Email.of(loginRequest.getEmail());
        final RawPassword loginRawPassword = RawPassword.of(loginRequest.getPassword());
        if (!loginRawPassword.passwordMatch(userRepository.findPassword(loginEmail))) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        final UserId userId = userRepository.selectLoginUserId(loginEmail);
        final JwtPayload jwtPayload = JwtPayload.of(userId);
        final JwtToken jwtToken = JwtTokenGenerator.generateToken(key, jwtPayload);
        LoginResponse loginResponse = new LoginResponse(jwtToken.getToken());
        return loginResponse;
    }

    /**
     * ユーザ新規登録時用のログイン認証
     * @param user ユーザ新規登録の情報
     * @return JWTトークン
     * @throws IllegalArgumentException
     */
    public LoginResponse issueLoginToken(final UserPostRequest user) throws IllegalArgumentException{
        final Email loginEmail = Email.of(user.getEmail());
        final RawPassword loginRawPassword = RawPassword.of(user.getPassword());
        if (!loginRawPassword.passwordMatch(userRepository.findPassword(loginEmail))) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        final UserId userId = userRepository.selectLoginUserId(loginEmail);
        final JwtPayload jwtPayload = JwtPayload.of(userId);
        final JwtToken jwtToken = JwtTokenGenerator.generateToken(key, jwtPayload);
        LoginResponse loginResponse = new LoginResponse(jwtToken.getToken());
        return loginResponse;
    }
}

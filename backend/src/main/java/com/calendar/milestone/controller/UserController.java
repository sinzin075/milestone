package com.calendar.milestone.controller;

import com.calendar.milestone.controller.dto.request.user.UserEmailChangeRequest;
import com.calendar.milestone.controller.dto.request.user.UserPasswordChangeRequest;
import com.calendar.milestone.controller.dto.request.user.UserPostRequest;
import com.calendar.milestone.controller.dto.request.user.UserPutRequest;
import com.calendar.milestone.controller.dto.response.user.UserApiStatusResponse;
import com.calendar.milestone.controller.dto.response.user.UserResponse;
import com.calendar.milestone.infrastructure.MilestoneLogger;
import com.calendar.milestone.model.service.UserService;
import com.calendar.milestone.security.token.JwtUserIdExtractor;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtUserIdExtractor jwtUserIdExtractor;

    public UserController(UserService userService,JwtUserIdExtractor jwtUserIdExtractor) {
        this.userService = userService;
        this.jwtUserIdExtractor = jwtUserIdExtractor;
    }

    /**
     * ユーザ情報の新規登録
     * @param user ユーザ登録する情報
     * @return ユーザ登録した情報・ログイン用JWTトークン
    */
    @PostMapping
    public ApiResponse<UserPostResponse> insertUser(@RequestBody @Valid UserPostRequest user) {
        final UserPostResponse userPostResponse = userService.insert(user);
        final ApiResponse<UserPostResponse> apiResponse = 
            new ApiResponse<UserPostResponse>(userPostResponse, ApiStatus.CREATE);
        return apiResponse;
    }

    /**
     * ログインしているユーザのユーザ情報のみを取得
     * @param jwt
     * @return
     */
    @GetMapping
    public ApiResponse<UserResponse> select(@AuthenticationPrincipal Jwt jwt) {
        final UserResponse userResponse = userService.select(jwtUserIdExtractor.extract(jwt));
        final ApiResponse<UserResponse> apiResponse = new ApiResponse<UserResponse>(userResponse, ApiStatus.OK);
        return apiResponse;

    }

    /**
     * ユーザ情報のうち、認証に使用しないプロフィール情報を更新する。
     * 更新対象は name、photo、birthday とする。
     * @param jwt
     * @param user
     * @return
     */
    @PutMapping
    public int updateUser(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid UserPutRequest user) {
        user.setId(jwtUserIdExtractor.extract(jwt));
        return userService.update(user);
    }

    @PatchMapping("/email")
    public UserApiStatusResponse userEmailUpdate(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid UserEmailChangeRequest user) {
        MilestoneLogger.info("UserのEmail変更を開始");
        user.setId(jwtUserIdExtractor.extract(jwt));
        return userService.emailUpdate(user);
    }

    @PatchMapping("/password")
    public UserApiStatusResponse userPasswordUpdate(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid UserPasswordChangeRequest user) {
        user.setId(jwtUserIdExtractor.extract(jwt));
        return userService.passwordUpdate(user);
    }

    @DeleteMapping
    public int deleteUser(@AuthenticationPrincipal Jwt jwt) {
        return userService.delete(jwtUserIdExtractor.extract(jwt));
    }



}

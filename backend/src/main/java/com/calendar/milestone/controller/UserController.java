package com.calendar.milestone.controller;

import com.calendar.milestone.controller.dto.request.user.UserEmailChangeRequest;
import com.calendar.milestone.controller.dto.request.user.UserPasswordChangeRequest;
import com.calendar.milestone.controller.dto.request.user.UserPostRequest;
import com.calendar.milestone.controller.dto.request.user.UserPutRequest;
import com.calendar.milestone.controller.dto.response.user.UserApiStatusResponse;
import com.calendar.milestone.controller.dto.response.user.UserResponse;
import com.calendar.milestone.model.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public int insertUser(@RequestBody @Valid UserPostRequest user) {
        return userService.insert(user);
    }

    @GetMapping("/{id}")
    public UserResponse selectUser(@PathVariable int id) {
        return userService.select(id);
    }

    @PutMapping("/{id}")
    public int updateUser(@PathVariable int id, @RequestBody @Valid UserPutRequest user) {
        user.setId(id);
        return userService.update(user);
    }

    @PatchMapping("/{id}/email")
    public UserApiStatusResponse userEmailUpdate(@PathVariable int id,
            @RequestBody @Valid UserEmailChangeRequest user) {
        user.setId(id);
        return userService.emailUpdate(user);
    }

    @PatchMapping("/{id}/password")
    public UserApiStatusResponse userPasswordUpdate(@PathVariable int id,
            @RequestBody @Valid UserPasswordChangeRequest user) {
        user.setId(id);
        return userService.passwordUpdate(user);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }



}

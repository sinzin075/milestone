package com.calendar.milestone.controller;

import com.calendar.milestone.controller.dto.request.UserPutRequest;
import com.calendar.milestone.controller.dto.request.UserRequest;
import com.calendar.milestone.controller.dto.response.UserResponse;
import com.calendar.milestone.model.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public int insertUser(@RequestBody @Valid UserRequest user) {
        return userService.insert(user);
    }

    @GetMapping("/{id}")
    public UserResponse selectUser(@PathVariable int id) {
        return userService.select(id);
    }

    @PutMapping("/{id}")
    public int updateUser(@PathVariable int id, @RequestBody @Valid UserPutRequest user) {
        user.setId(id);
        System.out.println("User Controller");
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }



}

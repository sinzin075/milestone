package com.calendar.milestone.controller;


import com.calendar.milestone.controller.dto.request.goal.GoalRequest;
import com.calendar.milestone.controller.dto.response.goal.GoalResponse;
import com.calendar.milestone.model.service.GoalService;
import com.calendar.milestone.security.token.JwtUserIdExtractor;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;
    private final JwtUserIdExtractor jwtUserIdExtractor;
    public GoalController(GoalService goalService,JwtUserIdExtractor jwtUserIdExtractor) {
        this.goalService = goalService;
        this.jwtUserIdExtractor = jwtUserIdExtractor;
    }

    @PostMapping
    public int insert(@AuthenticationPrincipal Jwt jwt,@RequestBody @Valid GoalRequest goal) {
        goal.setUserId(jwtUserIdExtractor.extract(jwt));
        return goalService.insert(goal);
    }

    @GetMapping("/{id}")
    public GoalResponse select(@PathVariable int id) {
        return goalService.select(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable int id, @RequestBody @Valid GoalRequest goal) {
        goal.setId(id);
        return goalService.update(goal);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return goalService.delete(id);
    }

}

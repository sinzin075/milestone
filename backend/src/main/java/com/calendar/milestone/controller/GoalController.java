package com.calendar.milestone.controller;


import com.calendar.milestone.controller.dto.request.goal.GoalRequest;
import com.calendar.milestone.controller.dto.response.goal.GoalResponse;
import com.calendar.milestone.model.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public int insertGoal(@RequestBody @Valid GoalRequest goal) {
        // TODO: Set user_id from logged-in session when login feature is implemented
        return goalService.insert(goal);
    }

    @GetMapping("/{id}")
    public GoalResponse selectGoal(@PathVariable int id) {
        return goalService.select(id);
    }

    @PutMapping("/{id}")
    public int updateGoal(@PathVariable int id, @RequestBody @Valid GoalRequest goal) {
        goal.setId(id);
        // TODO: Set user_id from logged-in session when login feature is implemented
        return goalService.update(goal);
    }

    @DeleteMapping("/{id}")
    public int deleteGoal(@PathVariable int id) {
        return goalService.delete(id);
    }

}

package com.calendar.milestone.controller;


import com.calendar.milestone.controller.dto.request.milestone.MilestoneRequest;
import com.calendar.milestone.controller.dto.response.milestone.MilestoneResponse;
import com.calendar.milestone.model.service.MilestoneService;
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
@RequestMapping("/milestone")
public class MilestoneController {
    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping
    public int insertMilestone(@RequestBody @Valid MilestoneRequest milestone) {
        // TODO: Set user_id from logged-in session when login feature is implemented
        return milestoneService.insert(milestone);
    }

    @GetMapping("/{id}")
    public MilestoneResponse selectMilestone(@PathVariable int id) {
        return milestoneService.select(id);
    }

    @PutMapping("/{id}")
    public int updateMilestone(@PathVariable int id,
            @RequestBody @Valid MilestoneRequest milestone) {
        milestone.setId(id);
        // TODO: Set user_id from logged-in session when login feature is implemented
        return milestoneService.update(milestone);
    }

    @DeleteMapping("/{id}")
    public int deleteMilestone(@PathVariable int id) {
        return milestoneService.delete(id);
    }

}

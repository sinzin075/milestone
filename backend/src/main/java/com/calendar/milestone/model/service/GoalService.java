package com.calendar.milestone.model.service;

import com.calendar.milestone.controller.dto.request.goal.GoalRequest;
import com.calendar.milestone.controller.dto.response.goal.GoalResponse;
import com.calendar.milestone.model.entity.Goal;
import com.calendar.milestone.model.repository.GoalRepository;
import org.springframework.stereotype.Service;


@Service
public class GoalService {

    private GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public GoalResponse convertToGoalResponse(Goal goal) {
        GoalResponse goalResponse = new GoalResponse();
        goalResponse.setId(goal.getId());
        goalResponse.setUserId(goal.getUserId());
        goalResponse.setTitle(goal.getTitle());
        goalResponse.setContent(goal.getContent());
        goalResponse.setDate(goal.getDate());
        goalResponse.setCreatedAt(goal.getCreatedAt());
        if (goal.getUpdatedAt() != null) {
            goalResponse.setUpdatedAt(goal.getUpdatedAt());
        }
        if (goal.getDeletedAt() != null) {
            goalResponse.setDeletedAt(goal.getDeletedAt());
        }
        return goalResponse;
    }

    public Goal convertToGoalUpdate(GoalRequest goalRequest) {
        Goal goal = goalRepository.select(goalRequest.getId());
        if (goalRequest.getTitle() != null) {
            goal.setTitle(goalRequest.getTitle());
        }
        if (goalRequest.getContent() != null) {
            goal.setContent(goalRequest.getContent());
        }
        if (goalRequest.getDate() != null) {
            goal.setDate(goalRequest.getDate());
        }
        return goal;
    }

    public int insert(GoalRequest goalRequest) {
        Goal goal = new Goal();
        goal.setId(goalRequest.getId());
        goal.setUserId(goalRequest.getUserId());
        goal.setTitle(goalRequest.getTitle());
        goal.setContent(goalRequest.getContent());
        goal.setDate(goalRequest.getDate());
        return goalRepository.insert(goal);
    }

    public GoalResponse select(int id) {
        return convertToGoalResponse(goalRepository.select(id));
    }

    public int update(GoalRequest goal) {
        return goalRepository.update(convertToGoalUpdate(goal));
    }

    public int delete(int id) {
        return goalRepository.delete(id);
    }
}

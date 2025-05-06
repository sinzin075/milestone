package com.calendar.milestone.model.service;

import com.calendar.milestone.model.entity.Goal;
import com.calendar.milestone.model.repository.GoalRepository;
import org.springframework.stereotype.Service;


@Service
public class GoalService{

    private GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository){this.goalRepository=goalRepository;}

    public int insert(Goal goal){
        return goalRepository.insert(goal);
    }

    public Goal select(int id){
        return goalRepository.select(id);
    }

    public int update(Goal goal){
        Goal existGoal =goalRepository.select(goal.getId());
        if(goal.getTitle()!=null){existGoal.setTitle(goal.getTitle());}
        if(goal.getContent()!=null){existGoal.setContent(goal.getContent());}
        if(goal.getDate()!=null){existGoal.setDate(goal.getDate());}
        return goalRepository.update(existGoal);
    }

    public int delete(int id){
        return goalRepository.delete(id);
    }
}
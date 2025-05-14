package com.calendar.milestone.model.service;

import com.calendar.milestone.model.entity.Milestone;
import com.calendar.milestone.model.repository.MilestoneRepository;
import org.springframework.stereotype.Service;


@Service
public class MilestoneService{

    private MilestoneRepository milestoneRepository;

    public MilestoneService(MilestoneRepository milestoneRepository){this.milestoneRepository=milestoneRepository;}

    public int insert(Milestone milestone){
        return milestoneRepository.insert(milestone);
    }

    public Milestone select(int id){
        return milestoneRepository.select(id);
    }

    public int update(Milestone milestone){
        Milestone existMilestone =milestoneRepository.select(milestone.getId());
        if(milestone.getTitle()!=null){existMilestone.setTitle(milestone.getTitle());}
        if(milestone.getContent()!=null){existMilestone.setContent(milestone.getContent());}
        if(milestone.getDate()!=null){existMilestone.setDate(milestone.getDate());}
        return milestoneRepository.update(existMilestone);
    }

    public int delete(int id){
        return milestoneRepository.delete(id);
    }
}
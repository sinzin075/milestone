package com.calendar.milestone.model.service;

import com.calendar.milestone.controller.dto.request.milestone.MilestoneRequest;
import com.calendar.milestone.controller.dto.response.milestone.MilestoneResponse;
import com.calendar.milestone.model.entity.Milestone;
import com.calendar.milestone.model.repository.MilestoneRepository;
import org.springframework.stereotype.Service;


@Service
public class MilestoneService {

    private MilestoneRepository milestoneRepository;

    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    public Milestone convertToMilestoneUpdate(MilestoneRequest milestoneRequest) {
        Milestone milestone = milestoneRepository.select(milestoneRequest.getId());
        milestone.setGoalId(milestoneRequest.getGoalId());
        if (milestoneRequest.getTitle() != null) {
            milestone.setTitle(milestoneRequest.getTitle());
        }
        if (milestoneRequest.getContent() != null) {
            milestone.setContent(milestoneRequest.getContent());
        }
        if (milestoneRequest.getDate() != null) {
            milestone.setDate(milestoneRequest.getDate());
        }
        return milestone;
    }

    public MilestoneResponse convertToMilestoneResponse(Milestone milestone) {
        MilestoneResponse milestoneResponse = new MilestoneResponse();
        milestoneResponse.setId(milestone.getId());
        milestoneResponse.setGoalId(milestone.getGoalId());
        milestoneResponse.setTitle(milestone.getTitle());
        milestoneResponse.setContent(milestone.getContent());
        milestoneResponse.setDate(milestone.getDate());
        milestoneResponse.setCreatedAt(milestone.getCreatedAt());
        if (milestone.getUpdatedAt() != null) {
            milestoneResponse.setUpdatedAt(milestone.getUpdatedAt());
        }
        if (milestone.getDeletedAt() != null) {
            milestoneResponse.setDeletedAt(milestone.getDeletedAt());
        }
        return milestoneResponse;
    }

    public int insert(MilestoneRequest milestoneRequest) {
        Milestone milestone = new Milestone();
        milestone.setGoalId(milestoneRequest.getGoalId());
        milestone.setTitle(milestoneRequest.getTitle());
        milestone.setContent(milestoneRequest.getContent());
        milestone.setDate(milestoneRequest.getDate());
        return milestoneRepository.insert(milestone);
    }

    public MilestoneResponse select(int id) {
        return convertToMilestoneResponse(milestoneRepository.select(id));
    }

    public int update(MilestoneRequest milestone) {
        return milestoneRepository.update(convertToMilestoneUpdate(milestone));
    }

    public int delete(int id) {
        return milestoneRepository.delete(id);
    }
}

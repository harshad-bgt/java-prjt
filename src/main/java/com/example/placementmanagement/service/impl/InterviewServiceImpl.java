package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.request.InterviewRequestDto;
import com.example.placementmanagement.dto.request.InterviewResultUpdateDto;
import com.example.placementmanagement.dto.response.InterviewResponseDto;
import com.example.placementmanagement.entity.Application;
import com.example.placementmanagement.entity.Interview;
import com.example.placementmanagement.enums.ApplicationStatus;
import com.example.placementmanagement.enums.InterviewResult;
import com.example.placementmanagement.enums.InterviewStatus;
import com.example.placementmanagement.exception.ApplicationNotFoundException;
import com.example.placementmanagement.exception.BusinessRuleException;
import com.example.placementmanagement.exception.InterviewNotFoundException;
import com.example.placementmanagement.mapper.InterviewMapper;
import com.example.placementmanagement.repository.ApplicationRepository;
import com.example.placementmanagement.repository.InterviewRepository;
import com.example.placementmanagement.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public InterviewResponseDto scheduleInterview(InterviewRequestDto dto) {
        Application application = applicationRepository.findById(dto.getApplicationId())
            .orElseThrow(() -> new ApplicationNotFoundException(dto.getApplicationId()));

        if (interviewRepository.existsByApplicationIdAndRoundNumber(dto.getApplicationId(), dto.getRoundNumber())) {
            throw new BusinessRuleException("Round number " + dto.getRoundNumber() + " is already scheduled.");
        }

        Interview interview = new Interview();
        interview.setApplication(application);
        interview.setRoundNumber(dto.getRoundNumber());
        interview.setInterviewType(dto.getInterviewType());
        interview.setScheduledDateTime(dto.getScheduledDateTime());
        interview.setInterviewerName(dto.getInterviewerName());
        interview.setResult(InterviewResult.PENDING);
        interview.setStatus(InterviewStatus.SCHEDULED);

        Interview savedInterview = interviewRepository.save(interview);

        // Update application status
        application.setStatus(ApplicationStatus.INTERVIEW_SCHEDULED);
        applicationRepository.save(application);

        return InterviewMapper.toDto(savedInterview);
    }

    @Override
    public InterviewResponseDto updateInterviewResult(Long id, InterviewResultUpdateDto dto) {
        Interview interview = interviewRepository.findById(id)
            .orElseThrow(() -> new InterviewNotFoundException(id));

        interview.setResult(dto.getResult());
        if (dto.getFeedback() != null) {
            interview.setFeedback(dto.getFeedback());
        }
        interview.setStatus(dto.getStatus() != null ? dto.getStatus() : InterviewStatus.COMPLETED);

        Interview updatedInterview = interviewRepository.save(interview);

        // Update student application status based on interview outcome
        Application application = interview.getApplication();
        if (dto.getResult() == InterviewResult.PASSED) {
            application.setStatus(ApplicationStatus.SELECTED);
            applicationRepository.save(application);
        } else if (dto.getResult() == InterviewResult.FAILED) {
            application.setStatus(ApplicationStatus.NOT_SELECTED);
            applicationRepository.save(application);
        }

        return InterviewMapper.toDto(updatedInterview);
    }

    @Override
    public List<InterviewResponseDto> getInterviewsByApplication(Long applicationId) {
        return interviewRepository.findByApplicationIdOrderByRoundNumberAsc(applicationId)
            .stream()
            .map(InterviewMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<InterviewResponseDto> getAllInterviews() {
        return interviewRepository.findAll()
            .stream()
            .map(InterviewMapper::toDto)
            .collect(Collectors.toList());
    }
}

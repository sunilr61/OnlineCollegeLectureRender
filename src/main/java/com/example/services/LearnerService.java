package com.example.services;

import com.example.exceptions.InvalidLearnerException;
import com.example.models.ScheduledLecture;

import java.util.List;

public interface LearnerService {

    public List<ScheduledLecture> fetchTimeline(long learnerId) throws InvalidLearnerException;
}

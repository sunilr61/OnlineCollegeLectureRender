package com.example.scaler.services;

import com.example.scaler.exceptions.InvalidLearnerException;
import com.example.scaler.models.Batch;
import com.example.scaler.models.BatchLearner;
import com.example.scaler.models.Learner;
import com.example.scaler.models.ScheduledLecture;
import com.example.scaler.repositories.BatchLearnerRepository;
import com.example.scaler.repositories.LearnerRepository;
import com.example.scaler.repositories.ScheduledLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearnerServiceImpl implements LearnerService{

    private LearnerRepository  learnerRepository;

    private BatchLearnerRepository batchLearnerRepository;

    private ScheduledLectureRepository scheduledLectureRepository;

    @Autowired
    public LearnerServiceImpl(LearnerRepository learnerRepository,
                              BatchLearnerRepository batchLearnerRepository,
                              ScheduledLectureRepository scheduledLectureRepository){
        this.learnerRepository=learnerRepository;
        this.batchLearnerRepository=batchLearnerRepository;
        this.scheduledLectureRepository=scheduledLectureRepository;
    }


    @Override
    public List<ScheduledLecture> fetchTimeline(long learnerId) throws InvalidLearnerException {
        Learner learner = learnerRepository.findById(learnerId).orElseThrow(()-> new InvalidLearnerException("Invalid Learner Id"));
        List<BatchLearner> learnerBatches = batchLearnerRepository.findBatchLearnerByLearner(learner);
        List<ScheduledLecture> timeline = new ArrayList<>();
        for (BatchLearner learnerBatch : learnerBatches) {
            List<ScheduledLecture> scheduledLectures;
            if(learnerBatch.getExitDate() != null) {
                scheduledLectures = this.scheduledLectureRepository.findByBatchIdAndLectureStartTimeAfterAndLectureEndTimeBeforeOrderByLectureStartTime(learnerBatch.getBatch().getId(), learnerBatch.getEntryDate(), learnerBatch.getExitDate());
            }else{
                scheduledLectures = this.scheduledLectureRepository.findByBatchIdAndLectureStartTimeAfterOrderByLectureStartTime(learnerBatch.getBatch().getId(), learnerBatch.getEntryDate());
            }
            timeline.addAll(scheduledLectures);
        }

        return timeline;
    }
}

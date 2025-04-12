package com.example.services;

import com.example.exceptions.InvalidLearnerException;
import com.example.models.BatchLearner;
import com.example.models.Learner;
import com.example.models.ScheduledLecture;
import com.example.repositories.BatchLearnerRepository;
import com.example.repositories.LearnerRepository;
import com.example.repositories.ScheduledLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearnerServiceImpl implements LearnerService{

    private LearnerRepository learnerRepository;

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

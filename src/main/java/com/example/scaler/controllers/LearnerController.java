package com.example.scaler.controllers;

import com.example.scaler.dtos.FetchTimelineRequestDto;
import com.example.scaler.dtos.FetchTimelineResponseDto;
import com.example.scaler.dtos.ResponseStatus;
import com.example.scaler.models.ScheduledLecture;
import com.example.scaler.services.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LearnerController {
    private LearnerService learnerService;
    @Autowired
    public LearnerController(LearnerService learnerService){
        this.learnerService=learnerService;
    }
    public FetchTimelineResponseDto fetchTimeline(FetchTimelineRequestDto requestDto){
        FetchTimelineResponseDto fetchTimelineResponseDto = new FetchTimelineResponseDto();
        try{
            List<ScheduledLecture> scheduledLectureList = learnerService.fetchTimeline(requestDto.getLearnerId());
            fetchTimelineResponseDto.setLectures(scheduledLectureList);
            fetchTimelineResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception ex){
            fetchTimelineResponseDto.setLectures(null);
            fetchTimelineResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return null;
    }
}

package com.example.controllers;

import com.example.dtos.FetchTimelineRequestDto;
import com.example.dtos.FetchTimelineResponseDto;
import com.example.dtos.ResponseStatus;
import com.example.models.ScheduledLecture;
import com.example.services.LearnerService;
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

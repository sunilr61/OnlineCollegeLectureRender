package com.example.dtos;

import com.example.models.ScheduledLecture;
import lombok.Data;

import java.util.List;
@Data
public class FetchTimelineResponseDto {

    private List<ScheduledLecture> lectures;
    private ResponseStatus responseStatus;
}

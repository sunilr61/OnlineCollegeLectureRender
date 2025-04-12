package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class ScheduledLecture extends BaseModel{
    @ManyToOne
    private Lecture lecture;
    @ManyToOne
    private Batch batch;
    private Date lectureStartTime;
    private Date lectureEndTime;
    private String lectureLink;
}

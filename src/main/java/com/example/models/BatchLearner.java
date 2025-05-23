package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class BatchLearner extends BaseModel{
    @ManyToOne
    public Batch batch;
    @ManyToOne
    public Learner learner;
    public Date entryDate;
    public Date exitDate;
}

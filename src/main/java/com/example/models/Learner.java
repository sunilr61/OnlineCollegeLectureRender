package com.example.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Learner extends BaseModel{

    private String name;
    private String email;

}

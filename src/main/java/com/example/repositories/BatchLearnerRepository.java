package com.example.repositories;

import com.example.models.BatchLearner;
import com.example.models.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchLearnerRepository extends JpaRepository<BatchLearner, Long> {
    List<BatchLearner> findBatchLearnerByLearner(Learner learner);
}

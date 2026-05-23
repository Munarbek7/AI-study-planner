// 2. PlanRepository.java
package com.study.planner.repository;

import com.study.planner.model.StudyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanRepository extends JpaRepository<StudyPlan, Long> {
    List<StudyPlan> findByUserId(Long userId);
}
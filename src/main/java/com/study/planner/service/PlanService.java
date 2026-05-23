// 2. PlanService.java
package com.study.planner.service;

import com.study.planner.dto.PlanDto;
import com.study.planner.model.StudyPlan;
import com.study.planner.model.Task;
import com.study.planner.model.Topic;
import com.study.planner.model.User;
import com.study.planner.repository.PlanRepository;
import com.study.planner.repository.UserRepository;
import com.study.planner.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public StudyPlan createPlan(Long userId, PlanDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        StudyPlan plan = new StudyPlan();
        plan.setExamName(dto.getExamName());
        plan.setExamDate(dto.getExamDate());
        plan.setLevel(dto.getLevel());
        plan.setHoursPerDay(dto.getHoursPerDay());
        plan.setUser(user);

        for (String topicName : dto.getSelectedTopics()) {
            Topic topic = new Topic();
            topic.setShortName(topicName.split(" ")[0]);
            topic.setFullName(topicName);
            topic.setStudyPlan(plan);

            String[] defaultTasks = {"Learn " + topicName.toLowerCase(), "Solve practice problems", "Review mistakes"};
            for (String taskDesc : defaultTasks) {
                Task task = new Task();
                task.setDescription(taskDesc);
                task.setCompleted(false);
                task.setTopic(topic);
                topic.getTasks().add(task);
            }
            plan.getTopics().add(topic);
        }
        return planRepository.save(plan);
    }

    public List<StudyPlan> getPlansByUserId(Long userId) {
        return planRepository.findByUserId(userId);
    }
}
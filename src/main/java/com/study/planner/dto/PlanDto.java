// 2. PlanDto.java
package com.study.planner.dto;

import lombok.Data;
import java.util.List;

@Data
public class PlanDto {
    private String examName;
    private String examDate;
    private String level;
    private String hoursPerDay;
    private List<String> selectedTopics;
}
// 4. Task.java
package com.study.planner.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("tasks") // ИСПРАВЛЕНО
    private Topic topic;
}
package com.bside.server.module.task.domain;

import com.bside.server.module.routine.domain.Routine;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(name = "title")
    private String title;

    @Column(name = "expected_time")
    private Integer expectedTime;

    @Column(name = "created_date")
    private LocalDateTime createdDateTime;

}

package com.bside.server.module.routine.domain;

import com.bside.server.module.category.domain.Category;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "anchor")
    private String anchor;

    @Column(name = "total_time")
    private Integer totalTime;
}
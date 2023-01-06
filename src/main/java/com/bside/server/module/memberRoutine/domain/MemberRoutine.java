package com.bside.server.module.memberroutine.domain;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.routine.domain.Routine;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "member_routine")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MemberRoutine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_routine_id", nullable = false)
  private Integer memberRoutineId;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "routine_id")
  private Routine routine;

  @Column(name = "start_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime endDate;

  @Column(name = "color", nullable = false)
  private String color;

  @Column(name = "start_time")
  private String startTime;

  @Column(name = "anchor")
  private String anchor;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "is_deleted", nullable = false)
  private Integer isDeleted;

  @Column(name = "is_push", nullable = false)
  private Integer isPush;

  @Column(name = "total_time")
  private Integer totalTime;

  @Column(name = "created_date")
  @CreationTimestamp
  private LocalDateTime createdDate;
}

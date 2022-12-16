package com.bside.server.module.memberRoutine.domain;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.routine.domain.Routine;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoutine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member routine id", nullable = false)
  private Integer memberRoutineId;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "routine_id")
  private Routine routine;

  @JoinColumn(name = "start_date")
  private LocalDateTime startDate;

  @JoinColumn(name = "end_date")
  private LocalDateTime endDate;

  @JoinColumn(name = "start_time")
  private String startTime;

  @JoinColumn(name = "anchor")
  private String anchor;

  @JoinColumn(name = "total_time")
  private Integer totalTime;

  @Column(name = "created_date")
  @CreationTimestamp
  private LocalDateTime createdDateTime;
}

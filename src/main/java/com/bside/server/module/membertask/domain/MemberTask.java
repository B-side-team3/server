package com.bside.server.module.membertask.domain;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.task.domain.Task;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member_task")
public class MemberTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_task_id", nullable = false)
  private Integer memberTaskId;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private Task task;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "member_routine_id")
  private MemberRoutine memberRoutine;

  @Column(name = "actual_time", nullable = false)
  private Integer actualTime;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "is_deleted", nullable = false)
  private Integer isDeleted;

  @Column(name = "created_date")
  @CreationTimestamp
  private LocalDateTime createdDate;
}

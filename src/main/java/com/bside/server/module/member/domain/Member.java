package com.bside.server.module.member.domain;

import com.bside.server.global.auth.permission.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 임의로 생성한 user 테이블 entity.
 * todo 추후 정책에 따라 변경한다.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "is_admin", nullable = false)
    private Integer isAdmin;

    @Column(name = "join_date", nullable = false)
    @CreatedDate
    private LocalDateTime joinDate;

    @Column(name = "del_date")
    private LocalDateTime delDate;

    public String getRole() {
        if(this.getIsAdmin() == 0) {
            return Role.USER.getValue();
        }
        return Role.ADMIN.getValue();
    }
}

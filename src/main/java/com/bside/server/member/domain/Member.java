package com.bside.server.member.domain;

import com.bside.server.global.auth.permission.Role;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", nullable = false)
    private Integer memberId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_admin", nullable = false)
    private Integer isAdmin;

    @Column(name = "is_deleted", length = 1)
    private String isDeleted;

    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    public String getRole() {
        if(this.getIsAdmin() == 0) {
            return Role.USER.getValue();
        }
        return Role.ADMIN.getValue();
    }
}

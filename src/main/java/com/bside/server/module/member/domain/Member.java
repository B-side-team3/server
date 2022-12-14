package com.bside.server.module.member.domain;

import com.bside.server.global.auth.permission.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_admin", nullable = false)
    private Integer isAdmin;

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public String getRole() {
        if(this.getIsAdmin() == 0) {
            return Role.USER.getValue();
        }
        return Role.ADMIN.getValue();
    }
}

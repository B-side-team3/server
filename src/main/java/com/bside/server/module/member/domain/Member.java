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
@Setter
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

    @Column(name = "is_admin", columnDefinition = "TINYINT", nullable = false)
    private boolean isAdmin;

    @Column(name = "is_deleted", columnDefinition = "TINYINT", nullable = false)
    private boolean isDeleted;

    @Column(name = "is_notification", columnDefinition = "TINYINT", nullable = false)
    private boolean isNotification;

    @Column(name = "notification_token")
    private String notificationToken;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public String getRole() {
        if(this.isAdmin()) {
            return Role.ADMIN.getValue();
        }
        return Role.USER.getValue();
    }

    public void updateIsNotification(boolean isNotification) {
        this.isNotification = isNotification;
    }

    public void updateNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

}

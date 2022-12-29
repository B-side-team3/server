package com.bside.server.module.auth.repository;

import com.bside.server.module.auth.domain.Oauth;
import com.bside.server.module.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
    Optional<Oauth> findByMember(Member member);
}

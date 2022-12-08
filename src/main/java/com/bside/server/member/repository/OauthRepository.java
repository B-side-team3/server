package com.bside.server.member.repository;

import com.bside.server.member.domain.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
  Oauth findByAccessTokenAndIsDeleted(String accessToken, String isDeleted);
}

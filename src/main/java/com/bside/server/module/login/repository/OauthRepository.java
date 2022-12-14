package com.bside.server.module.login.repository;

import com.bside.server.module.login.domain.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
  Oauth findByAccessTokenAndIsDeleted(String accessToken, Integer isDeleted);
}

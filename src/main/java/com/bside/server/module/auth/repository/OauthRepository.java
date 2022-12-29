package com.bside.server.module.auth.repository;

import com.bside.server.module.auth.domain.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
}

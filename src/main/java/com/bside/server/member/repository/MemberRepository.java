package com.bside.server.global.common.repository;

import com.bside.server.global.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email);
}

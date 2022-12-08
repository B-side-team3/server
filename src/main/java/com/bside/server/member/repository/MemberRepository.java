package com.bside.server.member.repository;

import com.bside.server.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email);
}

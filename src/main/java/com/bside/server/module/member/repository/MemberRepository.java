package com.bside.server.module.member.repository;

import com.bside.server.module.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmailAndIsDeletedIs(String email, boolean isDeleted);
}

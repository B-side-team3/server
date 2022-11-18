package com.bside.server.global.auth.security;

import com.bside.server.global.auth.permission.Role;
import com.bside.server.module.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Getter
public class UserAdapter extends User {

    private Member member;

    public UserAdapter(Member member) {
        super(member.getEmail(), "", Set.of(new SimpleGrantedAuthority(member.getRole())));
        this.member = member;
    }
}

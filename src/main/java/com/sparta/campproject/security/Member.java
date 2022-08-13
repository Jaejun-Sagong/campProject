package com.sparta.campproject.security;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(Long id, String nickname, String password, Authority authority) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.authority = authority;
    }

    public enum Authority {
        ROLE_USER, ROLE_ADMIN
    }
}
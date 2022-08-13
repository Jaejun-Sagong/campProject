package com.sparta.campproject.security.dto;

import com.sparta.campproject.Timestamped;
import com.sparta.campproject.security.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto extends Timestamped {
    private Long id;
    private String nickname;

    public static MemberResponseDto of(Member member) {
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setNickname(member.getNickname());
        return memberResponseDto;
    }
}
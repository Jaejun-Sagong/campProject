package com.sparta.campproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CampDto {
    private String title;
    private String review;
    private String nickname;

}

package com.kusitms.finit.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayChallengeRes {
    private String title;
    private String intro;
    private String challenge_image;
}

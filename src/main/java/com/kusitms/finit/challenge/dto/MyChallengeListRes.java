package com.kusitms.finit.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyChallengeListRes {
    private String category;
    private int num;
    private List<ChallengeImgRes> challengeImgResList;
}

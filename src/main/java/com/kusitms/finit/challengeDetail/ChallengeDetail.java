package com.kusitms.finit.challengeDetail;


import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challenge.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ChallengeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_detail_id")
    private Long id;

    private String title;

    private String intro;

    private int goodCount;

    private String challengeImage;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

//    @ManyToOne (targetEntity = Challenge.class, fetch = FetchType.LAZY)
////    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn(name="challenge_id")
//    private Long challengeId;

//    public ChallengeDetail(Long)
}

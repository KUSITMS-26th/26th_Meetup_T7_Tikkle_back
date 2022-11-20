package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChallengeDetailRepository extends JpaRepository<ChallengeDetail, Long> {

    @Query(value = "select cd.title, cd.intro, cd.challenge_image " +
            "from challenge_detail cd, participation p " +
            "where cd.challenge_detail_id = p.challenge_detail_id" +
            "and p.account_id = :id", nativeQuery = true)
    TodayChallengeRes getTodayChallenge(@Param(value = "id") Long id);
}

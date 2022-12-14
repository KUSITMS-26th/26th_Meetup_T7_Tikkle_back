package com.kusitms.finit.participation;

import com.kusitms.finit.participation.dto.ParticipationRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query(value = "SELECT p.participation_id AS id, c.title " +
            "FROM participation p, challenge_detail c " +
            "WHERE p.challenge_detail_id = c.challenge_detail_id " +
            "AND p.account_id = :id " +
            "AND p.certificate = false", nativeQuery = true)
    List<ParticipationRes> findTitleByAccountIdAndCertificate(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE participation p SET p.certificate = true WHERE p.participation_id = :id", nativeQuery = true)
    void updateCertificate(@Param("id") Long id);
}

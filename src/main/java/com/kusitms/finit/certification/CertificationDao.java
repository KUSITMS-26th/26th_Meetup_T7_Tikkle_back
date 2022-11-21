package com.kusitms.finit.certification;

import com.kusitms.finit.certification.dto.GetCertificationListRes;
import com.kusitms.finit.certification.dto.GetCertificationRes;
import com.kusitms.finit.challenge.dto.ChallengeImgRes;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CertificationDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetCertificationRes selectCertification(long certificationId ) {
        String selectCertificationQuery = "select category, certification.title, account.nickname, certification_date, certification_image, content, likes\n" +
                "from account, certification, challenge_detail, challenge\n" +
                "where account.account_id = certification.account_id\n" +
                "  and certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "  and challenge_detail.challenge_id = challenge.challenge_id\n" +
                "and certification_id = ?; ";
        long selectCertificationParam = certificationId;

        return this.jdbcTemplate.queryForObject(selectCertificationQuery,
                (rs, rowNum) -> new GetCertificationRes(
                        rs.getString("category"),
                        rs.getString("title"),
                        rs.getString("nickname"),
                        rs.getDate("certification_date"),
                        rs.getString("certification_image"),
                        rs.getString("content"),
                        rs.getInt("likes")),
                selectCertificationParam);

    }

    public List<GetCertificationListRes> selectCertificationByChallengeId (long challenge_id ) {
        String selectCertificationByCategoryQuery = "select certification_image, certification.title, content\n" +
                "from certification, challenge_detail, challenge\n" +
                "where certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "and challenge_detail.challenge_id = challenge.challenge_id\n" +
                "and challenge.challenge_id = ?";
        long selectCertificationByCategoryParam = challenge_id;

        return this.jdbcTemplate.query(selectCertificationByCategoryQuery,
                (rs, rowNum) -> new GetCertificationListRes(
                        rs.getString("certification_image"),
                        rs.getString("title"),
                        rs.getString("content")),
                selectCertificationByCategoryParam);

    }
}
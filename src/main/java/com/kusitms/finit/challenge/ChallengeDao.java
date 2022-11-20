package com.kusitms.finit.challenge;

import com.kusitms.finit.challenge.dto.ChallengeImgRes;
import com.kusitms.finit.challenge.dto.MyChallengeListRes;
import com.kusitms.finit.challenge.dto.TodayChallengeNumRes;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ChallengeDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TodayChallengeRes> selectPostPoint(long accountId) {
        String selectPostPointQuery = "select title, intro, challenge_image\n" +
                "from challenge_detail, participation\n" +
                "where challenge_detail.challenge_detail_id = participation.challenge_detail_id\n" +
                "and participation.account_id = ?;";
        long selectPostPointParam = accountId;

        return this.jdbcTemplate.query(selectPostPointQuery,
                (rs, rowNum) -> new TodayChallengeRes(
                        rs.getString("title"),
                        rs.getString("intro"),
                        rs.getString("challenge_image")),
                selectPostPointParam);

    }

    public int selectTotalChallengeNum(Long id) {
        String selectTotalChallengeNumQuery = "select count(*) from participation\n" +
                "where account_id = ?;";
        long selectTotalChallengeNumParams = id;
        return this.jdbcTemplate.queryForObject(selectTotalChallengeNumQuery,
                int.class,
                selectTotalChallengeNumParams);

    }

    public int selectChallengeNum(Long id) {
        String selectChallengeNumQuery = "select count(*) from certification where account_id = ?\n" +
                "                and DATE_FORMAT(certification_date , '%Y-%m-%d') = DATE_FORMAT(now(), '%Y-%m-%d');";
        long selectChallengeNumParams = id;
        return this.jdbcTemplate.queryForObject(selectChallengeNumQuery,
                int.class,
                selectChallengeNumParams);
    }


    public String getCategory(Long challenge_id) {
        String getCategoryQuery = "select category\n" +
                "from challenge\n" +
                "where challenge_id = ?;";
        long getCategoryParams = challenge_id;
        return this.jdbcTemplate.queryForObject(getCategoryQuery,
                String.class,
                getCategoryParams);
    }

    public int getNum(Long account_id, Long challenge_id) {
        String getNumQuery = "\n" +
                "select count(*)\n" +
                "from challenge_Detail, certification\n" +
                "where challenge_detail.challenge_detail_id = certification.challenge_detail_id\n" +
                "and certification.account_id = ?\n" +
                "and challenge_Detail.challenge_id = ?;";
        Object[] getNumParams = new Object[]{account_id, challenge_id};

        return this.jdbcTemplate.queryForObject(getNumQuery,
                int.class,
                getNumParams);
    }

    public List<ChallengeImgRes> selectChallengeList(Long account_id, Long challenge_id) {
        String selectChallengeListQuery = "select certification_image\n" +
                "from certification, challenge_detail\n" +
                "where certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "and certification.account_id = ?\n" +
                "and challenge_id = ?;";
        Object[] selectChallengeListParams = new Object[]{account_id, challenge_id};

        return this.jdbcTemplate.query(selectChallengeListQuery,
                (rs, rowNum) -> new ChallengeImgRes(
                        rs.getString("certification_image")),
                selectChallengeListParams);
    }
}
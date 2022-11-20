package com.kusitms.finit.challenge;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challenge.dto.ChallengeImgRes;
import com.kusitms.finit.challenge.dto.MyChallengeListRes;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.challengeDetail.ChallengeDetailRepository;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeDetailRepository challengeDetailRepository;
    private final AccountRepository accountRepository;
    private final ChallengeDao challengeDao;

    public List<TodayChallengeRes> getTodayChallengeByAccountId(CustomUserDetails customUserDetails) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));
        List<TodayChallengeRes> todayChallengeRes = challengeDao.selectPostPoint(account.getId());
        return todayChallengeRes;
    }


    public String getTodayChallengeNum(CustomUserDetails customUserDetails) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));
        int num1 =  challengeDao.selectTotalChallengeNum(account.getId());
        int num2 =  challengeDao.selectChallengeNum(account.getId());
        String result = "총 " + num1 + "개의 챌린지 중 " + num2 + "개를 수행하셨네요!";
        return result;
    }

    public MyChallengeListRes getChallengeList(CustomUserDetails customUserDetails, Long challenge_id) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        MyChallengeListRes myChallengeListResList = new MyChallengeListRes();
        myChallengeListResList.setCategory(challengeDao.getCategory(challenge_id));
        myChallengeListResList.setNum(challengeDao.getNum(account.getId(), challenge_id));
        myChallengeListResList.setChallengeImgResList(challengeDao.selectChallengeList(account.getId(), challenge_id));
        return myChallengeListResList;
    }
}

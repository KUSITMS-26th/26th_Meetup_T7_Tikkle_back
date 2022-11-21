package com.kusitms.finit.certification;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.certification.dto.GetCertificationListRes;
import com.kusitms.finit.certification.dto.GetCertificationRes;
import com.kusitms.finit.challenge.ChallengeDao;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.challengeDetail.ChallengeDetailRepository;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.PrimitiveCharacterArrayClobType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final CertificationDao certificationDao;
    private final AccountRepository accountRepository;

    public GetCertificationRes getCertificationById(Long certification_id) {
        GetCertificationRes getCertificationRes = certificationDao.selectCertification(certification_id);
        return getCertificationRes;
    }

    public List<GetCertificationListRes> getCertificationByChallengeId(Long challenge_id) {
        List<GetCertificationListRes> getCertificationListResList = certificationDao.selectCertificationByChallengeId(challenge_id);
        return getCertificationListResList;
    }

    public List<GetCertificationListRes> getCertificationLike(Long challenge_id) {
        List<GetCertificationListRes> getCertificationListResList = certificationDao.selectCertificationLike(challenge_id);
        return getCertificationListResList;
    }

}
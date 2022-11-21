package com.kusitms.finit.certification;

import com.kusitms.finit.certification.dto.GetCertificationListRes;
import com.kusitms.finit.certification.dto.GetCertificationRes;
import com.kusitms.finit.challenge.ChallengeService;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;
    private final ResponseService responseService;

    @GetMapping("/certification/{certification_id}")
    public DataResponse<GetCertificationRes> getCertificationById(@PathVariable("certification_id") Long certification_id) {
        GetCertificationRes res = certificationService.getCertificationById(certification_id);
        return responseService.getDataResponse(res);
    }

    @GetMapping("/certification/feed/{challenge_id}")
    public DataResponse<List<GetCertificationListRes>> getCertificationByChallengeId(@PathVariable("challenge_id") Long challenge_id) {
        List<GetCertificationListRes> res = certificationService.getCertificationByChallengeId(challenge_id);
        return responseService.getDataResponse(res);
    }

}
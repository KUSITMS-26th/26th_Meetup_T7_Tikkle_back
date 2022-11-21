package com.kusitms.finit.certification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCertificationListRes {
    private String certification_image;
    private String title;
    private String content;
}

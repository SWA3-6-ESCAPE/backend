package com.swa.escape.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportCreateRequest {
    private Boolean category1;
    private float latitude;
    private float longitude;
}

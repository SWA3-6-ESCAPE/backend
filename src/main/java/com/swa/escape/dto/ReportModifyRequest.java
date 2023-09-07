package com.swa.escape.dto;

import com.swa.escape.domain.Category2;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportModifyRequest {
    private Category2 category2;
    private String detail;
}

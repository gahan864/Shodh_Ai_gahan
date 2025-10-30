package com.shodhacode.dto;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Long userId;
    private Long problemId;
    private String language;
    private String code;
}

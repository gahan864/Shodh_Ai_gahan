package com.shodhacode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubmissionResponse {
    private Long submissionId;
    private String status;
}

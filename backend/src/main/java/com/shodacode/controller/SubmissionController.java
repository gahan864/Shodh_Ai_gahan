package com.shodhacode.controller;

import com.shodhacode.dto.*;
import com.shodhacode.model.Submission;
import com.shodhacode.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@CrossOrigin
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public SubmissionResponse submitCode(@RequestBody SubmissionRequest req) {
        Submission s = submissionService.createSubmission(req);
        return new SubmissionResponse(s.getId(), s.getStatus());
    }

    @GetMapping("/{id}")
    public Submission getSubmission(@PathVariable Long id) {
        return submissionService.getSubmission(id).orElseThrow();
    }
}

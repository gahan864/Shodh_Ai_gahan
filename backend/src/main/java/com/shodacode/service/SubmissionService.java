package com.shodhacode.service;

import com.shodhacode.dto.SubmissionRequest;
import com.shodhacode.model.*;
import com.shodhacode.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final JudgeService judgeService;

    public Submission createSubmission(SubmissionRequest req) {
        User user = userRepository.findById(req.getUserId()).orElseThrow();
        Problem problem = problemRepository.findById(req.getProblemId()).orElseThrow();

        Submission sub = Submission.builder()
                .user(user)
                .problem(problem)
                .language(req.getLanguage())
                .code(req.getCode())
                .status("Pending")
                .createdAt(LocalDateTime.now())
                .build();
        submissionRepository.save(sub);

        new Thread(() -> judgeService.executeSubmission(sub, problem.getExpectedOutput(), problem.getInputSample())).start();
        return sub;
    }

    public Optional<Submission> getSubmission(Long id) {
        return submissionRepository.findById(id);
    }
}

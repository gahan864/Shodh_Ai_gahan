package com.shodhacode.controller;

import com.shodhacode.model.Contest;
import com.shodhacode.repository.SubmissionRepository;
import com.shodhacode.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor
@CrossOrigin
public class ContestController {

    private final ContestService contestService;
    private final SubmissionRepository submissionRepository;

    @GetMapping("/{id}")
    public Contest getContest(@PathVariable Long id) {
        return contestService.getContest(id).orElseThrow();
    }

    @GetMapping("/{id}/leaderboard")
    public List<Map<String, Object>> getLeaderboard(@PathVariable Long id) {
        var submissions = submissionRepository.findByProblemContestId(id);
        Map<String, Long> scores = new HashMap<>();
        for (var s : submissions) {
            if ("Accepted".equals(s.getStatus()))
                scores.merge(s.getUser().getUsername(), 1L, Long::sum);
        }
        List<Map<String, Object>> leaderboard = new ArrayList<>();
        scores.forEach((u, s) -> leaderboard.add(Map.of("username", u, "score", s)));
        leaderboard.sort((a,b) -> Long.compare((Long)b.get("score"), (Long)a.get("score")));
        return leaderboard;
    }
}

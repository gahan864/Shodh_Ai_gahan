package com.shodhacode.service;

import com.shodhacode.model.Contest;
import com.shodhacode.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContestService {
    private final ContestRepository contestRepository;

    public Optional<Contest> getContest(Long id) {
        return contestRepository.findById(id);
    }
}
